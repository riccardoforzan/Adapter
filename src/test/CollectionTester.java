package test;

import interfaces.HCollection;

import interfaces.HIterator;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * Shard test between Set and List
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
        assertTrue("New collection must be empty", itt.isEmpty());
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
        assertTrue("Adding a new element on an empty colleciton", itt.add(toAdd));
    }

    /**
     * @title Test of add(object) method
     * @description Test adding an object already contained in the collection
     * @expectedResults for ListAdapter true, the object is added
     * @expectedResults for SetAdapter false, the object is already contained
     * @preConditions the object to add must be already added
     */
    public abstract void test_addDuplicate();

    /**
     * @title Test if add(Object o) throws NullPointerException
     * @description the method add(Object o) throws NullPointerException if o==null
     * @expectedResults throws NullPointerException
     */
    @Test
    public void check_add_npe(){
        assertThrows("Null parameter for value o given to add(Object o)",NullPointerException.class, () -> itt.add(null));
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
        assertFalse("Collection ins't empty",itt.isEmpty());
        assertNotEquals("Collection isn't empty, so size !=0 ",0,itt.size());
    }

    /**
     * @title Test of clear() method
     * @expectedResults The collection must be empty after invocation
     * @dependencies uses the method isEmpty() to check correctness
     * @preConditions the collection on which it is invoked must be not empty
     */
    @Test
    public void test_clear() {
        itt.add(new Object());
        itt.clear();
        assertTrue("Collection now must be empty", itt.isEmpty());
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
        assertTrue("The object is found on the collection", itt.contains(toFind));
    }

    /**
     * @title Test of contains() method
     * @description Tests the correctness of contains() method
     * @expectedResults false, it's invoked on an empty collection
     * @preConditions collection must be empty
     */
    @Test
    public void test_containsNotPresent() {
        Object toFind = new Object();
        assertFalse("The object isn't found on the collection", itt.contains(toFind));
    }

    /**
     * @title Test if contains(Object o) throws NullPointerException
     * @description the method contains(Object o) throws NullPointerException if o==null
     * @expectedResults throws NullPointerException
     */
    @Test
    public void check_contains_npe(){
        assertThrows("Null parameter for value o given to contains(Object o)",NullPointerException.class, () -> itt.contains(null));
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

    /**
     * @title Test of Iterator.hasNext()
     * @description Test the iterator
     * @expectedResults false, the collection is empty
     * @dependencies HCollection.iterator()
     * @preConditions The collection must be empty
     */
    @Test
    public void test_Iterator_hasNext_EmptyCollection() {
        HIterator it = itt.iterator();
        assertFalse("Collection is empty", it.hasNext());
    }

    /**
     * @title Test of Iterator.hasNext()
     * @description Test the iterator
     * @expectedResults false, the collection is empty
     * @dependencies HCollection.iterator() and HCollection.add(Object a)
     * @preConditions The collection must be not empty
     */
    @Test
    public void test_Iterator_hasNext_NotEmptyCollection() {
        itt.add(new Object());
        HIterator it = itt.iterator();
        assertTrue("Collection has got some elements", it.hasNext());
    }

    /**
     * @title Test of Iterator.next()
     * @description Call of next on an empty collection throws NullPointerException
     * @expectedResults Throws NullPointerException
     * @dependencies HCollection.iterator()
     * @preConditions The collection must be empty
     */
    @Test
    public void test_Iterator_next_npe() {
        assertThrows("No element in this collection", NoSuchElementException.class, () -> itt.iterator().next());
    }

    /**
     * @title Test of Iterator.next()
     * @description Checking that iterator iterates on all the element of this collection
     * @expectedResults All elements are iterated
     * @dependencies HCollection.iterator() and HCollection.add(Object a)
     * @preConditions The collection must be empty
     */
    @Test
    public void test_Iterator_next() {
        HIterator it = itt.iterator();

        Object obj1 = new Object();
        Object obj2 = new Object();
        itt.add(obj1);
        itt.add(obj2);

        int items=0;
        int found_obj1 = 0;
        int found_obj2 = 0;
        while(it.hasNext()){
            Object tmp = it.next();
            items++;
            if(obj1.equals(tmp)){
                found_obj1++;
            }else if(obj2.equals(tmp)) {
                found_obj2++;
            }
        }

        boolean test;

        test = items==2;
        test &= found_obj1==1;
        test &= found_obj2==1;

        assertTrue("Iterator found all 3 elements", test);
    }

    /**
     * @title Test of Iterator.remove()
     * @description Checking that iterator removes element in the correct way
     * @expectedResults All elements are iterated
     * @dependencies HCollection.iterator(), HCollection.add(Object a) and HCollection.contains(Object o)
     * @preConditions The collection must be empty
     */
    @Test
    public void test_Iterator_remove() {
        HIterator it = itt.iterator();

        int initSize = itt.size();

        Object removed = it.next();
        it.remove();

        HIterator it2 = itt.iterator();
        int actualSize = 0;
        while(it2.hasNext()) actualSize++;
        assertEquals("Dimension of the collection decreased by 1", (actualSize + 1), initSize);
        assertTrue("The removed element does not belong to this collection", itt.contains(removed));
    }

    /**
     * @title Test of Iterator.remove()
     * @description Checking that invocation of Iterator.remove() not preceded by a call of Iterator.next() throws NullPointerException
     * @expectedResults NullPointerException
     * @dependencies HCollection.iterator()
     */
    public void test_Iterator_remove_npe(){
        assertThrows("Il metodo Remove() non può essere invocato sull'iteratore prima di aver invocato next", NoSuchElementException.class, () -> {
            HIterator it = itt.iterator();
            it.remove();
        });
    }

    /**
     * @title Test invocation of Iterator.remove() two times in a row
     * @description Checking that invocation of Iterator.remove() not preceded by a call of Iterator.next() throws NullPointerException
     * @expectedResults NullPointerException
     * @dependencies HCollection.iterator(), HCollection.add(Object a)
     */
    public void test_Iterator_remove_tt(){
        assertThrows("remove() can not be called two times in a row", NoSuchElementException.class, () -> {
            itt.add(new Object());
            itt.add(new Object());
            HIterator it = itt.iterator();
            it.remove();
            it.remove();
        });
    }

}
