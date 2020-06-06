package test;

import interfaces.HCollection;

import interfaces.HIterator;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * Shared test between all HCollection implementation
 */
public abstract class CollectionTester implements IteratorTester{

    //HCollection instance to test
    protected HCollection itt;

    //METHOD THAT MUST BE OVERRIDE TO TEST *ALL METHOD

    /**
     * Method that returns a non empty collection
     * This method must be OVERRIDE by the concrete implementation of a HCollection Tester
     * @return a not empty HCollection
     */
    protected abstract HCollection createNotEmptyCollection();

    /**
     * Method that returns a non empty collection
     * This method must be OVERRIDE by the concrete implementation of a HCollection Tester
     * @return a HCollection with at least a null value inside
     */
    protected abstract HCollection createCollectionWithNull();

    /**
     * Method that creates an empty collection
     * This method must be OVERRIDE by the concrete implementation of a HCollection Tester
     * @return an empty HCollection
     */
    protected abstract HCollection createEmptyCollection();

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
     * @description Test adding an object already contained in the collection, the behavior depends:
     * @expectedResults for ListAdapter true, the object is added
     * @expectedResults for SetAdapter false, the object is already contained
     * @preConditions the object to add must be already added
     */
    @Test
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
        assertTrue("The object is removed", itt.remove(toRemove));
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
        assertFalse("The object is not removed", itt.remove(toRemove));
    }

    /**
     * @title Test if remove(Object o) throws NullPointerException
     * @description the method remove(Object o) throws NullPointerException if o==null
     * @expectedResults throws NullPointerException
     */
    @Test
    public void check_remove_npe(){
        assertThrows("Null parameter for value o given to remove(Object o)",NullPointerException.class, () -> itt.remove(null));
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
        assertTrue("The object is added to the collection", itt.add(obj));
        assertTrue("The object is contained in the collection", itt.contains(obj));
        assertTrue("The object is removed from the collection", itt.remove(obj));
        assertFalse("The object is not contained in the collection", itt.contains(obj));
    }

    /**
     * @title Test of toArray() method
     * @description Test toArray() method on a collection with 0 elements
     * @expectedResults an array with length == 0
     */
    @Test
    public void test_toArray_empty(){
        Object[] result = itt.toArray();
        assertEquals("Array with 0 elements",0,result.length);
    }

    /**
     * @title Test of toArray() method
     * @description Test toArray() method , the behavior depends:
     * @expectedResults for ListAdapter the order is defined, so the returned array must have the same order of the list
     * @expectedResults for SetAdapter the order is not defined, so the returned array has an undefined order
     */
    @Test
    public abstract void test_toArray_notEmpty();

    /**
     * @title Test of toArray(Object[] a) method
     * @description Test toArray(Object[] a) method , the behavior depends:
     * @expectedResults an array with length == 0
     */
    @Test
    public void test_toArrayGivenType_empty(){
        Object[] result = itt.toArray(new Object[0]);
        assertEquals("Array with 0 elements",0,result.length);
    }

    /**
     * @title Test of toArray(Object[] a) method
     * @description Test toArray(Object[] a) method giving as parameter an array that array.length == collection.size(), the behavior depends:
     * @expectedResults for ListAdapter the order is defined, so the returned array must have the same order of the list
     * @expectedResults for SetAdapter the order is not defined, so the returned array has an undefined order
     * @expectedResults in both cases it uses to return the array given as parameter
     */
    @Test
    public abstract void test_toArrayGivenType_notEmpty();

    /**
     * @title Test of toArray(Object[] a) method
     * @description Test toArray(Object[] a) method giving an array smaller than the collection's size, the behavior depends:
     * @expectedResults for ListAdapter the order is defined, so the returned array must have the same order of the list
     * @expectedResults for SetAdapter the order is not defined, so the returned array has an undefined order
     * @expectedResults in both cases it allocates a NEW array of the same length as the size of the collection
     */
    @Test
    public abstract void test_toArrayGivenType_small();

    /**
     * @title Test of toArray(Object[] a) method
     * @description Test toArray(Object[] a) method giving an array larger than the collection's size, the behavior depends:
     * @expectedResults for ListAdapter the order is defined, so the returned array must have the same order of the list
     * @expectedResults for SetAdapter the order is not defined, so the returned array has an undefined order
     * @expectedResults in both cases it uses to return the array given as a parameter, with the empty positions set at null
     */
    @Test
    public abstract void test_toArrayGivenType_large();

    /**
     * @title Test if toArray(Object[] a) throws NullPointerException
     * @description the method toArray(Object[] a) throws NullPointerException if a==null
     * @expectedResults throws NullPointerException
     */
    @Test
    public void check_toArrayGivenType_npe(){
        assertThrows("Null parameter for value a given to toArray(Object[] a)",NullPointerException.class, () -> itt.toArray(null));
    }

    //TEST addAll METHOD

    /**
     * @title Test of addAll(HCollection c) method
     * @description Test adding a void collection to test collection
     * @expectedResults false, the collection has not been modified because the given collection is empty
     */
    @Test
    public void test_addAll_Empty(){
        HCollection given = this.createEmptyCollection();
        assertFalse("Adding 0 elements from another collection",itt.addAll(given));
    }

    /**
     * @title Test of addAll(HCollection c) method
     * @description Test adding a non empty collection to test collection
     * @expectedResults true, the collection has been modified
     */
    @Test
    public void test_addAll_NotEmpty(){
        HCollection given = this.createNotEmptyCollection();
        assertTrue("Adding elements from another collection",itt.addAll(given));
    }

    /**
     * @title Test if addAll(HCollection c) throws NullPointerException
     * @description the method addAll(HCollection c) throws NullPointerException if an element of given collection is null
     * @expectedResults throws NullPointerException
     */
    @Test
    public void test_addAll_nullInside(){
        HCollection given = this.createCollectionWithNull();
        assertThrows("Null parameter inside given collection to addAll(HCollection c)",NullPointerException.class, () -> itt.addAll(given));
    }

    /**
     * @title Test if addAll(HCollection c) throws NullPointerException
     * @description the method addAll(HCollection c) throws NullPointerException if c==null
     * @expectedResults throws NullPointerException
     */
    @Test
    public void check_addAll_npe(){
        assertThrows("Null parameter as value given to addAll(HCollection c)",NullPointerException.class, () -> itt.addAll(null));
    }

    //TEST containsAll METHOD

    /**
     * @title Test of containsAll(HCollection c) method
     * @description Test containsAll(HCollection c) passing a void collection
     * @expectedResults true, an empty collection is contained in every other collection
     */
    @Test
    public void test_containsAll_Empty(){
        HCollection given = this.createEmptyCollection();
        assertTrue("An empty collection is contained in every other collection",itt.containsAll(given));
    }

    /**
     * @title Test of containsAll(HCollection c) method
     * @description Test containsAll(HCollection c) passing a not empty collection
     * @expectedResults true, all elements are contained because they were previuosly added
     * @dependencies addAll(HCollection) is used to grow this collection
     */
    @Test
    public void test_containsAll_NotEmpty(){
        HCollection given = this.createNotEmptyCollection();
        itt.addAll(given);
        assertTrue("All elements added from given collection are contained",itt.containsAll(given));
    }

    /**
     * @title Test of containsAll(HCollection c) method
     * @description Test containsAll(HCollection c) adding some, but not all, elements of a non empty collection
     * @expectedResults false, not all elements are inserted in the collection
     * @dependencies addAll(HCollection) is used to grow this collection
     */
    @Test
    public void test_containsAll_Part(){
        HCollection given = createNotEmptyCollection();
        itt.addAll(given);
        //Changing given after addAll
        given.add(new Object());
        assertFalse("Not all elements of given collection are contained",itt.containsAll(given));
    }

    /**
     * @title Test if containsAll(HCollection c) throws NullPointerException
     * @description the method addAll(HCollection c) throws NullPointerException if an element of given collection is null
     * @expectedResults throws NullPointerException
     */
    @Test
    public void test_containsAll_nullInside(){
        HCollection given = this.createCollectionWithNull();
        assertThrows("Null parameter inside given collection to containsAll(HCollection c)",NullPointerException.class, () -> itt.containsAll(given));
    }

    /**
     * @title Test if containsAll(HCollection c) throws NullPointerException
     * @description the method containsAll(HCollection c) throws NullPointerException if c==null
     * @expectedResults throws NullPointerException
     */
    @Test
    public void check_containsAll_npe(){
        assertThrows("Null parameter as value given to containsAll(HCollection c)",NullPointerException.class, () -> itt.containsAll(null));
    }

    //TEST removeAll METHOD

    /**
     * @title Test of removeAll(HCollection c) method
     * @description Test removeAll(HCollection c) passing a void collection
     * @expectedResults false, removing an empty collection does not modify the test collection
     */
    @Test
    public void test_removeAll_Empty(){
        HCollection given = this.createEmptyCollection();
        assertFalse("Removing an empty collection does not modify the test collection",itt.removeAll(given));
    }

    /**
     * @title Test of removeAll(HCollection c) method
     * @description Test removeAll(HCollection c) passing a not empty collection
     * @expectedResults true, collection has been modified deleting all objects previously added
     * @dependencies addAll(HCollection) is used to grow this collection and isEmpty() to check correctness
     */
    @Test
    public void test_removeAll_NotEmpty(){
        HCollection given = this.createNotEmptyCollection();
        itt.addAll(given);
        assertTrue("Collection has been modified",itt.removeAll(given));
        assertTrue("All elements are removed from the test collection",itt.isEmpty());
    }

    /**
     * @title Test of removeAll(HCollection c) method
     * @description Test removeAll(HCollection c) removing some, but not all, elements
     * @expectedResults true, collection has been modified after the call
     * @dependencies addAll(HCollection) is used to grow this collection,
     *               size() and contains(Object o) are used to check correctness
     */
    @Test
    public void test_removeAll_Part(){
        HCollection given = createNotEmptyCollection();
        itt.addAll(given);
        //Changing given after addAll
        Object stillPresent = new Object();
        itt.add(stillPresent);
        assertTrue("Collection has been modified",itt.removeAll(given));
        assertEquals("Collection still contain an element",1,itt.size());
        assertTrue("The object is still present",itt.contains(stillPresent));
    }

    /**
     * @title Test if removeAll(HCollection c) throws NullPointerException
     * @description the method addAll(HCollection c) throws NullPointerException if an element of given collection is null
     * @expectedResults throws NullPointerException
     */
    @Test
    public void test_removeAll_nullInside(){
        HCollection given = this.createCollectionWithNull();
        assertThrows("Null parameter inside given collection to removeAll(HCollection c)",NullPointerException.class, () -> itt.removeAll(given));
    }

    /**
     * @title Test if removeAll(HCollection c) throws NullPointerException
     * @description the method removeAll(HCollection c) throws NullPointerException if c==null
     * @expectedResults throws NullPointerException
     */
    @Test
    public void check_removeAll_npe(){
        assertThrows("Null parameter as value given to removeAll(HCollection c)",NullPointerException.class, () -> itt.removeAll(null));
    }

    //TEST retainAll METHOD

    /**
     * @title Test of retainAll(HCollection c) method
     * @description Test retainAll(HCollection c) passing a void collection
     * @expectedResults true, the collection has been modified and all elements have been removed
     * @dependencies isEmpty() used to check correctness
     */
    @Test
    public void test_retainAll_Empty(){
        HCollection given = this.createEmptyCollection();
        assertTrue("Collection has been modified",itt.retainAll(given));
        assertTrue("All elemnts have been removed",itt.isEmpty());
    }

    /**
     * @title Test of retainAll(HCollection c) method
     * @description Test retainAll(HCollection c) passing a not empty collection
     * @expectedResults false, test collection contains the same elements of the collection given as parameter to retain
     * @dependencies addAll(HCollection) is used to grow this collection
     */
    @Test
    public void test_retainAll_NotEmpty(){
        HCollection given = this.createNotEmptyCollection();
        itt.addAll(given);
        assertFalse("Collection has not been modified",itt.retainAll(given));
    }

    /**
     * @title Test of retainAll(HCollection c) method
     * @description Test retainAll(HCollection c) adding a collection and then saving a subset of the added collection
     * @expectedResults true, collection has been modified
     * @dependencies addAll(HCollection c) and add(Object o) and clear() are used to grow the collection,
     *               size() and contains(Object o) are used to check correctness
     */
    @Test
    public void test_retainAll_Part(){
        HCollection given = createNotEmptyCollection();
        Object toSave = new Object();
        given.add(toSave);
        itt.addAll(given);

        //Removing all objects and adding the one to save
        given.clear();
        given.add(toSave);

        assertFalse("Collection has been modified",itt.retainAll(given));
        assertEquals("The collection contains 1 element",1,itt.size());
        assertTrue("Contains the element to save",itt.contains(toSave));
    }

    /**
     * @title Test if retainAll(HCollection c) throws NullPointerException
     * @description the method addAll(HCollection c) throws NullPointerException if an element of given collection is null
     * @expectedResults throws NullPointerException
     */
    @Test
    public void test_retainAll_nullInside(){
        HCollection given = this.createCollectionWithNull();
        assertThrows("Null parameter inside given collection to retainAll(HCollection c)",NullPointerException.class, () -> itt.retainAll(given));
    }

    /**
     * @title Test if retainAll(HCollection c) throws NullPointerException
     * @description the method retainAll(HCollection c) throws NullPointerException if c==null
     * @expectedResults throws NullPointerException
     */
    @Test
    public void check_retainAll_npe(){
        assertThrows("Null parameter as value given to retainAll(HCollection c)",NullPointerException.class, () -> itt.retainAll(null));
    }

    /**
     * @title Test of addAll(), containsAll() and removeAll()
     * @description test the common behavior of the collection when adding, checking and removing a collection
     * @expectedResults the collection adds, finds and removes the objects
     * @dependencies uses the method addAll(), removeAll() and containsAll()
     */
    @Test
    public void  check_ARC_All(){
        HCollection given = createNotEmptyCollection();
        assertTrue("Test collection has been modified", itt.addAll(given));
        assertTrue("All objects are contained in the test collection", itt.containsAll(given));
        assertTrue("All objects are removed in the test collection", itt.removeAll(given));
        assertFalse("All objects are not contained in the test collection", itt.containsAll(given));
    }

    //TEST ITERATOR
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
        assertThrows("Method remove can not be called if not preceded by a call to next()", NoSuchElementException.class, () -> {
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
