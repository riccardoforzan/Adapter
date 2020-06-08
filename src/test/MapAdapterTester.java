package test;

import interfaces.HMap;
import interfaces.HSet;

import adapters.MapAdapter;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MapAdapterTester {

    private MapAdapter ma;

    @Before
    public void setup(){
        ma = new MapAdapter();
    }

    @Test
    public void testEntrySet(){
        /**
         * TODO: Test con iteratore attivo
         */
    }

    @Test
    public void testGet(){
        Object value = new Object();
        Integer key = value.hashCode();
        assertEquals("Cerco una chiave NON presente",null,ma.get(key));

        ma.put(key,value);
        assertEquals("Cerco una chiave presente",value,ma.get(key));

        assertThrows("Non è permesso l'inserimento di chiavi nulle",NullPointerException.class,()->{
            ma.containsKey(null);
        });
    }

    @Test
    public void testKeySet(){

        /**
         * TODO: Modifiche con iteratore attivo
         */
        assertEquals("Una mappa vuota non ha chiavi",true,ma.keySet().isEmpty());

        Object value = new Object();
        Integer key = value.hashCode();
        Object value2 = new Object();
        Integer key2 = value2.hashCode();
        ma.put(key,value);
        ma.put(key2,value2);

        HSet keySet = ma.keySet();
        assertEquals("Controllo siano presenti le due chiavi",true,keySet.contains(key) && keySet.contains(key2));
    }

    @Test
    public void testPut(){
        Object toInsert = new Object();
        Integer key = toInsert.hashCode();

        assertEquals("Controllo non ci fosse nessun altro valore associato",null,ma.put(key,toInsert));
        assertEquals("Controllo sia aumentata la dimensione",1,ma.size());
        assertEquals("Controllo la coerenza con il metodo containsKey",true,ma.containsKey(key));
        assertEquals("Controllo la coerenza con il metodo containsValue",true,ma.containsKey(toInsert));
        assertEquals("Controllo la coerenza con il metodo get",toInsert,ma.get(key));

        Object substitute = new Object();
        assertEquals("Controllo ci fosse il precedente valore associato",toInsert,ma.put(key,substitute));
        assertEquals("Controllo non sia cambiata la dimensione",1,ma.size());
        assertEquals("Controllo la coerenza con il metodo containsKey",true,ma.containsKey(key));
        assertEquals("Controllo la coerenza con il metodo containsValue",true,ma.containsKey(substitute));
        assertNotEquals("Controllo non sia più presente il valore precedente",true,ma.containsKey(toInsert));
        assertEquals("Controllo la coerenza con il metodo get",substitute,ma.get(key));

        assertThrows("Non è permesso l'inserimento di chiavi nulle",NullPointerException.class,()->{
            ma.put(null,new Object());
        });
        assertThrows("Non è permesso l'inserimento di valori nulli",NullPointerException.class,()->{
            ma.put(new Object().hashCode(),null);
        });
    }

    @Test
    public void testPutAll(){
        HMap oldMap = new MapAdapter();

        ma.putAll(oldMap);
        assertNotEquals("Controllo non sia aumentata la dimensione fornando una mappa nulla",false,ma.isEmpty());

        Object obj1 = new Object();
        Object obj2 = new Object();
        oldMap.put(obj1.hashCode(),obj1);
        oldMap.put(obj2.hashCode(),obj2);

        ma.putAll(oldMap);
        assertEquals("Controllo sia aumentata la dimensione",2,ma.size());
        assertEquals("Controllo la coerenza con il metodo containsKey",true,ma.containsKey(obj1.hashCode()) && ma.containsKey(obj2.hashCode()));
        assertEquals("Controllo la coerenza con il metodo containsValue",true,ma.containsKey(obj1) && ma.containsKey(obj2));

        assertThrows("Non è permesso fornire un parametro mappa nullo",NullPointerException.class,()->{
            ma.putAll(null);
        });
    }

    @Test
    public void testContainsKey(){
        Object value = new Object();
        Integer key = value.hashCode();
        assertNotEquals("Cerco una chiave NON presente",true,ma.containsKey(key));

        ma.put(key,value);
        assertEquals("Cerco una chiave presente",true,ma.containsKey(key));

        assertThrows("Non è permesso l'inserimento di chiavi nulle",NullPointerException.class,()->{
            ma.containsKey(null);
        });
    }

    @Test
    public void testContainsValue(){
        Object value = new Object();
        Integer key = value.hashCode();
        assertNotEquals("Cerco una valore NON presente",true,ma.containsValue(value));

        ma.put(key,value);
        assertEquals("Cerco un valore presente",true,ma.containsValue(value));

        assertThrows("Non è permesso l'inserimento di chiavi nulle",NullPointerException.class,()->{
            ma.containsKey(null);
        });
    }

    @Test
    public void testRemoveKey(){
        Object toRemove = new Object();
        Integer key = toRemove.hashCode();

        assertEquals("Rimozione di un elemento non presente",null,ma.remove(key));

        ma.put(key,toRemove);

        assertEquals("Rimozione dell'elemento",toRemove,ma.remove(key));
        assertEquals("Controllo la coerenza con il metodo size",0,ma.size());
        assertNotEquals("Controllo la coerenza con il metodo containsKey",true,ma.containsKey(key));
        assertNotEquals("Controllo la coerenza con il metodo containsValue",true,ma.containsKey(toRemove));
        assertNotEquals("Controllo la coerenza con il metodo get",toRemove,ma.get(key));


        assertThrows("Non è permesso l'inserimento di chiavi nulle",NullPointerException.class,()->{
            ma.put(null,new Object());
        });
        assertThrows("Non è permesso l'inserimento di valori nulli",NullPointerException.class,()->{
            ma.put(new Object().hashCode(),null);
        });}

    @Test
    public void testValues(){
        /**
         * TODO: Test modifiche con iteratore attivo
         */
    }

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
