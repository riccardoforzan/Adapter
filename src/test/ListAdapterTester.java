package test;

import adapters.ListAdapter;
import interfaces.HCollection;
import interfaces.HIterator;
import interfaces.HList;
import interfaces.HListIterator;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class ListAdapterTester extends CollectionTester {

    @Before
    public void setup(){
        super.itt = new ListAdapter();
    }

    /**
     * Method that returns a non empty collection
     * This method must be OVERRIDE by the concrete implementation of a HCollection Tester
     *
     * @return a not empty HCollection
     */
    @Override
    protected HCollection createNotEmptyCollection() {
        HCollection rv = new ListAdapter();
        rv.add(new Object());
        rv.add(new Object());
        return rv;
    }

    /**
     * Method that creates an empty collection
     * This method must be OVERRIDE by the concrete implementation of a HCollection Tester
     *
     * @return an empty HCollection
     */
    @Override
    protected HCollection createEmptyCollection() {
        return new ListAdapter();
    }

    /**
     * @title Test invocation of add()
     * @description Tests if invocation of add() on an empty collection. This test try to insert the same element
     * two times in the same collection.
     * @expectedResults The collection does not allow replicated object insertion, the collection should not
     *                  be modified after the call
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of add() method in the tested collection.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Override
    public void test_addDuplicate() {
        Object toAdd = new Object();
        itt.add(toAdd);
        assertTrue("Adding a new element on an empty collection", itt.add(toAdd));
    }

    /**
     * @title Test invocation of toArray()
     * @description Tests if invocation of toArray() on a non empty collection
     * @expectedResults The returned array must contains the same element in the same order as those are contained in
     *                  the collection which the method is invoked.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of add() method in the tested collection.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Override
    public void test_toArray_notEmpty() {
        ListAdapter la = (ListAdapter) itt;
        Object[] expected = new Object[] {new Object(), new Object()};
        la.add(expected[0]);
        la.add(expected[1]);
        Object[] result = la.toArray();
        assertArrayEquals("Checking the array (also the order)",expected,result);

    }

    /**
     * @title Test invocation of toArray(Object[] a)
     * @description Tests invocation of toArray() on a non empty collection, passing an array with same length as
     *              collection size.
     * @expectedResults The returned array must contains the same element in the same order as those are contained in
     *                  the collection which the method is invoked.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of add() method in the tested collection.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Override
    public void test_toArrayGivenType_notEmpty() {
        ListAdapter la = (ListAdapter) itt;
        Object[] expected = new Object[] {new Object(), new Object()};
        la.add(expected[0]);
        la.add(expected[1]);
        Object[] parameterToChange = new Object[2];
        la.toArray(parameterToChange);
        assertArrayEquals("Parameter changed",expected,parameterToChange);
    }

    /**
     * @title Test invocation of toArray(Object[] a)
     * @description Tests if invocation of addAll() on an empty collection using as parameter an array which
     *              length > collection's size.
     * @expectedResults The returned array must contains the same element in the same order as those are contained in
     *                  the collection which the method is invoked. The returned array is a new instance
     *                  and not the element given as a parameter.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of add() method in the tested collection.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Override
    public void test_toArrayGivenType_small() {
        ListAdapter la = (ListAdapter) itt;
        Object[] expected = new Object[] {new Object(), new Object()};
        la.add(expected[0]);
        la.add(expected[1]);
        Object[] result = la.toArray(new Object[0]);
        assertArrayEquals("Testing array equals",expected,result);
    }

    /**
     * @title Test invocation of toArray(Object[] a)
     * @description Tests if invocation of addAll() on an empty collection using as parameter an array which
     *              length > collection's size.
     * @expectedResults The returned array must contains the same element in the same order as those are contained in
     *                  the collection which the method is invoked.
     *                  The array used as a parameter must contain null values after the last valid collection element.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of add() method in the tested collection.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Override
    public void test_toArrayGivenType_large() {
        ListAdapter la = (ListAdapter) itt;
        Object[] expected = new Object[] {new Object(), new Object()};
        la.add(expected[0]);
        la.add(expected[1]);
        Object[] parameterToChange = new Object[10];
        la.toArray(parameterToChange);

        boolean correct = true;
        for(int i=0;i<parameterToChange.length && correct;i++){
            if(i<expected.length) {
                if (!expected[i].equals(parameterToChange[i])){
                    correct = false;
                }
            } else {
                if(parameterToChange[i]!=null) correct = false;
            }
        }

        assertTrue("The given array has been modified and it's correct",correct);
    }

    //STARTING TEST OF ListAdapter ONLY METHODS

    /**
     * @title Test invocation of add(index,element)
     * @description Tests if invocation of add() on an empty collection using as parameter a new object.
     * @expectedResults The test collection should be modified, an element should be inserted in first and last position.
     * @actualResult As expected result.
     * @dependencies This test does not depend by correctness of other methods.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void test_AddIndex(){
        ListAdapter la = (ListAdapter) itt;

        Object toAdd = new Object();
        la.add(0,toAdd);
        assertEquals("Inserting an element in first position", la.get(0), toAdd);

        Object toAdd2 = new Object();
        la.add(1,toAdd2);
        assertTrue("Checking the list after tail insertion ", la.get(0).equals(toAdd) && la.get(1).equals(toAdd2) && la.size()==2);
    }

    /**
     * @title Test if invocation of add(index,element) with an invalid index throws IndexOutOfBoundException
     * @description Tests if invocation of add(index,element) with an invalid index throws IndexOutOfBoundException.
     * @expectedResults The class is expected to throw a IndexOutOfBoundException.
     * @actualResult As expected result.
     * @dependencies This test does not depend by correctness of other method.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void check_AddIndex_ioobe(){
        ListAdapter la = (ListAdapter) itt;
        assertThrows("Inserting in position >= size())",IndexOutOfBoundsException.class, () -> la.add(5,new Object()));
        assertThrows("Inserting in position <0",IndexOutOfBoundsException.class, () -> la.add(-1,new Object()));
    }

    /**
     * @title Test if invocation of add(index,element) with a null element parameter throws NullPointerException.
     * @description Tests if invocation of add() with a null element parameter throws NullPointerException.
     * @expectedResults The class is expected to throw a NullPointerException.
     * @actualResult As expected result.
     * @dependencies This test does not depend by correctness of other method.
     * @preConditions The ListAdapter instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void check_AddIndex_npe(){
        ListAdapter la = (ListAdapter) itt;
        assertThrows("Inserting a null value",NullPointerException.class, () -> la.add(0,null));
    }

    /**
     * @title Test invocation of addAll(index,collection)
     * @description Tests if invocation of addAll(index,collection) on a non empty collection using as parameter a non
     *              empty collection of items to add.
     * @expectedResults The test collection should be modified adding elements from given collection.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the private method createNotEmptyCollection().
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void test_AddAllIndex(){
        ListAdapter la = (ListAdapter) itt;

        Object alreadyInside = new Object();
        la.add(alreadyInside);

        ListAdapter given = (ListAdapter) createNotEmptyCollection();

        assertTrue("Adding elements on head of collection",la.addAll(1,given));
        assertEquals("Checking size",3,la.size());

        boolean result = alreadyInside.equals(la.get(0)) && given.get(0).equals(la.get(1)) && given.get(1).equals(la.get(2));
        assertTrue("Checking correctness",result);
    }

    /**
     * @title Test invocation of addAll(index,collection)
     * @description Tests if invocation of addAll(index,collection) on a non empty collection using as parameter an
     *              empty collection of items to add.
     * @expectedResults The test collection should not be modified, there are no elements in the given collection.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the private method createEmptyCollection().
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void test_AddAllIndex_void(){
        ListAdapter la = (ListAdapter) itt;
        HCollection given = createEmptyCollection();
        assertFalse("Collection is not changed",la.addAll(given));
    }

    /**
     * @title Test if invocation of add(index,element) with an invalid index throws IndexOutOfBoundException.
     * @description Tests if invocation of add(index,element) with an invalid index throws IndexOutOfBoundException.
     * @expectedResults The class is expected to throw a IndexOutOfBoundException.
     * @actualResult As expected result.
     * @dependencies This test does not depend by correctness of other method.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void check_AddAllIndex_ioobe(){
        ListAdapter la = (ListAdapter) itt;
        assertThrows("Inserting in position >= size())",IndexOutOfBoundsException.class, () -> la.add(5,new Object()));
        assertThrows("Inserting in position <0",IndexOutOfBoundsException.class, () -> la.add(-1,new Object()));
    }

    /**
     * @title Test if invocation of add(index,element) with a null parameter throws NullPointerException.
     * @description Tests if invocation of add(index,element) with a null parameter throws NullPointerException.
     * @expectedResults The class is expected to throw a NullPointerException.
     * @actualResult As expected result.
     * @dependencies This test does not depend by correctness of other method.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void check_AddAllIndex_npe(){
        ListAdapter la = (ListAdapter) itt;
        assertThrows("Inserting a null value",NullPointerException.class, () -> la.add(0,null));
    }

    /**
     * @title Test indexOf(Object o) method
     * @description Tests invocation of indexOf(Object o) method on an empty ListAdapter.
     * @expectedResults The object given as a parameter is not found on the list, so the result is -1.
     * @actualResult As expected result.
     * @dependencies This test does not depend by correctness of other method.
     * @preConditions The ListAdapter instance must be a new (and empty) instance of ListAdapter.
     * @postConditions The ListAdapter instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void test_IndexOf_empty(){
        ListAdapter la = (ListAdapter) itt;
        Object toFind = new Object();
        assertEquals("Looking for an object in an empty collection",-1,la.indexOf(toFind));
    }

    /**
     * @title Test indexOf(Object o) method
     * @description Tests invocation of indexOf(Object o) method on a non empty ListAdapter.
     * @expectedResults The object given as a parameter is found on the list because it was previously added.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of add() method in the tested collection.
     * @preConditions The ListAdapter instance must be a new (and empty) instance of ListAdapter.
     * @postConditions The ListAdapter instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void test_IndexOf_found(){
        ListAdapter la = (ListAdapter) itt;
        Object toFind = new Object();
        la.add(toFind);
        int indexResult = la.indexOf(toFind);
        assertEquals("Check correctness",toFind,la.get(indexResult));
    }

    /**
     * @title Test indexOf(Object o) method
     * @description Tests invocation of indexOf(Object o) method on a non empty ListAdapter.
     * @expectedResults The object given as a parameter is found on the list because it has been added two times,
     *                  from this method invocation is expected to be returned the first instance.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of add() method in the tested collection.
     * @preConditions The ListAdapter instance must be a new (and empty) instance of ListAdapter.
     * @postConditions The ListAdapter instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void test_IndexOf_first(){
        ListAdapter la = (ListAdapter) itt;

        Object toFind = new Object();
        la.add(toFind);
        la.add(toFind);

        int indexResult = la.indexOf(toFind);

        assertEquals("Checking returns the first",0,indexResult);
    }

    /**
     * @title Test indexOf(Object o) method
     * @description Tests invocation of indexOf(Object o) method on a non empty ListAdapter.
     * @expectedResults The object given as a parameter is not found on the list.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of add() method in the tested collection.
     *               Method contains() is used to check correctness.
     * @preConditions The ListAdapter instance must be a new (and empty) instance of ListAdapter.
     * @postConditions The ListAdapter instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void test_IndexOf_notFound(){
        ListAdapter la = (ListAdapter) itt;

        Object toFind = new Object();
        la.add(toFind);
        la.add(toFind);

        Object notPresent = new Object();

        assertEquals("Looking for an object not present in a non empty list",-1,la.indexOf(notPresent));
        assertNotEquals("Checking correctness",true,la.contains(notPresent));
    }

    /**
     * @title Test if invocation of indexOf(Object o) with null value for o, throws NullPointerException.
     * @description Tests if invocation of indexOf(Object o) with null value for o, throws NullPointerException.
     * @expectedResults The class is expected to throw a NullPointerException.
     * @actualResult As expected result.
     * @dependencies This test does not depend by correctness of other method.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void check_IndexOf_npe(){
        ListAdapter la = (ListAdapter) itt;
        assertThrows("Testing if NullPointerException is thrown",NullPointerException.class, () -> la.indexOf(null));
    }

    /**
     * @title Test lastIndexOf(Object o) method
     * @description Tests invocation of lastIndexOf(Object o) method on an empty ListAdapter.
     * @expectedResults The object given as a parameter is not found on the list, so the result is -1.
     * @actualResult As expected result.
     * @dependencies This test does not depend by correctness of other method.
     * @preConditions The ListAdapter instance must be a new (and empty) instance of ListAdapter.
     * @postConditions The ListAdapter instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void test_lastIndexOf_empty(){
        ListAdapter la = (ListAdapter) itt;
        Object toFind = new Object();
        assertEquals("Looking for an object in an empty collection",-1,la.lastIndexOf(toFind));
    }

    /**
     * @title Test lastIndexOf(Object o) method
     * @description Tests invocation of lastIndexOf(Object o) method on a non empty ListAdapter.
     * @expectedResults The object given as a parameter is found on the list because it was previously added.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of add() method in the tested collection.
     * @preConditions The ListAdapter instance must be a new (and empty) instance of ListAdapter.
     * @postConditions The ListAdapter instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void test_lastIndexOf_found(){
        ListAdapter la = (ListAdapter) itt;
        Object toFind = new Object();
        la.add(toFind);
        int indexResult = la.lastIndexOf(toFind);
        assertEquals("Check correctness",toFind,la.get(indexResult));
    }

    /**
     * @title Test lastIndexOf(Object o) method
     * @description Tests invocation of lastIndexOf(Object o) method on a non empty ListAdapter.
     * @expectedResults The object given as a parameter is found on the list because it has been added two times,
     *                  from this method invocation is expected to be returned the last instance.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of add() method in the tested collection.
     * @preConditions The ListAdapter instance must be a new (and empty) instance of ListAdapter.
     * @postConditions The ListAdapter instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void test_lastIndexOf(){
        ListAdapter la = (ListAdapter) itt;

        Object toFind = new Object();
        la.add(toFind);
        la.add(toFind);

        int indexResult = la.lastIndexOf(toFind);

        assertEquals("Checking returns the last",1,indexResult);
    }

    /**
     * @title Test lastIndexOf(Object o) method
     * @description Tests invocation of lastIndexOf(Object o) method on a non empty ListAdapter.
     * @expectedResults The object given as a parameter is not found on the list.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of add() method in the tested collection.
     *               Method contains() is used to check correctness.
     * @preConditions The ListAdapter instance must be a new (and empty) instance of ListAdapter.
     * @postConditions The ListAdapter instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void test_lastIndexOf_notFound(){
        ListAdapter la = (ListAdapter) itt;

        Object toFind = new Object();
        la.add(toFind);
        la.add(toFind);

        Object notPresent = new Object();

        assertEquals("Looking for an object not present in a non empty list",-1,la.lastIndexOf(notPresent));
        assertNotEquals("Checking correctness",true,la.contains(notPresent));
    }

    /**
     * @title Test if invocation of lastIndexOf(Object o) with null value for o, throws NullPointerException.
     * @description Tests if invocation of lastIndexOf(Object o) with null value for o, throws NullPointerException.
     * @expectedResults The class is expected to throw a NullPointerException.
     * @actualResult As expected result.
     * @dependencies This test does not depend by correctness of other method.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void check_lastIndexOf_npe(){
        ListAdapter la = (ListAdapter) itt;
        assertThrows("Testing if NullPointerException is thrown",NullPointerException.class, () -> la.lastIndexOf(null));
    }

    /**
     * @title Test remove method
     * @description Test remove method
     * @expectedResults The object is removed from the list
     * @dependencies uses the add() method
     * @preConditions the object to remove belongs to the collection
     */

    @Test
    public void test_RemoveIndex(){
        ListAdapter la = (ListAdapter) itt;

        la.add(new Object());

        Object target = new Object();
        la.add(target);

        Object removed = la.remove(1);
        assertEquals("Removing the object",target,removed);
        assertEquals("Only 1 object left",1,la.size());
    }

    /**
     * @title Test if invocation of remove(index) with an invalid index throws IndexOutOfBoundException.
     * @description Tests if invocation of remove(index)  with an invalid index throws IndexOutOfBoundException.
     * @expectedResults The class is expected to throw a IndexOutOfBoundException.
     * @actualResult As expected result.
     * @dependencies This test does not depend by correctness of other method.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void check_RemoveIndex_ioobe(){
        ListAdapter la = (ListAdapter) itt;
        assertThrows("Removing position < 0",IndexOutOfBoundsException.class, () -> la.remove(-1));
        assertThrows("Removing position > size()",IndexOutOfBoundsException.class, () -> la.remove(1));
    }

    /**
     * @title Test of set method
     * @description Testing set method calling it in a valid position
     * @expectedResults After set method invocation the object saved in position 0 in modified
     * @dependencies uses add() method to setup the invocation, uses size() and get() to check correctness
     */

    @Test
    public void test_set(){
        ListAdapter la = (ListAdapter) itt;
        Object obj1 = new Object();
        la.add(obj1);

        Object substitute = new Object();
        Object previous = la.set(0,substitute);

        assertEquals("Checking size is not changed",1,la.size());
        assertEquals("Checking the returned object",previous,obj1);
        assertEquals("Checking substitute element inserted",substitute,la.get(0));
    }

    /**
     * @title Test if invocation of set(index,element) with a null parameter throws NullPointerException.
     * @description Tests if invocation of set(index,element) with a null parameter throws NullPointerException.
     * @expectedResults The class is expected to throw a NullPointerException.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of add() method in the tested collection.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void check_set_npe(){
        ListAdapter la = (ListAdapter) itt;
        la.add(new Object());
        assertThrows("Setting position with null",NullPointerException.class, () -> la.set(1,null));
    }

    /**
     * @title Test if invocation of set(index,element) with an invalid index throws IndexOutOfBoundException.
     * @description Tests if invocation of set(index,element) with an invalid index throws IndexOutOfBoundException.
     * @expectedResults The class is expected to throw a IndexOutOfBoundException.
     * @actualResult As expected result.
     * @dependencies This test does not depend by correctness of other method.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void check_set_ioobe(){
        ListAdapter la = (ListAdapter) itt;
        assertThrows("Setting position < 0",IndexOutOfBoundsException.class, () -> la.set(-1,new Object()));
        assertThrows("Setting position > size()",IndexOutOfBoundsException.class, () -> la.set(1,new Object()));
    }

    //TEST LIST ITERATOR

    /**
     * @title Test ListIterator.nextIndex() method
     * @description Testing ListIterator.nextIndex() method to check if iterates correctly over the elements.
     * @expectedResults The iterator iterates correctly over elements of the list.
     * @actualResult As expected result.
     * @dependencies This Test has no dependencies on other class methods.
     * @preConditions The ListAdapter instance must be a new instance of List.
     * @postConditions The ListAdapter instance should not be modified by the execution of the method.
     */
    @Test
    public void test_listIterator_nextIndex() {
        ListAdapter la = (ListAdapter) itt;
        HListIterator it = la.listIterator();
        assertEquals("Cheking iterator position on empty list",0, it.nextIndex());

        la.add(new Object());
        it=la.listIterator();
        it.next();
        assertFalse("No more elements to iterate",it.hasNext());
        assertEquals("nextIndex should return size()", itt.size(), it.nextIndex());
    }

    /**
     * @title Test ListIterator.hasPrevious() method
     * @description Testing ListIterator.hasPrevious() method to check if iterates correctly over the elements.
     * @expectedResults The iterator iterates correctly over elements of the list.
     * @actualResult As expected result.
     * @dependencies This Test has no dependencies on other class methods.
     * @preConditions The ListAdapter instance must be a new instance of List.
     * @postConditions The ListAdapter instance should not be modified by the execution of the method.
     */
    @Test
    public void test_listIterator_hasPrevious(){
        ListAdapter la = (ListAdapter) itt;
        HListIterator it = la.listIterator();
        assertFalse("Cheking iterator on empty list",it.hasPrevious());

        la.add(new Object());
        it=la.listIterator();
        it.next();
        assertTrue("Has 1 element",it.hasPrevious());
    }

    /**
     * @title Test ListIterator.previous() method
     * @description Testing ListIterator.previous() method to check if iterates correctly over the elements.
     * @expectedResults The iterator iterates correctly over elements of the list.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of add() method in the tested collection.
     * @preConditions The ListAdapter instance must be a new instance of List.
     * @postConditions The ListAdapter instance should not be modified by the execution of the method.
     */
    @Test
    public void test_listIterator_previous(){
        Object first = new Object();
        ListAdapter la = (ListAdapter) itt;
        la.add(first);

        HListIterator it = la.listIterator();
        it.next();

        assertEquals("Has 1 element, the first inserted",first,it.previous());
    }

    /**
     * @title Test ListIterator.previous() method
     * @description This test tests the behaviour of the iterator, it calls HIterator.previous() on an empty list.
     * @expectedResults The class is expected to throw a NoSuchElementException.
     * @actualResult As expected result.
     * @dependencies This test does not depend by correctness of other methods.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void test_listIterator_previous_nse(){
        ListAdapter la = (ListAdapter) itt;
        HListIterator it = la.listIterator();
        assertThrows("No element in this collection", NoSuchElementException.class, () -> it.previous());
    }

    /**
     * @title Test ListIterator.previousIndex() method
     * @description Testing ListIterator.previousIndex() method to check if iterates correctly over the elements.
     * @expectedResults The iterator iterates correctly over elements of the list.
     * @actualResult As expected result.
     * @dependencies This Test has no dependencies on other class methods.
     * @preConditions The ListAdapter instance must be a new instance of List.
     * @postConditions The ListAdapter instance should not be modified by the execution of the method.
     */
    @Test
    public void test_listIterator_previousIndex(){
        ListAdapter la = (ListAdapter) itt;
        HListIterator it = la.listIterator();
        assertEquals("Cheking iterator position on empty list",0, it.nextIndex());

        la.add(new Object());
        it=la.listIterator();
        it.next();
        assertTrue("No more elements to iterate",it.hasPrevious());
        assertEquals(" should return size()", 0, it.previousIndex());
    }

    /**
     * @title Test ListIterator.add() method
     * @description Testing ListIterator.add() method to check if adds correctly the elements.
     * @expectedResults The iterator should add elements to the main list.
     * @actualResult As expected result.
     * @dependencies The correctness of this test depends on the correcntess of method add(), get() and size().
     * @preConditions The list instance must be a new instance of List.
     * @postConditions The list instance should be modified by the direct execution of the tested method.
     */
    @Test
    public void test_listIterator_add(){
        Object obj1 = new Object();
        itt.add(obj1);

        ListAdapter la = (ListAdapter) itt;
        HListIterator it = la.listIterator();

        int prev = it.previousIndex(), next = it.nextIndex();
        Object obj2 = new Object();
        it.add(obj2);
        boolean in = (it.nextIndex()==next+1) && (it.previousIndex()==prev+1);
        assertTrue("Values of nextIndex and previousIndex decremented by 1 after call to add", in);
        assertEquals("Insert head", obj2, la.get(0));
        assertEquals("Size increased", 2, la.size());

        it.next();
        Object obj3 = new Object();
        it.add(obj3);
        assertEquals("Insert tail", obj3, la.get(2));
        assertEquals("Size increased", 3, itt.size());
    }

    /**
     * @title Test ListIterator.add() method throws IllegalArgumentException
     * @description Testing ListIterator.add() method to check if it throws IllegalArgumentException if parameter
     *              given is null (null is not a valid value in ListAdapter).
     * @expectedResults The class is expected to throw a IllegalArgumentException.
     * @actualResult As expected result.
     * @dependencies This test does not depend by correctness of other methods.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void check_listIterator_add_iae(){
        assertThrows("ListIterator.add() called passing null parameter", IllegalArgumentException.class, () -> {
            ListAdapter la = (ListAdapter) itt;
            HListIterator it = la.listIterator();
            it.add(null);
        });
    }

    /**
     * @title Test ListIterator.set() method
     * @description This test tests the behaviour of the ListIterator returned by listIterator() method.
     * @expectedResults The iterator should modify elements of the list.
     * @actualResult As expected result.
     * @dependencies The correctness of this test depends on the correcntess of method add() and get().
     * @preConditions The list instance must be a new istance of List.
     * @postConditions The list instance should be modified by the direct execution of the tested method.
     */
    @Test
    public void test_listIterator_set(){
        Object obj1 = new Object();
        Object obj2 = new Object();
        Object obj3 = new Object();
        Object obj4 = new Object();

        itt.add(obj1);
        itt.add(obj2);

        ListAdapter la = (ListAdapter) itt;
        HListIterator it = la.listIterator();

        it.next();
        it.set(obj3);
        it.next();
        it.set(obj4);
        assertEquals("Check change on ListAdapter parent", obj3, la.get(0));
        assertEquals("Check change on ListAdapter parent", obj4, la.get(1));
        assertEquals("Check consistency with Iterator", obj4, it.previous());
        assertEquals("Check consistency with Iterator", obj3, it.previous());
    }

    /**
     * @title Test ListIterator.set() method throws IllegalArgumentException
     * @description Testing ListIterator.set() method to check if it throws IllegalArgumentException if parameter
     *              given is null (null is not a valid value in ListAdapter).
     * @expectedResults The class is expected to throw a IllegalArgumentException.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of add() method in the tested collection.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void check_listIterator_set_iae(){
        assertThrows("ListIterator.add() called passing null parameter", IllegalArgumentException.class, () -> {
            ListAdapter la = (ListAdapter) itt;
            la.add(new Object());
            HListIterator it = la.listIterator();
            it.set(null);
        });
    }

    /**
     * @title Test ListIterator.set() method throws IllegalStateException
     * @description Testing ListIterator.set() method to check if it throws IllegalStateException if is invoked
     *              after a call to ListIterator.remove() or ListIterator.add().
     *              Throws exception even if is not preceded by a call to ListIterator.next() or
     *              ListIterator.previous().
     * @expectedResults The class is expected to throw a IllegalArgumentException.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of add() method in the tested collection.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void check_listIterator_set_ise(){
        assertThrows("next() / previous() not invoked", exception.IllegalStateException.class, () -> {
            ListAdapter la = (ListAdapter) itt;
            HListIterator it = la.listIterator();
            it.set(new Object());
        });
        assertThrows("invoked after a call to remove()", exception.IllegalStateException.class, () -> {
            itt.add(new Object());
            ListAdapter la = (ListAdapter) itt;
            HListIterator it = la.listIterator();
            it.next();
            it.remove();
            it.set(new Object());
        });
        assertThrows("invoked after a call to add()", exception.IllegalStateException.class, () -> {
            ListAdapter la = (ListAdapter) itt;
            HListIterator it = la.listIterator();
            it.add(new Object());
            it.set(new Object());
        });
    }

    /**
     * @title Test of ListIterator method
     * @description This test tests the behaviour of the ListIterator in a full iteration on the list.
     * @expectedResults The iterator should iterate all the elements of the list, in both travisalò
     * @actualResult As expected result.
     * @dependencies The correctness of this test depends on the correctness of methods add() and get().
     * @preConditions The list instance must be a new instance of List.
     * @postConditions The list instance should be modified by the direct execution of the tested method.
     */
    @Test
    public void test_ListIterator_iteration() {
        ListAdapter la = (ListAdapter) itt;

        Object[] test = new Object[4];
        for(int i=0;i<test.length;i++) test[i]=new Object();

        for(int i=0;i<test.length;i++) la.add(test[i]);

        int i = 0;
        HListIterator it = la.listIterator();
        while(it.hasNext()) assertEquals("Next pass", test[i++], it.next());
        while(it.hasPrevious()) assertEquals("Previous pass", test[--i], it.previous());
    }

    /**
     * @title Test of ListIterator method
     * @description This test tests the behaviour of the ListIterator in a common use scenario.
     * @expectedResults The iterator should successfully conclude the sequence of operations.
     * @actualResult As expected result.
     * @dependencies The correctness of this test depends on the correctness of method add(), get(), size().
     * @preConditions The list instance must be a new instance of List.
     * @postConditions The list instance should be modified by the direct execution of the tested method.
     */
    @Test
    public void test_ListIterator_complex() {
        Object[] tmp = new Object[5];
        for(int i=0;i<tmp.length;i++) tmp[i]=new Object();

        ListAdapter la = (ListAdapter) itt;
        for(int i=0;i<tmp.length;i++) la.add(tmp[i]);

        HListIterator it = la.listIterator();

        Object newHead = new Object();
        it.add(newHead);
        assertTrue("Head inserted", la.get(0).equals(newHead) && la.get(1).equals(tmp[0]));
        assertEquals("Increased size", tmp.length+1, itt.size());

        it.next();
        it.remove();
        assertEquals("Removing second", tmp[2] , la.get(2));

        it.next();
        it.next();
        it.next();
        it.next();
        assertFalse("Last element", it.hasNext());

        it.previous();
        //Removing last element
        it.remove();
        assertFalse("End", it.hasNext());

        assertEquals("Check last value", tmp[3], la.get(3));
        assertEquals("Checking index values", true, it.previousIndex()==3 && it.nextIndex()==itt.size());
    }

    /**
     * @title Test ListIterator giving an index as starting point.
     * @description This test tests the behaviour of ListIterator when is given a starting point.
     *              The ListIterator starts at specified point of the list,
     *              this method check correctness with next() and previous() elements.
     * @expectedResults A ListIterator set using an index should have as first next element the corresponding index element,
     *                  as previous it should have the element at index (index-1).
     * @actualResult As expected result.
     * @dependencies The correctness of this test depends on the correctness of methods add(), size(), get()
     * @preConditions The list instance must be a new instance of List.
     * @postConditions The list instance should be modified by the direct execution of the tested method.
     */
    @Test
    public void test_ListIterator_Index() {
        Object[] tmp = new Object[5];
        for(int i=0;i<tmp.length;i++) tmp[i]=new Object();

        ListAdapter la = (ListAdapter) itt;
        for(int i=0;i<tmp.length;i++) la.add(tmp[i]);

        HListIterator it = la.listIterator(1);
        Object result = it.next();
        assertEquals("Checking that offset works", tmp[1], result);

        it.previous();
        result = it.previous();
        assertEquals("Checking that offset works", tmp[0], result);
    }

    /**
     * @title Test ListIterator giving an incorrect value as index, it should throw IndexOutOfBoundException.
     * @description Tests if invocation of method with an invalid index throws IndexOutOfBoundException.
     * @expectedResults The class is expected to throw a IndexOutOfBoundException.
     * @actualResult As expected result.
     * @dependencies This test does not depend by correctness of other method.
     * @preConditions The HCollection instance must be a new (and empty) instance of Collection.
     * @postConditions The collection instance isn't directly modified by the execution of the method tested.
     */
    @Test
    public void testParametricListIterator_exceptions() {
        assertThrows("Index < 0", IndexOutOfBoundsException.class, () -> {
            ListAdapter la = (ListAdapter) itt;
            HListIterator it = la.listIterator(-1);
        });
        assertThrows("index is > size", IndexOutOfBoundsException.class, () -> {
            ListAdapter la = (ListAdapter) itt;
            HListIterator it = la.listIterator(la.size()+1);
        });
    }

    //TEST SUBLIST

    /**
     * @title Test #1 of subList method, of Class list.
     * @description This test tests the behaviour of subList() method when called on an empty list. When modify, the main list will always equal to the sublist.
     * @expectedResults The returned list must be empty and should always equals to the upper list.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctnes of methods isEmpty(), add(), size(), remove(), equals().
     * @preConditions The list instance must be a new istance of List.
     * @postConditions The list instance should be modified directly by the execution of the method.
     */
    @Test
    public void testSubList_empty() {
        ListAdapter ls = (ListAdapter) itt;
        HList sub = ls.subList(0, 0);
        assertTrue("la sottolista è vuota", sub.isEmpty());

        sub.add("pippo");
        sub.add("pluto");
        assertEquals("aggiunte apportate alla sottolista", 2, sub.size());
        assertEquals("aggiunte apportate alla lista madre", 2, ls.size());

        assertTrue("le due liste si equivalgono", sub.equals(ls));
        sub.remove(0);
        assertEquals("rimozioni apportate alla lista madre", 1, ls.size());
        assertEquals("le due liste si equivalgono", sub, ls);
    }

    /**
     * @title Test #2 of subList method, of Class list.
     * @description This test tests the behaviour of subList() method when called on a not-empty list, using as parameters (0, size()) (the sublist is a view of all the main list).
     * @expectedResults The returned list must contains all the elements of the main list and must be alway equals to it.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctnes of methods add, size(), equals() and remove().
     * @preConditions The list instance must be a new istance of List.
     * @postConditions The list instance should be modified directly by the execution of the method.
     */
    @Test
    public void testSubList_all() {
        ListAdapter ls = (ListAdapter) itt;
        ls.add("pippo");
        ls.add("pluto");
        HList sub = ls.subList(0, ls.size());
        assertEquals("la sottolista ha la stessa dimensione della main", 2, sub.size());
        assertEquals("le due liste sono equivalenti", sub, ls);

        sub.add("pippo");
        sub.add("pluto");
        assertEquals("aggiunte apportate alla lista madre", 4, ls.size());
        //assertEquals("le due liste si equivalgono", sub, ls);
        sub.remove(0);
        assertEquals("rimozioni apportate alla lista madre", 3, ls.size());
        //assertEquals("le due liste si equivalgono", sub, ls);
    }

    /**
     * @title Test #3 of subList method, of Class list.
     * @description This test tests the behaviour of subList() when the sublist is a a view of just a portion of the list.
     * @expectedResults The returned list must reflect all the changes to the main list.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctnes of methods add(), size(), get() and remove().
     * @preConditions The list instance must be a new istance of List.
     * @postConditions The list instance should be modified directly by the execution of the method.
     */
    @Test
    public void testSubList_portion() {
        ListAdapter ls = (ListAdapter) itt;
        ls.add("pippo");
        ls.add("pluto");
        ls.add("asso");
        ls.add("pluto");
        HList sub = ls.subList(1, 3);
        assertEquals("controllo dimensione", 2, sub.size());
        boolean result = sub.get(0).equals("pluto") && sub.get(1).equals("asso");
        assertTrue("la sottolista deve essere una vista della sola porzione specificata", result);
        sub.add("pippo");
        sub.add("pluto");
        assertEquals("aggiunte apportate alla lista madre", 6, ls.size());

        assertEquals("controllo di uno degli elementi aggiunti nella lista principale", "pippo", ls.get(3));
        sub.remove("pippo");
        sub.remove("pluto");
        assertEquals("rimozioni apportate alla lista madre", 4, ls.size());
    }

    /**
     * @title Test #4 of subList method, of Class list.
     * @description This test tests the behaviour of subList() when the sublist return is used to perform a complex sequence of operation.
     * @expectedResults The returned list must reflect all the changes to the main list and must be coherent with the operations specified.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctnes of methods add, remove, size, get, addAll, removeAll.
     * @preConditions The list instance must be a new istance of List.
     * @postConditions The list instance should be modified directly by the execution of the method.
     */
    @Test
    public void testSubList_complex() {
        ListAdapter ls = (ListAdapter) itt;
        ls.add("pippo");
        ls.add("pluto");
        ls.add("asso");
        ls.add("pluto");
        HList sub = ls.subList(0, 3);
        assertEquals("controllo dimensione", 3, sub.size());
        boolean result = sub.get(0).equals("pippo") && sub.get(1).equals("pluto") && sub.get(2).equals("asso");

        //assertTrue("la sottolista deve essere una vista della sola porzione specificata", result);
        sub.add("pippo");

        assertEquals("aggiunte apportate alla lista madre", 5, ls.size());
        assertEquals("controllo di uno degli elementi aggiunti nella lista principale", "pippo", ls.get(3));
        sub.remove(0);
        assertEquals("rimozioni apportate alla lista madre", 4, ls.size());
        assertEquals("controllo di uno degli elementi aggiunti nella lista principale", "pluto", ls.get(0));
        HCollection param = new ListAdapter();
        param.add("pietra");
        param.add("asso");
        sub.addAll(0, param);
        assertEquals("rimozioni apportate alla lista madre", 6, ls.size());
        assertTrue("controllo elementi aggiunti", ls.get(0).equals("pietra") && ls.get(1).equals("asso"));
        param.remove("pietra");
        sub.removeAll(param);
        assertEquals("rimozioni apportate alla lista madre", 4, ls.size());
        assertEquals("controllo get", "pietra", sub.get(0));
    }

    /**
     * @title Test #5 of subList method, of Class list.
     * @description This test tests the behaviour of subList() when the sublist is used to clear a part of the main list.
     * @expectedResults All the elements contained in the sublist must be removed from the main list.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctnes of methods add, size and get.
     * @preConditions The list instance must be a new istance of List.
     * @postConditions The list instance should be modified directly by the execution of the method.
     */
    @Test
    public void testSubList_clear() {
        ListAdapter ls = (ListAdapter) itt;
        ls.add("pippo");
        ls.add("pluto");
        ls.add("asso");
        ls.add("pietra");
        HList sub = ls.subList(0, 3);
        sub.clear();
        assertEquals("rimozioni apportate alla lista madre", 1, ls.size());
        assertEquals("unico elemento rimasto era fuori dalla sottolista", "pietra", ls.get(0));
    }

    /**
     * @title Test #6 of subList method, of Class list.
     * @description This test tests the behaviour of subList() when the sublist is used to find the indexOf or lastIndexOf a parameter in a view of the main list.
     * @expectedResults The minIndex and maxIndex of an object in a specified view of the list.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctnes of method add().
     * @preConditions The list instance must be a new istance of List.
     * @postConditions The list instance should not be modified directly by the execution of the method.
     */
    @Test
    public void testSubList_minMaxIndex() {
        ListAdapter ls = (ListAdapter) itt;
        ls.add("pippo");
        ls.add("pippo");
        ls.add("pippo");
        ls.add("pippo");
        HList sub = ls.subList(1, 4);
        int min = sub.indexOf("pippo"), max = sub.lastIndexOf("pippo");
        assertEquals("minimo indice nella vista", 0, min);
        assertEquals("massimo indice nella vista", 2, max);
    }

    /**
     * @title Test #7 of subList method, of Class list.
     * @description This test tests the behaviour of subList() when the sublist is used to find the set a new value for an element contained in the list.
     * @expectedResults The modifications should be reflected to the main list.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctnes of methods .
     * @preConditions The list instance must be a new istance of List.
     * @postConditions The list instance should be modified directly by the execution of the method.
     */
    @Test
    public void testSubList_set() {
        ListAdapter ls = (ListAdapter) itt;
        ls.add("pippo");
        ls.add("pluto");
        ls.add("paperino");
        HList sub = ls.subList(1, 2);
        Object expected = ls.get(1);
        Object result = sub.set(0, "asso");
        assertEquals("vecchio valore oggetto", expected, result);
        assertEquals("nuovo valore settato correttamente", "asso", ls.get(1));
    }

    /**
     * @title Test #1 of toArray method, of Class list.
     * @description This test tests the behaviour of toArray() method when called on a non-empty list. More in details, it tests that the returned array elements are placed in the right order defined by the iterator.
     * @expectedResults The returned array must contains the list elements placed in the order defined by the iterator.
     * @actualResult As expected result.
     * @dependencies This test has no correctness dependencies on other class methods.
     * @preConditions The list instance must be a new istance of List.
     * @postConditions The list instance should not be modified directly by the execution of the method.
     */
    @Test
    public void testToArray_notEmpty() {
        ListAdapter ls = (ListAdapter) itt;
        ls.add("pippo");
        ls.add("pluto");

        Object[] result = ls.toArray();
        HIterator it = ls.iterator();
        boolean check = true;
        for(int i=0; it.hasNext() && i<result.length; i++) {
            check = check && it.next().equals(result[i]);
        }
        assertTrue("gli elementi sono copiati nell'array nell'ordine definito dall'iteratore", check);
    }

    /**
     * @title Test #1 of parametric toArray method, of class List.
     * @description This test tests the behaviour of parametric method toArray() when is called on a not-empty list passing an array which length is greater than list size. More in detail, it tests that the elements are returned in the right order.
     * @expectedResults The array returned should be exactly the one which has been passed as parameter, but the first size() must be modified, as they should contains the elements of this list in the right order.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method add().
     * @preConditions The list instance must be a new istance of List.
     * @postConditions The list instance should not be directly modified by the call of the method tested.
     */
    @Test
    public void testParametricToArray_notEmpty() {
        ListAdapter ls = (ListAdapter) itt;
        String[] param = new String[10];
        for(int i=0; i<10; i++) param[i] = "pippo";
        ls.add("pluto");
        ls.add("paperino");
        ls.add("topolino");

        Object[] result = ls.toArray(param);
        HIterator it = ls.iterator();
        boolean check = true;
        for(int i=0; it.hasNext(); i++) {
            check = check && it.next().equals(result[i]);
        }
        assertEquals("gli elementi della lista sono stati copiati nell'ordine definito dall'iteratore", true, check);
    }

    /**
     * @title Test #2 of parametric toArray method, of class List.
     * @description This test tests the behaviour of parametric method toArray() when is called on a not-empty collection using an array which is not big enough to contain the whole list. More in details, the it tests the order of the returned array.
     * @expectedResults The array returned should be a new istance of a generic Object array (the parameter should not be modified), and it should contains all the elements contained in the list in the proper order.
     * @actualResult As expected result.
     * @dependencies Depends on the correctess of method add().
     * @preConditions The list instance must be a new istance of List.
     * @postConditions The list instance should be directly modified by the call of this method.
     */
    @Test
    public void testParametricToArray_notBigEnough() {
        ListAdapter ls = (ListAdapter) itt;
        String[] param = new String[1];
        param[0] = "pippo";
        ls.add("pluto");
        ls.add("paperino");
        ls.add("topolino");

        Object[] result = ls.toArray(param);
        HIterator it = ls.iterator();
        boolean check = true;
        for(int i=0; it.hasNext(); i++) {
            check = check && it.next().equals(result[i]);
        }
        assertTrue("gli elementi della lista sono stati copiati nell'ordine definito dall'iteratore", check);
    }

}
