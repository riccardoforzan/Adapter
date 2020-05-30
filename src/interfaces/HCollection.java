package interfaces;

public interface HCollection {

    boolean add(Object o);

    boolean addAll(HCollection collection);

    void clear();

    boolean contains(Object o);

    boolean containsAll(HCollection collection);

    boolean equals(Object o);

    int hashCode();

    boolean isEmpty();

    HIterator iterator();

    boolean remove(Object o);

    boolean removeAll(HCollection collection);

    boolean retainAll(HCollection collection);

    int size() ;

    Object[] toArray();

}
