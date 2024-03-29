package interfaces;

import java.util.NoSuchElementException;
import exceptions.IllegalStateException;

/**
 * An iterator over a collection.
 */
public interface HIterator {

    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if next() would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    boolean hasNext();

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    Object next();

    /**
     * Removes from the underlying collection the last element returned
     * by this iterator (optional operation).  This method can be called
     * only once per call to next().  The behavior of an iterator
     * is unspecified if the underlying collection is modified while the
     * iteration is in progress in any way other than by calling this
     * method.
     *
     * @throws UnsupportedOperationException if the {@code remove}
     *         operation is not supported by this iterator
     *
     * @throws exceptions.IllegalStateException if the {@code next} method has not
     *                                          yet been called, or the {@code remove} method has already
     *                                          been called after the last call to the {@code next}
     *                                          method
     */
    void remove();
    
}
