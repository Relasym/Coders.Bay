import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DLL2Test {


    @Test
    void lengthTest() {
        DLL2<String> list = new DLL2<>();
        assertEquals(0,list.getLength());
    }

    @Test
    void addGetTest() {
        DLL2<Integer> list = new DLL2<>();
        list.add(1);
        assertEquals(1, list.getLength());
        assertEquals(1,list.get(0));
        list.add(2);
        assertEquals(2,list.getLength());
        assertEquals(2,list.get(1));
        assertNull(list.get(700));
        assertNull(list.get(-3));
    }

    @Test
    void constructWithObjectTest() {
        DLL2<String> list1 = new DLL2<>("");
        DLL2<String> list2 = new DLL2<>();
        list2.add("");
        assertEquals(list2.get(0), list1.get(0));
        assertEquals(1,list1.getLength());
        assertEquals(1,list2.getLength());
    }

    @Test
    void removalTest() {
        DLL2<Integer> list = new DLL2<>();
        list.add((Integer) 33);
        list.add((Integer) 33);
        list.add((Integer) 33);
        list.add((Integer) 33);
        list.add(44);
        list.remove(0); //remove first
        assertEquals(4,list.getLength());
        list.remove(3); //remove last
        assertEquals(3,list.getLength());
        list.remove(1); //remove middle
        assertEquals(2,list.getLength());
        list.remove(0); //remove another
        list.remove(0); //remove last remaining item
        assertEquals(0,list.getLength());
    }

    @Test
    void getFirstLastTest() {
        DLL2<String> list = new DLL2<>();
        list.add("Test");
        assertEquals("Test", list.getFirst());
        assertEquals("Test", list.getLast());
        list.add("1");
        assertEquals("Test",list.getFirst());
        assertEquals("1",list.getLast());
        list.remove(0);
        assertEquals("1",list.getFirst());
        assertEquals("1",list.getLast());
        list.remove(0);
        assertNull(list.getFirst());
        assertNull(list.getLast());
    }

    @Test
    void containsTest() {
        DLL2<String> list = new DLL2<>();
        assertFalse(list.contains("house"));
        list.add("house");
        assertTrue(list.contains("house"));
        list.remove(0);
        assertFalse(list.contains("house"));
    }

    @Test
    void indexOfTest() {
        DLL2<String> list = new DLL2<>();
        assertEquals(-1,list.indexOf("house"));
        list.add("house");
        assertEquals(0,list.indexOf("house"));
        list.remove(0);
        assertEquals(-1,list.indexOf("house"));

    }

    @Test
    void clearTest() {
        DLL2<Integer> list = new DLL2<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.clear();
        assertEquals(0,list.getLength());
        assertNull(list.getFirst());
        assertNull(list.getLast());
    }

    @Test
    void reverseTest() {
        DLL2<Integer> list = new DLL2<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.reverse();
        assertEquals(3,list.get(0));
        assertEquals(2,list.get(1));
        assertEquals(1,list.get(2));

    }

}
