package test;

import adapters.ListAdapter;
import interfaces.HCollection;
import interfaces.HIterator;
import interfaces.HMap;
import interfaces.HSet;

import adapters.MapAdapter;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests HMap implementation.
 * This class contains tests that must be passed by each implementation of HMap that does not support null as
 * a valid argument not as key, nor as value.
 * Those test are obtained by the HMap interface contract.
 *
 * <br>
 * <b>All documentation of tested method is in another file </b>
 *
 * <p>ClassCastException, IllegalArgumentException are not tested because
 * the implementation MapAdapter does not throw these exception.
 */
public class MapAdapterTester {

    private MapAdapter itt;

    /**
     * Method used to set a value to itt attribute on this class.
     */
    @Before
    public void setup(){
        itt = new MapAdapter();
    }

    /**
     * @title Test of get() method
     * @description This test tests the behaviour of get() method when is called on an empty Map
     * @expectedResults The map is expected to return null reference.
     *                  Null is the return value used to specify that the key is not contained.
     * @actualResult As expected result.
     * @dependencies The correctness of this test doesn't depends on the correctness of other map methods
     * @preConditions The map instance must be a new instance of HMap.
     * @postConditions The map instance is not modified by the call of this method.

     */
    @Test
    public void test_Get_empty() {
        assertNull("Invoked on an empty map", itt.get(new Object()));
    }

    /**
     * @title Test of get() method
     * @description This test tests the behaviour of get() method when is called using a not contained key as parameter
     * @expectedResults The map is expected to return null reference.
     *                  Null is the return value used to specify that the key is not contained.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method put()
     * @preConditions The map instance must be a new instance of HMap.
     * @postConditions The map instance is not modified by the call of this method.
     */
    @Test
    public void test_Get_notContained() {
        Object toAdd = new Object();
        itt.put(toAdd, toAdd);
        assertNull("Searching a key that is not present", itt.get(new Object()));
    }

    /**
     * @title Test of get() method
     * @description This test tests the behaviour of get() method when is called using a contained key as parameter
     * @expectedResults The map is expected to return the value associated with the specified key.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method put()
     * @preConditions The map instance must be a new instance of HMap.
     * @postConditions The map instance is not modified by the call of this method.
     */
    @Test
    public void test_Get_contained() {
        Object toAdd = new Object();
        itt.put(toAdd, toAdd);
        Object result = itt.get(toAdd);
        assertEquals("metodo invocato utilizzando come parametro una chiave presente", toAdd, result);
    }

    /**
     * @title Test of get() method
     * @description This test tests the behaviour of get() method when is called using a key which is not contained,
     *              but has the same hash of another key inserted.
     * @expectedResults The map is expected to return a null reference,
     *                  because the comparison is performed using equals() method of key objects.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of methods put().
     * @preConditions The map instance must be a new instance of HMap.
     * @postConditions The map instance is not modified by the call of this method.
     */
    @Test
    public void test_Get_hash() {
        itt.put("Aa","Aa");
        Object result = itt.get("BB");
        assertNull("Test using equals() if both has the same hash", result);
    }

    /**
     * @title Test of get() method
     * @description Tests if invocation of get(Object o) with null value for o, throws NullPointerException.
     * @expectedResults The class is expected to throw a NullPointerException.
     * @actualResult As expected result.
     * @dependencies The correctness of this test doesn't depends on the correctness of other map methods
     * @preConditions The map instance must be a new instance of HMap.
     * @postConditions The map instance is not modified by the call of this method.
     */
    @Test
    public void test_Get_exceptions() {
        assertThrows("Null parameter given to get", NullPointerException.class, () -> itt.get(null));
    }

    /**
     * @title Test of get() method
     * @description This test tests the consistency between the methods containsKey() and get().
     *              This implementation of the map doesn't accept null not as key, nor as Value.
     *              A null value is returned only when the key is not contained in the map
     * @expectedResults A consistent behaviour between containsKey() and get() methods is expected.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method put()
     * @preConditions The map instance must be a new instance of HMap.
     * @postConditions The map instance is not modified by the call of this method.
     */
    @Test
    public void test_Get_containsKey() {
        Object toAdd = new Object();
        itt.put(toAdd,toAdd);

        Object searched = new Object();
        boolean result = itt.get(searched) == null && !itt.containsKey(searched);
        assertTrue("Check consistency when not found", result);

        result = itt.get(toAdd) == null && !itt.containsKey(toAdd);
        assertFalse("Check consistency when found", result);
    }

    /**
     * @title Test of put() method
     * @description This test tests the behaviour of method put() when is called on an empty Map.
     * @expectedResults The map should to insert the new entry (as it is empty) and return a null reference.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method isEmpty().
     * @preConditions The set instance must be a new instance of Map.
     * @postConditions The set instance should be modified by the call of the tested method
     */
    @Test
    public void test_Put_empty() {
        Object toAdd = new Object();
        assertNull("Add", itt.put(toAdd, toAdd));
        assertFalse("Map now has 1 element", itt.isEmpty());
    }

    /**
     * @title Test of put() method
     * @description This test tests the behaviour of method put() when the map is not empty but it does not contain
     *              already the object that this method tries to insert.
     * @expectedResults The map should to insert the new entry and return a null reference.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method size().
     * @preConditions The set instance must be a new instance of Map.
     * @postConditions The set instance should be modified by the call of the tested method
     */
    @Test
    public void test_Put_notContained() {
        itt.put(new Object(),new Object());
        Object toAdd = new Object();
        assertNull("Add", itt.put(toAdd, toAdd));
        assertEquals("Map now has 1 element",2, itt.size());

    }

    /**
     * @title Test of put() method
     * @description This test tests the behaviour of method put() when is called on a not-empty Map and the entry is already contained in the map.
     * @expectedResults The map is expected to update the value associated with that key.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of methods size() and get().
     * @preConditions The set instance must be a new instance of Map.
     * @postConditions The set instance should be modified by the call of the tested method
     */
    @Test
    public void test_Put_contained() {
        Object toAdd = new Object();
        Object newValue = new Object();
        itt.put(toAdd, toAdd);

        assertEquals("Updating", toAdd, itt.put(toAdd, newValue));
        assertEquals("Size does not change", 1, itt.size());
        assertEquals("Updated value", newValue, itt.get(toAdd));
    }

    /**
     * @title Test of put() method
     * @description This test tests the behaviour of method put() when is called on a map that contains
     *              a key with the same hash of the key specified, but the value is different.
     * @expectedResults The map is expected to add the new entry because
     *                  the comparison is performed using equals() method of key objects.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method size().
     * @preConditions The map instance must be a new instance of HMap.
     * @postConditions The map instance should be modified by the call of this method.
     */
    @Test
    public void test_Put_hash() {
        itt.put("Aa", "Aa");
        Object result = itt.put("BB", "BB");
        assertNull("Adding an object with same hashcode for the key", result);
        assertEquals("Size increased", 2, itt.size());
    }

    /**
     * @title Test of put() method
     * @description Tests if invocation of put(Object key, Object value) with null value for at least 1 of the parametrs
     *              throws NullPointerException.
     * @expectedResults The class is expected to throw a NullPointerException.
     * @actualResult As expected result.
     * @dependencies The correctness of this test doesn't depends on the correctness of other map methods
     * @preConditions The map instance must be a new instance of HMap.
     * @postConditions The map instance is not modified by the call of this method.
     */
    @Test
    public void check_Put_npe() {
        assertThrows("Null key", NullPointerException.class, () -> itt.put(null,new Object()));
        assertThrows("Null value", NullPointerException.class, () -> itt.put(new Object().hashCode(), null));
        assertThrows("Both null", NullPointerException.class, () -> itt.put(null, null));
    }

    /**
     * @title Test of putAll method,
     * @description This test tests the behaviour of class Map when putAll() method is called using
     *              an empty map as parameter
     * @expectedResults The test map should not change.
     * @actualResult As expected result.
     * @dependencies  Depends on the correctness of method isEmpty()
     * @preConditions The map instance must be a new instance of Map.
     * @postConditions The map instance is not modified by the call of this method.
     */
    @Test
    public void test_PutAll_empty() {
        MapAdapter toAdd = new MapAdapter();
        itt.putAll(toAdd);
        assertTrue("Map should stay empty", itt.isEmpty());
    }

    /**
     * @title Test of putAll() method
     * @description This test tests the behaviour of method putAll() when is called using a map that contains
     *              entries not contained in the test map.
     * @expectedResults The map must add all the elements of the other map.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of methods size() and put().
     *               Methods containsKey() and containsValue() are used to check correctness.
     * @preConditions The map instance must be a new instance of HMap.
     * @postConditions The map instance is modified by the call of this method.
     */
    @Test
    public void test_PutAll_notContained() {
        MapAdapter other = new MapAdapter();
        String s1 = "A1";
        other.put(s1,s1);
        String s2 = "B2";
        other.put(s2,s2);
        itt.putAll(other);

        assertEquals("All elements of other added", 2, itt.size());
        assertTrue("Contains s1",itt.containsKey(s1) && itt.containsValue(s1));
        assertTrue("Contains s2",itt.containsKey(s2) && itt.containsValue(s2));
    }

    /**
     * @title Test of putAll() method
     * @description Tests if invocation of putAll(HCollection c) with null value for c, throws NullPointerException.
     * @expectedResults The class is expected to throw a NullPointerException.
     * @actualResult As expected result.
     * @dependencies The correctness of this test doesn't depends on the correctness of other map methods
     * @preConditions The map instance must be a new instance of HMap.
     * @postConditions The map instance is not modified by the call of this method.
     */
    @Test
    public void check_PutAll_npe() {
        assertThrows("Null parameter given as a collection", NullPointerException.class, () -> itt.putAll(null));
    }

    /**
     * @title Test of Map.containsKey() method
     * @description This test tests the behaviour of containsKey() method when is called on an empty Map
     * @expectedResults The map doesn't contain the searched key, map is empty.
     * @actualResult As expected result.
     * @dependencies The correctness of this test doesn't depends on the correctness of other map methods
     * @preConditions The map instance must be a new instance of HMap.
     * @postConditions The map instance is not modified by the call of this method.
     */
    @Test
    public void test_ContainsKey_empty() {
        assertFalse("An empty map does not contains keys", itt.containsKey(new Object()));
    }

    /**
     * @title Test of Map.containsKey() method
     * @description This test tests the behaviour of containsKey() method when is called on a non empty Map which does
     *              not contain the specified key.
     * @expectedResults The map isn't expected to contain the specified key.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method put()
     * @preConditions The map instance must be a new instance of HMap.
     * @postConditions The map instance is not modified by the call of this method.
     */
    @Test
    public void test_ContainsKey_notFound() {
        Object tp = new Object();
        itt.put(tp,tp);
        assertFalse("Key not found", itt.containsKey(new Object().hashCode()));
    }

    /**
     * @title Test of Map.containsKey() method
     * @description This test tests the behaviour of containsKey() method when is called on a non empty Map which contains
     *              the specified key.
     * @expectedResults The map is expected to contain the specified key.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method put()
     * @preConditions The map instance must be a new instance of HMap.
     * @postConditions The map instance is not modified by the call of this method.
     */
    @Test
    public void test_ContainsKey_found() {
        Object tp = new Object();
        itt.put(tp,tp);
        assertTrue("Key found", itt.containsKey(tp));
    }

    /**
     * @title Test of Map.containsKey() method
     * @description This test tests the behaviour of containsKey() method when is called on a Map which contains a
     *              the specified key value.
     * @expectedResults The map is expected to not contain the specified value,
     *                  because the comparison is performed using equals() method of key objects.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method put()
     * @preConditions The map instance must be a new instance of HMap.
     * @postConditions The map instance is not modified by the call of this method.
     */
    @Test
    public void test_ContainsKey_sameHash() {
        itt.put("Aa", new Object());
        assertFalse("Uses key.equals(), that String class override.", itt.containsKey("BB"));
    }

    /**
     * @title Test of Map.containsKey() method
     * @description Tests if invocation of containsKey(Object o) with null value for o, throws NullPointerException.
     * @expectedResults The class is expected to throw a NullPointerException.
     * @actualResult As expected result.
     * @dependencies The correctness of this test doesn't depends on the correctness of other map methods
     * @preConditions The map instance must be a new instance of HMap.
     * @postConditions The map instance is not modified by the call of this method.
     */
    @Test
    public void check_ContainsKey_npe() {
        assertThrows("Null value for object given", NullPointerException.class,() -> itt.containsKey(null));
    }

    /**
     * @title Test of Map.containsValue() method
     * @description This test tests the behaviour of containsValue() method when is called on an empty Map
     * @expectedResults The map is expected to not contain the specified value because it's empty.
     * @actualResult As expected result.
     * @dependencies The correctness of this test doesn't depends on the correctness of other map methods
     * @preConditions The map instance must be a new instance of HMap.
     * @postConditions The map instance is not modified by the call of this method.
     */
    @Test
    public void test_ContainsValue_empty() {
        assertFalse("una mappa vuota non contiene valori", itt.containsValue(new Object()));
    }

    /**
     * @title Test of Map.containsValue() method
     * @description This test tests the behaviour of containsValue() method when is called on a non empty Map which does
     *              not contain the specified value.
     * @expectedResults The map isn't expected to contain the specified key.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method put()
     * @preConditions The map instance must be a new instance of HMap.
     * @postConditions The map instance is not modified by the call of this method.
     */
    @Test
    public void test_ContainsValue_notFound() {
        Object toAdd = new Object();
        itt.put(toAdd, toAdd);
        assertFalse("Value not found", itt.containsValue(new Object()));
    }

    /**
     * @title Test of Map.containsValue() method
     * @description This test tests the behaviour of containsValue() method when is called on a non empty Map which
     *              contains the specified value.
     * @expectedResults The map is expected to contain the specified key.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method put()
     * @preConditions The map instance must be a new instance of HMap.
     * @postConditions The map instance is not modified by the call of this method.
     */
    @Test
    public void test_ContainsValue_found() {
        Object toAdd = new Object();
        itt.put(toAdd, toAdd);
        assertTrue("Value found", itt.containsValue(toAdd));
    }

    /**
     * @title Test of containsValue() method
     * @description Tests if invocation of containsValue(Object o) with null value for o, throws NullPointerException.
     * @expectedResults The class is expected to throw a NullPointerException.
     * @actualResult As expected result.
     * @dependencies The correctness of this test doesn't depends on the correctness of other map methods
     * @preConditions The map instance must be a new instance of HMap.
     * @postConditions The map instance is not modified by the call of this method.
     */
    @Test
    public void check_ContainsValue_npe() {
        assertThrows("Null value for given object", NullPointerException.class, () -> itt.containsValue(null));
    }

    /**
     * @title Test of remove() method
     * @description This test tests the behaviour of class Map when remove() method is called on an empty map.
     * @expectedResults The map is expected to return a null reference because the map is empty
     * @actualResult As expected result.
     * @dependencies The correctness of this test doesn't depends on the correctness of other map methods
     * @preConditions The map instance must be a new instance of HMap.
     * @postConditions The map instance is not modified by the call of this method.
     */
    @Test
    public void test_Remove_empty() {
        assertNull("Remove on empty map", itt.remove(new Object().hashCode()));
    }

    /**
     * @title Test of remove() method
     * @description This test tests the behaviour of class Map when remove() method is called on a map that does not
     *              contain the key given as a parameter.
     * @expectedResults The map is expected to return a null reference.
     * @actualResult As expected result.
     * @dependencies The correctness of this test depends on the correctness of put().
     *               The correctness of this method is checked using the method size().
     * @preConditions The map instance must be a new instance of HMap.
     * @postConditions The map instance is not modified by the call of this method.
     */
    @Test
    public void test_Remove_notContained() {
        Object toAdd = new Object();
        itt.put(toAdd, toAdd);
        assertNull("Removing an object not present", itt.remove(new Object()));
        assertEquals("Size does not change", 1, itt.size());
    }

    /**
     * @title Test of remove() method
     * @description This test tests the behaviour of class Map when remove() method is called on a map that
     *              contains the key given as a parameter.
     * @expectedResults The map is expected to remove the entry and return the value associated to the key.
     * @actualResult As expected result.
     * @dependencies The correctness of this test depends on the correctness of put().
     *               The correctness of this method is checked using the method size().
     * @preConditions The map instance must be a new instance of HMap.
     * @postConditions The map instance is modified by the call of this method, an entry will be removed.
     */
    @Test
    public void test_Remove_contained() {
        Object target = new Object();
        itt.put(target,target);
        int initSize = itt.size();
        assertEquals("Removing", target, itt.remove(target));
        assertEquals("Size decreased", initSize-1, itt.size());
    }

    /**
     * @title Test of remove() method
     * @description This test tests the behaviour of remove() when called on a map that contains a key which hash is
     *              equals to the hash of key given as a parameter.
     *              In this case, if the object given is not the same associated in the map, the entry should not be
     *              removed.
     * @expectedResults The map is expected to return a null reference and not modify the map
     * @actualResult As expected result.
     * @dependencies  Depends on the correctness of the methods put() and size().
     * @preConditions The map instance must be a new instance of Map.
     * @postConditions The map instance is not modified by the call of this method.
     */
    @Test
    public void test_Remove_hash() {
        itt.put("Aa", "Aa");
        assertNull("Removing a key with equal hash", itt.remove("BB"));
    }

    /**
     * @title Test of remove() method
     * @description Tests if invocation of remove(Object o) with null value for o, throws NullPointerException.
     * @expectedResults The class is expected to throw a NullPointerException.
     * @actualResult As expected result.
     * @dependencies The correctness of this test doesn't depends on the correctness of other map methods
     * @preConditions The map instance must be a new instance of HMap.
     * @postConditions The map instance is not modified by the call of this method.
     */
    @Test
    public void testRemove_exceptions() {
        assertThrows("Null value given as a paramter", NullPointerException.class, () -> itt.remove(null));
    }

    /**
     * @title Test of size() method
     * @description This test tests the behaviour of size() method when called on an empty map.
     * @expectedResults The size is expected to be zero because the map is empty.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method put()
     * @preConditions The map instance must be a new instance of HMap.
     * @postConditions The map instance is not modified by the call of this method.
     */
    @Test
    public void test_Size_empty() {
        assertEquals("Map size when created expected to be 0", 0, itt.size());
    }

    /**
     * @title Test of size() method
     * @description This test tests the behaviour of size() method when called on a non empty map.
     * @expectedResults The size is expected to be equal to the number of elements that added to the map.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method put()
     * @preConditions The map instance must be a new instance of HMap.
     * @postConditions The map instance is not modified by the call of this method.
     */
    @Test
    public void test_Size_notEmpty() {
        itt.put(new Object(),new Object());
        assertEquals("Map dimension grows when inserting", 1, itt.size());
    }

    /**
     * @title Test of isEmpty() method
     * @description This test tests the behaviour of the method isEmpty() when called on an empty Map
     * @expectedResults A new map should empty.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method put()
     * @preConditions The map instance must be a new instance of HMap.
     * @postConditions The map instance is not modified by the call of this method.
     */
    @Test
    public void test_IsEmpty_empty() {
        assertTrue("Empty collection", itt.isEmpty());
    }

    /**
     * @title Test of isEmpty() method
     * @description This test tests the behaviour of the method isEmpty() when called on an empty Map
     * @expectedResults An element was added to the map, the map should not be empty.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method put()
     * @preConditions The map instance must be a new instance of HMap.
     * @postConditions The map instance is not modified by the call of this method.
     */
    @Test
    public void test_IsEmpty_notEmpty() {
        itt.put(new Object(),new Object());
        assertFalse("controllo su collezione non vuota", itt.isEmpty());
    }

    /**
     * @title Test of consistency between isEmpty() and size() methods
     * @description This test tests the coherence between methods size() and isEmpty().
     * @expectedResults Consistent behaviour expected between size() and isEmpty() methods.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method put()
     * @preConditions The map instance must be a new instance of HMap.
     * @postConditions The map instance is not modified by the call of this method.
     */
    @Test
    public void check_consistency_SizeIsEmpty() {
        assertEquals("Checking when size=0", itt.size()==0, itt.isEmpty());
        itt.put(new Object(),new Object());
        assertEquals("Checking when size!=0", itt.size()!=0, !itt.isEmpty());
    }

    /**
     * @title Test of clear() method
     * @description This test tests the behaviour of clear() method when called on an empty HMap.
     * @expectedResults The map is expected to be empty (as it was previously) after the invocation of the method.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method isEmpty()
     * @preConditions The map instance must be a new instance of HMap.
     * @postConditions The map instance is not modified by the call of this method.
     */
    @Test
    public void testClear_empty() {
        itt.clear();
        assertTrue("Clear invocation on an empty map", itt.isEmpty());
    }

    /**
     * @title Test of clear() method
     * @description This test tests the behaviour of clear method when called on a non empty HMap.
     * @expectedResults The map should be empty after the invocation of the method.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method isEmpty()
     * @preConditions The map instance must be a new instance of HMap.
     * @postConditions The map instance is not modified by the call of this method.
     */
    @Test
    public void testClear_notEmpty() {
        itt.put("Aa", "Aa");
        itt.clear();
        assertTrue("Clear invocation on a non empty map", itt.isEmpty());
    }

    /**
     * @title Test of hashCode() method
     * @description This test tests the behaviour of hashCode() method when is called on an empty map.
     * @expectedResults The result hash is expected to be 0.
     * @actualResult As expected result.
     * @dependencies The correctness of this method does not depends on the correctness of other methods of the class Map.
     * @preConditions The map instance must be a new instance of Map.
     * @postConditions The map instance doesn't have to be modified by the execution of the method tested.
     */
    @Test
    public void test_HashCode_empty() {
        assertEquals("HashCode of empty map should be 0", 0, itt.hashCode());
    }

    /**
     * @title Test of hashCode() method
     * @description This test tests the behaviour of hashCode() method when is called on a non empty map.
     * @expectedResults The result hash is expected to be the sum of key.hashCode() ^ value.hashCode() for each
     *                  Entry of the map.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method put()
     * @preConditions The map instance must be a new instance of HMap.
     * @postConditions The map instance is not modified by the call of this method.
     */
    @Test
    public void test_HashCode_notEmpty() {
        String s1 = "AAA";
        String s2 = "BBB";
        itt.put(s1, s1);
        itt.put(s2, s2);

        int result = itt.hashCode();

        assertEquals("HashCode equals to expected", 0, result);
    }

    /**
     * @title Test of equals() method
     * @description This test tests the behaviour of equals() method two empty maps are given.
     * @expectedResults Both maps are empty, so they are expected to be equal.
     * @actualResult As expected result.
     * @dependencies The correctness of this test doesn't depends on the correctness of other map methods
     * @preConditions The map instance must be a new instance of HMap.
     * @postConditions The map instance is not modified by the call of this method.
     */
    @Test
    public void test_Equals_empty() {
        HMap other = new MapAdapter();
        boolean result = itt.equals(other);
        assertTrue("Two empty maps are equals", result);
    }

    /**
     * @title Test of equals() method
     * @description This test tests the behaviour of equals() method when one map is empty ant the other is not.
     * @expectedResults The maps are expected not to equals
     * @actualResult As expected result.
     * @dependencies The correctness of this test doesn't depends on the correctness of other map methods
     * @preConditions The map instance must be a new instance of HMap.
     * @postConditions The map instance is not modified by the call of this method.
     */
    @Test
    public void test_Equals_oneIsEmpty() {
        HMap other = new MapAdapter();
        other.put("Aa", "Aa");
        boolean result = itt.equals(other);
        assertFalse("Expected to be different", result);
    }

    /**
     * @title Test of equals() method
     * @description This test tests the behaviour of equals() method when two different HMap are given.
     * @expectedResults The maps should not be equals.
     * @actualResult As expected result.
     * @dependencies The correctness of this test doesn't depends on the correctness of other map methods
     * @preConditions The map instance must be a new instance of HMap.
     * @postConditions The map instance is not modified by the call of this method.
     */
    @Test
    public void test_Equals_notEquivalents() {
        itt.put("Aa", "Aa");
        HMap other = new MapAdapter();
        other.put("Cc", "Cc");
        boolean result = itt.equals(other);
        assertFalse("Expected to be different", result);
    }

    /**
     * @title Test of equals() method
     * @description This test tests the behaviour of equals() method when the two maps are equals
     * @expectedResults The maps are expected to equals.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method put()
     * @preConditions The map instance must be a new instance of HMap.
     * @postConditions The map instance is modified by the call of this method.
     */
    @Test
    public void test_Equals_equivalents() {
        itt.put("Aa", "Aa");
        itt.put("Cc", "Cc");
        HMap other = new MapAdapter();
        other.put("Aa", "Aa");
        other.put("Cc", "Cc");
        assertEquals("Expected to be equals, same entry inside", itt, other);
    }

    /**
     * @title Test of equals() method
     * @description This test tests the behaviour of equals() method two maps contain same key,
     *              but different values associated.
     * @expectedResults The maps are not equals.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method put()
     * @preConditions The map instance must be a new instance of HMap
     * @postConditions The map instance is modified by the call of this method.
     */
    @Test
    public void test_Equals_sameKeys() {
        itt.put("Aa", "Aa");
        HMap other = new MapAdapter();
        other.put("Aa", "Bb");
        assertNotEquals("Expected not to be equals, same keys inside, but not same values associated", itt, other);
    }

    /**
     * @title Test of equals() method
     * @description This test tests the behaviour of equals() method two maps contain same key and value.
     * @expectedResults The maps are equals.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method put()
     * @preConditions The map instance must be a new instance of HMap
     * @postConditions The map instance is modified by the call of this method.
     */
    @Test
    public void test_Equals_sameValues() {
        itt.put("Aa", "Bb");
        HMap other = new MapAdapter();
        other.put("Bb", "Aa");
        assertNotEquals("Expected not to be equals, same values inside, but not same keys", itt, other);
    }

    /**
     * @title Test of consistency between equals() and hashCode() methods
     * @description This test tests the behaviour of equals() and hashCode() methods
     *              to check if those 2 are consistent.
     * @expectedResults The result should be consistent, so if two HMap are equals those must have the same hashCode.
     * @actualResult As expected result.
     * @dependencies Depends on the correctness of method put()
     * @preConditions The map instance must be a new instance of HMap
     * @postConditions The map instance is modified by the call of this method.
     */
    @Test
    public void check_ConsistencyEqualsHashCode(){
        MapAdapter ma2 = new MapAdapter();
        assertTrue("Both empty", (itt.equals(ma2) && itt.hashCode() == ma2.hashCode()));

        Object obj1 = new Object();
        Object obj2 = new Object();
        itt.put(obj1,obj1);
        itt.put(obj2,obj2);
        ma2.put(obj1,obj1);
        ma2.put(obj2,obj2);

        assertTrue("Same elements -> Same hashcode", (itt.equals(ma2) && itt.hashCode() == ma2.hashCode()));

        Object key2 = "BBB".hashCode();
        Object value2 = new Object();
        itt.put(key2,value2);

        assertNotEquals("Different elements", itt, ma2);
        assertNotEquals("Different hashcodes",itt.hashCode(), ma2.hashCode());
    }

    //TEST ENTRYSET

    /**
     * @title Test of entrySet() method
     * @description This test tests the behaviour of entrySet() method when called on an empty map.
     * @expectedResults The set is expected to be empty.
     * @actualResult As expected result.
     * @dependencies The correctness of this method depends on the correctness of method isEmpty.
     * @preConditions The map instance must be a new instance of Map.
     * @postConditions The map instance should not be modified by the execution of the method.
     */
    @Test
    public void test_Entry_empty() {
        HSet entries = itt.entrySet();
        assertTrue("The map is empty", entries.isEmpty());
    }

    /**
     * @title Test of entrySet() method
     * @description This test tests the behaviour of entrySet() method when called on a not-empty map.
     * @expectedResults The set size is expected to be equals to the number of entries in the map.
     *                  The set is expected to contain all the entries contained in the map.
     * @actualResult As expected result.
     * @dependencies The correctness of this method depends on the correctness of methods put, isEmpty and contains.
     * @preConditions The map instance must be a new instance of Map.
     * @postConditions The map instance should not be modified by the execution of the method.
     */
    @Test
    public void test_Entry_notEmpty() {
        Object obj1 = new Object();
        Object obj2 = new Object();

        itt.put(obj1, obj1);
        itt.put(obj2, obj2);
        HSet entries = itt.entrySet();
        assertEquals("Check size", 2, entries.size());
        assertTrue("Check items", entries.contains(new MapAdapter.Entry(obj1, obj1)));
        assertTrue("Check items", entries.contains(new MapAdapter.Entry(obj2, obj2)));
    }

    /**
     * @title Test of entrySet() method
     * @description This test tests the behaviour of entrySet() method called on a non empty map
     *              that contains duplicate values.
     * @expectedResults The set size is expected to equals to the number of entries inserted.
     * @actualResult As expected result.
     * @dependencies The correctness of this method depends on the correctness of methods size and put.
     * @preConditions The map instance must be a new instance of Map.
     * @postConditions The map instance should not be modified by the execution of the method.
     */
    @Test
    public void test_Entry_duplicates() {
        itt.put("Aa", "Aa");
        itt.put("Bb", "Aa");
        itt.put("Cc", "Aa");
        HSet entries = itt.entrySet();
        assertEquals("Check size", 3, entries.size());
    }

    /**
     * @title Test of entrySet() method
     * @description This test tests the behaviour of entrySet() method.
     *              This test that the new set returned is backed to the map so,
     *              structural modification are performed in set are reflected in the parent map.
     * @expectedResults The modifications to the returned set must be reflected to the parent map.
     * @actualResult As expected result.
     * @dependencies The correctness of this method depends on the correctness of method size(), isEmpty(),
     *               containsValue() and put().
     * @preConditions The map instance must be a new instance of Map.
     * @postConditions The map instance should be modified by the execution of the method.
     */
    @Test
    public void test_Entry_backed() {
        itt.put("Aa", "Aa");
        itt.put("Bb", "Bb");
        itt.put("Cc", "Cc");
        itt.put("Zz","Zz");
        HSet entries = itt.entrySet();

        entries.remove(new MapAdapter.Entry("Aa", "Aa"));

        assertEquals("Size changed", 3, entries.size());
        assertEquals("Modification on parent", 3, itt.size());
        assertFalse("Check if is contained", itt.containsKey("Aa") && itt.containsValue("Aa"));

        HCollection toRemove = new ListAdapter();
        toRemove.add(new MapAdapter.Entry("Bb", "Bb"));
        toRemove.add(new MapAdapter.Entry("Cc", "Cc"));

        entries.removeAll(toRemove);

        assertEquals("Modification on parent", 1, entries.size());
        assertEquals("Remove on parent", 1, itt.size());
        assertFalse("Check remove", itt.containsKey("Bb") && itt.containsValue("Bb"));
        assertFalse("Check remove", itt.containsKey("Cc") && itt.containsValue("Cc"));
        assertTrue("Check survived", itt.containsKey("Zz") && itt.containsValue("Zz"));

        HCollection toRetain = new ListAdapter();
        entries.retainAll(toRetain);

        assertTrue("Set is now void", entries.isEmpty());
        assertTrue("Remove on parent", itt.isEmpty());
    }

    /**
     * @title Test of entrySet() method
     * @description This test tests the behaviour of entrySet() method.
     *              This test tests the iterator of the set of entries returned.
     * @expectedResults The iterator must be capable of iterating all map entries and
     *                  Modifications performed by the iterator should be reflected to the parent map.
     * @actualResult As expected result.
     * @dependencies The correctness of this method depends on the correctness of method size(), containsValue() and put().
     * @preConditions The map instance must be a new instance of Map.
     * @postConditions The map instance should be modified by the execution of the method.
     */
    @Test
    public void test_Entry_iterator() {
        HIterator it = itt.entrySet().iterator();
        assertFalse("Iterator on empty map", it.hasNext());

        itt.put("Aa", "Aa");
        itt.put("Bb", "Bb");
        itt.put("Cc", "Cc");
        it = itt.entrySet().iterator();

        int i = 0;
        Object second = null;
        while(it.hasNext()){
            HMap.Entry tmp = (HMap.Entry) it.next();
            if(i==1) second = tmp;
            assertTrue("Is in the map", itt.containsKey(tmp.getKey()) && itt.containsValue(tmp.getValue()));
            i++;
        }

        it = itt.entrySet().iterator();
        HMap.Entry removed = (HMap.Entry) it.next();
        it.remove();
        assertEquals("Remove reflected on parent", 2, itt.size());
        assertFalse("Element is removed", itt.containsKey(removed.getKey()));
        assertEquals("Second becomes first", second, it.next());
    }

    /**
     * @title Test of entrySet() method
     * @description This test tests the behaviour of entrySet() method.
     *              Tests hashCode and equals method of the returned set of entries.
     * @expectedResults Two entry set should be equals when both contains the same elements, check if
     *                  hashcode and equals are consistent.
     * @actualResult As expected result.
     * @dependencies The correctness of this method depends on the correctness of method put().
     * @preConditions The map instance must be a new instance of Map.
     * @postConditions The map instance should not be modified by the execution of the method tested.
     */
    @Test
    public void test_Entry_equalsHash() {
        itt.put("Aa", "Az");
        itt.put("Bb", "Bz");
        HMap other = new MapAdapter();
        other.put("Aa", "Az");
        other.put("Bb", "Bz");
        assertEquals("Should be equals", itt.entrySet(), other.entrySet());
        assertEquals("So, if equals -> same hash", itt.entrySet().hashCode(), other.entrySet().hashCode());

        itt.put("Xx", "Xz");
        assertNotEquals("Should not be equals", itt.entrySet(), other.entrySet());
        assertNotEquals("Should not (but could be) have same hash", itt.entrySet().hashCode(), other.entrySet().hashCode());
    }

    /**
     * @title Test of entrySet() method
     * @description This test tests the behaviour of entrySet() method.
     *              It tests clear method of the returned set.
     * @expectedResults The clear method should delete all the entries of the map.
     * @actualResult As expected result.
     * @dependencies The correctness of this method depends on the correctness of method put().
     * @preConditions The map instance must be a new instance of Map.
     * @postConditions The map instance should be modified by the execution of the method tested.
     */
    @Test
    public void test_Entry_clear() {
        itt.put("Aa", "Aa");
        itt.put("Bb", "Bb");
        itt.put("Cc", "Cc");
        HSet entries = itt.entrySet();
        entries.clear();
        assertTrue("Map should be empty", itt.isEmpty());
        assertTrue("Collection should be empty", entries.isEmpty());
    }

    /**
     * @title Test of entrySet() method
     * @description This test tests the behaviour of entrySet() method.
     *              It tests containsAll() method of the returned set.
     * @expectedResults The method should correctly verify if a collection of entries is contained or not in the map.
     * @actualResult As expected result.
     * @dependencies The correctness of this method depends on the correctness of method put().
     * @preConditions The map instance must be a new instance of Map.
     * @postConditions The map instance should not be modified by the execution of the method tested.
     */
    @Test
    public void test_Entry_containsAll() {
        itt.put("Aa", "Aa");
        itt.put("Bb", "Bb");
        itt.put("Cc", "Cc");
        HCollection param = new ListAdapter();
        param.add(new MapAdapter.Entry("Aa", "Aa"));
        param.add(new MapAdapter.Entry("Bb", "Bb"));
        HSet entries = itt.entrySet();
        assertTrue("All entries contained", entries.containsAll(param));
        param.add(new MapAdapter.Entry("Jj", "Jj"));
        assertFalse("Not all entries contained", entries.containsAll(param));
    }

    //TEST KEYSET

    /**
     * @title Test of keySet() method
     * @description This test tests the behaviour of keySet() method when called on an empty map.
     * @expectedResults The set is expected to be empty.
     * @actualResult As expected result.
     * @dependencies The correctness of this method depends on the correctness of method isEmpty().
     * @preConditions The map instance must be a new instance of Map.
     * @postConditions The map instance should not be modified by the execution of the method.
     */
    @Test
    public void test_KeySet_empty() {
        HSet entries = itt.keySet();
        assertTrue("The map is empty", entries.isEmpty());
    }

    /**
     * @title Test of keySet() method
     * @description This test tests the behaviour of keySet() method when called on a not-empty map.
     * @expectedResults The set size is expected to be equals to the number of keys in the map.
     *                  The set is expected to contain all the keys contained in the map.
     * @actualResult As expected result.
     * @dependencies The correctness of this method depends on the correctness of methods put(), isEmpty() and contains().
     * @preConditions The map instance must be a new instance of Map.
     * @postConditions The map instance should not be modified by the execution of the method.
     */
    @Test
    public void test_KeySet_notEmpty() {
        Object obj1 = new Object();
        Object obj2 = new Object();

        itt.put(obj1, obj1);
        itt.put(obj2, obj2);
        HSet entries = itt.keySet();
        assertEquals("Check size", 2, entries.size());
        assertTrue("Check items", entries.contains(obj1));
        assertTrue("Check items", entries.contains(obj2));
    }

    /**
     * @title Test of keySet() method
     * @description This test tests the behaviour of keySet() method called on a non empty map.
     *              This method tries inserting two times the same key.
     * @expectedResults The set size is expected to equals to 1.
     * @actualResult As expected result.
     * @dependencies The correctness of this method depends on the correctness of methods size() and put().
     * @preConditions The map instance must be a new instance of Map.
     * @postConditions The map instance should not be modified by the execution of the method.
     */
    @Test
    public void test_KeySet_duplicates() {
        assertNull("First insertion", itt.put("Aa", "Aa"));
        assertEquals("Second insertion","Aa",itt.put("Aa", "Bb"));
        HSet entries = itt.keySet();
        assertEquals("Check size", 1, entries.size());
    }

    /**
     * @title Test of keySet() method
     * @description This test tests the behaviour of keySet() method.
     *              This test that the new set returned is backed to the map so,
     *              structural modification are performed in set are reflected in the parent map.
     * @expectedResults The modifications to the returned set must be reflected to the parent map.
     * @actualResult As expected result.
     * @dependencies The correctness of this method depends on the correctness of method size(), isEmpty(),
     *               containsValue() and put().
     * @preConditions The map instance must be a new instance of Map.
     * @postConditions The map instance should be modified by the execution of the method.
     */
    @Test
    public void test_KeySet_backed() {
        itt.put("Aa", "Aa");
        itt.put("Bb", "Bb");
        itt.put("Cc", "Cc");
        itt.put("Zz","Zz");
        HSet entries = itt.keySet();

        entries.remove("Aa");

        assertEquals("Size changed", 3, entries.size());
        assertEquals("Modification on parent", 3, itt.size());
        assertFalse("Check if is contained", itt.containsKey("Aa") && itt.containsValue("Aa"));

        HCollection toRemove = new ListAdapter();
        toRemove.add("Bb");
        toRemove.add("Cc");

        entries.removeAll(toRemove);

        assertEquals("Modification on parent", 1, entries.size());
        assertEquals("Remove on parent", 1, itt.size());
        assertFalse("Check remove", itt.containsKey("Bb") && itt.containsValue("Bb"));
        assertFalse("Check remove", itt.containsKey("Cc") && itt.containsValue("Cc"));
        assertTrue("Check survived", itt.containsKey("Zz") && itt.containsValue("Zz"));

        HCollection toRetain = new ListAdapter();
        entries.retainAll(toRetain);

        assertTrue("Set is now void", entries.isEmpty());
        assertTrue("Remove on parent", itt.isEmpty());
    }

    /**
     * @title Test of keySet() method
     * @description This test tests the behaviour of keySet() method.
     *              This test tests the iterator of the set of entries returned.
     * @expectedResults The iterator must be capable of iterating all map entries and
     *                  modifications performed by the iterator should be reflected to the parent map.
     * @actualResult As expected result.
     * @dependencies The correctness of this method depends on the correctness of method size(), containsValue() and put().
     * @preConditions The map instance must be a new instance of Map.
     * @postConditions The map instance should be modified by the execution of the method.
     */
    @Test
    public void test_KeySet_iterator() {
        HIterator it;
        it = itt.keySet().iterator();
        assertFalse("Iterator on empty map", it.hasNext());

        itt.put("Aa", "Aa");
        itt.put("Bb", "Bb");
        itt.put("Cc", "Cc");
        it = itt.keySet().iterator();

        int i = 0;
        Object second = null;
        while(it.hasNext()){
            Object tmp = it.next();
            if(i==1) second = tmp;
            assertTrue("Is in the map", itt.containsKey(tmp));
            i++;
        }

        it = itt.keySet().iterator();
        Object removed = it.next();
        it.remove();
        assertEquals("Remove reflected on parent", 2, itt.size());
        assertFalse("Element is removed", itt.containsKey(removed));
        assertEquals("Second becomes first", second, it.next());
    }

    /**
     * @title Test of entrySet() method
     * @description This test tests the behaviour of entrySet() method.
     *              Tests hashCode and equals method of the returned set of entries.
     * @expectedResults Two entry set should be equals when both contains the same elements, check if
     *                  hashcode and equals are consistent.
     * @actualResult As expected result.
     * @dependencies The correctness of this method depends on the correctness of method put().
     * @preConditions The map instance must be a new instance of Map.
     * @postConditions The map instance should not be modified by the execution of the method tested.
     */
    @Test
    public void test_KeySet_equalsHash() {
        itt.put("Aa", "Az");
        itt.put("Bb", "Bz");
        HMap other = new MapAdapter();
        other.put("Aa", "Az");
        other.put("Bb", "Bz");

        assertEquals("Should be equals", itt.keySet(), other.keySet());
        assertEquals("So, if equals -> same hash", itt.keySet().hashCode(), other.keySet().hashCode());

        itt.put("Xx", "Xz");
        assertNotEquals("Should not be equals", itt.keySet(), other.keySet());
        assertNotEquals("Should not (but could be) have same hash", itt.keySet().hashCode(), other.keySet().hashCode());
    }

    /**
     * @title Test of keySet() method
     * @description This test tests the behaviour of keySet() method.
     *              It tests clear method of the returned set.
     * @expectedResults The clear method should delete all the entries of the map.
     * @actualResult As expected result.
     * @dependencies The correctness of this method depends on the correctness of method put().
     * @preConditions The map instance must be a new instance of Map.
     * @postConditions The map instance should be modified by the execution of the method tested.
     */
    @Test
    public void test_KeySet_clear() {
        itt.put("Aa", "Aa");
        itt.put("Bb", "Bb");
        itt.put("Cc", "Cc");
        HSet entries = itt.keySet();
        entries.clear();
        assertTrue("Map should be empty", itt.isEmpty());
        assertTrue("keySet should be empty", entries.isEmpty());
    }

    /**
     * @title Test of keySet() method
     * @description This test tests the behaviour of keySet() method.
     *              It tests containsAll() method of the returned set.
     * @expectedResults The method should correctly verify if a collection of entries is contained or not in the map.
     * @actualResult As expected result.
     * @dependencies The correctness of this method depends on the correctness of method put().
     * @preConditions The map instance must be a new instance of Map.
     * @postConditions The map instance should not be modified by the execution of the method tested.
     */
    @Test
    public void test_KeySet_containsAll() {
        itt.put("Aa", "Aa");
        itt.put("Bb", "Bb");
        itt.put("Cc", "Cc");
        HCollection param = new ListAdapter();
        param.add("Aa");
        param.add("Bb");
        HSet entries = itt.keySet();
        assertTrue("All entries contained", entries.containsAll(param));
        param.add("Jj");
        assertFalse("Not all entries contained", entries.containsAll(param));
    }

    //TEST VALUES

    /**
     * @title Test of values() method
     * @description This test tests the behaviour of values() method when called on an empty map.
     * @expectedResults The collection is expected to be empty.
     * @actualResult As expected result.
     * @dependencies The correctness of this method depends on the correctness of method isEmpty().
     * @preConditions The map instance must be a new instance of Map.
     * @postConditions The map instance should not be modified by the execution of the method.
     */
    @Test
    public void test_Values_empty() {
        HCollection entries = itt.values();
        assertTrue("The collection is empty", entries.isEmpty());
    }

    /**
     * @title Test of values() method
     * @description This test tests the behaviour of values() method when called on a not-empty map.
     * @expectedResults The collection size is expected to be equals to the number of keys in the map.
     *                  The collection is expected to contain all the keys contained in the map.
     * @actualResult As expected result.
     * @dependencies The correctness of this method depends on the correctness of methods put(), isEmpty() and contains().
     * @preConditions The map instance must be a new instance of Map.
     * @postConditions The map instance should not be modified by the execution of the method.
     */
    @Test
    public void test_Values_notEmpty() {
        Object obj1 = new Object();
        Object obj2 = new Object();

        itt.put(obj1, obj1);
        itt.put(obj2, obj2);
        HCollection entries = itt.values();
        assertEquals("Check size", 2, entries.size());
        assertTrue("Check items", entries.contains(obj1));
        assertTrue("Check items", entries.contains(obj2));
    }

    /**
     * @title Test of values() method
     * @description This test tests the behaviour of values() method called on a non empty map.
     *              This method tries inserting two times the same value with same key.
     * @expectedResults The collection size is expected to equals to 1.
     * @actualResult As expected result.
     * @dependencies The correctness of this method depends on the correctness of methods size() and put().
     * @preConditions The map instance must be a new instance of Map.
     * @postConditions The map instance should not be modified by the execution of the method.
     */
    @Test
    public void test_Values_duplicates_sk() {
        assertNull("First insertion", itt.put("Aa", "Bb"));
        assertEquals("Second insertion","Bb",itt.put("Aa", "Bb"));
        HCollection entries = itt.values();
        assertEquals("Check size", 1, entries.size());
    }

    /**
     * @title Test of values() method
     * @description This test tests the behaviour of values() method called on a non empty map.
     *              This method tries inserting two times the same value with different key.
     * @expectedResults The collection size is expected to equals to 2.
     * @actualResult As expected result.
     * @dependencies The correctness of this method depends on the correctness of methods size() and put().
     * @preConditions The map instance must be a new instance of Map.
     * @postConditions The map instance should not be modified by the execution of the method.
     */
    @Test
    public void test_Values_duplicates_dk() {
        assertNull("First insertion", itt.put("Aa", "Aa"));
        assertNull("Second insertion",itt.put("Bb", "Aa"));
        HCollection entries = itt.values();
        assertEquals("Check size", 2, entries.size());
    }

    /**
     * @title Test of values() method
     * @description This test tests the behaviour of values() method.
     *              This test that the new collection returned is backed to the map so,
     *              structural modification are performed in collection are reflected in the parent map.
     * @expectedResults The modifications to the returned set must be reflected to the parent map.
     * @actualResult As expected result.
     * @dependencies The correctness of this method depends on the correctness of method size(), isEmpty(),
     *               containsValue() and put().
     * @preConditions The map instance must be a new instance of Map.
     * @postConditions The map instance should be modified by the execution of the method.
     */
    @Test
    public void test_Values_backed() {
        itt.put("Aa", "Aa");
        itt.put("Bb", "Bb");
        itt.put("Cc", "Cc");
        itt.put("Zz","Zz");
        HCollection entries = itt.values();

        entries.remove("Aa");

        assertEquals("Size changed", 3, entries.size());
        assertEquals("Modification on parent", 3, itt.size());
        assertFalse("Check if is contained", itt.containsValue("Aa"));

        HCollection toRemove = new ListAdapter();
        toRemove.add("Bb");
        toRemove.add("Cc");

        entries.removeAll(toRemove);

        assertEquals("Modification on parent", 1, entries.size());
        assertEquals("Remove on parent", 1, itt.size());
        assertFalse("Check remove", itt.containsValue("Bb"));
        assertFalse("Check remove", itt.containsValue("Cc"));
        assertTrue("Check survived", itt.containsValue("Zz"));

        HCollection toRetain = new ListAdapter();
        entries.retainAll(toRetain);

        assertTrue("Set is now void", entries.isEmpty());
        assertTrue("Remove on parent", itt.isEmpty());
    }

    /**
     * @title Test of values() method
     * @description This test tests the behaviour of values() method.
     *              This test tests the iterator of the set of entries returned.
     * @expectedResults The iterator must be capable of iterating all map entries and
     *                  modifications performed by the iterator should be reflected to the parent map.
     * @actualResult As expected result.
     * @dependencies The correctness of this method depends on the correctness of method size(), containsValue() and put().
     * @preConditions The map instance must be a new instance of Map.
     * @postConditions The map instance should be modified by the execution of the method.
     */
    @Test
    public void test_Values_iterator() {
        HIterator it;
        it = itt.values().iterator();
        assertFalse("Iterator on empty map", it.hasNext());

        itt.put("Aa", "Aa");
        itt.put("Bb", "Bb");
        itt.put("Cc", "Cc");
        it = itt.values().iterator();

        int i = 0;
        Object second = null;
        while(it.hasNext()){
            Object tmp = it.next();
            if(i==1) second = tmp;
            assertTrue("Is in the map", itt.containsValue(tmp));
            i++;
        }

        it = itt.keySet().iterator();
        Object removed = it.next();
        it.remove();
        assertEquals("Remove reflected on parent", 2, itt.size());
        assertFalse("Element is removed", itt.containsValue(removed));
        assertEquals("Second becomes first", second, it.next());
    }

    /**
     * @title Test of values() method
     * @description This test tests the behaviour of values() method.
     *              Tests hashCode and equals method of the returned collection.
     * @expectedResults Two entry set should be equals when both contains the same elements, check if
     *                  hashcode and equals are consistent.
     * @actualResult As expected result.
     * @dependencies The correctness of this method depends on the correctness of method put().
     * @preConditions The map instance must be a new instance of Map.
     * @postConditions The map instance should not be modified by the execution of the method tested.
     */
    @Test
    public void test_Values_equalsHash() {
        itt.put("Aa", "Az");
        itt.put("Bb", "Bz");

        HMap other = new MapAdapter();
        other.put("Aa", "Az");
        other.put("Bb", "Bz");

        assertEquals("Should be equals", itt.values(), other.values());
        assertEquals("So, if equals -> same hash", itt.values().hashCode(), other.values().hashCode());

        itt.put("Xx", "Xz");
        assertNotEquals("Should not be equals", itt.values(), other.values());
        assertNotEquals("Should not (but could be) have same hash", itt.values().hashCode(), other.values().hashCode());
    }

    /**
     * @title Test of values() method
     * @description This test tests the behaviour of values() method.
     *              It tests clear method of the returned collection.
     * @expectedResults The clear method should delete all the entries of the map.
     * @actualResult As expected result.
     * @dependencies The correctness of this method depends on the correctness of method put().
     * @preConditions The map instance must be a new instance of Map.
     * @postConditions The map instance should be modified by the execution of the method tested.
     */
    @Test
    public void test_Values_clear() {
        itt.put("Aa", "Aa");
        itt.put("Bb", "Bb");
        itt.put("Cc", "Cc");
        HCollection entries = itt.values();
        entries.clear();
        assertTrue("Map should be empty", itt.isEmpty());
        assertTrue("keySet should be empty", entries.isEmpty());
    }

    /**
     * @title Test of values() method
     * @description This test tests the behaviour of values() method.
     *              It tests containsAll() method of the returned collection.
     * @expectedResults The method should correctly verify if a collection of entries is contained or not in the map.
     * @actualResult As expected result.
     * @dependencies The correctness of this method depends on the correctness of method put().
     * @preConditions The map instance must be a new instance of Map.
     * @postConditions The map instance should not be modified by the execution of the method tested.
     */
    @Test
    public void test_Values_containsAll() {
        itt.put("Aa", "Aa");
        itt.put("Bb", "Bb");
        itt.put("Cc", "Cc");
        HCollection param = new ListAdapter();
        param.add("Aa");
        param.add("Bb");
        HCollection entries = itt.values();
        assertTrue("All entries contained", entries.containsAll(param));
        param.add("Jj");
        assertFalse("Not all entries contained", entries.containsAll(param));
    }

}
