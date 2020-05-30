package test;

import adapters.MapAdapter;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MapAdapterTester {

    @Test
    public void testEntry(){}

    @Test
    public void testEntrySet(){}

    @Test
    public void testGet(){}

    @Test
    public void testGetOrDefault(){}

    @Test
    public void testKeySet(){}

    @Test
    public void testPut(){}

    @Test
    public void testPutAll(){}

    @Test
    public void testPutIfAbsent(){}

    @Test
    public void testContainsKey(){}

    @Test
    public void testContainsValue(){}

    @Test
    public void testRemoveKey(){}

    @Test
    public void testRemoveKeyValue(){}

    @Test
    public void testReplaceKeyValue(){}

    @Test
    public void testReplaceKeyOldValueNewValue(){}

    @Test
    public void testValues(){}

    @Test
    public void testSize(){
        MapAdapter ma = new MapAdapter();
        assertEquals(0,ma.size());

        ma.put(new Object(),new Object());
        assertNotEquals(0,ma.size());
        assertEquals(1,ma.size());
    }

    @Test
    public void testIsEmpty(){
        MapAdapter ma = new MapAdapter();

        assertEquals(true, ma.isEmpty());

        ma.put(new Object(),new Object());
        assertNotEquals(false,ma.isEmpty());
    }

    @Test
    public void testClear(){
        MapAdapter ma = new MapAdapter();
        ma.put(new Object(),new Object());
        ma.clear();
        assertNotEquals(1,ma.size());
        assertEquals(0,ma.size());
    }

    @Test
    public void testHashCode(){
        MapAdapter ma1 = new MapAdapter();
        MapAdapter ma2 = new MapAdapter();

        assertEquals(ma1.hashCode(),ma2.hashCode());

        Object key = new Object();
        Object value = new Object();
        ma1.put(key,value);
        ma2.put(key,value);

        assertEquals(ma1.hashCode(),ma2.hashCode());

        ma2.put(new Object(),new Object());
        assertNotEquals(ma1.hashCode(),ma2.hashCode());
    }

    @Test
    public void testEquals(){
        MapAdapter ma1 = new MapAdapter();
        MapAdapter ma2 = new MapAdapter();

        assertEquals(true,ma1.equals(ma2));

        Object key = new Object();
        Object value = new Object();
        ma1.put(key,value);
        ma2.put(key,value);

        assertEquals(true,ma1.equals(ma2));

        ma2.put(new Object(),new Object());
        assertNotEquals(false,ma1.equals(ma2));
    }

}
