package test;

import interfaces.HCollection;
import interfaces.HIterator;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * Shared test between all HCollection implementation
 */
public abstract class CollectionTester implements IteratorTester {

    //HCollection instance to test
    protected HCollection itt;

    //METHOD THAT MUST BE OVERRIDE TO TEST *ALL METHOD

    /**
     * Method that returns a non empty collection
     * This method must be OVERRIDE by the concrete implementation of a HCollection Tester
     * @return a not empty HCollection
     */
    protected abstract HCollection createNotEmptyCollection();

    /**
     * Method that creates an empty collection
     * This method must be OVERRIDE by the concrete implementation of a HCollection Tester
     * @return an empty HCollection
     */
    protected abstract HCollection createEmptyCollection();

    /**
     * @title Test invocation of isEmpty()
     * @description Tests invocation of isEmpty() on an empty collection
     * @expectedResults The test should return true because the collection is empty.
     * @actualResult As expected result.
     * @dependencies This test does not depend by correctness of other methods.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void test_isEmpty() {
        assertTrue("New collection must be empty", itt.isEmpty());
    }

    /**
     * @title Test invocation of size()
     * @description Tests invocation of size() on an empty collection
     * @expectedResults The test should return 0 because the collection is empty.
     * @actualResult As expected result.
     * @dependencies This test does not depend by correctness of other methods.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void test_size() {
        assertEquals("New collection size must be 0, it must be empty",0,itt.size());
    }

    /**
     * @title Test invocation of add()
     * @description Tests if invocation of add() on an empty collection using as parameter a new object.
     * @expectedResults The test collection should be modified
     * @actualResult As expected result.
     * @dependencies This test does not depend by correctness of other methods.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void test_add() {
        Object toAdd = new Object();
        assertTrue("Adding a new element on an empty colleciton", itt.add(toAdd));
    }

    /**
     * @title Test invocation of add()
     * @description Tests if invocation of add() on an empty collection. This test try to insert the same element
     *              two times in the same collection.
     * @expectedResults The result depends on the implementation of the collection.
     *                  If the collection does not make any guarantees about uniqueness of inserted object
     *                  the collection should be modified.
     *                  Instead, if the collection does not allow replicated object insertion, the collection should not
     *                  be modified.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public abstract void test_addDuplicate();

    /**
     * @title Test if invocation of add() with a null parameter throws NullPointerException.
     * @description Tests if invocation of add() with a null parameter throws NullPointerException.
     * @expectedResults The class is expected to throw a NullPointerException.
     * @actualResult As expected result.
     * @dependencies This test does not depend by correctness of other method.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void check_add_npe(){
        assertThrows("Null parameter for value o given to add(Object o)",NullPointerException.class, () -> itt.add(null));
    }

    /**
     * @title Test consistency of size() and isEmpty() methods.
     * @description Test the behavior of those size() and isEmpty()
     * @expectedResults Test if collection contains 0 elements, then isEmpty() returns true, and vice versa
     *                  if collections contains >0 elements, then isEmpty returns false.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of add(), size() and isEmpty() methods in the tested collection.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void check_consistencySizeIsEmpty(){
        assertTrue("Collection in empty",itt.isEmpty());
        assertEquals("Collection is empty, so size !=0 ",0,itt.size());

        itt.add(new Object());
        assertFalse("Collection ins't empty",itt.isEmpty());
        assertNotEquals("Collection isn't empty, so size !=0 ",0,itt.size());
    }

    /**
     * @title Test invocation of clear()
     * @description Tests if invocation of clear() on a non empty collection
     * @expectedResults The test collection should be empty after invocation.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of isEmpty() method in the tested collection.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void test_clear() {
        itt.add(new Object());
        itt.clear();
        assertTrue("Collection now must be empty", itt.isEmpty());
    }

    /**
     * @title Test invocation of contains()
     * @description Tests invocation of contains() on a HCollection.
     *              The searched element is added to the HCollection before the invocation.
     * @expectedResults The test collection should contain the element researched.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of add() method in the tested collection.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void test_contains() {
        Object toFind = new Object();
        itt.add(toFind);
        assertTrue("The object is found on the collection", itt.contains(toFind));
    }

    /**
     * @title Test invocation of contains()
     * @description Tests if invocation of contains() on an empty collection.
     * @expectedResults The test collection does not contain the element researched, it's empty.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the private method createEmptyCollection().
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void test_containsNotPresent() {
        Object toFind = new Object();
        assertFalse("The object isn't found on the collection", itt.contains(toFind));
    }

    /**
     * @title Test if invocation of contains() with a null parameter throws NullPointerException.
     * @description Tests if invocation of contains() with a null parameter throws NullPointerException.
     * @expectedResults The class is expected to throw a NullPointerException.
     * @actualResult As expected result.
     * @dependencies This test does not depend by correctness of other method.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void check_contains_npe(){
        assertThrows("Null parameter for value o given to contains(Object o)",NullPointerException.class, () -> itt.contains(null));
    }

    /**
     * @title Test invocation of remove()
     * @description Tests if invocation of remove() on a non empty collection modifies the collection.
     * @expectedResults The test collection should be modified, the only element inserted should be removed.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of add() method in the tested collection.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void test_remove() {
        Object toRemove = new Object();
        itt.add(toRemove);
        assertTrue("The object is removed", itt.remove(toRemove));
    }

    /**
     * @title Test invocation of remove()
     * @description Tests if invocation of remove() on an empty collection modifies the collection.
     * @expectedResults The test collection should not be modified, the collection is empty so there are no
     *                  elements to remove.
     * @actualResult As expected result.
     * @dependencies This test does not depend by correctness of other methods.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void test_removeNotPresent() {
        Object toRemove = new Object();
        assertFalse("The object is not removed", itt.remove(toRemove));
    }

    /**
     * @title Test if invocation of remove() with a null parameter throws NullPointerException.
     * @description Tests if invocation of remove() with a null parameter throws NullPointerException.
     * @expectedResults The class is expected to throw a NullPointerException.
     * @actualResult As expected result.
     * @dependencies This test does not depend by correctness of other method.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void check_remove_npe(){
        assertThrows("Null parameter for value o given to remove(Object o)",NullPointerException.class, () -> itt.remove(null));
    }

    /**
     * @title Test invocation of add(), contains() and remove() on an empty collection.
     * @description Tests the common behavior of the collection when adding, checking and removing an element
     * @expectedResults The result of a sequence of operation performed should be consistent.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of add(), contains(), remove() and
     *               isEmpty() methods in the tested collection.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void  check_ARC(){
        Object obj = new Object();
        assertTrue("The object is added to the collection", itt.add(obj));
        assertTrue("The object is contained in the collection", itt.contains(obj));
        assertTrue("The object is removed from the collection", itt.remove(obj));
        assertFalse("The object is not contained in the collection", itt.contains(obj));
    }

    /**
     * @title Test invocation of toArray()
     * @description Tests if invocation of toArray() on an empty collection
     * @expectedResults The array generated contains 0 elements because the collection which is invoked is empty.
     * @actualResult As expected result.
     * @dependencies This test does not depend by correctness of other method.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void test_toArray_empty(){
        Object[] result = itt.toArray();
        assertEquals("Array with 0 elements",0,result.length);
    }

    /**
     * @title Test invocation of toArray()
     * @description Tests if invocation of toArray() on a non empty collection
     * @expectedResults The result depends on the implementation of the collection.
     *                  If the collection makes any about order of inserted object the returned array must have the
     *                  same order.
     *                  Otherwise, the returned array has an undefined order.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public abstract void test_toArray_notEmpty();

    /**
     * @title Test invocation of toArray(Object[] a)
     * @description Tests if invocation of toArray(Object[] a) on an empty collection passing an array with length 0.
     * @expectedResults The array generated contains 0 elements because the collection which is invoked is empty.
     * @actualResult As expected result.
     * @dependencies This test does not depend by correctness of other method.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void test_toArrayGivenType_empty(){
        Object[] result = itt.toArray(new Object[0]);
        assertEquals("Array with 0 elements",0,result.length);
    }

    /**
     * @title Test invocation of toArray(Object[] a)
     * @description Tests invocation of toArray() on a non empty collection, passing an array with same length as
     *              collection size.
     * @expectedResults The result depends on the implementation of the collection.
     *                  If the collection makes any about order of inserted object the returned array must have the
     *                  same order.
     *                  Otherwise, the returned array has an undefined order.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public abstract void test_toArrayGivenType_notEmpty();

    /**
     * @title Test invocation of toArray(Object[] a)
     * @description Tests if invocation of toArray(Object[] a) on an empty collection using as parameter an array which
     *              length > collection's size.
     * @expectedResults The result depends on the implementation of the collection.
     *                  If the collection makes any about order of inserted object the returned array must have the
     *                  same order.
     *                  Otherwise, the returned array has an undefined order.
     *                  The returned array is a new instance and not the element given as a parameter.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public abstract void test_toArrayGivenType_small();

    /**
     * @title Test invocation of toArray(Object[] a)
     * @description Tests if invocation of toArray(Object[] a) on an empty collection using as parameter an array which
     *              length > collection's size.
     * @expectedResults The result depends on the implementation of the collection.
     *                  If the collection makes any about order of inserted object the returned array must have the
     *                  same order.
     *                  Otherwise, the returned array has an undefined order.
     *                  The array used as a parameter must contain null values after the last valid collection element.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public abstract void test_toArrayGivenType_large();

    /**
     * @title Test invocation of toArray(Object[] a)
     * @description Tests if invocation of toArray(Object[] a) on an empty collection using as parameter null.
     * @expectedResults The class is expected to throw a NullPointerException.
     * @actualResult As expected result.
     * @dependencies This test does not depend by correctness of other method.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void check_toArrayGivenType_npe(){
        assertThrows("Null parameter for value a given to toArray(Object[] a)",NullPointerException.class, () -> itt.toArray(null));
    }

    //TEST addAll METHOD

    /**
     * @title Test invocation of addAll()
     * @description Tests if invocation of addAll() on an empty collection using as parameter an
     *              empty collection of items to add.
     * @expectedResults The test collection should not be modified, both are empty.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the private method createEmptyCollection().
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void test_addAll_Empty(){
        HCollection given = this.createEmptyCollection();
        assertFalse("Adding 0 elements from another collection",itt.addAll(given));
    }

    /**
     * @title Test invocation of addAll()
     * @description Tests if invocation of addAll() on an empty collection using as parameter a
     *              non empty collection of items to add.
     * @expectedResults The test collection should be modified.
     *                  It should contain all elements contained in parameter collection.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the private method createNotEmptyCollection().
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void test_addAll_NotEmpty(){
        HCollection given = this.createNotEmptyCollection();
        assertTrue("Adding elements from another collection",itt.addAll(given));
    }

    /**
     * @title Test if invocation of addAll() with a null parameter throws NullPointerException.
     * @description Tests if invocation of addAll() with a null parameter throws NullPointerException.
     * @expectedResults The class is expected to throw a NullPointerException.
     * @actualResult As expected result.
     * @dependencies This test does not depend by correctness of other method.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void check_addAll_npe(){
        assertThrows("Null parameter as value given to addAll(HCollection c)",NullPointerException.class, () -> itt.addAll(null));
    }

    //TEST containsAll METHOD

    /**
     * @title Test invocation of containsAll()
     * @description Tests if invocation of containsAll() on an empty collection using as parameter
     *              an empty collection of items to check.
     * @expectedResults The invocation should state that parameter collection is contained in test collection.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of the private method createEmptyCollection().
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void test_containsAll_Empty(){
        HCollection given = this.createEmptyCollection();
        assertTrue("An empty collection is contained in every other collection",itt.containsAll(given));
    }

    /**
     * @title Test invocation of containsAll()
     * @description Tests if invocation of containsAll() on a non empty collection using as parameter a
     *              non empty collection of items to check. The parameter collection contains
     *              the same elements previously added to the test collection.
     * @expectedResults The invocation should state that parameter collection is contained in test collection.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of addAll() method in the tested collection.
     *               It also depends by the private method createNotEmptyCollection().
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void test_containsAll_NotEmpty(){
        HCollection given = this.createNotEmptyCollection();
        itt.addAll(given);
        assertTrue("All elements added from given collection are contained",itt.containsAll(given));
    }

    /**
     * @title Test invocation of containsAll()
     * @description Tests if invocation of containsAll() on a non empty collection using as parameter a
     *              non empty collection of items to check that contains one element more than the test collection.
     * @expectedResults The invocation should state that parameter collection is not contained in test collection.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of add(), addAll() methods in the tested collection.
     *               It also depends by the private method createNotEmptyCollection().
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void test_containsAll_Part(){
        HCollection given = createNotEmptyCollection();
        itt.addAll(given);
        //Changing given after addAll
        given.add(new Object());
        assertFalse("Not all elements of given collection are contained",itt.containsAll(given));
    }

    /**
     * @title Test if invocation of containsAll() with a null parameter throws NullPointerException.
     * @description Tests if invocation of containsAll() with a null parameter throws NullPointerException.
     * @expectedResults The class is expected to throw a NullPointerException.
     * @actualResult As expected result.
     * @dependencies This test does not depend by correctness of other method.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void check_containsAll_npe(){
        assertThrows("Null parameter as value given to containsAll(HCollection c)",NullPointerException.class, () -> itt.containsAll(null));
    }

    //TEST removeAll METHOD

    /**
     * @title Test invocation of removeAll()
     * @description Tests if invocation of removeAll() on an empty collection using as parameter
     *              an empty collection of items to remove.
     * @expectedResults The collection should not be modified by the invocation, both are empty.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of the private method createEmptyCollection().
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void test_removeAll_Empty(){
        HCollection given = this.createEmptyCollection();
        assertFalse("Removing an empty collection does not modify the test collection",itt.removeAll(given));
    }

    /**
     * @title Test invocation of removeAll()
     * @description Tests if invocation of removeAll() on a non empty collection using as parameter a
     *              non empty collection of items to remove that contains the same elements previously added to the
     *              test collection.
     * @expectedResults The collection should be modified by the invocation, it should be empty.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of addAll() and isEmpty() methods in the tested collection.
     *               It also depends by the private method createNotEmptyCollection().
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void test_removeAll_NotEmpty(){
        HCollection given = this.createNotEmptyCollection();
        itt.addAll(given);
        assertTrue("Collection has been modified",itt.removeAll(given));
        assertTrue("All elements are removed from the test collection",itt.isEmpty());
    }

    /**
     * @title Test invocation of removeAll()
     * @description Tests if invocation of removeAll() on a non empty collection using as parameter a
     *              non empty collection of items to remove.
     * @expectedResults The collection should be modified by the invocation and an element must be present after the
     *                  invocation.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of add(), addAll(), size() and contains() methods in the tested collection.
     *               It also depends by the private method createNotEmptyCollection().
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void test_removeAll_Part(){
        HCollection given = createNotEmptyCollection();
        itt.addAll(given);
        //Changing given after addAll
        Object stillPresent = new Object();
        itt.add(stillPresent);
        assertTrue("Collection has been modified",itt.removeAll(given));
        assertEquals("Collection still contain an element",1,itt.size());
        assertTrue("The object is still present",itt.contains(stillPresent));
    }

    /**
     * @title Test if invocation of removeAll() with a null parameter throws NullPointerException.
     * @description Tests if invocation of removeAll() with a null parameter throws NullPointerException.
     * @expectedResults The class is expected to throw a NullPointerException.
     * @actualResult As expected result.
     * @dependencies This test does not depend by correctness of other method.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void check_removeAll_npe(){
        assertThrows("Null parameter as value given to removeAll(HCollection c)",NullPointerException.class, () -> itt.removeAll(null));
    }

    //TEST retainAll METHOD

    /**
     * @title Test invocation of retainAll()
     * @description Tests if invocation of retainAll() on an empty collection using as parameter
     *              an empty collection of items to retain.
     * @expectedResults The collection should not be modified by the invocation, both are empty.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of the private method createNotEmptyCollection().
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void test_retainAll_Empty(){
        HCollection given = this.createEmptyCollection();
        assertFalse("Collection has not been modified, already empty",itt.retainAll(given));
        assertTrue("All elements have been removed",itt.isEmpty());
    }

    /**
     * @title Test invocation of retainAll()
     * @description Tests if invocation of retainAll() on a non empty collection using as parameter a
     *              non empty collection of items to retain that contains the same elements previously added to the
     *              test collection.
     * @expectedResults The collection should not be modified by the invocation.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of addAll() method in the tested collection.
     *               It also depends by the private method createNotEmptyCollection().
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void test_retainAll_NotEmpty(){
        HCollection given = this.createNotEmptyCollection();
        itt.addAll(given);
        assertFalse("Collection has not been modified",itt.retainAll(given));
    }

    /**
     * @title Test invocation of retainAll()
     * @description Tests if invocation of retainAll() on a non empty collection using as parameter a
     *              non empty collection of items to retain.
     * @expectedResults The collection has been modified and only the items in the given collection are retained.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of add(), addAll(), clear(), size() and contains() methods in the tested collection.
     *               It also depends by the private method createNotEmptyCollection().
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void test_retainAll_Part(){
        HCollection given = createNotEmptyCollection();
        Object toSave = new Object();
        given.add(toSave);
        itt.addAll(given);

        //Removing all objects and adding the one to save
        given.clear();
        given.add(toSave);

        assertTrue("Collection has been modified",itt.retainAll(given));
        assertEquals("The collection contains 1 element",1,itt.size());
        assertTrue("Contains the element to save",itt.contains(toSave));
    }

    /**
     * @title Test if invocation of retainAll() with a null parameter throws NullPointerException.
     * @description Tests if invocation of retainAll() with a null parameter throws NullPointerException.
     * @expectedResults The class is expected to throw a NullPointerException.
     * @actualResult As expected result.
     * @dependencies This test does not depend by correctness of other method.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void check_retainAll_npe(){
        assertThrows("Null parameter as value given to retainAll(HCollection c)",NullPointerException.class, () -> itt.retainAll(null));
    }

    /**
     * @title Test invocation of addAll(), containsAll() and removeAll() on an empty collection.
     * @description Tests the common behavior of the collection when adding, checking and removing a collection
     * @expectedResults The result of a sequence of operation performed should be consistent.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of addAll(), containsAll(), removeAll() and
     *               isEmpty() methods in the tested collection.
     *               It also depends by the private method createNotEmptyCollection().
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void  check_ARC_All(){
        HCollection given = createNotEmptyCollection();
        assertTrue("Test collection has been modified", itt.addAll(given));
        assertTrue("All objects are contained in the test collection", itt.containsAll(given));
        assertTrue("All objects in the test collection are removed", itt.removeAll(given));
        assertTrue("The collection is now empty",itt.isEmpty());
    }

    //TEST ITERATOR

    /**
     * @title Test invocation of HIterator.hasNext() on an empty collection.
     * @description This test tests the behaviour of the iterator returned by iterator() method,
     *              this test performs calls to HIterator.hasNext()
     * @expectedResults The call to hasNext() must return false because the collection has 0 element
     * @actualResult As expected result.
     * @dependencies This test does not depend by correctness of other method.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void test_Iterator_hasNext_EmptyCollection() {
        HIterator it = itt.iterator();
        assertFalse("Collection is empty", it.hasNext());
    }

    /**
     * @title Test invocation of HIterator.hasNext() on a non empty collection.
     * @description This test tests the behaviour of the iterator returned by iterator() method,
     *              this test performs calls to HIterator.hasNext()
     * @expectedResults The call to hasNext() must return true because the collection has 1 element
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of add() method in the tested collection.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void test_Iterator_hasNext_NotEmptyCollection() {
        itt.add(new Object());
        HIterator it = itt.iterator();
        assertTrue("Collection has got some elements", it.hasNext());
    }

    /**
     * @title Test invocation of HIterator.next() on an empty collection.
     * @description This test tests the behaviour of the iterator returned by iterator() method,
     *              this test performs calls to HIterator.next()
     * @expectedResults The class is expected to throw a NoSuchElementException.
     * @actualResult As expected result.
     * @dependencies This test does not depend by correctness of other methods.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void test_Iterator_next_npe() {
        assertThrows("No element in this collection", NoSuchElementException.class, () -> itt.iterator().next());
    }

    /**
     * @title Test invocation of HIterator.remove() on a non empty collection.
     * @description This test tests the behaviour of the iterator returned by iterator() method,
     *              this test performs calls to HIterator.hasNext() and HIterator.next() methods to check if
     *              the iterator iterates on all elements of the test collection.
     * @expectedResults The collection should be modified, the element removed by the iterator should not be part
     *                  of the colelction.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of add() method in the tested collection.
     * @preConditions The HCollection instance must be a new instance of Collection.
     * @postConditions The HCollection instance should not be modified by the execution of the method.
     */
    @Test
    public void test_Iterator_next() {
        Object obj1 = new Object();
        Object obj2 = new Object();
        itt.add(obj1);
        itt.add(obj2);

        HIterator it = itt.iterator();

        int items=0;
        int found_obj1 = 0;
        int found_obj2 = 0;
        while(it.hasNext()){
            Object tmp = it.next();
            items++;
            if(obj1.equals(tmp)){
                found_obj1++;
            }else if(obj2.equals(tmp)) {
                found_obj2++;
            }
        }

        boolean test;

        test = items==2;
        test &= found_obj1==1;
        test &= found_obj2==1;

        assertTrue("Iterator found all 3 elements", test);
    }

    /**
     * @title Test invocation of HIterator.remove() on a non empty collection.
     * @description This test tests the behaviour of the iterator returned by iterator() method,
     *              this test performs a call of remove() and checks if modification done by the iterator are visible
     *              on the collection which this Iterator belongs to.
     * @expectedResults The collection should be modified, the element removed by the iterator should not be part
     *                  of the colelction.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of add(), size(), and contains() methods in the tested collection.
     * @preConditions The HCollection instance must be a new instance of Collection.
     * @postConditions The HCollection instance should be modified by the execution of the method.
     */
    @Test
    public void test_Iterator_remove() {
        itt.add(new Object());
        itt.add(new Object());

        HIterator it = itt.iterator();
        int initSize = itt.size();

        Object removed = it.next();
        it.remove();

        int finalSize = itt.size();

        assertEquals("Dimension of the collection decreased by 1", (finalSize + 1), initSize);
        assertFalse("The removed element does not belong to this collection", itt.contains(removed));
    }

    /**
     * @title Test invocation of HIterator.remove() two times in a row on the iterator
     * @description This test tests the behaviour of the iterator returned by iterator() method,
     *              this test performs a calls of remove() without calling next() previously.
     * @expectedResults The class is expected to throw a IllegalStateException.
     * @actualResult As expected result.
     * @dependencies This test does not depend by correctness of other method.
     * @preConditions The HCollection instance must be a new instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void check_Iterator_remove_nse(){
        assertThrows("Method remove can not be called if not preceded by a call to next()", exceptions.IllegalStateException.class, () -> {
            HIterator it = itt.iterator();
            it.remove();
        });
    }

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
    public void check_Iterator_remove_tt(){
        assertThrows("remove() can not be called two times in a row", exceptions.IllegalStateException.class, () -> {
            itt.add(new Object());
            itt.add(new Object());
            HIterator it = itt.iterator();
            it.next();
            it.remove();
            it.remove();
        });
    }

    /**
     * @title Test of iterator methods of an instance class that implements HCollection interface.
     * @description This test tests the behaviour of the iterator returned by iterator() method,
     *              this test performs a common sequence of operation with the iterator.
     * @expectedResults The result of a sequence of operation performed by the iterator should be
     *                  consistent and the changes should be correctly reflected to the HCollection tested.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of add() method in the tested collection.
     * @preConditions The HCollection instance must be a new instance of Collection.
     * @postConditions The HCollection instance should be modified by the execution of the method.
     */
    @Test
    public void testIterator_removenext() {
        itt.add(new Object());
        itt.add(new Object());
        itt.add(new Object());
        itt.add(new Object());
        itt.add(new Object());
        itt.add(new Object());
        HIterator it = itt.iterator();
        it.next();
        it.remove();
        assertEquals("Size decreased", 5, itt.size());
        it.next();
        it.next();
        it.remove();
        assertEquals("Size decreased", 4, itt.size());
        it.next();
        it.next();
        it.remove();
        assertEquals("Size decreased", 3, itt.size());
        it.next();
        it.remove();
        assertEquals("Size decreased", 2, itt.size());
        assertFalse("Lower bound reached", it.hasNext());
    }

}
