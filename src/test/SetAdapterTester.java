package test;

import interfaces.HCollection;

import adapters.SetAdapter;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.NoSuchElementException;

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
     * @title Test of add(object) method
     * @description Test adding an object already contained in the collection
     * @expectedResults false, the object is already part of the collection
     * @preConditions the object to add must be already added
     */
    @Test
    public void test_addDuplicate() {
        Object toAdd = new Object();
        itt.add(toAdd);
        assertEquals("Inserting the same element for the second time, not allowed",false, itt.add(toAdd));
    }

    /**
     * @title Test of toArray() method
     * @description Test toArray() method , the behavior depends:
     * @expectedResults for ListAdapter the order is defined, so the returned array must have the same order of the list
     * @expectedResults for SetAdapter the order is not defined, so the returned array has an undefined order
     */
    @Override
    public void test_toArray_notEmpty() {

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

    }

    /**
     * Method that returns a non empty collection
     * This method must be OVERRIDE by the concrete implementation of a HCollection Tester
     *
     * @return a not empty HCollection
     */
    @Override
    protected HCollection createNotEmptyCollection() {
        return null;
    }

    /**
     * Method that returns a non empty collection
     * This method must be OVERRIDE by the concrete implementation of a HCollection Tester
     *
     * @return a HCollection with at least a null value inside
     */
    @Override
    protected HCollection createCollectionWithNull() {
        return null;
    }

    /**
     * Method that creates an empty collection
     * This method must be OVERRIDE by the concrete implementation of a HCollection Tester
     *
     * @return an empty HCollection
     */
    @Override
    protected HCollection createEmptyCollection() {
        return null;
    }

}