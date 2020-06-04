package test;

import interfaces.HCollection;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test of the methods shared by all the implementation of HCollection Interface.
 * This class tests only the common behavior and does not distinguish between ListAdapter or SetAdapter.
 * ListAdapter and SetAdapter DOES NOT ALLOW insertion of null values.
 * Specific test are demanded to respective test class.
 */
public abstract class CollectionTester{

    //HCollection instance to test
    protected HCollection itt;

    /**
     * @title Test of isEmpty() method
     * @expectedResults a HCollection just created must be empty
     */
    @Test
    public void test_isEmpty() {
        assertEquals("New collection must be empty",true,itt.isEmpty());
    }

    /**
     * @title Test of size() method
     * @expectedResults the size of a HCollection just created must be 0
     */
    @Test
    public void test_size() {
        assertEquals("New collection size must be 0, it must be empty",0,itt.size());
    }

    /**
     * @title Test of add(object) method
     * @description Test adding an object to an empty collection
     * @expectedResults true, the object is added
     */
    @Test
    public void test_add() {
        Object toAdd = new Object();
        assertEquals("Adding a new element on an empty colleciton",true,itt.add(toAdd));
    }

    /**
     * @title Test if add(Object o) throws NullPointerException
     * @description the method add(Object o) throws NullPointerException if o==null
     * @expectedResults throws NullPointerException
     */
    @Test
    public void check_add_npe(){
        assertThrows("Null parameter for value o given to add(Object o)",NullPointerException.class, () -> {
            itt.add(null);
        });
    }

    /**
     * @title Test consistency of size() with isEmpty()
     * @description Test the behavior of those size() and isEmpty()
     * @expectedResults size()==0 if isEmpty() returns true
     * @dependencies uses methods size() and isEmpty()
     * @preConditions the collection on which it is invoked must be not empty
     */
    @Test
    public void check_consistencySizeIsEmpty(){
        itt.add(new Object());
        assertNotEquals("Collection ins't empty",true,itt.isEmpty());
        assertNotEquals("Collection isn't empty, so size !=0 ",0,itt.size());
    }

    /**
     * @title Test of clear() method
     * @expectedResults The collection must be empty after invocation
     * @dependencies uses the method isEmpty() to check correctness
     * @preConditions the collection on which it is invoked must be not empty
     */
    @Test
    public void test_clearOnEmpty() {
        itt.add(new Object());
        itt.clear();
        assertEquals("Collection now must be empty",true,itt.isEmpty());
    }

    /**
     * @title Test of contains() method
     * @description Tests the correctness of contains() method
     * @expectedResults true, the object was previously added
     * @dependencies uses the add(Object) method
     * @preConditions the object to find must be added to the collection
     */
    @Test
    public void test_contains() {
        Object toFind = new Object();
        itt.add(toFind);
        assertEquals("The object is found on the collection",true,itt.contains(toFind));
    }

    /**
     * @title Test of contains() method
     * @description Tests the correctness of contains() method
     * @expectedResults false, it's invoked on an empty collection
     * @preConditions collection must be empty
     */
    @Test
    public void test_containsNot() {
        Object toFind = new Object();
        assertEquals("The object isn't found on the collection",false,itt.contains(toFind));
    }

    /**
     * @title Test if contains(Object o) throws NullPointerException
     * @description the method contains(Object o) throws NullPointerException if o==null
     * @expectedResults throws NullPointerException
     */
    @Test
    public void check_contains_npe(){
        assertThrows("Null parameter for value o given to contains(Object o)",NullPointerException.class, () -> {
            itt.contains(null);
        });
    }

    /**
     * @title Test of remove() method
     * @description Tests the correctness of remove() method
     * @expectedResults true, the object is removed and the collection is changed
     * @dependencies uses the add() method
     * @preConditions the object to remove belongs to the collection
     */
    @Test
    public void test_remove() {
        Object toRemove = new Object();
        itt.add(toRemove);
        assertEquals("The object is removed",true,itt.remove(toRemove));
    }

    /**
     * @title Test of remove() method
     * @description Tests the correctness of remove() method when called with an object that does not belong to the collection
     * @expectedResults false, the collection is not changed
     * @preConditions the collection does not contain the specified value
     */
    @Test
    public void test_removeNotPresent() {
        Object toRemove = new Object();
        assertEquals("The object is not removed",false,itt.remove(toRemove));
    }

    /**
     * @title Test if remove(Object o) throws NullPointerException
     * @description the method remove(Object o) throws NullPointerException if o==null
     * @expectedResults throws NullPointerException
     */
    @Test
    public void check_remove_npe(){
        assertThrows("Null parameter for value o given to remove(Object o)",NullPointerException.class, () -> {
            itt.remove(null);
        });
    }

    /**
     * @title Test of add(), contains() and remove()
     * @description test the common behavior of the collection when adding, checking and removing an object
     * @expectedResults the collection adds, finds and removes the object
     * @dependencies uses the method add(), remove() and contains()
     * @preConditions the collection must be empty
     */
    @Test
    public void  check_ARC(){
        Object obj = new Object();
        assertEquals("The object is added to the collection",true,itt.add(obj));
        assertEquals("The object is contained in the collection",true,itt.contains(obj));
        assertEquals("The object is removed from the collection",true,itt.remove(obj));
        assertEquals("The object is not contained in the collection",false,itt.contains(obj));
    }


    /**
     * @title Test if toArray(Object[] a) throws NullPointerException
     * @description the method toArray(Object[] a) throws NullPointerException if a==null
     * @expectedResults throws NullPointerException
     */
    @Test
    public void check_toArrayGivenType_npe(){
        assertThrows("Null parameter for value a given to toArray(Object[] a)",NullPointerException.class, () -> {
            itt.toArray(null);
        });
    }

    /**
     * @title Test if addAll(HCollection c) throws NullPointerException
     * @description the method addAll(HCollection c) throws NullPointerException if c==null
     * @expectedResults throws NullPointerException
     */
    public void check_addAll_npe(){
        assertThrows("Null parameter for value a given to toArray(Object[] a)",NullPointerException.class, () -> {
            itt.addAll(null);
        });
    }

    /**
     * @title Test if containsAll(HCollection c) throws NullPointerException
     * @description the method containsAll(HCollection c) throws NullPointerException if c==null
     * @expectedResults throws NullPointerException
     */
    public void check_containsAll_npe(){
        assertThrows("Null parameter for value a given to toArray(Object[] a)",NullPointerException.class, () -> {
            itt.containsAll(null);
        });
    }

    /**
     * @title Test if removeAll(HCollection c) throws NullPointerException
     * @description the method removeAll(HCollection c) throws NullPointerException if c==null
     * @expectedResults throws NullPointerException
     */
    public void check_removeAll_npe(){
        assertThrows("Null parameter for value a given to toArray(Object[] a)",NullPointerException.class, () -> {
            itt.removeAll(null);
        });
    }

    /**
     * @title Test if retainAll(HCollection c) throws NullPointerException
     * @description the method retainAll(HCollection c) throws NullPointerException if c==null
     * @expectedResults throws NullPointerException
     */
    public void check_retainAll_npe(){
        assertThrows("Null parameter for value a given to toArray(Object[] a)",NullPointerException.class, () -> {
            itt.retainAll(null);
        });
    }


}
