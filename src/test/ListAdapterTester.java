package test;

import adapters.ListAdapter;
import interfaces.HCollection;
import interfaces.HList;
import interfaces.HListIterator;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * This is the test class for ListAdapter.
 * This test class is a concrete implementation of ListTester.
 *
 * <br>
 * <b>All documentation of tested method is in another file </b>
 *
 * <p>ClassCastException, IllegalArgumentException are not tested because
 * the implementation ListAdapter does not throw these exception.
 */
public class ListAdapterTester extends ListTester {

    @Override
    /**
     * Method to setup the object to be tested.
     */
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

}
