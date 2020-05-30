package interfaces;

public interface HListIterator extends HIterator {

    void add(Object o);

    boolean hasPrevious();

    int nextIndex();

    Object previous();

    int previousIndex();

    void set(Object o);

}
