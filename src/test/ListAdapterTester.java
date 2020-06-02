package test;

import adapters.ListAdapter;
import adapters.SetAdapter;
import interfaces.HCollection;
import interfaces.HIterator;
import interfaces.HListIterator;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.NoSuchElementException;

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

    /**
     * Dipende da size() e get()
     */
    @Test
    public void testAddIndex(){

        //Inserimento in una lista vuota
        Object toAdd = new Object();
        la.add(0,toAdd);
        assertEquals("Inserimento di un elemento nella prima posizione",true,la.get(0).equals(toAdd) && la.size()==1);

        //Inserimento in testa
        Object toAdd2 = new Object();
        la.add(0,toAdd2);
        assertEquals("Inserimento di un secondo elemento nella prima posizione",true,la.get(0).equals(toAdd2) && la.size()==2);
        assertEquals("Controllo che l'elemento che precedentemente era il primo ora sia shiftato",true,la.get(1).equals(toAdd));

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
     * Dipende da size() get() e add()
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
     * Dipende da size(), contains() e add()
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

    /**
     * Dipende da add()
     * Controllo della coerenza con il metodo contains()
     * TODO: Forse meglio splittare il controllo della coerenza
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
     * TODO: Possibile l'integrazione tra i metodi IndexOf e LastIndexOf
     */

    /**
     * Dipende dal metodo get()
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

    @Test
    public void testListIteratorIndex(){
        /**
         * TODO
         */
    }

    @Test
    public void testRemoveIndex(){
        /**
         * TODO
         */
    }

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

    /**
     * Dipende dal metodo add(), size() e get()
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
     * Dipende da add()
     */
    @Test
    public void testSize(){
        assertEquals("La lista inizialmente è creata vuota",0,la.size());
        la.add(new Object());
        assertEquals("La lista contiene un elemento",1,la.size());
    }

    @Test
    public void testSubList(){
        /**
         * TODO
         */
    }

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
