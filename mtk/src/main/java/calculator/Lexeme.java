package calculator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Lexeme {
    public Lexeme(LexemeType type) {
        this.type = type;
    }

    private String data;
    private LexemeType type;
}
