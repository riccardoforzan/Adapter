package test;

import adapters.ListAdapter;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ListAdapterTester {

    private ListAdapter la;

    @Before
    public void setup(){
        la = new ListAdapter();
    }

    @Test
    public void testAddElement(){}

    @Test
    public void testAddElementIndex(){}

    @Test
    public void testAddAllCollection(){}

    @Test
    public void testAddAllCollectionIndex(){}

    @Test
    public void testClear(){
    }

    @Test
    public void testContains(){}

    @Test
    public void testContainsAll(){}

    @Test
    public void testEquals(){}

    @Test
    public void testHashCode(){}

    @Test
    public void testIndexOf(){}

    @Test
    public void testIsEmpty(){

    }

    @Test
    public void testIterator(){}

    @Test
    public void testLastIndexOf(){}

    @Test
    public void testListIterator(){}

    @Test
    public void testListIteratorIndex(){}

    @Test
    public void testRemoveIndex(){}

    @Test
    public void testRemoveObject(){}

    @Test
    public void testRemoveAll(){}

    @Test
    public void testReplaceAll(){}

    @Test
    public void testRetainAll(){}

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

    @Test
    public void testToObjectArray(){}

    @Test
    public void testToTypeArray(){}

}
