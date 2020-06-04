package test;

import interfaces.HIterator;
import interfaces.HCollection;

import adapters.SetAdapter;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.NoSuchElementException;

/**
 * UnsupportedOperationException, ClassCastException, IllegalArgumentException are not tested because
 * the implementation of SetAdapter does not throw this exception
 */

public class SetAdapterTester {

    private SetAdapter itt;

    @Before
    public void setup(){
        itt = new SetAdapter();
    }

    /**
     * @title Test of add(object) method
     * @description Test adding an object already contained in the collection
     * @expectedResults false, the object is already part of the collection
     * @preConditions the object to add must be already added
     */
    @Test
    public void test_addDuplicate() {
        Object toAdd = new Object();
        itt.add(toAdd);
        assertEquals("Inserting the same element for the second time, not allowed",false, itt.add(toAdd));
    }

    /**
     * @title Test of addAll(HCollection c) method
     * @description
     * @expectedResults
     * @actualResult
     * @dependencies
     * @preConditions
     * @postConditions
     */
    @Test
    public void testAddAll() {
        HCollection collection = new SetAdapter();
        Object obj1 = new Object();
        Object obj2 = new Object();
        collection.add(obj1);
        collection.add(obj2);

        assertEquals("Il set viene modificato aggiungendo due elementi",true, se.addAll(collection));
        assertEquals("Mi aspetto che siano stati aggiunti 2 elementi al set vuoto",2, se.size());
        assertEquals("Controllo con il metodo contains",true,se.contains(obj1) && se.contains(obj2));

        assertNotEquals("Controllo non vengano effettuati doppi inserimenti",true, se.addAll(collection));
        assertEquals(2, se.size());

        collection.add(new Object());
        assertEquals("Il set viene modificato aggiungendo un elemento",true, se.addAll(collection));
        assertEquals("Controllo che la dimensione sia aumentata sino a 3",3, se.size());

        assertThrows(NullPointerException.class, () -> {
            HCollection collection1 = new SetAdapter();
            collection1.add(null);
            se.addAll(collection1);
        });
    }

    /**
     * @title
     * @description
     * @expectedResults
     * @actualResult
     * @dependencies
     * @preConditions
     * @postConditions
     */
    @Test
    public void testRetainAll() {
        Object obj1 = new Object();
        Object obj2 = new Object();
        Object obj3 = new Object();
        Object obj4 = new Object();
        se.add(obj1);
        se.add(obj2);
        se.add(obj3);
        se.add(obj4);

        HCollection toRetain = new SetAdapter();
        toRetain.add(obj1);
        toRetain.add(obj2);

        assertEquals("Rimuovo tutti gli oggetti tranne quelli nella collezione, il metodo modifica la collezione",true,se.retainAll(toRetain));
        assertEquals("Controllo la dimensione del set rimanente",2,se.size());
        assertEquals("Controllo che contenga i due oggetti nella collezione",true,se.contains(obj1) && se.contains(obj2));
        assertNotEquals("Controllo che non contenga i due oggetti non presenti nella collezione",true,se.contains(obj3) || se.contains(obj4));

        assertNotEquals("La seconda invocazione non deve modificare la collezione",true,se.retainAll(toRetain));
        assertEquals("Controllo la dimensione del set, non deve essere modificata",2,se.size());

        assertThrows(NullPointerException.class, () -> se.retainAll(null));
        assertThrows(NullPointerException.class, () -> {
            HCollection collection1 = new SetAdapter();
            collection1.add(null);
            se.retainAll(collection1);
        });
    }

    /**
     * @title
     * @description
     * @expectedResults
     * @actualResult
     * @dependencies
     * @preConditions
     * @postConditions
     */
    @Test
    public void testContainsAll() {
        HCollection collection = new SetAdapter();
        collection.add(new Object());
        collection.add(new Object());
        se.addAll(collection);

        assertEquals("Controllo se il set contiene tutti gli elementi della collezione",true,se.containsAll(collection));

        Object NotFound = new Object();
        collection.add(NotFound);
        assertNotEquals("Aggiungo un oggetto alla collezione e controllo che ora il set non li contenga più tutti ",true,se.containsAll(collection));

        assertThrows("Tento la ricerca di un riferimento a null",NullPointerException.class, () -> se.containsAll(null));
        assertThrows(NullPointerException.class, () -> {
            HCollection collection1 = new SetAdapter();
            collection1.add(null);
            se.containsAll(collection1);
        });
    }

    /**
     * @title
     * @description
     * @expectedResults
     * @actualResult
     * @dependencies
     * @preConditions
     * @postConditions
     */
    @Test
    public void testRemoveAll() {
        Object obj1 = new Object();
        Object obj2 = new Object();
        Object obj3 = new Object();
        Object obj4 = new Object();
        se.add(obj1);
        se.add(obj2);
        se.add(obj3);
        se.add(obj4);

        HCollection toDelete = new SetAdapter();
        toDelete.add(obj3);
        toDelete.add(obj4);

        assertEquals("Rimozione degli oggetti della collezione dal set",true,se.removeAll(toDelete));
        assertEquals(2,se.size());
        assertEquals("Controllo che gli oggetti non presenti nella collezione appartengano ancora al set", true, se.contains(obj1) && se.contains(obj2));
        assertNotEquals("Controllo che gli oggetti presenti nella collezione non appartengano al set", true, (se.contains(obj3) || se.contains(obj4)) );

        //Modifica della collezione da eliminare
        toDelete.add(obj2);
        assertEquals("Aggiunto un elemento alla collezione da eliminare, controllo sia stato eliminato",true,se.removeAll(toDelete));
        assertEquals(1,se.size());
        assertEquals(se.contains(obj1), true);
        assertNotEquals(se.contains(obj2), true);
        assertNotEquals(se.contains(obj3), true);
        assertNotEquals(se.contains(obj4), true);

        assertNotEquals("Controllo una ulteriore invocazione non faccia alcuna modifica",true,se.removeAll(toDelete));
        assertEquals(1,se.size());

        assertThrows(NullPointerException.class, () -> se.remove(null));
        assertThrows(NullPointerException.class, () -> {
            HCollection collection1 = new SetAdapter();
            collection1.add(null);
            se.removeAll(collection1);
        });
    }

    // ________________________________________________________ //

    /**
     * L'implementazione dei due metodi sul controllo degli array è da riverede, ma
     * TODO: Il metodo assertArrayEquals() TIENE conto dell'ordinamento, non definito per set, quindi non può essere usato
     * Object[] expected = new Object[]{obj1,obj2};
     * assertArrayEquals("Array con due oggetti inseriti",expected,se.toArray());
     */

    /**
     * Dipende dal metodo add()
     * Controllo della correttezza con i metodi size() e contains()
     */
    @Test
    public void testToObjectArray() {
        Object[] objArray = se.toArray();
        assertEquals("Il set inizialmente non contiene nessun elemento",0,objArray.length);

        Object obj1 = new Object();
        Object obj2 = new Object();
        se.add(obj1);
        se.add(obj2);

        objArray = se.toArray();

        assertEquals("Controllo la dimensione dell'array sia corretta",2,objArray.length);

        boolean found_obj1 = false, found_obj2=false;
        for(int i=0;i<objArray.length;i++){
            Object tmp = objArray[i];
            if(!found_obj1 && tmp.equals(obj1)) found_obj1=true;
            else if(!found_obj2 && tmp.equals(obj2)) found_obj2 = true;
        }

        assertEquals("Controllo contenga i due elementi inseriti",true,found_obj1&&found_obj2);

    }

    /**
     * Dipende dal metodo add()
     * Controllo della correttezza con i metodi size() e contains()
     */
    @Test
    public void testToObjectGivenArray() {
        Object[] objArray = se.toArray(new Object[0]);
        assertEquals("Il set inizialmente non contiene nessun elemento",0,objArray.length);

        Object obj1 = new Object();
        Object obj2 = new Object();
        se.add(obj1);
        se.add(obj2);

        //Controllo cosa succede se passo una dimensione nulla
        objArray = se.toArray(new Object[0]);


        assertEquals("Controllo la dimensione dell'array sia corretta",2,objArray.length);
        boolean found_obj1, found_obj2;
        found_obj1=false;
        found_obj2=false;
        for(int i=0;i<objArray.length;i++){
            Object tmp = objArray[i];
            if(!found_obj1 && tmp.equals(obj1)) found_obj1=true;
            else if(!found_obj2 && tmp.equals(obj2)) found_obj2 = true;
        }
        assertEquals("Controllo contenga i due elementi inseriti",true,found_obj1&&found_obj2);

        //Controllo cosa succede se passo una dimensione maggiore di quella del set
        objArray = se.toArray(new Object[12]);

        assertEquals("Controllo la dimensione dell'array sia corretta",2,objArray.length);
        found_obj1=false;
        found_obj2=false;
        int null_counter=0;
        for(int i=0;i<objArray.length;i++){
            Object tmp = objArray[i];
            if(tmp == null) null_counter++;
            else if(!found_obj1 && tmp.equals(obj1)) found_obj1=true;
            else if(!found_obj2 && tmp.equals(obj2)) found_obj2 = true;
        }
        assertEquals("Controllo contenga i due elementi inseriti",true,found_obj1&&found_obj2);
        assertEquals("Controllo contenga 10 valori nulli (12 - 2)",10,null_counter);

    }
}