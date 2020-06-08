package test;

import interfaces.HCollection;

import adapters.SetAdapter;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * UnsupportedOperationException, ClassCastException, IllegalArgumentException are not tested because
 * the implementation of SetAdapter does not throw this exception
 */

public class SetAdapterTester extends CollectionTester {

    @Before
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
     * @preConditions the object to add must be already added
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
     * @expectedResults for ListAdapter the order is defined, so the returned array must have the same order of the list
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
     * @description Test toArray(Object[] a) method giving as parameter an array that array.length == collection.size(), the behavior depends:
     * @expectedResults for ListAdapter the order is defined, so the returned array must have the same order of the list
     * @expectedResults for SetAdapter the order is not defined, so the returned array has an undefined order
     * @expectedResults in both cases it uses to return the array given as parameter
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
     * @expectedResults for ListAdapter the order is defined, so the returned array must have the same order of the list
     * @expectedResults for SetAdapter the order is not defined, so the returned array has an undefined order
     * @expectedResults in both cases it allocates a NEW array of the same length as the size of the collection
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
     * @expectedResults for ListAdapter the order is defined, so the returned array must have the same order of the list
     * @expectedResults for SetAdapter the order is not defined, so the returned array has an undefined order
     * @expectedResults in both cases it uses to return the array given as a parameter, with the empty positions set at null
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