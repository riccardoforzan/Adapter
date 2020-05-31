package test;

import adapters.CollectionAdapter;
import adapters.ListAdapter;

import adapters.SetAdapter;
import interfaces.HCollection;
import interfaces.HIterator;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class ListAdapterTester {

    private ListAdapter la;

    @Before
    public void setup(){
        la = new ListAdapter();
    }

    /**
     * Dipende da size()
     */
    @Test
    public void testAdd(){
        Object toAdd = new Object();
        assertEquals("Aggiunta di un oggetto alla collezione",true,la.add(toAdd));
        assertEquals("Controllo sia aumentata la dimensione",1,la.size());

        assertEquals("Aggiunta dello stesso oggetto alla collezione",true,la.add(toAdd));
        assertEquals("Controllo sia aumentata la dimensione",2,la.size());

        assertEquals("Aggiunta di un NULL alla collezione",true,la.add(toAdd));
        assertEquals("Controllo sia aumentata la dimensione",1,la.size());
    }

    @Test
    public void testAddIndex(){}

    /**
     * Dipende da size()
     */
    @Test
    public void testAddAll(){
        CollectionAdapter cta = new CollectionAdapter();
        cta.add(new Object());
        cta.add(new Object());
        cta.add(new Object());

        /**
         * TODO: Potrei controllare che effettivamente abbia aggiunto quegli elementi con il metodo contains()
         */

        assertEquals("Aggiungo a questa lista una collezione",true,la.addAll(cta));
        assertEquals("Controllo sia stata aggiunta",3,la.size());

        assertEquals("Aggiungo a questa lista la stessa collezione una seconda volta, mi aspetto che la lista si modifichi visto che permette duplicati",true,la.addAll(cta));
        assertEquals("Controllo sia stata aggiunta",6,la.size());
    }

    @Test
    public void testAddAllIndex(){}

    /**
     * Dipende da add(), size() e isEmpty()
     */
    @Test
    public void testClear(){
        assertEquals("Controllo sia vuota una lista appena creata",true,la.isEmpty());

        la.add(new Object());
        la.add(new Object());
        assertEquals("Controllo non sia vuota in partenza",2,la.size());

        la.clear();
        assertEquals("Controllo sia vuota dopo l'invocazione",true,la.isEmpty());
    }

    /**
     * Dipende da add()
     */
    @Test
    public void testContains(){
        Object toFind = new Object();

        assertEquals("Cerco un oggetto non presente nella lista",false,la.contains(toFind));

        la.add(toFind);
        assertEquals("Cerco un oggetto presente nella lista",true,la.contains(toFind));
    }

    @Test
    public void testContainsAll(){
        HCollection collection = new ListAdapter();
        collection.add(new Object());
        collection.add(new Object());
        la.addAll(collection);

        assertEquals("Controllo se la lista contiene tutti gli elementi della collezione",true,la.containsAll(collection));

        Object NotFound = new Object();
        collection.add(NotFound);
        assertNotEquals("Aggiungo un oggetto alla collezione e controllo che ora la lista non li contenga più tutti ",true,la.containsAll(collection));
    }

    /**
     * Dipende dal metodo add()
     */
    @Test
    public void testEquals(){
        ListAdapter la2 = new ListAdapter();

        assertEquals(true, la.equals(la2));

        //Costruisco due liste con gli stessi elementi
        Object obj1 = new Object();
        Object obj2 = new Object();
        la.add(obj1);
        la.add(obj2);
        la2.add(obj1);
        la2.add(obj2);
        assertEquals("Controllo che liste con gli stessi elementi siano uguali",true,la.equals(la2));

        la2.add(new Object());
        assertNotEquals("Controllo che dopo l'aggiunta le due non siano più uguali",true,la.equals(la2));
    }

    /**
     * Dipende dal metodo add()
     */
    @Test
    public void testHashCode(){
        ListAdapter la2 = new ListAdapter();

        //Costruisco due liste con gli stessi elementi
        Object obj1 = new Object();
        Object obj2 = new Object();
        la.add(obj1);
        la.add(obj2);
        la2.add(obj1);
        la2.add(obj2);

        //L'hashcode viene creato come somma degli hascode degli oggetti
        assertEquals("Controllo abbiano il medesimo hashcode, dato dalla somma degli hashcode degli elementi al suo interno",true, (la.hashCode() == la2.hashCode()) );

        //Aggiungo un oggetto, mi aspetto che gli hashcode siano diversi, perchè quello del nuovo Object inserito è legato all'indirizzo di memoria dell'oggetto
        la2.add(new Object());
        assertNotEquals("Controllo non abbiano il medesimo hashcode",true, (la.hashCode() == la2.hashCode()) );
    }

    /**
     * Test della consistenza tra il metodo equals() e hashCode()
     * Dipende da add(), equals() e hashCode()
     */
    @Test
    public void TestConsistencyEqualsHashCode(){
        ListAdapter la2 = new ListAdapter();
        assertEquals("Se entrambe sono vuote allora sono uguali",true, (la.equals(la2)&&la.hashCode()==la2.hashCode()));

        //Costruisco due set con gli stessi elementi
        Object obj1 = new Object();
        Object obj2 = new Object();
        la.add(obj1);
        la.add(obj2);
        la2.add(obj1);
        la2.add(obj2);

        //L'hashcode viene creato come somma degli hascode degli oggetti
        assertEquals("Se entrambe hanno gli stessi elementi allora sono uguali",true, (la.equals(la2)&&la.hashCode()==la2.hashCode()));

        //Aggiungo un oggetto, mi aspetto che gli hashcode siano diversi e che i due oggetti non siano più uguali
        la2.add(new Object());
        assertNotEquals("Controllo non siano uguali",true,la.equals(la2));
        assertNotEquals("Controllo non abbiano il medesimo hashcode",true, (la.hashCode() == la2.hashCode()) );
    }

    @Test
    public void testIndexOf(){}

    /**
     * Dipende da add() e remove()
     */
    @Test
    public void testIsEmpty(){
        assertEquals("Controllo che una collezione appena creata sia vuota",true,la.isEmpty());

        Object toAdd = new Object();
        la.add(toAdd);
        assertNotEquals("Controllo che dopo l'inserimento di un elemento non sia vuota",true,la.isEmpty());

        la.remove(toAdd);
        assertEquals("Controllo che dopo la rimozione dell'unico elemento sia vuota",true,la.isEmpty());
    }

    /**
     * Dipende da add(), size()
     */
    @Test
    public void testIterator(){
        HIterator it = la.iterator();
        assertEquals("La lista è vuota quindi l'iteratore non ha un successivo",false,it.hasNext());

        Object obj1 = new Object();
        Object obj2 = new Object();
        Object obj3 = new Object();
        la.add(obj1);
        la.add(obj2);
        la.add(obj3);

        it = la.iterator();
        assertEquals("La collezione ha più elementi quindi deve eslare possibile invocare next",true,it.hasNext());

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
        int initSize = la.size();
        it = la.iterator();
        removed = it.next();
        it.remove();

        HIterator it2 = la.iterator();
        int actualSize = 0;
        while(it2.hasNext()) actualSize++;
        assertEquals("Dopo l'invocazione di remove la dimensione è diminuita di 1 unità",true,(actualSize+1)==initSize);
        assertEquals("L'elemento rimosso non deve più far parte del lat",true,la.contains(removed));

        assertThrows("L'iteratore non ha un elemento successivo", NoSuchElementException.class, () -> {
            la.clear();
            la.iterator().next();
        });

        assertThrows("Il metodo Remove() non può eslare invocato sull'iteratore prima di aver invocato next", NoSuchElementException.class, () -> {
            la.clear();
            la.add(new Object());
            HIterator it1 = la.iterator();
            it1.remove();
        });

        assertThrows("Il metodo Remove() non può eslare invocato due volte conlacutivamente", NoSuchElementException.class, () -> {
            la.clear();
            la.add(new Object());
            la.add(new Object());
            HIterator it12 = la.iterator();
            it12.next();
            it12.remove();
            it12.remove();
        });
    }

    @Test
    public void testLastIndexOf(){}

    @Test
    public void testListIterator(){}

    @Test
    public void testListIteratorIndex(){}

    @Test
    public void testRemoveIndex(){}

    @Test
    public void testRemove(){
        Object obj1 = new Object();
        Object obj2 = new Object();
        la.add(obj1);
        la.add(obj2);

        /**
         * TODO: Forse potrei aggiungere una dipendenza dal metodo size()
         */

        assertEquals("Rimozione di un oggetto contenuto",true,la.remove(obj1));
        assertNotEquals("Controllo non permetta la rimozione dello stesso oggetto due volte",true,la.remove(obj1));
    }

    /**
     * Dipende dal metodo add() e contains()
     */
    @Test
    public void testRemoveAll(){
        Object obj1 = new Object();
        Object obj2 = new Object();
        Object obj3 = new Object();
        Object obj4 = new Object();
        la.add(obj1);
        la.add(obj2);
        la.add(obj3);
        la.add(obj4);

        HCollection toDelete = new SetAdapter();
        toDelete.add(obj3);
        toDelete.add(obj4);

        assertEquals("Rimozione degli oggetti della collezione dalla lista",true,la.removeAll(toDelete));
        assertEquals(2,la.size());
        assertEquals("Controllo che gli oggetti non presenti nella collezione appartengano ancora alla lista", true, (la.contains(obj1) && la.contains(obj2)) );
        assertNotEquals("Controllo che gli oggetti presenti nella collezione non appartengano alla lista", true, (la.contains(obj3) || la.contains(obj4)) );

        //Modifica della collezione da eliminare
        toDelete.add(obj2);
        assertEquals("Aggiunto un elemento alla collezione da eliminare, controllo sia stato eliminato",true,la.removeAll(toDelete));
        assertEquals(1,la.size());
        assertEquals(la.contains(obj1), true);
        assertNotEquals(la.contains(obj2), true);
        assertNotEquals(la.contains(obj3), true);
        assertNotEquals(la.contains(obj4), true);

        assertNotEquals("Controllo una ulteriore invocazione non faccia alcuna modifica",true,la.removeAll(toDelete));
        assertEquals(1,la.size());
    }

    @Test
    public void testReplaceAll(){}

    /**
     * Dipende dal metodo add() e contains()
     */
    @Test
    public void testRetainAll(){
        Object obj1 = new Object();
        Object obj2 = new Object();
        Object obj3 = new Object();
        Object obj4 = new Object();
        la.add(obj1);
        la.add(obj2);
        la.add(obj3);
        la.add(obj4);

        HCollection toRetain = new SetAdapter();
        toRetain.add(obj1);
        toRetain.add(obj2);

        assertEquals("Rimuovo tutti gli oggetti tranne quelli nella collezione, il metodo modifica la collezione",true,la.retainAll(toRetain));
        assertEquals("Controllo che contenga i due oggetti nella collezione",true,(la.contains(obj1) && la.contains(obj2)) );
        assertNotEquals("Controllo che non contenga i due oggetti non presenti nella collezione",true, (la.contains(obj3) || la.contains(obj4)) );

        assertNotEquals("La seconda invocazione non deve modificare la collezione",true,la.retainAll(toRetain));
    }

    @Test
    public void testSet(){}

    /**
     * Dipende da add()
     */
    @Test
    public void testSize(){
        assertEquals("La lista inizialmente è creata vuota",0,la.size());
        la.add(new Object());
        assertEquals("La lista contiene un elemento",1,la.size());
    }

    @Test
    public void testSort(){}

    @Test
    public void testSubList(){}

    /**
     * Controllo mantenga la sequenza di visita della lista.
     * Dipende dal metodo add()
     */
    @Test
    public void testToArray(){
        Object[] objArray = la.toArray();
        assertEquals("La lista inizialmente non contiene nessun elemento",0,objArray.length);

        Object obj1 = new Object();
        Object obj2 = new Object();
        la.add(obj1);
        la.add(obj2);
        Object[] expected = new Object[]{obj1,obj2};
        //Il metodo assertArrayEquals controlla che gli oggetti negli array si trovino nello stesso ordine
        assertArrayEquals("Array con due oggetti inseriti, nello stesso ordine",expected,la.toArray());
    }

}
