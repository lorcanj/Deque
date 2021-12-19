package src.deque;

import jh61b.junit.In;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    /* Test empty */
    public void testEmptySize() {
        ArrayDeque<Integer> L = new ArrayDeque<Integer>();
        assertEquals(0, L.size());
    }

    @Test
    /* Add two elements to the end of the Deque and check the size */
    public void testAddAndSize() {
        ArrayDeque<Integer> L = new ArrayDeque<Integer>();
        L.addLast(99);
        L.addLast(99);
        assertEquals(2, L.size());
    }


    @Test
    /* Check that the Deque is correctly adding elements to the end of the Deque */
    public void testAddAndGetLast() {
        ArrayDeque<Integer> L = new ArrayDeque<Integer>();
        L.addLast(99);
        assertEquals(99, (int)L.removeLast());
        L.addLast(36);
        assertEquals(36, (int) L.removeLast());
    }


    @Test
    /* Check that the Deque is correctly adding elements to the start of the Deque */
    public void testGetFirst() {
        ArrayDeque<Integer> L = new ArrayDeque<Integer>();
        L.addFirst(99);
        assertEquals(99, (int) L.get(0));
        L.addFirst(36);
        assertEquals(36, (int) L.get(0));
        assertEquals(99, (int) L.get(1));
    }

    @Test
    /* Check that the Deque is correctly adding elements to the start of the Deque
    * Element added is of type String */
    public void testGetFirstString() {
        ArrayDeque<String> L = new ArrayDeque<String>();
        L.addFirst("99");
        assertEquals("99", L.get(0));
        assertNotEquals(99, L.get(0));
        L.addFirst("36");
        assertEquals("36", L.get(0));
        assertEquals("99", L.get(1));
    }

    @Test
    public void testGetLast() {
        ArrayDeque<Integer> L = new ArrayDeque<Integer>();
        L.addLast(22);
        assertEquals(22, (int)L.get(0));
        L.addLast(55);
        assertEquals(55, (int)L.get(1));
        assertEquals(22, (int)L.get(0));
    }


    @Test
    /* Test to check the remove method is working and updating the size of the Deque */
    public void testRemove() {
        ArrayDeque<Integer> L = new ArrayDeque<Integer>();
        L.addLast(99);
        assertEquals(99, (int)L.get(0));
        L.addLast(36);
        assertEquals(99, (int)L.get(0));
        L.removeLast();
        assertEquals(99, (int)L.removeLast());
        L.addLast(100);
        assertEquals(100, (int)L.removeLast());
        assertEquals(0, L.size());
    }

    /**
     * Tests insertion of a large number of items.
     */
    @Test
    public void testMegaInsert() {
        ArrayDeque<Integer> L = new ArrayDeque<Integer>();
        int N = 1000000;
        for (int i = 0; i < N; i += 1) {
            L.addLast(i);
        }

        for (int i = 0; i < N; i += 1) {
            L.addLast(L.get(i));
        }
    }

    @Test
    public void testResizeBigger() {
        ArrayDeque<Integer> L = new ArrayDeque<Integer>();
        for (int i = 0; i < 5; i += 1) {
            L.addFirst(i);
        }

        for (int i = 0; i < 3; i++) {
            L.addLast(i);
        }

        L.addLast(99);
        assertEquals(99, (int)L.get(L.size() - 1));
    }

    @Test
    public void testAddAndGetFirst() {
        ArrayDeque<Integer> L = new ArrayDeque<Integer>();
        L.addFirst(50);
        assertEquals(50, (int)L.get(0));
        assertNull(L.get(1));
    }

    @Test
    public void testResizeSmallerFirstOnly() {
        ArrayDeque<Integer> L = new ArrayDeque<Integer>();

        for (int i = 0; i < 9; i++) {
            L.addFirst(i);
        }

        for (int i = 0; i < 6; i++) {
            L.removeFirst();
        }
        assertEquals(L.size(), 3);
        assertEquals(L.getArrayLength(), 8);
    }

    @Test
    public void testResizeSmallerLastAdded() {
        ArrayDeque<Integer> L = new ArrayDeque<Integer>();

        for (int i = 0; i < 8; i++) {
            L.addFirst(i);
        }

        L.addLast(8);

        for (int i = 0; i < 6; i++) {
            L.removeFirst();
        }
        assertEquals(L.size(), 3);
        assertEquals(L.getArrayLength(), 8);
        L.printDeque();
    }

    @Test
    public void testResizeSmallerLastBeforeFirst() {
        ArrayDeque<Integer> L = new ArrayDeque<Integer>();
        for (int i = 0; i < 14; i++) {
            L.addFirst(i);
        }

        for (int i = 0; i < 10; i++) {
            L.removeLast();
        }
        assertEquals(L.size(), 4);
        assertEquals(L.getArrayLength(), 8);
        assertEquals((int)L.get(0), 13);
        assertEquals((int)L.get(L.size()-1), 10);
    }

    @Test
    public void testIterator() {
        ArrayDeque<Integer> L = new ArrayDeque<Integer>();

        for (int i = 0; i < 8; i++) {
            L.addFirst(i);
        }

        for (int i : L) {
            System.out.println(i);
        }
    }

    @Test
    public void testEquals() {
        ArrayDeque<Integer> L = new ArrayDeque<Integer>();
        ArrayDeque<Integer> L2 = new ArrayDeque<Integer>();

        for (int i = 0; i < 3; i++) {
            L.addFirst(i);
            L2.addFirst(i);
        }

        assertTrue(L.equals(L2));
        L.addFirst(9);
        assertFalse(L.equals(L2));
        String s = "hi";
        assertFalse(L.equals(s));
    }
}
