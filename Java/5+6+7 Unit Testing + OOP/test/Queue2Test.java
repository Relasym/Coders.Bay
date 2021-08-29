import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Queue2Test {

    @Test
    void enqueueDequeueTest() {
        Queue2<String> queue = new Queue2<>();
        queue.enqueue("1");
        assertEquals("1",queue.dequeue());
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.enqueue("4");
        assertEquals("1",queue.dequeue());
        assertEquals("2",queue.dequeue());
        assertEquals("3",queue.dequeue());
        assertEquals("4",queue.dequeue());



    }
}
