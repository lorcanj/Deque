package src.deque;

import java.util.Iterator;

public class LinkedListDeque<Type> implements Deque<Type>, Iterable<Type> {

    private class LinkedListDequeIterator implements Iterator<Type> {
        ListNode curr;
        int counter = 0;
        public LinkedListDequeIterator() {curr = sentinal.next;}
        @Override
        public boolean hasNext() {
            counter += 1;
            return counter <= size;
        }
        @Override
        public Type next() {
            Type returnItem = curr.item;
            curr = curr.next;
            return returnItem;
        }
    }


    /**
     * Private class which are the building blocks of the LinkedListDeque
     */
    private class ListNode {
        public Type item;
        public ListNode next;
        public ListNode prev;

        public ListNode(Type i, ListNode n, ListNode p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    private ListNode sentinal;
    private int size;

    /**
     * Constructor for LinkedListDeque using a circular deque
     * Uses a sentinal where sentinal.next is the start of the deque
     * sentinal.prev is the end of the deque
     * when empty the sentinal points at itself
     */
    public LinkedListDeque() {
        sentinal = new ListNode(null, sentinal, sentinal);
        size = 0;
    }

    public Iterator<Type> iterator() {
        return new LinkedListDequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {return false;}
        if (this == o) {return true;}
        if (o instanceof LinkedListDeque) {
            LinkedListDeque<Type> temp = (LinkedListDeque<Type>) o;
            return checkDequeContents(temp);
        }
        return false;
    }

    private boolean checkDequeContents(LinkedListDeque<Type> listToCheck) {
        if (size != listToCheck.size) {return false;}

        StringBuilder returnSB1 = new StringBuilder("");

        StringBuilder returnSB2 = new StringBuilder("");

        for (Type t : listToCheck) {
            returnSB1.append(t);
        }
        String output_1 = returnSB1.toString();

        for (Type t : this) {
            returnSB2.append(t);
        }
        String output_2 = returnSB2.toString();
        for (int i = 0; i < output_2.length(); i++) {
            if(output_2.charAt(i) != output_1.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds a ListNode to the start of the LLDeque
     * @param x
     */
    public void addFirst(Type x){
        if (size == 0) {
            // if size is 0 then will make the first node which should point to itself
            // for the next and prev pointers
            ListNode newNode = new ListNode(x, null, null);
            // could make setters for the below?
            newNode.next = newNode;
            newNode.prev = newNode;
            sentinal.next = newNode;
            sentinal.prev = newNode;
        } else {
            ListNode newNode = new ListNode(x, sentinal.next, sentinal.prev);
            sentinal.prev.next = newNode;
            sentinal.next = newNode;
        }
        size += 1;
    }

    // addFirst and addLast might be the same apart from just updating the sentinal's member
    // depending on whether it is the front or back to be updated
    // can probably refactor this
    public void addLast(Type x){
        if (size == 0) {
            // if size is 0 then will make the first node which should point to itself
            // for the next and prev pointers
            ListNode newNode = new ListNode(x, null, null);
            // could make setters for the below?
            newNode.next = newNode;
            newNode.prev = newNode;
            sentinal.next = newNode;
            sentinal.prev = newNode;
        } else {
            ListNode newNode = new ListNode(x, sentinal.next, sentinal.prev);
            sentinal.prev.next = newNode;
            sentinal.prev = newNode;
        }
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.
     */
    public void printDeque() {
        if (size == 0) {
            return;
        }
        // could do a while loop which loops from sentinal.next and stops when
        // the pointer reaches sentinal again
        ListNode temp = sentinal.next;

        while (temp.next != sentinal.next) {
            System.out.print(temp.item.toString() + ' ');
            temp = temp.next;
        }
        // below item should be the last item in the linked list
        System.out.print(temp.item.toString());
        System.out.println();
    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     * @return the item that was removed
     */
    public Type removeFirst() {
        if (size == 0) {
            return null;
        }
        Type firstNode = sentinal.next.item;
        sentinal.next = sentinal.next.next;
        sentinal.prev.next = sentinal.next;
        size--;
        return firstNode;
    }

    public Type removeLast() {
        if (size == 0) {
            return null;
        }
        Type lastNode = sentinal.prev.item;
        sentinal.prev = sentinal.prev.prev;
        sentinal.prev.next = sentinal.next;
        size--;
        return lastNode;
    }

    public Type get(int i) {
        // although the Deque is circular, can still have index out of bounds
        // if i th value is greater than the size of the list
        if (size == 0 || i > size - 1) {
            return null;
        }
        int counter = 0;
        ListNode temp = sentinal.next;
        while (counter != i) {
            counter++;
            temp = temp.next;
        }
        return temp.item;
    }

    // don't like this implementation and might come back to it another time
    private ListNode helperRecursive(int i, int counter, ListNode node) {
        if (counter == i) {
            return node;
        }
        return helperRecursive(i, counter + 1, node.next);
    }

    public Type getRecursive(int i) {
        if (size == 0 || i > size - 1) {
            return null;
        }
        return helperRecursive(i, 0, sentinal.next).item;
    }
}
