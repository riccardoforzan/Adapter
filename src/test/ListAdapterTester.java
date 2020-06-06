package test;

import interfaces.HCollection;
import interfaces.HListIterator;

import adapters.ListAdapter;
import adapters.SetAdapter;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ListAdapterTester extends CollectionTester{

    @Before
    public void setup(){
        super.itt = new ListAdapter();
    }

    /**
     * Method that returns a non empty collection
     * This method must be OVERRIDE by the concrete implementation of a HCollection Tester
     *
     * @return a not empty HCollection
     */
    @Override
    protected HCollection createNotEmptyCollection() {
        HCollection rv = new ListAdapter();
        rv.add(new Object());
        rv.add(new Object());
        return rv;
    }

    /**
     * Method that returns a non empty collection
     * This method must be OVERRIDE by the concrete implementation of a HCollection Tester
     *
     * @return a HCollection with at least a null value inside
     */
    @Override
    protected HCollection createCollectionWithNull() {
        HCollection rv = new ListAdapter();
        rv.add(null);
        return rv;
    }

    /**
     * Method that creates an empty collection
     * This method must be OVERRIDE by the concrete implementation of a HCollection Tester
     *
     * @return an empty HCollection
     */
    @Override
    protected HCollection createEmptyCollection() {
        return new ListAdapter();
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
        assertTrue("Adding a new element on an empty collection", itt.add(toAdd));
    }

    /**
     * @title Test of toArray() method
     * @description Test toArray() method , the behavior depends:
     * @expectedResults for ListAdapter the order is defined, so the returned array must have the same order of the list
     * @dependencies uses ListAdapter.add(element) to setup the object
     * @preConditions list must not be empty
     */
    @Override
    public void test_toArray_notEmpty() {
        ListAdapter la = (ListAdapter) itt;
        Object[] expected = new Object[] {new Object(), new Object()};
        la.add(expected[0]);
        la.add(expected[1]);
        Object[] result = la.toArray();
        assertArrayEquals("Checking the array (also the order)",expected,result);

    }

    /**
     * @title Test of toArray(Object[] a) method
     * @description Test toArray(Object[] a) method giving as parameter an array that array.length == collection.size(), the behavior depends:
     * @expectedResults for ListAdapter the order is defined, so the returned array must have the same order of the list
     * @expectedResults for SetAdapter the order is not defined, so the returned array has an undefined order
     * @expectedResults in both cases it uses to return the array given as parameter
     */
    @Override
    public void test_toArrayGivenType_notEmpty() {
        ListAdapter la = (ListAdapter) itt;
        Object[] expected = new Object[] {new Object(), new Object()};
        la.add(expected[0]);
        la.add(expected[1]);
        Object[] parameterToChange = new Object[2];
        la.toArray(parameterToChange);
        assertArrayEquals("Parameter changed",expected,parameterToChange);
    }

    /**
     * @title Test of toArray(Object[] a) method
     * @description Test toArray(Object[] a) method giving an array smaller than the collection's size, the behavior depends:
     * @expectedResults for ListAdapter the order is defined, so the returned array must have the same order of the list
     *                  in both cases it allocates a NEW array of the same length as the size of the collection
     * @dependencies  uses ListAdapter.add(element) to setup the object and uses ListAdapter.toArray() to check correctness
     * @preConditions list must not be empty
     */
    @Override
    public void test_toArrayGivenType_small() {
        ListAdapter la = (ListAdapter) itt;
        Object[] expected = new Object[] {new Object(), new Object()};
        la.add(expected[0]);
        la.add(expected[1]);
        Object[] result = la.toArray(new Object[0]);
        assertArrayEquals("Testing array equals",expected,result);
    }


    /**
     * @title Test of toArray(Object[] a) method
     * @description Test toArray(Object[] a) method giving an array larger than the collection's size, the behavior depends:
     * @expectedResults for ListAdapter the order is defined, so the returned array must have the same order of the list
     * @expectedResults for SetAdapter the order is not defined, so the returned array has an undefined order
     * @expectedResults in both cases it uses to return the array given as a parameter, with the empty positions set at null
     */
    @Override
    public void test_toArrayGivenType_large() {
        ListAdapter la = (ListAdapter) itt;
        Object[] expected = new Object[] {new Object(), new Object()};
        la.add(expected[0]);
        la.add(expected[1]);
        Object[] parameterToChange = new Object[0];
        la.toArray(parameterToChange);

        boolean correct = true;
        for(int i=0;i<parameterToChange.length && correct;i++){
            if(i<expected.length) {
                if (!expected[i].equals(parameterToChange[i])){
                    correct = false;
                }
            } else {
                if(expected[i]!=null) correct = false;
            }
        }

        assertTrue("The given array has been modified and it's correct",correct);
    }

    //STARTING TEST OF ListAdapter ONLY METHODS

    /**
     * @title Test add(index,element) method
     * @description Testing some insertion using add(index,element)
     * @expectedResults Successful insertions on specified positions
     * @dependencies uses the method ListAdapter.get(index) and ListAdapter.size() to check correctness
     * @preConditions list must be empty
     */
    @Test
    public void testAddIndex(){
        ListAdapter la = (ListAdapter) itt;

        Object toAdd = new Object();
        la.add(0,toAdd);
        assertEquals("Inserting an element in first position", la.get(0), toAdd);

        Object toAdd2 = new Object();
        la.add(1,toAdd2);
        assertTrue("Checking the list after tail insertion ", la.get(0).equals(toAdd2) && la.get(1).equals(toAdd) && la.size()==2);
    }

    /**
     * @title Testing add(index,element) throws IndexOutOfBoundException
     * @description Test the case in which the method throws IndexOutOfBoundException
     * @expectedResults IndexOutOfBoundException thrown
     * @preConditions List where insertions are made must be empty
     */
    @Test
    public void check_AddIndex_ioobe(){
        ListAdapter la = (ListAdapter) itt;
        assertThrows("Inserting in position >= size())",IndexOutOfBoundsException.class, () -> la.add(5,new Object()));
        assertThrows("Inserting in position <0",IndexOutOfBoundsException.class, () -> la.add(-1,new Object()));
    }

    /**
     * @title Testing add(index,element) throws NullPointerException
     * @description Test the case in which the method throws NullPointerException
     * @expectedResults NullPointerException thrown
     */
    @Test
    public void check_AddIndex_npe(){
        ListAdapter la = (ListAdapter) itt;
        assertThrows("Inserting a null value",NullPointerException.class, () -> la.add(0,null));
    }

    /**
     * @title Test addAll(index,collection) method
     * @description Testing insertion using addAll(index,collection)
     * @expectedResults Successful insertions of given collection on specified position
     * @dependencies uses the method ListAdapter.add(element) to set up the list
     *               uses the method ListAdapter.get(index) and ListAdapter.size() to check correctness
     * @preConditions list contains 1 element
     */
    @Test
    public void testAddAllIndex(){
        ListAdapter la = (ListAdapter) itt;

        Object alreadyInside = new Object();
        la.add(alreadyInside);

        ListAdapter given = (ListAdapter) createNotEmptyCollection();

        assertTrue("Adding elements on head of collection",la.addAll(0,given));
        assertEquals("Checking size",3,la.size());

        boolean result = la.get(0) == given.get(0) && la.get(1) == given.get(1) && la.get(2) == alreadyInside;
        assertTrue("Checking result correctness",result);
    }

    /**
     * @title Test addAll(index,collection) method
     * @description Testing insertion of an empty using addAll(index,collection)
     * @expectedResults False, the collection has not been changed after invocation
     * @preConditions list must be empty
     */
    @Test
    public void testAddAllIndex_void(){
        ListAdapter la = (ListAdapter) itt;
        HCollection given = createEmptyCollection();
        assertFalse("Collection is not changed",la.addAll(given));
    }

    /**
     * @title Testing add(index,element) throws IndexOutOfBoundException
     * @description Test the case in which the method throws IndexOutOfBoundException
     * @expectedResults IndexOutOfBoundException thrown
     * @preConditions List where insertions are made must be empty
     */
    @Test
    public void check_AddAllIndex_ioobe(){
        ListAdapter la = (ListAdapter) itt;
        assertThrows("Inserting in position >= size())",IndexOutOfBoundsException.class, () -> la.add(5,new Object()));
        assertThrows("Inserting in position <0",IndexOutOfBoundsException.class, () -> la.add(-1,new Object()));
    }

    /**
     * @title Testing add(index,element) throws NullPointerException
     * @description Test the case in which the method throws NullPointerException
     * @expectedResults NullPointerException thrown
     */
    @Test
    public void check_AddAllIndex_npe(){
        ListAdapter la = (ListAdapter) itt;
        assertThrows("Inserting a null value",NullPointerException.class, () -> la.add(0,null));
    }

    /**
     * TODO
     */

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
    public void testSubList(){
        /**
         * TODO
         */
    }


}
