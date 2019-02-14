import calculator.Lexeme;
import calculator.LexemeType;
import calculator.Lexer;
import calculator.LexerException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class LexerTest {
    private Lexer lexer;

    public LexerTest() {
        try {
            lexer = new Lexer(new StringReader("11+-*/()^g"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getNextLexeme(){
        try {
            Lexeme lexeme = lexer.getLexeme();
            assertEquals(lexeme.getData(),"11");
            assertEquals(lexeme.getType(), LexemeType.NUMBER);
            assertEquals(lexer.getLexeme().getType(), LexemeType.PLUS);
            assertEquals(lexer.getLexeme().getType(), LexemeType.MINUS);
            assertEquals(lexer.getLexeme().getType(), LexemeType.MULTIPLY);
            assertEquals(lexer.getLexeme().getType(), LexemeType.DIVIDE);
            assertEquals(lexer.getLexeme().getType(), LexemeType.OPEN_BRACKET);
            assertEquals(lexer.getLexeme().getType(), LexemeType.CLOSE_BRACKET);
            assertEquals(lexer.getLexeme().getType(), LexemeType.POWER);
            assertThrows(LexerException.class, ()->lexer.getLexeme());
        } catch (IOException | LexerException e) {
            e.printStackTrace();
        }
    }
}
