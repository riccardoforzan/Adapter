package test;

import adapters.CollectionAdapter;

import interfaces.HIterator;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import static org.junit.Assert.*;

public class CollectionAdapterTester {

    @Test
    public void testAdd(){
        assertThrows(ClassCastException.class, () -> {
            //TODO
        });
    }

    @Test
    public void testRemove(){
        assertThrows(ClassCastException.class, () -> {
            //TODO
        });

    }

    @Test
    public void testContains(){
        assertThrows(ClassCastException.class, () -> {
            //TODO
        });

    }

    @Test
    public void testAddAll(){
        assertThrows(ClassCastException.class, () -> {
            //TODO
        });

    }

    @Test
    public void testRetainAll(){
        assertThrows(ClassCastException.class, () -> {
            //TODO
        });

    }

    @Test
    public void testRemoveAll(){
        assertThrows(ClassCastException.class, () -> {
            //TODO
        });

    }

    @Test
    public void testContainsAll(){

        assertThrows(ClassCastException.class, () -> {
            //TODO
        });

    }

    @Test
    public void testIterator(){
        CollectionAdapter ca = new CollectionAdapter();
        ca.add(new Object());
        HIterator it = ca.iterator();
        assertEquals(true,it.hasNext());
        it.next();
        assertNotEquals(true,it.hasNext());
    }

    @Test
    public void testIsEmpty(){
        CollectionAdapter ca = new CollectionAdapter();
        assertEquals(true,ca.isEmpty());
        ca.add(new Object());
        assertEquals(false,ca.isEmpty());
    }

    @Test
    public void testSize(){
        CollectionAdapter ca = new CollectionAdapter();
        assertEquals(0,ca.size());

        ca.add(new Object());
        assertEquals(1,ca.size());
    }

    @Test
    public void testClear(){
        CollectionAdapter ca = new CollectionAdapter();
        ca.add(new Object());
        assertEquals(1,ca.size());
        ca.clear();
        assertEquals(0,ca.size());
    }

    @Test
    public void testEquals(){
        CollectionAdapter ca1 = new CollectionAdapter();
        CollectionAdapter ca2 = new CollectionAdapter();

        assertEquals(true,ca1.equals(ca1));
        assertNotEquals(true,ca1.equals(ca2));

        //TODO: Controllare la coerenza con HashCode
    }

    @Test
    public void testHashCode(){
        CollectionAdapter ca1 = new CollectionAdapter();
        CollectionAdapter ca2 = new CollectionAdapter();

        assertEquals(ca1.hashCode(),ca1.hashCode());
        assertNotEquals(ca1.hashCode(), ca2.hashCode());

        //TODO: Controllare la coerenza con Equals
    }

    @Test
    public void testToObjectArray(){
        CollectionAdapter ca = new CollectionAdapter();
        Object[] objs = ca.toArray();
        assertEquals(ca.size(),objs.length);
    }


}