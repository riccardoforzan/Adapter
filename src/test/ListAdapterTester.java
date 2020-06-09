package test;

import adapters.ListAdapter;
import interfaces.HCollection;
import interfaces.HList;
import interfaces.HListIterator;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class ListAdapterTester extends ListTester {

    @Override
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
