package calculator;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

public class Main {
    public static void main(String[] arg){
        Parser parser;
        try {
            parser = new Parser(new Lexer(new StringReader("j")));
            System.out.println("Create parser");
            while (true) {
                try {
                    System.out.println("I CALC "+ parser.parseExpression());
                } catch (IOException | LexerException | ParseException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException | LexerException e) {
            e.printStackTrace();
        }

    }
}
