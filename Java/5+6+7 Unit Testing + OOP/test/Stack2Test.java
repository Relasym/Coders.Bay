import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Stack2Test {

    @Test
    void pushPopTest() {
        Stack2<Integer> stack = new Stack2<>();
        stack.push(1);
        assertEquals(1,stack.pop());
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        assertEquals(4,stack.pop());
        assertEquals(3,stack.pop());
        assertEquals(2,stack.pop());
        assertEquals(1,stack.pop());
    }

    @Test
    void peekTest() {
        Stack2<String> stack = new Stack2<>();
        stack.push("1");
        assertEquals("1",stack.peek());
        stack.push("2");
        stack.push("3");
        assertEquals("3",stack.peek());
        stack.pop();
        assertEquals("2",stack.peek());
    }

    @Test
    void clearTest() {
        Stack2<String> stack = new Stack2<>();
        stack.push("1");
        stack.push("2");
        stack.clear();
        assertNull(stack.peek());


    }
}
