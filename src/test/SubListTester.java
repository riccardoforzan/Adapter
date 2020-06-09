package test;

import adapters.ListAdapter;
import interfaces.HCollection;
import interfaces.HList;
import org.junit.Before;

public class SubListTester extends ListTester {

    @Before
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
