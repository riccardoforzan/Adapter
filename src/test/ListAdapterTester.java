package test;

import interfaces.HCollection;
import interfaces.HListIterator;

import adapters.ListAdapter;
import adapters.SetAdapter;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.NoSuchElementException;

public class ListAdapterTester {

    //Instance to test
    private ListAdapter itt;

    @Before
    public void setup(){
        itt = new ListAdapter();
    }

    /**
     * @title Test of add(object) method
     * @description Test adding an object already contained in the collection
     * @expectedResults true, the object is added
     * @preConditions the object to add must be already added
     */
    @Test
    public void test_addDuplicate() {
        Object toAdd = new Object();
        itt.add(toAdd);
        assertEquals("Adding a new element on an empty colleciton",true,itt.add(toAdd));
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
    public void testAddIndex(){

        //Inserimento in una lista vuota
        Object toAdd = new Object();
        la.add(0,toAdd);
        assertEquals("Inserimento di un elemento nella prima posizione",true,la.get(0).equals(toAdd) && la.size()==1);

        //Inserimento in coda
        Object toAdd3 = new Object();
        la.add(2,toAdd3);
        assertEquals("Inserimento di un terzo elemento in coda",true,la.get(2).equals(toAdd3) && la.size()==3);
        assertEquals("Controllo siano presenti i due elementi precedenti",true,la.get(0).equals(toAdd2) && la.get(1).equals(toAdd));

        assertThrows("Cerco di posizionare un elemento in una posizione non permessa (>= size())",IndexOutOfBoundsException.class, () -> {
            la.add(5,new Object());
        });
        assertThrows("Cerco di posizionare un elemento in una posizione non permessa (<0)",IndexOutOfBoundsException.class, () -> {
            la.add(-1,new Object());
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
    public void testAddAll(){
        ListAdapter cta = new ListAdapter();
        assertEquals("L'aggiunta di una collezione vuota non modifica la lista",false,la.addAll(cta));

        //Costruzione della collezione
        Object obj1 = new Object();
        Object obj2 = new Object();
        Object obj3 = new Object();
        cta.add(obj1);
        cta.add(obj2);
        cta.add(obj3);

        assertEquals("Aggiungo a questa lista una collezione",true,la.addAll(cta));
        assertEquals("Controllo sia stata aggiunta",3,la.size());
        assertEquals("Controllo che effettivamente contenga gli elementi inseriti",true,la.get(0).equals(obj1)&&la.get(1).equals(obj2)&&la.get(2).equals(obj3));

        assertEquals("Aggiungo a questa lista la stessa collezione una seconda volta, mi aspetto che la lista si modifichi visto che permette duplicati",true,la.addAll(cta));
        assertEquals("Controllo sia stata aggiunta",6,la.size());
        assertEquals("Controllo che effettivamente contenga gli elementi inseriti",true,
                la.get(0).equals(obj1) && la.get(1).equals(obj2) && la.get(2).equals(obj3) && la.get(3).equals(obj1) && la.get(4).equals(obj2) && la.get(5).equals(obj3));
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
    public void testAddAllIndex(){
        ListAdapter cta = new ListAdapter();
        assertEquals("L'aggiunta di una collezione vuota non modifica la lista",false,la.addAll(0,cta));

        //Costruzione della collezione
        Object obj1 = new Object();
        Object obj2 = new Object();
        Object obj3 = new Object();
        cta.add(obj1);
        cta.add(obj2);
        cta.add(obj3);

        Object alreadyInside = new Object();
        la.add(alreadyInside);

        assertEquals("Aggiungo a questa lista una collezione in testa shiftando gli oggetti",true,la.addAll(0,cta));
        assertEquals("Controllo sia stata aggiunta",4,la.size());
        assertEquals("Controllo che effettivamente contenga gli elementi inseriti",true,la.get(0).equals(obj1)&&la.get(1).equals(obj2)&&la.get(2).equals(obj3)&&la.get(3).equals(alreadyInside));

        assertEquals("Aggiungo a questa lista la stessa collezione una seconda volta però in coda",true,la.addAll(cta));
        assertEquals("Controllo sia stata aggiunta",7,la.size());
        assertEquals("Controllo che effettivamente contenga gli elementi inseriti",true,
                la.get(0).equals(obj1) && la.get(1).equals(obj2) && la.get(2).equals(obj3)
                        && la.get(3).equals(alreadyInside)
                        &&  la.get(4).equals(obj1) && la.get(5).equals(obj2) && la.get(6).equals(obj3));

        assertThrows("Cerco di posizionare una collezione in una posizione non permessa (>= size())",IndexOutOfBoundsException.class, () -> {
            la.add(15, cta);
        });
        assertThrows("Cerco di posizionare una collezione in una posizione non permessa (<0)",IndexOutOfBoundsException.class, () -> {
            la.add(-1, cta);
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
     * @title
     * @description
     * @expectedResults
     * @actualResult
     * @dependencies
     * @preConditions
     * @postConditions
     */
    @Test
    public void testIndexOf(){
        Object toFind = new Object();
        assertEquals("Controllo che nella collezione vuota non venga trovato l'oggetto",-1,la.indexOf(toFind));

        la.add(toFind);
        assertEquals("Controllo che l'oggetto sia presente nella collezione",true,la.contains(toFind));
        int indexResult = la.indexOf(toFind);
        assertEquals("Controllo che effettivamente restituisca l'indice corretto",true,la.get(indexResult).equals(toFind));

        la.add(toFind);
        assertEquals("Controllo che effettivamente restituisca la prima occorrenza trovata",0,la.indexOf(toFind));

        Object notPresent = new Object();
        assertEquals("Controllo che non venga trovato un oggetto non presente nella lista",-1,la.indexOf(notPresent));
        assertNotEquals("Controllo che non venga trovato un oggetto non presente nella lista",true,la.contains(notPresent));
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
    public void testLastIndexOf(){
        Object toFind = new Object();
        assertEquals("Controllo che nella collezione vuota non venga trovato l'oggetto",-1,la.indexOf(toFind));

        la.add(toFind);
        assertEquals("Controllo che l'oggetto sia presente nella collezione",true,la.contains(toFind));
        int indexResult = la.indexOf(toFind);
        assertEquals("Controllo che effettivamente restituisca l'indice corretto",true,la.get(indexResult).equals(toFind));

        la.add(toFind);
        assertEquals("Controllo che effettivamente restituisca l'ultima occorrenza trovata",1,la.indexOf(toFind));

        Object notPresent = new Object();
        assertEquals("Controllo che non venga trovato un oggetto non presente nella lista",-1,la.indexOf(notPresent));
        assertNotEquals("Controllo che non venga trovato un oggetto non presente nella lista",true,la.contains(notPresent));
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
    public void testListIterator(){
        HListIterator it = la.listIterator();
        assertEquals("La lista è vuota quindi l'iteratore non ha un successivo",false,it.hasNext());

        //Assumo siano testati tutti i metodi di un iteratore standard

        Object toAdd = new Object();
        it.add(toAdd);
        assertEquals("Controllo abbia inserito l'oggetto nella prima posizione",toAdd,la.get(0));

        assertEquals("Controllo che abbia un precedente",true,it.hasPrevious());
        assertEquals("Controllo che il precedente sia l'elemento appena inserito",toAdd,it.previous());

        assertEquals("Controllo che l'iteratore sia a inzio lista",-1,it.previousIndex());
        assertEquals("Controllo che l'iteratore sia a inzio lista",0,it.nextIndex());

        //Riporto in testa l'iteratore
        it=la.listIterator();
        Object newValue = new Object();
        it.set(newValue);
        assertEquals("Controllo che abbia modificato l'ogggetto iniziale",newValue,la.get(0));

        assertThrows(IllegalStateException.class, ()->{
            la.clear();
            HListIterator li = la.listIterator();
            li.set(new Object());
        });

        /**
         * TODO: Controllare
         */

        //remove or add have been called after the last call to next or previous.
        assertThrows(IllegalStateException.class, ()->{
            la.clear();
            HListIterator li = la.listIterator();
            Object toInsert = new Object();
            li.add(toInsert);
            li.set(new Object());
        });
        assertThrows(IllegalStateException.class, ()->{
            la.clear();
            HListIterator li = la.listIterator();
            Object toInsert = new Object();
            li.add(toInsert);
            li.remove();
            li.set(new Object());
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
    public void testListIteratorIndex(){
        /**
         * TODO
         */
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
    public void testRemoveIndex(){
        /**
         * TODO
         */
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
    public void testSet(){
        Object obj1 = new Object();
        la.add(0,obj1);

        Object substitute = new Object();
        Object previous = la.set(0,substitute);

        assertEquals("Controllo che la dimensione non sia cambiata",1,la.size());
        assertEquals("Controllo che sia stato restituto l'elemento contenuto precedentemente",true,obj1.equals(previous));
        assertEquals("Controllo che sia stato inserito il nuovo elemento",true,la.get(0).equals(substitute));

        assertThrows("Cerco di posizionare un elemento in una posizione non permessa (>= size())",IndexOutOfBoundsException.class, () -> {
            //Non è permesso usare set al posto di add()
            la.set(la.size(),new Object());
        });
        assertThrows("Cerco di posizionare un elemento in una posizione non permessa (<0)",IndexOutOfBoundsException.class, () -> {
            la.set(-1,new Object());
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
    public void testSubList(){
        /**
         * TODO
         */
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
