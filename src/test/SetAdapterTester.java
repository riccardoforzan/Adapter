package test;

import interfaces.HIterator;
import adapters.SetAdapter;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import static org.junit.Assert.*;

public class SetAdapterTester {

    @Test
    public void testAdd() {

        assertThrows(ClassCastException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                SetAdapter ses = new SetAdapter();
                Object value = new Integer(1);
                //TODO: Controllare quando lancia l'eccezione
                ses.add((String) value);
            }
        });

        assertThrows(NullPointerException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                SetAdapter sen = new SetAdapter();
                sen.add(null);
            }
        });

        /**
         * TODO: Implementazione IllegalArgumentException
         assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
        @Override
        public void run() throws Throwable {
        //TODO
        }
        });
         */

        SetAdapter se = new SetAdapter();

        //Test di funzionamento
        Object toAdd = new Object();

        assertEquals(true, se.add(toAdd));
        assertEquals(1, se.size());

        //Lo stesso oggetto non può essere inserito due volte
        assertNotEquals(true, se.add(toAdd));
    }

    @Test
    public void testContains() {

        //Controllo delle eccezioni
        assertThrows(ClassCastException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                SetAdapter sec = new SetAdapter();
                //TODO: Rivedere, lancia l'eccezione nel cast non in contains
                sec.contains((String) new Object());
            }
        });

        assertThrows(NullPointerException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                SetAdapter sen = new SetAdapter();
                sen.contains(null);
            }
        });

        //Controllo funzionamento
        SetAdapter se = new SetAdapter();
        Object toFind = new Object();

        //Controllo che non trovi l'oggetto prima che io lo immetta
        assertEquals(false,se.contains(toFind));

        //Controllo che trovi un oggetto che ho immesso
        se.add(toFind);
        assertEquals(true,se.contains(toFind));
    }

    @Test
    public void testRemove() {

        SetAdapter se = new SetAdapter();
        Object obj1 = new Object();
        Object obj2 = new Object();
        Object obj3 = new Object();
        se.add(obj1);
        se.add(obj2);
        se.add(obj3);

        assertEquals(true,se.remove(obj1));

        assertEquals(2,se.size());
        assertEquals(true,se.contains(obj2));
        assertEquals(true,se.contains(obj3));

        assertNotEquals(true,se.remove(obj1));
        assertNotEquals(true,se.contains(obj1));

        assertThrows(NullPointerException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                SetAdapter sen = new SetAdapter();
                sen.remove(null);
            }
        });

        /**
         //TODO: Implementazione ClassCastException e IllegalArgumentException
         assertThrows(ClassCastException.class, new ThrowingRunnable() {
        @Override
        public void run() throws Throwable {
        //TODO
        }
        });
         assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
        @Override
        public void run() throws Throwable {
        //TODO
        }
        });
         */
    }

    @Test
    public void testAddAll() {

        /* TODO: Implementazione ClassCastException e IllegalArgumentException
        assertThrows(ClassCastException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                //Non è permesso nelle interfacce
                SetAdapter<String> sec = new SetAdapter<>();
                Collection<Object> collection = new ArrayList<>();
                collection.add(new Object());
                collection.add(new Object());
                //sec.addAll(collection);
            }
        });
        assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
            }
        });
        */

        /* TODO: Come comportarsi con Collection?
        assertThrows(NullPointerException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                SetAdapter sen = new SetAdapter();
                Collection<Object> collection = new ArrayList<>();
                collection.add(null);
                sen.addAll(collection);
            }
        });

        SetAdapter<Object> se = new SetAdapter<>();

        //Creo una collezione e la importo nel set
        Collection<Object> collection = new ArrayList<>();
        collection.add(new Object());
        collection.add(new Object());
        assertEquals(true, se.addAll(collection));
        assertEquals(2, se.size());

        collection.add(new Object());
        assertEquals(true, se.addAll(collection));
        assertEquals(3, se.size());

        //Controllo che NON faccia doppi inserimenti
        assertNotEquals(true, se.addAll(collection));
        assertNotEquals(4, se.size());
         */

    }

    @Test
    public void testRetainAll() {
        SetAdapter se = new SetAdapter();

        Object obj1 = new Object();
        Object obj2 = new Object();
        Object obj3 = new Object();
        Object obj4 = new Object();
        se.add(obj1);
        se.add(obj2);
        se.add(obj3);
        se.add(obj4);

        /** TODO: Come comportarsi con Collecion?
        Collection<Object> toRetain = new ArrayList<>();
        toRetain.add(obj1);
        toRetain.add(obj2);

        //Rimozione di tutti gli oggetti tranne quelli nella collection toRetain
        assertEquals(true,se.retainAll(toRetain));
        assertEquals(true,se.contains(obj1));
        assertEquals(true,se.contains(obj2));
        assertNotEquals(true,se.contains(obj3));
        assertNotEquals(true,se.contains(obj4));

        //La seconda invocazione non deve modificare la collezione
        assertNotEquals(true,se.retainAll(toRetain));
         */

        /**
         * TODO:
         * Iternaodo internamente sugli oggetti dell'hashtable e poi controllando se questi sono nella
         * lista di quelli da salvare non accade mai che si controlli un valore nullo, perchè non
         * vengono mai inseriti valori nulli all'interno della hashmap.
         /
         assertThrows(NullPointerException.class, new ThrowingRunnable() {
        @Override
        public void run() throws Throwable {
        SetAdapter<Object> sen = new SetAdapter<>();
        Collection<Object> toRetain = new ArrayList<>();
        toRetain.add(null);
        sen.retainAll(toRetain);
        }
        });
         */

        /*TODO: Controllo delle eccezioni
        assertThrows(ClassCastException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                //TODO
            }
        });
        assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                //TODO
            }
        });
         */
    }

    @Test
    public void testRemoveAll() {
        SetAdapter se = new SetAdapter();
        Object obj1 = new Object();
        Object obj2 = new Object();
        Object obj3 = new Object();
        Object obj4 = new Object();
        se.add(obj1);
        se.add(obj2);
        se.add(obj3);
        se.add(obj4);


        /** TODO: Come comportarsi con Collection?
        Collection<Object> toDelete = new ArrayList<>();
        toDelete.add(obj3);
        toDelete.add(obj4);

        //Rimozione di tutti gli oggetti dal set
        assertEquals(true,se.removeAll(toDelete));

        assertEquals(se.contains(obj1), true);
        assertEquals(se.contains(obj2), true);
        assertNotEquals(se.contains(obj3), true);
        assertNotEquals(se.contains(obj4), true);

        toDelete.add(obj2);
        assertEquals(true,se.removeAll(toDelete));
        assertEquals(se.contains(obj1), true);
        assertNotEquals(se.contains(obj2), true);
        assertNotEquals(se.contains(obj3), true);
        assertNotEquals(se.contains(obj4), true);

        assertNotEquals(true,se.removeAll(toDelete));

        /**
         * TODO:
         * Iternaodo internamente sugli oggetti dell'hashtable e poi controllando se questi sono nella
         * lista di quelli da salvare non accade mai che si controlli un valore nullo, perchè non
         * vengono mai inseriti valori nulli all'interno della hashmap.
         /
         assertThrows(NullPointerException.class, new ThrowingRunnable() {
        @Override
        public void run() throws Throwable {
        SetAdapter<Object> sen = new SetAdapter<>();
        Collection<Object> toDelete = new ArrayList<>();
        toDelete.add(null);
        sen.remove(toDelete);
        }
        });
         */

        /**
         //TODO: Controllo delle eccezioni
         assertThrows(ClassCastException.class, new ThrowingRunnable() {
        @Override
        public void run() throws Throwable {
        //TODO
        }
        });
         assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
        @Override
        public void run() throws Throwable {
        //TODO
        }
        });
         */
    }

    @Test
    public void testContainsAll() {

        /**TODO: Implementazione ClassCastException
         assertThrows(ClassCastException.class, new ThrowingRunnable() {
        @Override
        public void run() throws Throwable {
        SetAdapter<String> sec = new SetAdapter<>();
        //TODO
        }
        });
         */

        /**
         * TODO: Perchè non funziona?
         assertThrows(NullPointerException.class, new ThrowingRunnable() {
        @Override
        public void run() throws Throwable {
        SetAdapter<String> sen = new SetAdapter<>();
        Collection<Object> collection = new ArrayList<>();
        collection.add(null);
        sen.contains(collection);
        }
        });
         */

        SetAdapter se = new SetAdapter();

        /**TODO: Come comportarsi con collection?
        //Creo una collezione e la importo nel set
        Collection<Object> collection = new ArrayList<>();
        collection.add(new Object());
        collection.add(new Object());
        se.addAll(collection);

        assertEquals(true,se.containsAll(collection));

        //Aggiungo un oggetto alla collezione e controllo che non sia più presente tutta la collezione nel set
        Object NotFound = new Object();
        collection.add(NotFound);
        assertNotEquals(false,se.containsAll(collection));
         */
    }

    @Test
    @Ignore
    public void testIterator() {
        SetAdapter se = null;
        HIterator it = null;

        se = new SetAdapter();
        se.add(new Object());
        it = se.iterator();
        assertEquals(true,it.hasNext());

        se = new SetAdapter();
        it = se.iterator();
        assertNotEquals(true,it.hasNext());

    }

    @Test
    public void testIsEmpty() {
        SetAdapter se = new SetAdapter();
        assertEquals(true,se.isEmpty());

        se.add(new Object());
        assertNotEquals(true,se.isEmpty());
    }

    @Test
    public void testSize() {
        SetAdapter se = new SetAdapter();
        assertEquals(se.size(), 0);

        /**
         se.add(new Object());
         int size = se.size();
         assertEquals(1, size);
         assertNotEquals(2, size);
         */
    }

    @Test
    public void testClear() {
        SetAdapter se = new SetAdapter();
        //Test di funzionamento
        se.add(new Object());
        se.add(new Object());
        assertEquals(2, se.size());
        se.clear();
        assertEquals(0, se.size());
        //Test di non funzionamento
        se.add(new Object());
        assertEquals(1, se.size());
        se.clear();
        assertNotEquals(1, se.size());
    }

    @Test
    public void testEquals() {
        SetAdapter setA = new SetAdapter();
        SetAdapter setB = new SetAdapter();

        assertEquals(true, setA.equals(setB));

        //Costruisco due set identici con gli stessi elementi

        Object obj1 = new Object();
        Object obj2 = new Object();
        setA.add(obj1);
        setA.add(obj2);
        setB.add(obj1);
        setB.add(obj2);

        assertEquals(true,setA.equals(setB));

        setB.add(new Object());
        assertNotEquals(true,setA.equals(setB));
    }

    @Test
    public void testHashCode() {
        SetAdapter setA = new SetAdapter();
        SetAdapter setB = new SetAdapter();

        Object obj1 = new Object();
        Object obj2 = new Object();
        setA.add(obj1);
        setA.add(obj2);
        setB.add(obj1);
        setB.add(obj2);

        //L'hashcode viene creato come somma degli hascode degli oggetti
        assertEquals(setA.hashCode(), setB.hashCode());

        //Aggiungo un oggetto, mi aspetto che gli hashcode siano diversi, perchè quello del nuovo Object inserito è legato all'indirizzo di memoria dell'oggetto
        setB.add(new Object());
        assertNotEquals(setA.hashCode(), setB.hashCode());
    }

    @Test
    public void testToObjectArray() {
        SetAdapter se = new SetAdapter();
        Object[] objArray = se.toArray();
        assertEquals(se.size(),objArray.length);
    }

}