package calculator;

import lombok.Setter;

import java.io.IOException;

public class Parser {
    public Parser(Lexer lexer) throws IOException, LexerException {
        this.lexer = lexer;
        currentLexeme = lexer.getLexeme();
    }

    public int parseExpression() throws IOException, LexerException, ParseException {
        System.out.println("parseExpression");
        int temp = parseTerm();
        while (currentLexeme.getType() == LexemeType.PLUS ||
        currentLexeme.getType() == LexemeType.MINUS){
            if(currentLexeme.getType() == LexemeType.PLUS){
                currentLexeme = lexer.getLexeme();
                temp += parseTerm();
            }
            if(currentLexeme.getType() == LexemeType.MINUS){
                currentLexeme = lexer.getLexeme();
                temp -= parseTerm();
            }
        }
        return temp;
    }

    private int parseTerm() throws IOException, LexerException, ParseException {
        int temp = parseFactor();
        while (currentLexeme.getType() == LexemeType.MULTIPLY ||
                currentLexeme.getType() == LexemeType.DIVIDE){
            if(currentLexeme.getType() == LexemeType.MULTIPLY){
                currentLexeme = lexer.getLexeme();
                temp *= parseFactor();
            }
            if(currentLexeme.getType() == LexemeType.DIVIDE){
                currentLexeme = lexer.getLexeme();
                temp /= parseTerm();
            }
        }
        return temp;
    }

    private int parseFactor() throws IOException, LexerException, ParseException {
        int temp = parsePower();
        if (currentLexeme.getType() == LexemeType.POWER){
            currentLexeme = lexer.getLexeme();
            return (int)Math.pow(temp, parseFactor());
        }
        return temp;
    }

    private int parsePower() throws IOException, LexerException, ParseException {
        if (currentLexeme.getType() == LexemeType.MINUS){
            currentLexeme = lexer.getLexeme();
            return -parseAtom();
        }
        return parseAtom();
    }

    private int parseAtom() throws IOException, LexerException, ParseException {
        if (currentLexeme.getType() == LexemeType.NUMBER) {
            int temp = Integer.parseInt(currentLexeme.getData());
            currentLexeme = lexer.getLexeme();
            return temp;
        }
        if (currentLexeme.getType() == LexemeType.OPEN_BRACKET) {
            currentLexeme = lexer.getLexeme();
            int tmp = parseExpression();
            if (currentLexeme.getType() == LexemeType.CLOSE_BRACKET) {
                currentLexeme = lexer.getLexeme();
                return tmp;
            }
        }
        throw new ParseException("Invalid Expression");
    }

    private Lexeme currentLexeme;
    private Lexer lexer;

    public void setLexer(Lexer lexer) throws IOException, LexerException {
        this.lexer = lexer;
        currentLexeme = lexer.getLexeme();
    }
}
