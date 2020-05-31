package interfaces;
import java.util.NoSuchElementException;

public interface HIterator {

    /**
     * Restituisce true se l'iterazione ha ancora elementi da visitare
     * @return true se l'iterazione ha ancora elementi da visitare
     */
    boolean hasNext();

    /**
     * Restituisce il prossimo elemento nell'iterazione
     * @return il prossimo elemento nell'iterazione
     * @throws NoSuchElementException se non ci sono ulteriori elementi su cui iterare
     */
    Object next();

    /**
     * Rimuove dalla collezione su cui sta iterando l'ultimo elemento restituito da next()
     * @throws IllegalStateException se non è stato chiamato successivamente ad una chiamata a next() oppure se viene chiamato due volte consecutivamente.
     */
    void remove();

}
