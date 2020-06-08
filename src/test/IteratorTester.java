package test;

import org.junit.Test;

public interface IteratorTester {

    //TODO: Rivedere la documentazione


    /**
     * @title Test of Iterator.hasNext()
     * @description Test the iterator
     * @expectedResults false, the collection is empty
     * @dependencies HCollection.iterator()
     * @preConditions The collection must be empty
     */
    @Test
    void test_Iterator_hasNext_EmptyCollection();

    /**
     * @title Test of Iterator.hasNext()
     * @description Test the iterator
     * @expectedResults false, the collection is empty
     * @dependencies HCollection.iterator() and HCollection.add(Object a)
     * @preConditions The collection must be not empty
     */
    @Test
    void test_Iterator_hasNext_NotEmptyCollection();

    /**
     * @title Test of Iterator.next()
     * @description Call of next on an empty collection throws NullPointerException
     * @expectedResults Throws NullPointerException
     * @dependencies HCollection.iterator()
     * @preConditions The collection must be empty
     */
    @Test
    void test_Iterator_next_npe();

    /**
     * @title Test of Iterator.next()
     * @description Checking that iterator iterates on all the element of this collection
     * @expectedResults All elements are iterated
     * @dependencies HCollection.iterator() and HCollection.add(Object a)
     * @preConditions The collection must be empty
     */
    @Test
    void test_Iterator_next();

    /**
     * @title Test of Iterator.remove()
     * @description Checking that iterator removes element in the correct way
     * @expectedResults All elements are iterated
     * @dependencies HCollection.iterator(), HCollection.add(Object a) and HCollection.contains(Object o)
     * @preConditions The collection must be empty
     */
    @Test
    void test_Iterator_remove();

    /**
     * @title Test of Iterator.remove()
     * @description Checking that invocation of Iterator.remove() not preceded by a call of Iterator.next() throws NullPointerException
     * @expectedResults NullPointerException
     * @dependencies HCollection.iterator()
     */
    @Test
    void check_Iterator_remove_nse();

    /**
     * @title Test invocation of Iterator.remove() two times in a row
     * @description Checking that invocation of Iterator.remove() not preceded by a call of Iterator.next() throws NullPointerException
     * @expectedResults NullPointerException
     * @dependencies HCollection.iterator(), HCollection.add(Object a)
     */
    @Test
    void check_Iterator_remove_tt();
}
