package interfaces;
import java.util.NoSuchElementException;

public interface HListIterator extends HIterator {

    /**
     * Inserisce l'elemento passato come parametro prima della posizione dell'iteratore
     * @param o elemento da aggiungere
     * @throws IllegalArgumentException se alcuni aspetti dell'oggetto specificato non permettono l'inserimento di questo alla lista, ad esempio se la lista non supporta valori null e l'oggetto è null
     */
    void add(Object o);

    /**
     * Restituisce true se questo iteratore ha elementi da visitare a ritroso nella collezione.
     * @return true se questo iteratore ha elementi da visitare a ritroso nella collezione.
     */
    boolean hasPrevious();

    /**
     * Restituisce l'indice dell'elemento che sarebbe ritornato da una chiamata a next()
     * @return Restituisce l'indice dell'elemento che sarebbe ritornato da una chiamata a next(), oppure la dimensione della lista se si è alla fine di questa
     */
    int nextIndex();

    /**
     * Restituisce l'elemento precedente alla posizione dell'iteratore nella lista
     * @return l'elemento precedente alla posizione dell'iteratore
     * @throws NoSuchElementException se l'iteratore non ha elementi precedenti su cui iterare
     */
    Object previous();

    /**
     * Restituisce l'indice dell'elemento che verrebbe ritornato da una chiamata a previous(), -1 se si trova all'inizio della lista
     * @return l'indice dell'elemento che verrebbe ritornato da una chiamata a previous(), -1 se si trova all'inizio della lista
     */
    int previousIndex();

    /**
     * Sostituisce l'ultimo elemento restituito da next() o prevous() con l'elemento passato come parametro.
     * @param o elemento con cui sostituire l'ultimo elemento ritornato da next() o previous()
     * @throws IllegalStateException se non è stato chiamato ne next() o previous() prima della chiamata a questo metodo, oppure se remove() o add() sono stati chiamati subito prima di questo
     * @throws IllegalArgumentException se alcuni aspetti dell'oggetto specificato non permettono l'inserimento di questo alla lista, ad esempio se la lista non supporta valori null e l'oggetto è null
     */
    void set(Object o);

}
