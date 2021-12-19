package src.deque;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    public void testEmptySize() {
        ArrayDeque L = new ArrayDeque();
        assertEquals(0, L.size());
    }

    @Test
    public void testAddAndSize() {
        ArrayDeque L = new ArrayDeque();
        L.addLast(99);
        L.addLast(99);
        assertEquals(2, L.size());
    }


    @Test
    public void testAddAndGetLast() {
        ArrayDeque L = new ArrayDeque();
        L.addLast(99);
        assertEquals(99, L.removeLast());
        L.addLast(36);
        assertEquals(36, L.removeLast());
    }


    @Test
    public void testGetFirst() {
        ArrayDeque L = new ArrayDeque();
        L.addFirst(99);
        assertEquals(99, L.get(0));
        L.addFirst(36);
        assertEquals(36, L.get(0));
        assertEquals(99, L.get(1));
    }

    @Test
    public void testGetFirstString() {
        ArrayDeque L = new ArrayDeque();
        L.addFirst("99");
        assertEquals("99", L.get(0));
        assertNotEquals(99, L.get(0));
        L.addFirst("36");
        assertEquals("36", L.get(0));
        assertEquals("99", L.get(1));
    }

    @Test
    public void testGetLast() {
        ArrayDeque L = new ArrayDeque();
        L.addLast(22);
        assertEquals(22, L.get(0));
        L.addLast(55);
        assertEquals(55, L.get(1));
        assertEquals(22, L.get(0));
    }


    @Test
    public void testRemove() {
        ArrayDeque L = new ArrayDeque();
        L.addLast(99);
        assertEquals(99, L.get(0));
        L.addLast(36);
        assertEquals(99, L.get(0));
        L.removeLast();
        assertEquals(99, L.removeLast());
        L.addLast(100);
        assertEquals(100, L.removeLast());
        assertEquals(0, L.size());
    }

    /**
     * Tests insertion of a large number of items.
     */
    @Test
    public void testMegaInsert() {
        ArrayDeque L = new ArrayDeque();
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
        ArrayDeque L = new ArrayDeque();
        for (int i = 0; i < 5; i += 1) {
            L.addFirst(i);
        }

        for (int i = 0; i < 3; i++) {
            L.addLast(i);
        }

        L.addLast(99);
        assertEquals(99, L.get(L.size() - 1));
    }

    @Test
    public void testAddAndGetFirst() {
        ArrayDeque L = new ArrayDeque();
        L.addFirst(50);
        assertEquals(50, L.get(0));
        assertNull(L.get(1));
    }

    @Test
    public void testResizeSmallerFirstOnly() {
        ArrayDeque L = new ArrayDeque();

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
        ArrayDeque L = new ArrayDeque();

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
        ArrayDeque L = new ArrayDeque();
        for (int i = 0; i < 14; i++) {
            L.addFirst(i);
        }

        for (int i = 0; i < 10; i++) {
            L.removeLast();
        }
        assertEquals(L.size(), 4);
        assertEquals(L.getArrayLength(), 8);
        assertEquals(L.get(0), 13);
        assertEquals(L.get(L.size()-1), 10);
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
