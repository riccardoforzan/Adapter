package test;

import interfaces.HCollection;
import interfaces.HIterator;
import adapters.SetAdapter;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import static org.junit.Assert.*;

public class SetAdapterTester {

    private SetAdapter se;

    @Before
    public void setup(){
        se = new SetAdapter();
    }

    @Test
    public void testAdd() {
        Object toAdd = new Object();
        assertEquals("Inserimento di un primo elemento",true, se.add(toAdd));
        assertNotEquals("Provo a inderire lo stesso oggetto due volte",true, se.add(toAdd));

        /**
         * ClassCastException è controllata a compile time
         */

        assertThrows(NullPointerException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                se.add(null);
            }
        });

        /**
         * TODO: Implementazione IllegalArgumentException
         */

    }

    /**
     * Test del metodo Contains
     * Dipende dalla correttezza del metodo add
     */
    @Test
    public void testContains() {
        Object toFind = new Object();

        assertEquals("Cerco un oggetto non presente nella collezione",false,se.contains(toFind));

        se.add(toFind);
        assertEquals("Cerco un oggetto presente nella collezione",true,se.contains(toFind));

        assertThrows(NullPointerException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                se.contains(null);
            }
        });
    }

    @Test
    public void testRemove() {
        Object obj1 = new Object();
        Object obj2 = new Object();
        se.add(obj1);
        se.add(obj2);

        assertEquals("Rimozione di un oggetto contenuto",true,se.remove(obj1));
        assertNotEquals("Controllo non permetta la rimozione dello stesso oggetto due volte",true,se.remove(obj1));

        assertThrows("Tento la rimozione di un riferimento a null",NullPointerException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                se.remove(null);
            }
        });

        /**
         * TODO: Implementazione IllegalArgumentException
         */
    }

    /**
     * Dipende dalla correttezza del metodo add() e del metodo size()
     */
    @Test
    public void testAddAll() {
        HCollection collection = new SetAdapter();
        collection.add(new Object());
        collection.add(new Object());

        assertEquals("Il set viene modificato aggiungendo due elementi",true, se.addAll(collection));
        assertEquals("Mi aspetto che siano stati aggiunti 2 elementi al set vuoto",2, se.size());

        assertNotEquals("Controllo non vengano effettuati doppi inserimenti",true, se.addAll(collection));
        assertNotEquals(4, se.size());

        collection.add(new Object());
        assertEquals("Il set viene modificato aggiungendo un elemento",true, se.addAll(collection));
        assertEquals("Controllo che la dimensione sia aumentata sino a 3",3, se.size());

        assertThrows(NullPointerException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                HCollection collection = new SetAdapter();
                collection.add(null);
                se.addAll(collection);
            }
        });

        /**
         * TODO: Implementazione IllegalArgumentException
         */

    }

    /**
     * Dipende dalla correttezza dei metodi add() e contains()
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
        assertEquals("Controllo che contenga i due oggetti nella collezione",true,(se.contains(obj1) && se.contains(obj2)) );
        assertNotEquals("Controllo che non contenga i due oggetti non presenti nella collezione",true, (se.contains(obj3) || se.contains(obj4)) );

        assertNotEquals("La seconda invocazione non deve modificare la collezione",true,se.retainAll(toRetain));

        assertThrows(NullPointerException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                se.retainAll(toRetain);
            }
        });

        /**
         * TODO: Implementazione IllegalArgumentException
         */
    }

    /**
     * Dipende dalla correttezza dei metodi add() e contains()
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
        assertEquals("Controllo che gli oggetti non presenti nella collezione appartengano ancora al set", true, (se.contains(obj1) && se.contains(obj2)) );
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

        assertThrows(NullPointerException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                se.remove(null);
            }
        });

        /**
         * TODO: Implementaizone IllegalArgumentException
         */
    }

    /**
     * Dipende dalla correttezza del metodo addAll() e add()
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
        assertNotEquals("Aggiungo un oggetto alla collezione e controllo che ora il set non li contenga più tutti ",false,se.containsAll(collection));

        assertThrows("Tento la ricerca di un riferimento a null",NullPointerException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                se.containsAll(null);
            }
        });

    }

    @Test
    @Ignore
    public void testIterator() {
        se.add(new Object());
        HIterator it = se.iterator();
        assertEquals(true,it.hasNext());

        se = new SetAdapter();
        it = se.iterator();
        assertNotEquals(true,it.hasNext());
    }

    /**
     * Test del metodo isEmpty
     * È dipendente dal metodo add
     */
    @Test
    public void testIsEmpty() {
        assertEquals("Un set appena creato deve essere vuoto",true,se.isEmpty());

        se.add(new Object());
        assertNotEquals("Un set a cui viene aggiunto un elemento non deve essere vuoto",true,se.isEmpty());
    }

    @Test
    public void testSize() {
        assertEquals("Un set appena creato deve avere dimensione 0",se.size(), 0);
    }

    /**
     * Dipende dai metodi add() e isEmpty()
     */
    @Test
    public void testClear() {
        se.clear();
        assertEquals("Invocazione su un set vuoto",true,se.isEmpty());
        //Riempimento del set
        se.add(new Object());
        se.add(new Object());
        assertEquals(2, se.size());
        se.clear();
        assertEquals("Invocazione su un set contenente elementi",0, se.isEmpty());
    }

    /**
     * TODO: Implementare il controllo di coerenza tra equals e hashCode
     */

    /**
     * Dipende dal metodo add()
     */
    @Test
    public void testEquals() {
        SetAdapter se2 = new SetAdapter();

        assertEquals(true, se.equals(se2));

        //Costruisco due set con gli stessi elementi
        Object obj1 = new Object();
        Object obj2 = new Object();
        se.add(obj1);
        se.add(obj2);
        se2.add(obj1);
        se2.add(obj2);
        assertEquals("Controllo che set con gli stessi elementi siano uguali",true,se.equals(se2));

        se2.add(new Object());
        assertNotEquals("Controllo che dopo l'aggiunta i due non siano più uguali",true,se.equals(se2));
    }

    /**
     * Dipende dal metodo add()
     */
    @Test
    public void testHashCode() {
        SetAdapter se2 = new SetAdapter();

        //Costruisco due set con gli stessi elementi
        Object obj1 = new Object();
        Object obj2 = new Object();
        se.add(obj1);
        se.add(obj2);
        se2.add(obj1);
        se2.add(obj2);

        //L'hashcode viene creato come somma degli hascode degli oggetti
        assertEquals("Controllo abbiano il medesimo hashcode, dato dalla somma degli hashcode degli elementi al suo interno",true, (se.hashCode() == se2.hashCode()) );

        //Aggiungo un oggetto, mi aspetto che gli hashcode siano diversi, perchè quello del nuovo Object inserito è legato all'indirizzo di memoria dell'oggetto
        se2.add(new Object());
        assertNotEquals("Controllo non abbiano il medesimo hashcode",true, (se.hashCode() == se2.hashCode()) );
    }

    @Test
    public void testToObjectArray() {
        Object[] objArray = se.toArray();
        assertEquals(se.size(),objArray.length);
    }

}