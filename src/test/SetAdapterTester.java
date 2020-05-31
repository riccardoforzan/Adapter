package test;

import interfaces.HCollection;
import interfaces.HIterator;
import adapters.SetAdapter;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.NoSuchElementException;

/**
 * L'eccezione UnsupportedOperationException non è testata in quanto per definizione i metodi non implementati
 * lanciano tale eccezione.
 *
 * La classe lavora con Object quindi sta al programmatore controllare cosa immette all'interno di questa.
 *
 * L'eccezione ClassCastException non è testata in quanto viene controllata durante la compilazione.
 *
 * L'eccezione IllegalArgumentException non è testata, tutte le classi estendono Object e SetAdapter lavora con Object.
 */

/**
 * TODO: Forse provare a fare qualche metodo con le operazioni add() e remove() in fila per verificarne il funzionamento in serie
 */

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

        assertThrows(NullPointerException.class, () -> se.add(null));
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

        assertThrows(NullPointerException.class, () -> se.contains(null));
    }

    @Test
    public void testRemove() {
        Object obj1 = new Object();
        Object obj2 = new Object();
        se.add(obj1);
        se.add(obj2);

        assertEquals("Rimozione di un oggetto contenuto",true,se.remove(obj1));
        assertNotEquals("Controllo non permetta la rimozione dello stesso oggetto due volte",true,se.remove(obj1));

        assertThrows("Tento la rimozione di un riferimento a null",NullPointerException.class, () -> se.remove(null));
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

        assertThrows(NullPointerException.class, () -> {
            HCollection collection1 = new SetAdapter();
            collection1.add(null);
            se.addAll(collection1);
        });
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

        assertThrows(NullPointerException.class, () -> se.retainAll(null));
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

        assertThrows(NullPointerException.class, () -> se.remove(null));
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

        assertThrows("Tento la ricerca di un riferimento a null",NullPointerException.class, () -> se.containsAll(null));

    }

    /**
     * Dipende da add(), size()
     */
    @Test
    @Ignore
    public void testIterator() {
        HIterator it = se.iterator();
        assertEquals("L'insieme è vuoto quindi l'iteratore non ha un successivo",false,it.hasNext());

        Object obj1 = new Object();
        Object obj2 = new Object();
        Object obj3 = new Object();
        se.add(obj1);
        se.add(obj2);
        se.add(obj3);

        it = se.iterator();
        assertEquals("La collezione ha più elemento quindi deve essere possibile invocare next",true,it.hasNext());

        /**
         * Mi assicuro che l'iteratore iteri sul numero corretto di elementi, e che veda tutti gli elementi una sola
         * volta visto che sono presenti una sola volta per costruzione
         */
        int items=0;
        int found_obj1 = 0;
        int found_obj2 = 0;
        int found_obj3 = 0;
        while(it.hasNext()){
            Object tmp = it.next();
            items++;
            if(obj1.equals(tmp)){
                found_obj1++;
                break;
            }
            if(obj2.equals(tmp)) {
                found_obj2++;
                break;
            }
            if(obj3.equals(tmp)){
                found_obj1++;
                break;
            }
        }
        boolean test = items==3 && found_obj1==1 && found_obj2==1 && found_obj3==1;
        assertEquals("L'iteratore itera sul numero corretto di elemeti e nella maniera corretta",true,test);

        assertNotEquals("L'iteratore non può più avanzare dopo aver restituito tutti gli elementi della lista",true,it.hasNext());

        //Rimozione del primo elemento trovato dall'iteratore
        Object removed;
        int initSize = se.size();
        it = se.iterator();
        removed = it.next();
        it.remove();

        HIterator it2 = se.iterator();
        int actualSize = 0;
        while(it2.hasNext()) actualSize++;
        assertEquals("Dopo l'invocazione di remove la dimensione è diminuita di 1 unità",true,(actualSize+1)==initSize);
        assertEquals("L'elemento rimosso non deve più far parte del set",true,se.contains(removed));

        assertThrows("L'iteratore non ha un elemento successivo", NoSuchElementException.class, () -> {
            se.clear();
            se.iterator().next();
        });

        assertThrows("Il metodo Remove() non può essere invocato sull'iteratore prima di aver invocato next", NoSuchElementException.class, () -> {
            se.clear();
            se.add(new Object());
            HIterator it1 = se.iterator();
            it1.remove();
        });

        assertThrows("Il metodo Remove() non può essere invocato due volte consecutivamente", NoSuchElementException.class, () -> {
            se.clear();
            se.add(new Object());
            se.add(new Object());
            HIterator it12 = se.iterator();
            it12.next();
            it12.remove();
            it12.remove();
        });

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

    /**
     * Test della consistenza tra il metodo equals() e hashCode()
     * Dipende da add(), equals() e hashCode()
     */
    @Test
    public void TestConsistencyEqualsHashCode(){
        SetAdapter se2 = new SetAdapter();
        assertEquals("Se entrambi sono vuoti allora sono uguali",true, (se.equals(se2)&&se.hashCode()==se2.hashCode()));

        //Costruisco due set con gli stessi elementi
        Object obj1 = new Object();
        Object obj2 = new Object();
        se.add(obj1);
        se.add(obj2);
        se2.add(obj1);
        se2.add(obj2);

        //L'hashcode viene creato come somma degli hascode degli oggetti
        assertEquals("Se entrambi hanno gli stessi elementi allora sono uguali",true, (se.equals(se2)&&se.hashCode()==se2.hashCode()));

        //Aggiungo un oggetto, mi aspetto che gli hashcode siano diversi e che i due oggetti non siano più uguali
        se2.add(new Object());
        assertNotEquals("Controllo non siano uguali",true,se.equals(se2));
        assertNotEquals("Controllo non abbiano il medesimo hashcode",true, (se.hashCode() == se2.hashCode()) );
    }

    /**
     * Dipende dal metodo add()
     */
    @Test
    public void testToObjectArray() {
        Object[] objArray = se.toArray();
        assertEquals("Il set inizialmente non contiene nessun elemento",0,objArray.length);

        Object obj1 = new Object();
        Object obj2 = new Object();
        se.add(obj1);
        se.add(obj2);
        Object[] expected = new Object[]{obj1,obj2};
        assertArrayEquals("Array con due oggetti inseriti",expected,se.toArray());
    }

}