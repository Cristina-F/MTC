package calculator;

import java.io.IOException;
import java.io.Reader;

public class Lexer {
    public Lexer(Reader reader) throws IOException {
        this.reader = reader;
        currentSymbol = reader.read();
    }

    public Lexeme getLexeme() throws IOException, LexerException {
        StringBuilder number = new StringBuilder();
        while (Character.isWhitespace(currentSymbol)){
            currentSymbol = reader.read();
        }
        if(Character.isDigit(currentSymbol)){
            while (Character.isDigit(currentSymbol)){
                number.append((char)currentSymbol);
                currentSymbol = reader.read();
            }
            return new Lexeme(number.toString(), LexemeType.NUMBER);
        }

        switch (currentSymbol){
            case -1:
                currentSymbol = reader.read();
                return  new Lexeme(LexemeType.EOF);
            case '+':
                currentSymbol = reader.read();
                return new Lexeme(LexemeType.PLUS);
            case '-':
                currentSymbol = reader.read();
                return new Lexeme(LexemeType.MINUS);
            case '*':
                currentSymbol = reader.read();
                return new Lexeme(LexemeType.MULTIPLY);
            case '^':
                currentSymbol = reader.read();
                return new Lexeme(LexemeType.POWER);
            case '/':
                currentSymbol = reader.read();
                return new Lexeme(LexemeType.DIVIDE);
            case '(':
                currentSymbol = reader.read();
                return new Lexeme(LexemeType.OPEN_BRACKET);
            case ')':
                currentSymbol = reader.read();
                return new Lexeme(LexemeType.CLOSE_BRACKET);
            default:
                throw new LexerException("Unknown symbol");
        }
    }

    private Reader reader;
    private int currentSymbol;
}
