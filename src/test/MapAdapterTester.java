package test;

import adapters.MapAdapter;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MapAdapterTester {

    private MapAdapter ma;

    @Before
    public void setup(){
        ma = new MapAdapter();
    }

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
        assertEquals("Controllo che la dimensione iniziale della mappa sia nulla",0,ma.size());
    }

    /**
     * Dipende dal metodo put()
     */
    @Test
    public void testIsEmpty(){
        assertEquals(true, ma.isEmpty());
        ma.put(new Object().hashCode(),new Object());
        assertNotEquals(false,ma.isEmpty());
    }

    /**
     * Dipende dal metodo put() e isEmpty()
     */
    @Test
    public void testClear(){
        ma.put(new Object().hashCode(),new Object());
        //TODO: Controllare che effettivamente la dimensione della mappa sia 1
        ma.clear();
        assertEquals("Controllo che la mappa sia vuota",true,ma.isEmpty());
    }

    /**
     * Dipende da put()
     */
    @Test
    public void testHashCode(){
        MapAdapter ma2 = new MapAdapter();

        assertEquals("Controllo che la mappa vuota abbia hashcode 0",0,ma.hashCode());
        assertEquals("Controllo che le mappe vuote abbiano lo stesso hashcode",true,ma.hashCode() == ma2.hashCode());

        Object key = new Object().hashCode();
        Object value = new Object();
        ma.put(key,value);
        ma2.put(key,value);

        assertEquals("Controllo che le mappe con lo stesso elemento abbiano lo stesso hashcode",true,ma.hashCode() == ma2.hashCode());

        Object tp = new Object();
        ma2.put(tp.hashCode(),tp);
        assertNotEquals("Controllo che le mappe che non contengono lo stesso elemento non abbiano lo stesso hashcode",true,ma.hashCode() == ma2.hashCode());
    }

    /**
     * Dipende da put()
     */
    @Test
    public void testEquals(){
        MapAdapter ma2 = new MapAdapter();

        assertEquals("Controllo che le mappe vuote siano uguali",true,ma.equals(ma2));

        Object key = new Object().hashCode();
        Object value = new Object();
        ma.put(key,value);
        ma2.put(key,value);

        assertEquals("Controllo che le mappe con lo stesso elemento all'interno siano uguali",true,ma.equals(ma2));

        Object tp = new Object();
        ma2.put(tp.hashCode(),tp);
        assertEquals("Controllo che le mappe con elementi diversi al loro interno NON siano uguali",true,ma.equals(ma2));
    }

    /**
     * Test della consistenza tra il metodo equals() e hashCode()
     */
    @Test
    public void TestConsistencyEqualsHashCode(){
        MapAdapter ma2 = new MapAdapter();
        assertEquals("Se entrambi sono vuoti allora sono uguali",true, (ma.equals(ma2)&&ma.hashCode()==ma2.hashCode()));

        //Costruisco due set con gli stessi elementi
        Object obj1 = new Object();
        Object obj2 = new Object();
        ma.put(obj1.hashCode(),obj1);
        ma.put(obj2.hashCode(),obj2);
        ma2.put(obj1.hashCode(),obj1);
        ma2.put(obj2.hashCode(),obj2);

        //L'hashcode viene creato come somma degli hascode degli oggetti
        assertEquals("Se entrambi hanno gli stessi elementi allora sono uguali",true, (ma.equals(ma2)&&ma.hashCode()==ma2.hashCode()));

        //Aggiungo un oggetto, mi aspetto che gli hashcode siano diversi e che i due oggetti non siano più uguali
        Object tp = new Object();
        ma2.put(tp.hashCode(),tp);
        assertNotEquals("Controllo non siano uguali",true,ma.equals(ma2));
        assertNotEquals("Controllo non abbiano il medesimo hashcode",true, (ma.hashCode() == ma2.hashCode()) );
    }

}
