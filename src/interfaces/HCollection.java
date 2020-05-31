package interfaces;

/**
 * TODO: I metodi equals() e hashCode() funzionano guardando gli indirizzi di memoria
 */

public interface HCollection {

    /**
     * Aggiunge un elemento alla collezione, restituendo true nel caso questa sia cambiata a seguito dell'invocazione
     * @param o elemento da aggiungere alla collezione
     * @return true se la collezione ha subito cambiamenti a causa dell'invocazione del metodo, false se la collezione non permette l'inserimento di duplicati
     */
    boolean add(Object o);

    /**
     * Aggiunge gli elementi forniti come parametro alla collezione, restituendo true nel caso questa sia cambiata a seguito dell'invocazione
     * @param collection elementi da aggiungere alla collezione
     * @return true se la collezione ha subito cambiamenti a causa dell'invocazione del metodo, false se la collezione non permette l'inserimento di duplicati
     */
    boolean addAll(HCollection collection);

    /**
     * Rimuove tutti gli elementi dalla collezione. La collezione sarà vuota dopo la sua chiamata.
     */
    void clear();

    /**
     * Restituisce true se la collezione contiene almeno una occorrenza
     * dell'elemento specificato come parametro. Si basa sul metodo equals() dell'oggetto fornito.
     * @param o elemento di cui controllare la presenza
     * @return true se la collezione contiene l'elemento specificato
     */
    boolean contains(Object o);

    /**
     * Restutuisce true se la collezione su cui è invocato contiene tutti gli emlementi della collezione passata come parametro
     * @param collection collezione di elementi di cui controllare la presenza
     * @return true se la collezione su cui è invocato contiene contiene tutti gli elementi presenti nella collezione passata come parametro
     */
    boolean containsAll(HCollection collection);

    /**
     * Compara la collezione su cui è invocato (parametro implicito) con la collezione passata come parametro esplicito.
     * @param o collection con cui effettuare la comparazione
     * @return true se le due collection sono uguali
     */
    boolean equals(Object o);

    /**
     * Restituisce il valore dell'hashCode per la collezione su cui è invocato
     * @return valore dell'hashCode della collezione su cui è invocato
     */
    int hashCode();

    /**
     * Restituisce true se la collezione non contiene elementi
     * @return true se la collezione non contiene elementi
     */
    boolean isEmpty();

    /**
     * Restituisce un iteratore sugli elementi della collezione su cui è invocato.
     * Non ci sono garanzie sull'ordine di scorrimento degli elementi
     * (a meno che non venga invocato su una sottoclasse che preveda ordinamento).
     * @return iteratore sugli elementi della collezione
     */
    HIterator iterator();

    /**
     * Rimuove UNA singola istanza dell'elemento specificato dalla collezione se almeno una istanza è presente.
     * @param o oggetto da eliminare
     * @return true se la collezione ha subito cambiamenti a causa dell'invocazione del metodo
     */
    boolean remove(Object o);

    /**
     * Rimuove dalla collezione su cui è invocato TUTTE le istanze degli elementi della collezione passata come parametro
     * @param collection collezione di elementi da eliminare
     * @return true se la collezione ha subito cambiamenti a causa dell'invocazione del metodo
     */
    boolean removeAll(HCollection collection);

    /**
     * Mantiene dalla collezione solamente gli elementi che sono contenuti nella collezione passata come parametro
     * @param collection collezione di elementi da mantenere
     * @return true se la collezione ha subito cambiamenti a causa dell'invocazione del metodo
     */
    boolean retainAll(HCollection collection);

    /**
     * Restituisce il numero di elementi nella collezione
     * @return numero di elementi nella collezione
     */
    int size() ;

    /**
     * Restituisce un array contenente tutti gli elementi presenti nella collezione su cui è invocato.
     * TODO: L'ordinamento è il medesimo della collezione originale (se iterno l'originale e se scorro l'array vedo la stessa sequenza)
     * Restituisce un nuovo array che non viene referenziato dalla collection, quindi modifiche all'array non si ripercutono sulla collection.
     * Modifiche strutturali all'array non si propagano sull'originale e viceversa.
     * Modifiche agli oggetti interni all'array però si ripercuotono sulla collection (shallow copy).
     * @return array contenente tutti gli elementi della collezione
     */
    Object[] toArray();

}
