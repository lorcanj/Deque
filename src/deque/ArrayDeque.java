package deque;

import java.util.Iterator;

public class ArrayDeque<Type> implements Deque<Type>, Iterable<Type> {

    private class ArrayDequeIterator implements Iterator<Type> {
        private int pos;
        private int counter;
        public ArrayDequeIterator() {pos = Math.floorMod(nextFirst - 1, items.length);}
        @Override
        public boolean hasNext() {
            if (size != items.length) {
                return pos != nextFirst;
            } else {
                counter += 1;
                return counter <= size;
            }
        }
        @Override
        public Type next() {
            Type returnItem = items[pos];
            pos = Math.floorMod(pos - 1, items.length);
            return returnItem;
        }
    }

    // the minimum size of the array is 8 and should never be smaller as per
    // the requirements
    private final int MINSIZE = 8;
    private double capactiy;
    private int size;
    private int nextFirst;
    private int nextLast;
    private Type[] items;

    // for the array to be circular, will have to check mod size of the array when adding to the end etc
    //
    public ArrayDeque() {
        size = 0;
        items = (Type[]) new Object[MINSIZE];
        nextFirst = 0;
        nextLast = items.length - 1;
        capactiy = 0;
    }

    public Iterator<Type> iterator() {
        return new ArrayDequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {return false;}
        if (this == o) {return true;}

        if (o instanceof ArrayDeque) {
            ArrayDeque<Type> temp = (ArrayDeque<Type>) o;
            return checkDequeContents(temp);
        }
        return false;
    }

    private boolean checkDequeContents(ArrayDeque<Type> listToCheck) {
        if (size != listToCheck.size()) {return false;}

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
            if (output_1.charAt(i) != output_2.charAt(i)){return false;}
        }
        return true;
    }

    /**
     * if your front item is at position zero, and you addFirst, the new front should loop back around to the end of the array
     * (so the new front item in the deque will be the last item in the underlying array
     * @param x
     */
    public void addFirst(Type x) {
        if (x == null) {
            return;
        }
        if (size == items.length) {
            resizeBigger();
        }
        items[nextFirst] = x;
        nextFirst = (nextFirst + 1) % items.length;
        size += 1;
        capactiy = (double) size / items.length;
    }

    public void addLast(Type x) {
        if (x == null) {
            return;
        }
        if (size == items.length) {
            resizeBigger();
        }

        items[nextLast] = x;
        // below same as for a mob +ve b, and this will always give a positive result
        nextLast = Math.floorMod(nextLast - 1, items.length);
        size += 1;
        capactiy = (double) items.length / size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public int getArrayLength() { return items.length; }

    public void printDeque() {

        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {continue;}
            System.out.print(items[i].toString() + ' ');
        }
        System.out.println();
    }

    /**
     * Want to remove the item at the front of the queue
     * The item at the front will have an index of (nextFirst-1) % items.length
     * @return the Type stored at the front of the queue
     */
    public Type removeFirst() {
        int circular_index = Math.floorMod(nextFirst - 1, items.length);
        Type toReturn = items[circular_index];
        items[circular_index] = null;
        nextFirst = Math.floorMod(nextFirst - 1, items.length);
        size = size - 1;
        capactiy = (double) size / (double) items.length;
        if (capactiy <= 0.25 && items.length != MINSIZE) {
            resizeSmaller();
        }
        return toReturn;
    }

    public Type removeLast() {
        int circular_index = (nextLast + 1) % items.length;
        Type toReturn = items[circular_index];
        items[circular_index] = null;
        nextLast = Math.floorMod (nextLast + 1, items.length);
        size = size - 1;
        capactiy = (double) size / (double) items.length;
        if (capactiy <= 0.25 && items.length != MINSIZE) {
            resizeSmaller();
        }
        return toReturn;
    }

    /**
     * returns the item in the ith element where element 0 is the front and element items.length - 1 is the back
     * @param i
     * @return the element at index i where 0 is the front
     */
    public Type get(int i) {
        if (i >= items.length || size == 0 || i < 0) {
            return null;
        }
        int difference = Math.floorMod((nextFirst - 1 - i), items.length);
        return items[difference];
    }

    public int getNextFirst() {
        return nextFirst;
    }

    public int getNextLast() {
        return nextLast;
    }

    public double getCapactiy() {
        return capactiy;
    }

    /**
     * If the array has had more items added to it than the capacity allows, then double the size of the array,
     * copy all of the items
     */
    public void resizeBigger() {
        Type[] biggerArray = (Type[]) new Object[items.length * 2];
        boolean movedOnlyOneType = false;
        // is correct because for this function to hit, the array will need to be full
        if (nextFirst == 0 && nextLast == items.length - 1) {
            movedOnlyOneType = true;
        }
        copyArrayBigger(biggerArray, movedOnlyOneType);
        items = biggerArray;
    }

    private void copyArrayBigger(Type[] biggerArray, boolean movedOnlyOneType) {
        if (movedOnlyOneType) {
            System.arraycopy(items, 0, biggerArray, 0, items.length);
            nextLast = biggerArray.length - 1;
            nextFirst = items.length;
        } else {
            // copy last elements to start of the new list
            System.arraycopy(items, nextFirst, biggerArray, 0, items.length - nextFirst);
            System.arraycopy(items, 0, biggerArray, items.length - nextFirst, nextFirst);
            // nextFirst doesn't need to change the index as relative position is correct
            nextFirst = items.length;
            nextLast = biggerArray.length - 1;
            }
        }

    public void resizeSmaller() {
        Type[] smallerArray = (Type[]) new Object[items.length / 2];
        copyArraySmaller(smallerArray);
        nextLast = smallerArray.length - 1;
        nextFirst = size;
        items = smallerArray;
    }

    private void copyArraySmaller(Type[] smallerArray) {
        if (nextLast > nextFirst) {
            if (nextLast != (items.length) - 1){
                // copy the end of the bigger array to the start of the smaller array
                System.arraycopy(items, nextLast + 1, smallerArray, 0, items.length - 1 - nextLast);
            }
            if (nextFirst != 0) {
                System.arraycopy(items, 0, smallerArray, items.length - 1 - nextLast, nextFirst);
            }
        } else {
            System.arraycopy(items, nextLast + 1, smallerArray, 0, nextFirst - 1 - nextLast);
        }
    }

    public void resize(double factor) {
        int newArraySize = (int) (size * factor);
        Type[] biggerArray = (Type[]) new Object[newArraySize];
        System.arraycopy(items, 0, biggerArray, 0, size);
        items = biggerArray;
        size = size + 1;

        capactiy = (double) size / newArraySize;
    }

}
