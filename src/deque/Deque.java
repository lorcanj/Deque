package deque;
import java.util.Iterator;

public interface Deque<Type> {

    public void addFirst(Type x);
    public void addLast(Type x);
    public boolean isEmpty();
    public int size();
    public void printDeque();
    public Type removeFirst();
    public Type removeLast();
    public Type get(int i);
    public Iterator<Type> iterator();
    public boolean equals(Object o);

}
