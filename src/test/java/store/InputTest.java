package store;

import org.junit.jupiter.api.Test;
import store.util.parser.InputParser;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class InputTest {

    @Test
    void 수량_입력이_숫자가_아닐경우_예외가_발생한다() {
        assertThrows(IllegalArgumentException.class, () -> {
            InputParser.parseOrder("[감자칩-ㅁ],[콜라-1]");
        });
    }

}
