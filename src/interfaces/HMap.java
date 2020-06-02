package interfaces;

/**
 * La classe non supporta l'inserimento di valori null nè come chiave, nè come valore
 */
public interface HMap {

    /**
     * Rimuove tutte le associazioni chiave-valore dalla mappa su cui è invocato
     * @throws UnsupportedOperationException se l'implementazione della mappa non supporta questa funzionalità
     */
    void clear();

    /**
     * Restituisce true se la mappa su cui è invocato contiene un valore associato alla chiave specificata come parametro
     * @param o chiave di cui controllare la presenza nella mappa
     * @return true se viene trovata una corrispondenza chiave-valore nella mappa
     * @throws NullPointerException se l'implementazione della mappa non permette l'inserimento di chiavi null
     */
    boolean containsKey(Object o);

    /**
     * Restituisce true se la mappa su cui è invocato contiene una chiave associata al valore specificato come parametro
     * @param o valore di cui controllare la presenza nella mappa
     * @return true se viene trovata una corrispondenza chiave-valore nella mappa
     * @throws NullPointerException se l'implementazione della mappa non permette l'inserimento di valori null
     */
    boolean containsValue(Object o);

    /**
     * Restituisce una vista sotto forma d'insieme delle coppie chiave-valore conenute nella mappa.
     * Ogni elemento del set è un Map.Entry.
     * Modifiche al set si ripercuotono sulla mappa e viceversa.
     * Se la mappa viene modificata quando vi è un iteratore attivo il comportamento di quest'ultimo non è specificato.
     * TODO: Specificare il comportamento
     * TODO: È supportata l'eliminazione tramite l'iteratore ma non l'aggiunta. (ne add() ne addAll())
     * TODO: Le modifiche apportate tramite iteratore si ripercuotono sulla mappa.
     * @return una vista sotto forma d'insieme delle coppie chiave-valore contenute nella mappa
     */
    HSet entrySet();

    /**
     * Compara la mappa su cui è invocato (parametro implicito) con la mappa passata come parametro esplicito.
     * @param o mappa con cui effettuare la comparazione
     * @return true se le due mappe sono uguali
     */
    boolean equals(Object o);

    /**
     * Restituisce il valore associato alla chiave specificata come parametro, null altrimenti
     * @param o chiave di cui restituire il valore associato
     * @return valore associato alla chiave nel caso in cui questa sia presente nella mappa, null altrimenti
     * @throws NullPointerException se l'implementazione della mappa non permette l'inserimento di chiavi null
     */
    Object get(Object o);

    /**
     * Restituisce il valore dell'hashCode per la mappa su cui è invocato
     * @return valore dell'hashCode della mappa su cui è invocato
     */
    int hashCode();

    /**
     * Restituisce true se la mappa su cui è invocato non contiene alcuna coppia chiave-valore inserita
     * @return true se la mappa su cui è invocato non contiene alcuna coppia chiave-valore inserita
     */
    boolean isEmpty();

    /**
     * Restituisce una vista delle chiavi contenute nella mappa.
     * TODO: Modifiche effettuate al set (restituito da questo metodo) si riflettono anche sulla mappa su cui è stato chiamato il metodo.
     * Se la mappa viene modificata mentre c'è un iteratore attivo su questa il comportamento dell'iteratore non è specificato.
     * TODO: Specificare il comportamento
     * TODO: È supportata la rimozione di elementi ma non la loro aggiunta. (ne add() ne addAll())
     * @return una vista delle chiavi contenute nella mappa
     */
    HSet keySet();

    /**
     * Associa all'interno della mappa il valore specificato come parametro con la chiave specificata come parametro.
     * Restituisce il valore precedentemente associato se presente
     * @param key chiave a cui associare il valore
     * @param value valore da associare alla chiave specificata
     * @return valore precedentemente associato alla chiave specificata se precedentemente presente, null altrimenti
     * @throws NullPointerException se l'implementazione della mappa non permette l'inserimento di chiavi null
     * @throws NullPointerException se l'implementazione della mappa non permette l'inserimento di valori null
     * @throws UnsupportedOperationException se l'implementazione della mappa non supporta questa funzionalità
     */
    Object put(Object key, Object value);

    /**
     * Copia tutte le associazioni chiave-valore presenti sulla mappa passata come parametro sulla mappa attuale.
     * Il comportamento della mappa non è specificato se la mappa da cui
     * effettuare la copia viene modificata durante l'invocazione del metodo.
     * @param map associazioni chiave-valore da importare sulla mappa in cui il metodo è invocato
     * @throws NullPointerException se la mappa fornita come parametro è null oppure
     * @throws NullPointerException se l'implementazione della mappa non permette l'inserimento di chiavi null
     * presenti nella mappa sorgente
     * @throws NullPointerException se l'implementazione della mappa non permette l'inserimento di valori null
     * presenti nella mappa sorgente
     * @throws UnsupportedOperationException se l'implementazione della mappa non supporta questa funzionalità
     */
    void putAll(HMap map);

    /**
     * Rimuove l'associazione chiave-valore per la chiave specificata dalla mappa
     * @param key chiave di cui rimuovere l'associazione
     * @return valore associato alla chiave da rimuovere se presente, null altrimenti
     * @throws NullPointerException se l'implementazione della mappa non permette l'inserimento di chiavi null
     * @throws UnsupportedOperationException se l'implementazione della mappa non supporta questa funzionalità
     */
    Object remove(Object key);

    /**
     * Restituisce il numero delle coppie chiave-valore inserite nella mappa
     * @return numero delle coppie chiave-valore inserite nella mappa
     */
    int size();

    /**
     * Restituisce una collezione delle chiavi contenute nella mappa.
     * TODO: Modifiche alla collezione delle chiavi si riflettono sulla mappa e viceversa.
     * TODO: Se la mappa viene modificata mentre c'è un iteratore attivo su questa il comportamento non è definito,
     * TODO: Definire il comportamento
     * TODO: La collezione restituita supporta la rimozione di elementi ma non l'aggiunta. (ne add() ne addAll())
     * @return una collezione dei valori contenuti nella mappa
     */
    HCollection values();

    interface Entry{

        /**
         * Confronta l'oggetto implicito con quello passato come parametro per accertare se questi sono uguali.
         * Restituisce true se le due entry rappresentano la stessa coppia chiave-valore, false altrimenti
         * @param o oggetto con cui confrontare l'oggetto implicito
         * @return true se le due entry rappresentano la stessa coppia chiave-valore
         */
        @Override
        boolean equals(Object o);

        /**
         * Restituisce la chiave della entry su cui è invocato
         * @return chiave della entry su cui è invocato
         */
        Object getKey();

        /**
         * Restituisce il valore della entry su cui è invocato
         * @return valore della entry su cui è invocato
         */
        Object getValue();

        /**
         * Restituisce il valore dell'hash code per la entry su cui è invocato.
         * Il valore dell'hashcode per una entry è definito come:
         * (e.getKey()==null   ? 0 : e.getKey().hashCode()) ^ (e.getValue()==null ? 0 : e.getValue().hashCode())
         * @return valore dell'hashcode per la entry su cui è invocato.
         */
        @Override
        int hashCode();

        /**
         * Sostituisce il valore attuale della entry con quello passato come parametro.
         * Il comportamento non è definito se la entry è stata rimossa dalla mappa.
         * @param value nuovo valore della entry
         * @return valore precedente della entry
         * @throws NullPointerException se la mappa non permette l'inserimento di valori null
         * @throws UnsupportedOperationException se l'operazione non è supportata dall'implementazione della mappa
         */
        Object setValue(Object value);
    }

}
