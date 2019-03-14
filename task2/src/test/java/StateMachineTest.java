import com.company.StateMachine;
import org.junit.Test;

import java.io.StringReader;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StateMachineTest {
    @Test
    public void checkWord() {
        StateMachine machine = new StateMachine(
                new StringReader("0 1\n" +
                        "0 a 1\n" +
                        "0 b 2\n" +
                        "1 c 3\n" +
                        "3 a 0\n"));
        machine.setWord(new StringReader("acab"));
        assertFalse(machine.run());
        machine.setWord(new StringReader("acaa"));
        assertFalse(machine.run());
    }
}
