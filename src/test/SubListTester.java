package test;

import adapters.ListAdapter;
import interfaces.HCollection;
import interfaces.HList;
import org.junit.Before;

/**
 * This is the test class for ListAdapter.subList() method.
 * This test class is a concrete implementation of ListTester, tests that the returned HList given by the invocation
 * of ListAdapter.subList() matches the behavior of a HList.
 *
 * <br>
 * <b>All documentation of tested method is in another file </b>
 *
 * <p>ClassCastException, IllegalArgumentException are not tested because
 * the implementation ListAdapter does not throw these exception.
 */
public class SubListTester extends ListTester {

    @Override
    public void setup(){
        ListAdapter la = new ListAdapter();
        la.add(new Object());
        la.add(new Object());
        HList toTest = la.subList(1,1);
        super.itt = toTest;
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
