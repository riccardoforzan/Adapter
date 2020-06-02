package test;

import interfaces.HIterator;
import interfaces.HCollection;

import adapters.SetAdapter;

import org.junit.Before;
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
     * Dipende dal metodo add()
     */
    @Test
    public void testContains() {
        Object toFind = new Object();
        assertEquals("Cerco un oggetto non presente nel set",false,se.contains(toFind));

        se.add(toFind);
        assertEquals("Cerco un oggetto presente nel set",true,se.contains(toFind));

        assertThrows(NullPointerException.class, () -> se.contains(null));
    }

    /**
     * Dipende dal metodo add()
     */
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

    @Test
    public void testSize() {
        assertEquals("Un set appena creato deve avere dimensione 0",se.size(), 0);
    }

    /**
     * Test incrociato dei metodi add() remove() contains() e size()
     */
    @Test
    public void testARCS(){
        Object obj1 = new Object();
        Object obj2 = new Object();
        assertEquals("Inserimento di un nuovo elemento",true,se.add(obj1));
        assertEquals("Inserimento di un nuovo elemento",true,se.add(obj2));

        assertEquals("Controllo inserimento con il metodo size",2,se.size());
        assertEquals("Controllo inserimento con il metodo contains",true,se.contains(obj1));
        assertEquals("Controllo inserimentocon il metodo contains",true,se.contains(obj2));

        assertEquals("Rimozione di un oggetto contenuto",true,se.remove(obj1));
        assertEquals("Controllo rimozione con il metodo size",1,se.size());
        assertNotEquals("Controllo rimozione con il metodo contains",true,se.contains(obj1));
        assertEquals("Controllo rimozione con il metodo contains",true,se.contains(obj2));
    }

    /**
     * Dipende dai metodi add() e remove()
     */
    @Test
    public void testIsEmpty() {
        assertEquals("Un set appena creato deve essere vuoto",true,se.isEmpty());

        Object toAdd = new Object();
        se.add(toAdd);
        assertNotEquals("Un set a cui viene aggiunto un elemento non deve essere vuoto",true,se.isEmpty());

        se.remove(toAdd);
        assertEquals("Controllo che dopo la rimozione dell'unico elemento sia vuoto",true,se.isEmpty());
    }

    /**
     * Dipende dal metodo add()
     * Controllo incrociato della correttezza col metodo isEmpty
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
     * Controllo incrociato della correttezza con i metodi contains() e size()
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

        assertThrows(NullPointerException.class, () -> { se.addAll(null); });

        assertThrows(NullPointerException.class, () -> {
            HCollection collection1 = new SetAdapter();
            collection1.add(null);
            se.addAll(collection1);
        });
    }

    /**
     * Dipende dal metodo add()
     * Controllo incrociato della correttezza con i metodi size() e contains()
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
        assertNotEquals("Aggiungo un oggetto alla collezione e controllo che ora il set non li contenga più tutti ",true,se.containsAll(collection));

        assertThrows("Tento la ricerca di un riferimento a null",NullPointerException.class, () -> se.containsAll(null));
        assertThrows(NullPointerException.class, () -> {
            HCollection collection1 = new SetAdapter();
            collection1.add(null);
            se.containsAll(collection1);
        });
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

    /**
     * Dipende dal metodo add()
     * Controllo incrociato della correttezza col metodo size()
     */
    @Test
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
        assertEquals("La collezione ha più elementi quindi deve essere possibile invocare next",true,it.hasNext());

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
     * Dipende da add()
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