package test;

import adapters.ListAdapter;
import interfaces.HCollection;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ListAdapterTester extends CollectionTester{

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
     * @title Test of add(object) method
     * @description Test adding an object already contained in the collection
     * @expectedResults true, the object is added
     * @preConditions the object to add must be already added
     */
    @Test
    public void test_addDuplicate() {
        Object toAdd = new Object();
        itt.add(toAdd);
        assertTrue("Adding a new element on an empty collection", itt.add(toAdd));
    }

    /**
     * @title Test of toArray() method
     * @description Test toArray() method , the behavior depends:
     * @expectedResults for ListAdapter the order is defined, so the returned array must have the same order of the list
     * @dependencies uses ListAdapter.add(element) to setup the object
     * @preConditions list must not be empty
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
     * @title Test of toArray(Object[] a) method
     * @description Test toArray(Object[] a) method giving as parameter an array that array.length == collection.size(), the behavior depends:
     * @expectedResults for ListAdapter the order is defined, so the returned array must have the same order of the list
     * @expectedResults for SetAdapter the order is not defined, so the returned array has an undefined order
     * @expectedResults in both cases it uses to return the array given as parameter
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
     * @title Test of toArray(Object[] a) method
     * @description Test toArray(Object[] a) method giving an array smaller than the collection's size, the behavior depends:
     * @expectedResults for ListAdapter the order is defined, so the returned array must have the same order of the list
     *                  in both cases it allocates a NEW array of the same length as the size of the collection
     * @dependencies  uses ListAdapter.add(element) to setup the object and uses ListAdapter.toArray() to check correctness
     * @preConditions list must not be empty
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
     * @title Test of toArray(Object[] a) method
     * @description Test toArray(Object[] a) method giving an array larger than the collection's size, the behavior depends:
     * @expectedResults for ListAdapter the order is defined, so the returned array must have the same order of the list
     * @expectedResults for SetAdapter the order is not defined, so the returned array has an undefined order
     * @expectedResults in both cases it uses to return the array given as a parameter, with the empty positions set at null
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
     * @title Test add(index,element) method
     * @description Testing some insertion using add(index,element)
     * @expectedResults Successful insertions on specified positions
     * @dependencies uses the method ListAdapter.get(index) and ListAdapter.size() to check correctness
     * @preConditions list must be empty
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
     * @title Testing add(index,element) throws IndexOutOfBoundException
     * @description Test the case in which the method throws IndexOutOfBoundException
     * @expectedResults IndexOutOfBoundException thrown
     * @preConditions List where insertions are made must be empty
     */
    @Test
    public void check_AddIndex_ioobe(){
        ListAdapter la = (ListAdapter) itt;
        assertThrows("Inserting in position >= size())",IndexOutOfBoundsException.class, () -> la.add(5,new Object()));
        assertThrows("Inserting in position <0",IndexOutOfBoundsException.class, () -> la.add(-1,new Object()));
    }

    /**
     * @title Testing add(index,element) throws NullPointerException
     * @description Test the case in which the method throws NullPointerException
     * @expectedResults NullPointerException thrown
     */
    @Test
    public void check_AddIndex_npe(){
        ListAdapter la = (ListAdapter) itt;
        assertThrows("Inserting a null value",NullPointerException.class, () -> la.add(0,null));
    }

    /**
     * @title Test addAll(index,collection) method
     * @description Testing insertion using addAll(index,collection)
     * @expectedResults Successful insertions of given collection on specified position
     * @dependencies uses the method ListAdapter.add(element) to set up the list
     *               uses the method ListAdapter.get(index) and ListAdapter.size() to check correctness
     * @preConditions list contains 1 element
     */
    @Test
    public void test_AddAllIndex(){
        ListAdapter la = (ListAdapter) itt;

        Object alreadyInside = new Object();
        la.add(alreadyInside);

        ListAdapter given = (ListAdapter) createNotEmptyCollection();

        assertTrue("Adding elements on head of collection",la.addAll(1,given));
        assertEquals("Checking size",3,la.size());

        boolean result = la.get(0) == given.get(0) && la.get(1) == given.get(1) && la.get(2) == alreadyInside;
        assertTrue("Checking result correctness",result);
    }

    /**
     * @title Test addAll(index,collection) method
     * @description Testing insertion of an empty using addAll(index,collection)
     * @expectedResults False, the collection has not been changed after invocation
     * @preConditions list must be empty
     */
    @Test
    public void test_AddAllIndex_void(){
        ListAdapter la = (ListAdapter) itt;
        HCollection given = createEmptyCollection();
        assertFalse("Collection is not changed",la.addAll(given));
    }

    /**
     * @title Testing add(index,element) throws IndexOutOfBoundException
     * @description Test the case in which the method throws IndexOutOfBoundException
     * @expectedResults IndexOutOfBoundException thrown
     * @preConditions List where insertions are made must be empty
     */
    @Test
    public void check_AddAllIndex_ioobe(){
        ListAdapter la = (ListAdapter) itt;
        assertThrows("Inserting in position >= size())",IndexOutOfBoundsException.class, () -> la.add(5,new Object()));
        assertThrows("Inserting in position <0",IndexOutOfBoundsException.class, () -> la.add(-1,new Object()));
    }

    /**
     * @title Testing add(index,element) throws NullPointerException
     * @description Test the case in which the method throws NullPointerException
     * @expectedResults NullPointerException thrown
     */
    @Test
    public void check_AddAllIndex_npe(){
        ListAdapter la = (ListAdapter) itt;
        assertThrows("Inserting a null value",NullPointerException.class, () -> la.add(0,null));
    }

    /**
     * @title Test indexOf method
     * @description Testing IndexOf method looking for an object in an empty list
     * @expectedResults -1 not found
     * @preConditions List must be empty
     */
    @Test
    public void test_IndexOf_empty(){
        ListAdapter la = (ListAdapter) itt;
        Object toFind = new Object();
        assertEquals("Looking for an object in an empty collection",-1,la.indexOf(toFind));
    }

    /**
     * @title Test indexOf method
     * @description Testing IndexOf method looking for an object contained in a list
     * @expectedResults object found in the list
     * @dependencies add() method to setup and get() method to check correctness
     * @preConditions
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
     * @title Test indexOf method
     * @description Testing IndexOf method looking for the first index of an object contained in a list
     * @expectedResults first position containing the object found in the list
     * @dependencies add() method to setup and get() method to check correctness
     * @preConditions
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
     * @title Test indexOf method
     * @description Testing IndexOf method looking for an object not present in a non empty list
     * @expectedResults The object is not found
     * @dependencies method add() is used to setup the list and contains() is used to check correctness
     * @preConditions
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
     * @title Test indexOf throws NullPointerException
     * @description Test the case in which the method throws NullPointerException
     * @expectedResults NullPointerException thrown
     */
    @Test
    public void check_IndexOf_npe(){
        ListAdapter la = (ListAdapter) itt;
        assertThrows("Testing if NullPointerException is thrown",NullPointerException.class, () -> la.indexOf(null));
    }

    /**
     * @title Test lastIndexOf method
     * @description Testing lastIndexOf method looking for an object in an empty list
     * @expectedResults -1 not found
     * @preConditions List must be empty
     */
    @Test
    public void test_lastIndexOf_empty(){
        ListAdapter la = (ListAdapter) itt;
        Object toFind = new Object();
        assertEquals("Looking for an object in an empty collection",-1,la.lastIndexOf(toFind));
    }

    /**
     * @title Test last IndexOf method
     * @description Testing lastIndexOf method looking for an object contained in a list
     * @expectedResults object found in the list
     * @dependencies add() method to setup and get() method to check correctness
     * @preConditions
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
     * @title Test lastIndexOf method
     * @description Testing lastIndexOf method looking for the last index of an object contained in a list
     * @expectedResults last position containing the object found in the list
     * @dependencies add() method to setup and get() method to check correctness
     * @preConditions
     */
    @Test
    public void test_lastIndexOf_first(){
        ListAdapter la = (ListAdapter) itt;

        Object toFind = new Object();
        la.add(toFind);
        la.add(toFind);

        int indexResult = la.lastIndexOf(toFind);

        assertEquals("Checking returns the last",1,indexResult);
    }

    /**
     * @title Test lastIndexOf method
     * @description Testing lastIndexOf method looking for an object not present in a non empty list
     * @expectedResults The object is not found
     * @dependencies method add() is used to setup the list and contains() is used to check correctness
     * @preConditions
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
     * @title Test lastIndexOf throws NullPointerException
     * @description Test the case in which the method throws NullPointerException
     * @expectedResults NullPointerException thrown
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
     * @title Test remove method throws IndexOutOfBoundException
     * @description Test if remove method throws IndexOutOfBoundException
     * @expectedResults throws IndexOutOfBoundException
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
     * @title Test set method throws NullPointerException
     * @description Test if set method throws NullPointerException
     * @expectedResults throws NullPointerException
     * @dependencies method add() is used to setup the invocation
     */
    @Test
    public void check_set_npe(){
        ListAdapter la = (ListAdapter) itt;
        la.add(new Object());
        assertThrows("Setting position with null",NullPointerException.class, () -> la.set(1,null));
    }

    /**
     * @title Test set method throws IndexOutOfBoundException
     * @description Test if set method throws IndexOutOfBoundException
     * @expectedResults throws IndexOutOfBoundException
     */
    @Test
    public void check_set_ioobe(){
        ListAdapter la = (ListAdapter) itt;
        assertThrows("Setting position < 0",IndexOutOfBoundsException.class, () -> la.set(-1,new Object()));
        assertThrows("Setting position > size()",IndexOutOfBoundsException.class, () -> la.set(1,new Object()));
    }

    /**
     * TODO: Implement the test of ListIterator and SubList
     */

    //TEST LIST ITERATOR

    /**
     * @title
     * @description
     * @expectedResults
     * @actualResult
     * @dependencies
     * @preConditions
     * @postConditions
     */
    @Test
    public void test_listIterator_nextIndex(){}

    /**
     * @title
     * @description
     * @expectedResults
     * @actualResult
     * @dependencies
     * @preConditions
     * @postConditions
     */
    @Test
    public void test_listIterator_hasPrevious(){}
    /**
     * @title
     * @description
     * @expectedResults
     * @actualResult
     * @dependencies
     * @preConditions
     * @postConditions
     */
    @Test
    public void test_listIterator_previous(){}

    /**
     * @title
     * @description
     * @expectedResults
     * @actualResult
     * @dependencies
     * @preConditions
     * @postConditions
     */
    @Test
    public void test_listIterator_previousIndex(){}

    /**
     * @title
     * @description
     * @expectedResults
     * @actualResult
     * @dependencies
     * @preConditions
     * @postConditions
     */
    @Test
    public void test_listIterator_add(){}

    /**
     * @title
     * @description
     * @expectedResults
     * @actualResult
     * @dependencies
     * @preConditions
     * @postConditions
     */
    @Test
    public void test_listIterator_set(){}

    //TEST SUBLIST

    /**
     * @title
     * @description
     * @expectedResults
     * @actualResult
     * @dependencies
     * @preConditions
     * @postConditions
     */
    @Test
    public void testSubList(){}

}
