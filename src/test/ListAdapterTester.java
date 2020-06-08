package test;

import adapters.ListAdapter;
import interfaces.HCollection;
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
        HList la = (HList) itt;
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
        HList la = (HList) itt;
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
        HList la = (HList) itt;
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
        HList la = (HList) itt;
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
        HList la = (HList) itt;

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
        HList la = (HList) itt;
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
        HList la = (HList) itt;
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
        HList la = (HList) itt;

        Object alreadyInside = new Object();
        la.add(alreadyInside);

        HList given = (HList) createNotEmptyCollection();

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
        HList la = (HList) itt;
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
        HList la = (HList) itt;
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
        HList la = (HList) itt;
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
        HList la = (HList) itt;
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
        HList la = (HList) itt;
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
        HList la = (HList) itt;

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
        HList la = (HList) itt;

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
        HList la = (HList) itt;
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
        HList la = (HList) itt;
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
        HList la = (HList) itt;
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
        HList la = (HList) itt;

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
        HList la = (HList) itt;

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
        HList la = (HList) itt;
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
        HList la = (HList) itt;

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
        HList la = (HList) itt;
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
        HList la = (HList) itt;
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
        HList la = (HList) itt;
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
        HList la = (HList) itt;
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
        HList la = (HList) itt;
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
        HList la = (HList) itt;
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
        HList la = (HList) itt;
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
        HList la = (HList) itt;
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
        HList la = (HList) itt;
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

        HList la = (HList) itt;
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
            HList la = (HList) itt;
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

        HList la = (HList) itt;
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
            HList la = (HList) itt;
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
            HList la = (HList) itt;
            HListIterator it = la.listIterator();
            it.set(new Object());
        });
        assertThrows("invoked after a call to remove()", exception.IllegalStateException.class, () -> {
            itt.add(new Object());
            HList la = (HList) itt;
            HListIterator it = la.listIterator();
            it.next();
            it.remove();
            it.set(new Object());
        });
        assertThrows("invoked after a call to add()", exception.IllegalStateException.class, () -> {
            HList la = (HList) itt;
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
        HList la = (HList) itt;

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

        HList la = (HList) itt;
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
        assertTrue("Checking index values", it.previousIndex() == 3 && it.nextIndex() == itt.size());
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

        HList la = (HList) itt;
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
            HList la = (HList) itt;
            HListIterator it = la.listIterator(-1);
        });
        assertThrows("index is > size", IndexOutOfBoundsException.class, () -> {
            HList la = (HList) itt;
            HListIterator it = la.listIterator(la.size()+1);
        });
    }

    //TEST SUBLIST

    /**
     * @title Test of SubList method
     * @description This test tests the behaviour of subList() method on an empty parent list.
     *              Changes made on the object returned by sublist method must be visible on the parent list.
     * @expectedResults Changes made on the SubList must be visible on the parent list.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctness of methods
     *               isEmpty(), add(), size(), remove(), equals().
     * @preConditions The list instance must be a new instance of List.
     * @postConditions The parent list of the tested sublist instance should be modified directly by the execution of the method.
     */
    @Test
    public void test_SubList_void() {
        HList ls = (HList) itt;
        HList sub = ls.subList(0, 0);
        assertTrue("Empty sublist", sub.isEmpty());

        sub.add(new Object());
        assertEquals("Added to parent sublist", 1, sub.size());
        assertEquals("Added to parent list", 1, ls.size());
        assertEquals("Checking consistency", sub,ls);

        sub.remove(0);
        assertEquals("Removing from parent", 0, ls.size());
        assertEquals("Checking consistency", sub, ls);
    }

    /**
     * @title Test of SubList method
     * @description This test tests the behaviour of subList() method on a non empty parent list.
     *              This test works with a sublist smaller than its parent list.
     *              Changes made on the object returned by sublist method must be visible on the parent list.
     * @expectedResults Changes made on the SubList must be visible on the parent list.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctness of methods
     *               isEmpty(), add(), size(), remove(), equals().
     * @preConditions The list instance must be a new instance of List.
     * @postConditions The parent list of the tested sublist instance should be modified directly by the execution of the method.
     */
    @Test
    public void test_SubList_part() {
        HList ls = (HList) itt;

        Object obj1 = new Object();
        Object obj2 = new Object();
        Object obj3 = new Object();
        Object obj4 = new Object();
        Object obj5 = new Object();

        ls.add(obj1);
        ls.add(obj2);
        ls.add(obj3);
        ls.add(obj4);
        ls.add(obj5);

        HList sub = ls.subList(1, 3);
        assertEquals("Checking setup", 2, sub.size());

        assertEquals("Checking items in sublist",obj2,sub.get(0));
        assertEquals("Checking items in sublist",obj3,sub.get(1));

        sub.add(new Object());
        assertEquals("Added to parent", 6, ls.size());

        sub.remove(obj2);
        assertEquals("Removed at parent", 5, ls.size());
    }

    /**
     * @title Test of SubList method
     * @description This test tests the behaviour of subList() method on a non empty parent list.
     *              This test works with a sublist smaller than its parent list.
     *              This test check the correctness of set() method.
     * @expectedResults Changes made on the SubList must be visible on the parent list.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctness of method add().
     * @preConditions The list instance must be a new instance of List.
     * @postConditions The parent list of the tested sublist instance should be modified directly by the execution of the method.
     */
    @Test
    public void test_SubList_set() {
        HList ls = (HList) itt;
        for(int i=0;i<5;i++) ls.add(new Object());
        Object toSub = new Object();
        ls.add(toSub);
        for(int i=0;i<5;i++) ls.add(new Object());

        HList sub = ls.subList(4,7);

        Object newValue = new Object();
        Object old = sub.get(1);
        Object result = sub.set(1, newValue);
        assertEquals("Found old value", old, result);

        assertEquals("Set new value in parent", newValue, ls.get(5));
    }

    /**
     * @title Test of SubList method
     * @description This test tests the behaviour of subList() method on a non empty parent list.
     *              This test works with a sublist as big as its parent list.
     *              Changes made on the object returned by sublist method must be visible on the parent list.
     * @expectedResults Changes made on the SubList must be visible on the parent list.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctness of methods
     *               isEmpty(), add(), size(), remove(), equals().
     * @preConditions The list instance must be a new instance of List.
     * @postConditions The parent list of the tested sublist instance should be modified directly by the execution of the method.
     */
    @Test
    public void test_SubList_full() {
        HList ls = (HList) itt;
        ls.add(new Object());
        HList sub = ls.subList(0, ls.size());

        assertEquals("Sublist size == list size", 1, sub.size());
        assertEquals("Check consistency", sub, ls);

        sub.add(new Object());
        assertEquals("Added to parent", 2, ls.size());
        assertEquals("Checking consistency", sub,ls);

        sub.remove(0);
        assertEquals("Removed in parent", 1, ls.size());
        assertEquals("Checking consistency", sub,ls);
    }

    /**
     * @title Test of SubList method
     * @description This test tests the behaviour of subList() method on a non empty parent list.
     *              This test works with a sublist smaller than its parent list.
     *              This test check the correctness of indexOf and lastIndexOf
     * @expectedResults Changes made on the SubList must be visible on the parent list.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctness of method add().
     * @preConditions The list instance must be a new instance of List.
     * @postConditions The parent list of the tested sublist instance should be modified directly by the execution of the method.
     */
    @Test
    public void test_SubList_IndexMethods() {
        HList ls = (HList) itt;

        Object target = new Object();
        ls.add(new Object());
        ls.add(target);
        ls.add(new Object());
        ls.add(target);
        ls.add(new Object());

        HList sub = ls.subList(1, 4);
        assertEquals("indexOf", 0, sub.indexOf(target));
        assertEquals("lastIndexOf", 2, sub.lastIndexOf(target));
    }

    /**
     * @title Test of SubList method
     * @description This test tests the behaviour of subList() method on a non empty parent list.
     *              This test works with a sublist smaller than its parent list.
     *              Changes made on the object returned by sublist method must be visible on the parent list.
     *              This test clears the sublist and checks if this operation has effects in parent list
     * @expectedResults Changes made on the SubList must be visible on the parent list.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctness of methods
     *               isEmpty(), add(), size(), remove(), equals().
     * @preConditions The list instance must be a new instance of List.
     * @postConditions The parent list of the tested sublist instance should be modified directly by the execution of the method.
     */
    @Test
    public void test_SubList_clear() {
        HList ls = (HList) itt;
        Object saved = new Object();
        ls.add(new Object());
        ls.add(new Object());
        ls.add(new Object());
        ls.add(new Object());
        ls.add(new Object());
        ls.add(new Object());
        ls.add(new Object());
        ls.add(saved);
        HList sub = ls.subList(0, ls.size()-1);
        sub.clear();
        assertEquals("Check parent size", 1, ls.size());
        assertEquals("Saved is still present in parent", saved, ls.get(0));
    }

}
