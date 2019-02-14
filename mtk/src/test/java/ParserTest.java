import calculator.Lexer;
import calculator.LexerException;
import calculator.ParseException;
import calculator.Parser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {
    private Parser parser;

    @Test
    void parseTest() {
        try {
            parser = new Parser(new Lexer(new StringReader("2 +4^2/4-(4)")));
            assertEquals(parser.parseExpression(), 2);
            parser = new Parser(new Lexer(new StringReader("3^3")));
            assertEquals(parser.parseExpression(), 27);
            parser = new Parser(new Lexer(new StringReader("7-5")));
            assertEquals(parser.parseExpression(), 2);
            parser = new Parser(new Lexer(new StringReader("8/4")));
            assertEquals(parser.parseExpression(), 2);
            parser = new Parser(new Lexer(new StringReader("5*6")));
            assertEquals(parser.parseExpression(), 30);
            parser.setLexer(new Lexer(new StringReader("-8 + 9")));
            assertEquals(parser.parseExpression(), 1);
            parser.setLexer(new Lexer(new StringReader("(-8) + (9)")));
            assertEquals(parser.parseExpression(), 1);
            parser.setLexer(new Lexer(new StringReader("-9*7+(-1+1)")));
            assertEquals(parser.parseExpression(), -63);
            parser.setLexer(new Lexer(new StringReader("(3")));
            assertThrows(ParseException.class,()-> parser.parseExpression());
        } catch (IOException | LexerException | ParseException e) {
            e.printStackTrace();
        }
    }

}
