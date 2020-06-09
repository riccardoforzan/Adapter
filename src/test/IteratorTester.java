package test;

import org.junit.Test;

public interface IteratorTester {
    /**
     * @title Test invocation of HIterator.hasNext() on an iteration with 0 elements.
     * @description This test tests the behaviour of the iterator returned by iterator() method,
     *              this test performs calls to HIterator.hasNext()
     * @expectedResults The call to hasNext() must return false because the iteration has 0 element
     * @dependencies This test does not depend by correctness of other method.
     * @preConditions The iteration instance to test must be a new (and empty).
     * @postConditions The iteration instance isn't directly modified by the execution of the method tested.
     */
    @Test
    void test_Iterator_hasNext_EmptyCollection();

    /**
     * @title Test invocation of HIterator.hasNext() on a non empty iteration.
     * @description This test tests the behaviour of the iterator returned by iterator() method,
     *              this test performs calls to HIterator.hasNext()
     * @expectedResults The call to hasNext() must return true because the iteration has at least 1 element.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of growing methods in the tested iteration.
     * @preConditions The iteration instance to test must be a new (and empty).
     * @postConditions The iteration instance isn't directly modified by the execution of the method tested.
     */
    @Test
    void test_Iterator_hasNext_NotEmptyCollection();

    /**
     * @title Test invocation of HIterator.next() on an iteration with 0 elements.
     * @description This test tests the behaviour of the iterator returned by iterator() method,
     *              this test performs calls to HIterator.next()
     * @expectedResults The class is expected to throw a NoSuchElementException.
     * @dependencies This test does not depend by correctness of other method.
     * @preConditions The iteration instance to test must be a new (and empty).
     * @postConditions The iteration instance isn't directly modified by the execution of the method tested.
     */
    @Test
    void test_Iterator_next_npe();

    /**
     * @title Test invocation of HIterator.remove() on a non empty iteration.
     * @description This test tests the behaviour of the iterator returned by iterator() method,
     *              this test performs calls to HIterator.hasNext() and HIterator.next() methods to check if
     *              the iterator iterates on all elements of the test iteration.
     * @expectedResults The collection should be modified, the element removed by the iterator should not be part
     *                  of the iteration.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of growing methods in the tested iteration.
     * @preConditions The iteration instance to test must be a new (and empty).
     * @postConditions The iteration instance isn't directly modified by the execution of the method tested.
     */
    @Test
    void test_Iterator_next();

    /**
     * @title Test invocation of HIterator.remove() on a non empty iteration.
     * @description This test tests the behaviour of the iterator returned by iterator() method,
     *              this test performs a call of remove() and checks if modification done by the iterator are visible
     *              on the iteration which this Iterator belongs to.
     * @expectedResults The iteration should be modified, the element removed by the iterator should not be part
     *                  of the iteration.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of growing methods in the tested iteration.
     * @preConditions The iteration instance to test must be a new (and empty).
     * @postConditions The iteration instance isn't directly modified by the execution of the method tested.
     */
    @Test
    void test_Iterator_remove();

    /**
     * @title Test invocation of HIterator.remove() without a previous call to HIterator.next().
     * @description This test tests the behaviour of the iterator returned by iterator() method,
     *              this test performs a calls of remove() without calling next() previously.
     * @expectedResults The class is expected to throw a IllegalStateException.
     * @dependencies This test does not depend by correctness of other method.
     * @preConditions The iteration instance to test must be a new (and empty).
     * @postConditions The iteration instance isn't directly modified by the execution of the method tested.
     */
    @Test
    void check_Iterator_remove_nse();

    /**
     * @title Test invocation of HIterator.remove() two times in a row on the iterator
     * @description This test tests the behaviour of the iterator returned by iterator() method,
     *              this test performs two subsequent calls of remove().
     * @expectedResults The class is expected to throw a IllegalStateException.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of add() method in the tested collection.
     * @preConditions The HCollection instance must be a new instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    void check_Iterator_remove_tt();
}
