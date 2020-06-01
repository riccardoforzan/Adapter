package interfaces;

public interface HList extends HCollection {

    /**
     * Inserisce l'elemento passato come parametro nella posizione passata come parametro
     * @param index posizione in cui inserire l'elemento
     * @param element elemento da inserire
     * @throws IndexOutOfBoundsException se l'indice è al di fuori dell'intervallo [0,size()]
     */
    void add(int index, Object element);

    /**
     * Aggiunge gli elementi della collezione passata come parametro nella lista a partire dalla posizione specificata come parametro
     * @param index posizione da cui cominciare l'inserimento
     * @param c collezione di elementi da inserire
     * @return true se la lista è cambiata a seguito dell'invocazione del metodo
     * @throws IndexOutOfBoundsException se l'indice è al di fuori dell'intervallo [0,size()]
     */
    boolean addAll(int index, HCollection c);

    /**
     * Restituisce l'elemento della lista nella posizione specificata come parametro
     * @param index posizione all'interno della lista
     * @return elemento in posizione index
     * @trows IndexOutOfBoundsException se l'indice è al di fuori dell'intervallo [0,size()]
     */
    Object get(int index);

    /**
     * Restituisce l'indice della prima occorrenza trovata nella lista dell'elemento passato come parametro
     * @param o elemento di cui ricercare l'indice nella lista
     * @return posizione della prima occorrenza dell'elemento trovato, -1 se non viene trovato
     */
    int indexOf(Object o);

    /**
     * Restituisce un iteratore a partire dalla posizione iniziale sulla lista su cui è invocato
     * @return iteratore sulla lista su cui è invocato
     */
    HListIterator listIterator();

    /**
     * Restituisce un iteratore, a partire dalla posizione su cui è passato come parametro, sulla lista su cui è invocato
     * @param index posizione iniziale dell'iteratore
     * @return iteratore dalla posizione fornita a seguire
     * @throws IndexOutOfBoundsException se l'indice è al di fuori dell'intervallo [0,size()]
     */
    HListIterator listIterator(int index);

    /**
     * Restituisce l'indice dell'ultima occorrenza trovata dell'elemento passato come parametro,
     * oppure -1 nel caso la lista non contenga l'elemento.
     * @param o occorrenza da cercare all'interno della lista
     * @return indice dell'ultima posizione in cui è stato trovato l'elemento specificato, se presente, -1 altrimenti
     */
    int lastIndexOf(Object o);

    /**
     * Rimuove dalla lista e restituisce l'elemento che si trova alla posizione specificata come parametro.
     * Effettua poi lo shft degli elementi che seguono.
     * @param index indice dell'elemento da rimuovere
     * @return l'elemento che si trovava nella posizione specificata come parametro
     * @throws IndexOutOfBoundsException se l'indice è al di fuori dell'intervallo [0,size()]
     */
    Object remove (int index);

    /**
     * Sostituisce l'elemento che si trova nella posizione specificata con quello passato come parametro
     * @param index indice dell'elemento da sostituire
     * @param element elemento da inserire
     * @return elemento che occupava la posizione passata come parametro prima della sostituzione
     * @throws IndexOutOfBoundsException se l'indice è al di fuori dell'intervallo [0,size()]
     */
    Object set(int index, Object element);

    /**
     * Restituisce una vista della porzione della lista compresa tra fromIndex e toIndex (se questi due sono uguali restituisce una lista vuota).
     * Cambiamenti alla lista restituita si riflettono sulla lista originaria, quindi quella su cui è stato chiamato il metodo subList().
     * Effettua la shallow copy degli elementi.
     * TODO: Le modifiche strutturali devono essere propagate dalla sublist alla lista di provenienza
     * Modifiche strutturali slla lista portano ad un comportamento indefinito della sublist
     * TODO: Documentare il comportamento indefinito
     * @param fromIndex punto di inizio (incluso)
     * @param toIndex punto terminale (escluso)
     * @return una nuova lista che è una parte di quella su cui il metodo è stato chiamato.
     * @throws IndexOutOfBoundsException se l'indice è al di fuori dell'intervallo [0,size()]
     */
    HList subList(int fromIndex, int toIndex);

}
