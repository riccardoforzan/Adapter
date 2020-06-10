package interfaces;

import java.util.NoSuchElementException;

/**
 * An iterator for lists that allows the programmer to traverse the list in either direction, modify the list during iteration,
 * and obtain the iterator's current position in the list.
 * A ListIterator has no current element;
 * its cursor position always lies between the element that would be returned by a call to previous() and the element that would be returned by a call to next().
 * In a list of length n, there are n+1 valid index values, from 0 to n, inclusive.
 *
 * <p>Note that the remove() and set(Object) methods are not defined in terms of the cursor position;
 * they are defined to operate on the last element returned by a call to next() or previous().
 */
public interface HListIterator extends HIterator {

    /**
     * Returns {@code true} if this list iterator has more elements when
     * traversing the list in the forward direction. (In other words,
     * returns {@code true} if next() would return an element rather
     * than throwing an exception.)
     *
     * @return {@code true} if the list iterator has more elements when
     *         traversing the list in the forward direction
     */
    boolean hasNext();

    /**
     * Returns the next element in the list and advances the cursor position.
     * This method may be called repeatedly to iterate through the list,
     * or intermixed with calls to previous() to go back and forth.
     * (Note that alternating calls to {@code next} and {@code previous}
     * will return the same element repeatedly.)
     *
     * @return the next element in the list
     * @throws NoSuchElementException if the iteration has no next element
     */
    Object next();

    /**
     * Returns {@code true} if this list iterator has more elements when
     * traversing the list in the reverse direction.  (In other words,
     * returns {@code true} if previous() would return an element
     * rather than throwing an exception.)
     *
     * @return {@code true} if the list iterator has more elements when
     *         traversing the list in the reverse direction
     */
    boolean hasPrevious();

    /**
     * Returns the previous element in the list and moves the cursor
     * position backwards.  This method may be called repeatedly to
     * iterate through the list backwards, or intermixed with calls to
     * next() to go back and forth.  (Note that alternating calls
     * to {@code next} and {@code previous} will return the same
     * element repeatedly.)
     *
     * @return the previous element in the list
     * @throws NoSuchElementException if the iteration has no previous
     *         element
     */
    Object previous();

    /**
     * Returns the index of the element that would be returned by a
     * subsequent call to next(). (Returns list size if the list
     * iterator is at the end of the list.)
     *
     * @return the index of the element that would be returned by a
     *         subsequent call to {@code next}, or list size if the list
     *         iterator is at the end of the list
     */
    int nextIndex();

    /**
     * Returns the index of the element that would be returned by a
     * subsequent call to previous(). (Returns -1 if the list
     * iterator is at the beginning of the list.)
     *
     * @return the index of the element that would be returned by a
     *         subsequent call to {@code previous}, or -1 if the list
     *         iterator is at the beginning of the list
     */
    int previousIndex();

    /**
     * Removes from the list the last element that was returned by
     * next() or previous() (optional operation).  This call can
     * only be made once per call to {@code next} or {@code previous}.
     * It can be made only if add() has not been
     * called after the last call to {@code next} or {@code previous}.
     *
     * @throws UnsupportedOperationException if the {@code remove}
     *         operation is not supported by this list iterator
     * @throws exceptions.IllegalStateException if neither {@code next} nor
     *         {@code previous} have been called, or {@code remove} or
     *         {@code add} have been called after the last call to
     *         {@code next} or {@code previous}
     */
    void remove();

    /**
     * Replaces the last element returned by next() or
     * previous() with the specified element (optional operation).
     * This call can be made only if neither remove() nor
     * add() have been called after the last call to {@code next} or
     * {@code previous}.
     *
     * @param o the element with which to replace the last element returned by
     *          {@code next} or {@code previous}
     * @throws UnsupportedOperationException if the {@code set} operation
     *         is not supported by this list iterator
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this list
     * @throws IllegalArgumentException if some aspect of the specified
     *         element prevents it from being added to this list
     * @throws exceptions.IllegalStateException if neither {@code next} nor
     *         {@code previous} have been called, or {@code remove} or
     *         {@code add} have been called after the last call to
     *         {@code next} or {@code previous}
     */
    void set(Object o);

    /**
     * Inserts the specified element into the list (optional operation).
     * The element is inserted immediately before the next element that
     * would be returned by next(), if any, and after the next
     * element that would be returned by previous(), if any.  (If the
     * list contains no elements, the new element becomes the sole element
     * on the list.)  The new element is inserted before the implicit
     * cursor: a subsequent call to {@code next} would be unaffected, and a
     * subsequent call to {@code previous} would return the new element.
     * (This call increases by one the value that would be returned by a
     * call to {@code nextIndex} or {@code previousIndex}.)
     *
     * @param o the element to insert
     * @throws UnsupportedOperationException if the {@code add} method is
     *         not supported by this list iterator
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this list
     * @throws IllegalArgumentException if some aspect of this element
     *         prevents it from being added to this list
     */
    void add(Object o);
}
