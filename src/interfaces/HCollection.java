package interfaces;

public interface HCollection {

    /**
     * Aggiunge un elemento alla collezione, restituendo true nel caso questa sia cambiata a seguito dell'invocazione
     * @param o elemento da aggiungere alla collezione
     * @return true se la collezione ha subito cambiamenti a causa dell'invocazione del metodo, false se la collezione non permette l'inserimento di duplicati
     * @throws NullPointerException se la collezione in cui si sta invocando il metodo non supporta l'inserimento di valori null
     * @throws UnsupportedOperationException
     */
    boolean add(Object o);

    /**
     * Aggiunge gli elementi forniti come parametro alla collezione, restituendo true nel caso questa sia cambiata a seguito dell'invocazione
     * @param c elementi da aggiungere alla collezione
     * @return true se la collezione ha subito cambiamenti a causa dell'invocazione del metodo, false se la collezione non permette l'inserimento di duplicati
     * @throws NullPointerException se la collezione in cui si sta invocando il metodo non supporta l'inserimento di valori null e la collezione parametro contiene almeno un valore null
     */
    boolean addAll(HCollection c);

    /**
     * Rimuove tutti gli elementi dalla collezione. La collezione sarà vuota dopo la sua chiamata.
     */
    void clear();

    /**
     * Restituisce true se la collezione contiene almeno una occorrenza dell'elemento specificato come parametro.
     * Si basa sul metodo equals() dell'oggetto fornito.
     * @param o elemento di cui controllare la presenza
     * @return true se la collezione contiene l'elemento specificato
     * @throws NullPointerException se la collezione in cui si sta invocando il metodo non supporta l'inserimento di valori null
     */
    boolean contains(Object o);

    /**
     * Restutuisce true se la collezione su cui è invocato contiene tutti gli emlementi della collezione passata come parametro
     * @param c collezione di elementi di cui controllare la presenza
     * @return true se la collezione su cui è invocato contiene contiene tutti gli elementi presenti nella collezione passata come parametro
     * @throws NullPointerException se la collezione in cui si sta invocando il metodo non supporta l'inserimento di valori null e la collezione parametro contiene almeno un valore null
     */
    boolean containsAll(HCollection c);

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
     * Non ci sono garanzie sull'ordine di scorrimento degli elementi, a meno che non venga invocato su una sottoclasse che preveda ordinamento.
     * @return iteratore sugli elementi della collezione
     */
    HIterator iterator();

    /**
     * Rimuove UNA singola istanza dell'elemento specificato dalla collezione se almeno una istanza è presente.
     * @param o oggetto da eliminare
     * @return true se la collezione ha subito cambiamenti a causa dell'invocazione del metodo
     * @throws NullPointerException se la collezione in cui si sta invocando il metodo non supporta l'inserimento di valori null
     */
    boolean remove(Object o);

    /**
     * Rimuove dalla collezione su cui è invocato TUTTE le istanze degli elementi della collezione passata come parametro
     * @param c collezione di elementi da eliminare
     * @return true se la collezione ha subito cambiamenti a causa dell'invocazione del metodo
     * @throws NullPointerException se la collezione in cui si sta invocando il metodo non supporta l'inserimento di valori null e la collezione parametro contiene almeno un valore null
     */
    boolean removeAll(HCollection c);

    /**
     * Mantiene dalla collezione solamente gli elementi che sono contenuti nella collezione passata come parametro
     * @param c collezione di elementi da mantenere
     * @return true se la collezione ha subito cambiamenti a causa dell'invocazione del metodo
     * @throws NullPointerException se la collezione in cui si sta invocando il metodo non supporta l'inserimento di valori null e la collezione parametro contiene almeno un valore null
     */
    boolean retainAll(HCollection c);

    /**
     * Restituisce il numero di elementi nella collezione
     * @return numero di elementi nella collezione
     */
    int size() ;

    /**
     * Restituisce un array contenente tutti gli elementi presenti nella collezione su cui è invocato.
     * L'ordinamento è il medesimo della collezione originale (se iterno l'originale e se scorro l'array vedo la stessa sequenza), nel caso in cui questa abbia un ordinamento
     * Restituisce un nuovo array che non viene referenziato dalla collection, quindi modifiche all'array non si ripercutono sulla collection.
     * Modifiche strutturali all'array non si propagano sull'originale e viceversa.
     * Modifiche agli oggetti interni all'array però si ripercuotono sulla collection (shallow copy).
     * @return array contenente tutti gli elementi della collezione
     */
    Object[] toArray();

    /**
     * Restituisce un array contenente tutti gli elementi della collezione.
     * Il tipo di ritorno runtime dell'array è il tipo dell'array passato come parametro.
     * TODO: Se viene fornito un array di dimensione minore rispetto a quella supportata deve esserne creato uno di più grande.
     * Se viene fornito un array più grande del necessario i valori successivi a quelli della collezione vengono impostati a null.
     * L'array viene fornito nello stesso ordine in cui si trovano gli elementi della collezione, se un ordinaento per quella collezione esiste.
     * @param a array del tipo del valore di ritorno
     * @return array del tipo specificato come parametro contenente gli elementi della collezione
     */
    Object[] toArray(Object[] a);

}
