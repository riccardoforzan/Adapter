package test;

import interfaces.HCollection;

import adapters.SetAdapter;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests HSet implementation.
 * This class contains tests that must be passed by each implementation of HSet that does not support null as
 * a valid argument.
 * Those test are obtained by the HSet interface contract.
 *
 * <br>
 * <b>All documentation of tested method is in another file </b>
 *
 * <p>ClassCastException, IllegalArgumentException are not tested because
 * the implementation SetAdapter does not throw these exception.
 */

public class SetAdapterTester extends CollectionTester {

    @Override
    public void setup(){
        itt = new SetAdapter();
    }

    /**
     * Method that returns a non empty collection
     * This method must be OVERRIDE by the concrete implementation of a HCollection Tester
     *
     * @return a not empty HCollection
     */
    @Override
    protected HCollection createNotEmptyCollection() {
        SetAdapter rv = new SetAdapter();
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
        return new SetAdapter();
    }

    /**
     * @title Test of add(object) method
     * @description Test adding an object already contained in the collection
     * @expectedResults false, the object is already part of the collection
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of add() method in the tested set.
     * @preConditions The test instance must be a new (and empty) instance of HSet.
     * @postConditions The test instance is directly modified by the execution of the method tested.
     *                 The test adds 1 element to the set.
     */
    @Test
    public void test_addDuplicate() {
        Object toAdd = new Object();
        itt.add(toAdd);
        assertFalse("Inserting the same element for the second time, not allowed", itt.add(toAdd));
    }

    /**
     * @title Test of toArray() method
     * @description Test toArray() method , the behavior depends:
     * @expectedResults for ListAdapter the order is defined, so the returned array must have the same order of the list.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of add() method in the tested set.
     * @preConditions The test instance must be a new (and empty) instance of HSet.
     * @postConditions The test instance is directly modified by the execution of the method tested.
     *                 The test adds 2 element to the set.
     */
    @Override
    public void test_toArray_notEmpty() {
        Object obj1 = new Object();
        Object obj2 = new Object();
        itt.add(obj1);
        itt.add(obj2);

        Object[] result = itt.toArray();
        assertEquals("Checking expected length",2,result.length);

        boolean found1=false, found2=false;
        for(int i=0;i<result.length;i++){
            if(result[i].equals(obj1)) found1 = true;
            if(result[i].equals(obj2)) found2 = true;
        }
        assertTrue("All objects found in the array",found1&&found2);
    }

    /**
     * @title Test of toArray(Object[] a) method
     * @description Test toArray(Object[] a) method giving as parameter an array that array.length == collection.size()
     * @expectedResults In SetAdapter the order is not defined, so the returned array has an undefined order.
     *                  It uses to return the array given as parameter.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of add() method in the tested set.
     * @preConditions The test instance must be a new (and empty) instance of HSet.
     * @postConditions The test instance is directly modified by the execution of the method tested.
     *                 The test adds 2 element to the set.
     */
    @Override
    public void test_toArrayGivenType_notEmpty() {
        Object obj1 = new Object();
        Object obj2 = new Object();
        itt.add(obj1);
        itt.add(obj2);

        Object[] toChange = new Object[2];
        itt.toArray(toChange);
        assertEquals("Checking length",2,toChange.length);

        boolean found1=false, found2=false;
        for(int i=0;i<toChange.length;i++){
            if(toChange[i].equals(obj1)) found1 = true;
            if(toChange[i].equals(obj2)) found2 = true;
        }
        assertTrue("All objects found in the array",found1&&found2);
    }

    /**
     * @title Test of toArray(Object[] a) method
     * @description Test toArray(Object[] a) method giving an array smaller than the collection's size, the behavior depends:
     * @expectedResults In SetAdapter the order is not defined, so the returned array has an undefined order.
     *                  It uses to return a new array and not the one given as parameter, as it is too small.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of add() method in the tested set.
     * @preConditions The test instance must be a new (and empty) instance of HSet.
     * @postConditions The test instance is directly modified by the execution of the method tested.
     *                 The test adds 2 element to the set.
     */
    @Override
    public void test_toArrayGivenType_small() {
        Object obj1 = new Object();
        Object obj2 = new Object();
        itt.add(obj1);
        itt.add(obj2);
        Object[] res = itt.toArray(new Object[0]);
        assertEquals("Checking length",2,res.length);

        boolean found1=false, found2=false;
        for(int i=0;i<res.length;i++){
            if(res[i].equals(obj1)) found1 = true;
            if(res[i].equals(obj2)) found2 = true;
        }
        assertTrue("All objects found in the array",found1&&found2);
    }

    /**
     * @title Test of toArray(Object[] a) method
     * @description Test toArray(Object[] a) method giving an array larger than the collection's size, the behavior depends:
     * @expectedResults In SetAdapter the order is not defined, so the returned array has an undefined order.
     *                  It uses to return the array given as a parameter, setting the cells with index < size() with
     *                  values on the set, and the following ones with null.
     * @actualResult As expected result.
     * @dependencies This test correctness depends on the correctness of
     *               the implementation of add() method in the tested set.
     * @preConditions The test instance must be a new (and empty) instance of HSet.
     * @postConditions The test instance is directly modified by the execution of the method tested.
     *                 The test adds 2 element to the set.
     */
    @Override
    public void test_toArrayGivenType_large(){
        Object obj1 = new Object();
        Object obj2 = new Object();
        itt.add(obj1);
        itt.add(obj2);

        Object[] toChange = new Object[4];
        itt.toArray(toChange);
        assertEquals("Checking length",4,toChange.length);

        int nullFound = 0;
        boolean found1=false, found2=false;
        for(int i=0;i<toChange.length;i++){
            if(toChange[i]==null) nullFound++;
            else if(toChange[i].equals(obj1)) found1 = true;
            else if(toChange[i].equals(obj2)) found2 = true;
        }
        assertTrue("All objects found in the array",found1&&found2);
        assertEquals("Checking null values",2,nullFound);
    }

}