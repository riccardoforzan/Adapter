package interfaces;

public interface HIterator {

    /**
     * Returns true if the iteration has more elements
     * @return true if the iteration has more elements
     */
    boolean hasNext();

    /**
     * Returns the next element in the iteration.
     * @return the next element in the iteration.
     */
    Object next();

    /**
     * Removes from the underlying collection the last element returned by the iterator (optional operation).
     */
    void remove();

}
