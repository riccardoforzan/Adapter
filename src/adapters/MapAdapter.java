package adapters;

import interfaces.HCollection;
import interfaces.HIterator;
import interfaces.HMap;
import interfaces.HSet;

import java.util.Hashtable;
import java.util.Map;

/**
 * This implementation of HMap interface DOES NOT ALLOW null values as input not as key, nor as value
 */
public class MapAdapter implements HMap {

    private final Hashtable ht;

    public MapAdapter(){
        ht = new Hashtable();
    }

    /**
     * Check if a HMap can be a valid parameter for this class.
     * A valid HMap is not null and does not contains null values, not as key nor as value.
     * @param tt HMap to test
     * @return return true if the HMap is valid, false otherwise
     */
    private static boolean isMapValid(HMap tt){
        if(tt==null) return false;
        boolean containsNull = false;
        try{
            containsNull |= tt.containsKey(null);
        }catch (NullPointerException npe){
            containsNull = false;
        }
        try{
            containsNull |= tt.containsValue(null);
        }catch (NullPointerException npe){
            containsNull = false;
        }
        return !containsNull;
    }

    /**
     * Returns the number of key-value mappings in this map.  If the
     * map contains more than <tt>Integer.MAX_VALUE</tt> elements, returns
     * <tt>Integer.MAX_VALUE</tt>.
     *
     * @return the number of key-value mappings in this map
     */
    @Override
    public int size() {
        return ht.size();
    }

    /**
     * Returns <tt>true</tt> if this map contains no key-value mappings.
     *
     * @return <tt>true</tt> if this map contains no key-value mappings
     */
    @Override
    public boolean isEmpty() {
        return ht.isEmpty();
    }

    /**
     * Returns <tt>true</tt> if this map contains a mapping for the specified
     * key.  More formally, returns <tt>true</tt> if and only if
     * this map contains a mapping for a key <tt>k</tt> such that
     * <tt>(key==null ? k==null : key.equals(k))</tt>.  (There can be
     * at most one such mapping.)
     *
     * @param key key whose presence in this map is to be tested
     * @return <tt>true</tt> if this map contains a mapping for the specified
     * key
     * @throws NullPointerException if the specified key is null
     */
    @Override
    public boolean containsKey(Object key) {
        if(key == null) throw new NullPointerException();
        return ht.containsKey(key);
    }

    /**
     * Returns <tt>true</tt> if this map maps one or more keys to the
     * specified value.  More formally, returns <tt>true</tt> if and only if
     * this map contains at least one mapping to a value <tt>v</tt> such that
     * <tt>(value==null ? v==null : value.equals(v))</tt>.  This operation
     * will probably require time linear in the map size for most
     * implementations of the <tt>Map</tt> interface.
     *
     * @param value value whose presence in this map is to be tested
     * @return <tt>true</tt> if this map maps one or more keys to the
     * specified value
     * @throws NullPointerException if the specified value is null
     */
    @Override
    public boolean containsValue(Object value) {
        if(value==null) throw new NullPointerException();
        return ht.containsValue(value);
    }

    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     * {@code null} if this map contains no mapping for the key
     * @throws NullPointerException if the specified key is null
     */
    @Override
    public Object get(Object key) {
        if(key==null) throw new NullPointerException();
        return ht.get(key);
    }

    /**
     * Associates the specified value with the specified key in this map
     * (optional operation).  If the map previously contained a mapping for
     * the key, the old value is replaced by the specified value.  (A map
     * <tt>m</tt> is said to contain a mapping for a key <tt>k</tt> if and only
     * if m.containsKey(k) would return
     * <tt>true</tt>.)
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with <tt>key</tt>, or
     * <tt>null</tt> if there was no mapping for <tt>key</tt>.
     * (A <tt>null</tt> return can also indicate that the map
     * previously associated <tt>null</tt> with <tt>key</tt>,
     * if the implementation supports <tt>null</tt> values.)
     * @throws NullPointerException          if the specified key or value is null
     */
    @Override
    public Object put(Object key, Object value){
        if(key==null || value==null) throw new NullPointerException();
        return ht.put(key,value);
    }

    /**
     * Removes the mapping for a key from this map if it is present
     *
     * <p>Returns the value to which this map previously associated the key,
     * or <tt>null</tt> if the map contained no mapping for the key.
     *
     * <p>The map will not contain a mapping for the specified key once the
     * call returns.
     *
     * @param key key whose mapping is to be removed from the map
     * @return the previous value associated with <tt>key</tt>, or
     * <tt>null</tt> if there was no mapping for <tt>key</tt>.
     * @throws NullPointerException          if the specified key is null
     */
    @Override
    public Object remove(Object key) {
        if(key==null) throw new NullPointerException();
        return ht.remove(key);
    }

    /**
     * Removes all of the mappings from this map (optional operation).
     * The map will be empty after this call returns.
     */
    @Override
    public void clear() {
        ht.clear();
    }

    /**
     * Compares the specified object with this map for equality.  Returns
     * <tt>true</tt> if the given object is also a map and the two maps
     * represent the same mappings.  More formally, two maps <tt>m1</tt> and
     * <tt>m2</tt> represent the same mappings if
     * <tt>m1.entrySet().equals(m2.entrySet())</tt>.  This ensures that the
     * <tt>equals</tt> method works properly across different implementations
     * of the <tt>Map</tt> interface.
     *
     * @param o object to be compared for equality with this map
     * @return <tt>true</tt> if the specified object is equal to this map
     */
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null || !(o instanceof HMap)) return false;
        HMap other = (HMap) o;
        return this.entrySet().equals(other.entrySet());
    }

    /**
     * Returns the hash code value for this map.  The hash code of a map is
     * defined to be the sum of the hash codes of each entry in the map's
     * <tt>entrySet()</tt> view.  This ensures that <tt>m1.equals(m2)</tt>
     * implies that <tt>m1.hashCode()==m2.hashCode()</tt> for any two maps
     * <tt>m1</tt> and <tt>m2</tt>, as required by the general contract of
     * <tt>Object.hashCode</tt>
     *
     * @return the hash code value for this map
     */
    public int hashCode(){
        return this.entrySet().hashCode();
    }

    /**
     * Copies all of the mappings from the specified map to this map
     * The effect of this call is equivalent to that
     * of calling put(Object,Object) put(k, v) on this map once
     * for each mapping from key <tt>k</tt> to value <tt>v</tt> in the
     * specified map.  The behavior of this operation is undefined if the
     * specified map is modified while the operation is in progress.
     *
     * @param t mappings to be stored in this map
     * @throws NullPointerException          if the specified map is null, the
     *                                       specified map contains null keys or values
     */
    @Override
    public void putAll(HMap t){
        if(!this.isMapValid(t)) throw new NullPointerException();
        HIterator it = t.entrySet().iterator();
        while(it.hasNext()) {
            MapAdapter.Entry entry = (MapAdapter.Entry) it.next();
            put(entry.getKey(), entry.getValue());
        }
    }

    public class Entry implements HMap.Entry{

        private final Object key;
        private Object value;

        public Entry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Returns the key corresponding to this entry.
         *
         * @return the key corresponding to this entry
         * @throws IllegalStateException implementations may, but are not
         *                               required to, throw this exception if the entry has been
         *                               removed from the backing map.
         */
        @Override
        public Object getKey() {
            return this.key;
        }

        /**
         * Returns the value corresponding to this entry.  If the mapping
         * has been removed from the backing map (by the iterator's
         * <tt>remove</tt> operation), the results of this call are undefined.
         *
         * @return the value corresponding to this entry
         * @throws IllegalStateException implementations may, but are not
         *                               required to, throw this exception if the entry has been
         *                               removed from the backing map.
         */
        @Override
        public Object getValue() {
            return this.value;
        }

        /**
         * Replaces the value corresponding to this entry with the specified
         * value (optional operation).  (Writes through to the map.)  The
         * behavior of this call is undefined if the mapping has already been
         * removed from the map (by the iterator's <tt>remove</tt> operation).
         *
         * @param value new value to be stored in this entry
         * @return old value corresponding to the entry
         * @throws UnsupportedOperationException if the <tt>put</tt> operation
         *                                       is not supported by the backing map
         * @throws ClassCastException            if the class of the specified value
         *                                       prevents it from being stored in the backing map
         * @throws NullPointerException          if the backing map does not permit
         *                                       null values, and the specified value is null
         * @throws IllegalArgumentException      if some property of this value
         *                                       prevents it from being stored in the backing map
         * @throws IllegalStateException         implementations may, but are not
         *                                       required to, throw this exception if the entry has been
         *                                       removed from the backing map.
         */
        @Override
        public Object setValue(Object value) {
            Object rv = value;
            this.value = value;
            return rv;
        }

        /**
         * Compares the specified object with this entry for equality.
         * Returns <tt>true</tt> if the given object is also a map entry and
         * the two entries represent the same mapping.  More formally, two
         * entries <tt>e1</tt> and <tt>e2</tt> represent the same mapping
         * if<pre>
         *     (e1.getKey()==null ?
         *      e2.getKey()==null : e1.getKey().equals(e2.getKey()))  &amp;&amp;
         *     (e1.getValue()==null ?
         *      e2.getValue()==null : e1.getValue().equals(e2.getValue()))
         * </pre>
         * This ensures that the <tt>equals</tt> method works properly across
         * different implementations of the <tt>Map.Entry</tt> interface.
         *
         * @param o object to be compared for equality with this map entry
         * @return <tt>true</tt> if the specified object is equal to this map
         *         entry
         */
        public boolean equals(Object o){
            if(this==o) return true;
            if(o==null || !(o instanceof HMap.Entry)) return false;
            HMap.Entry other = (HMap.Entry) o;
            return this.key.equals(other.getKey()) && this.value.equals(other.getValue());
        }

        /**
         * Returns the hash code value for this map entry.  The hash code
         * of a map entry <tt>e</tt> is defined to be: <pre>
         *     (e.getKey()==null   ? 0 : e.getKey().hashCode()) ^
         *     (e.getValue()==null ? 0 : e.getValue().hashCode())
         * </pre>
         * This ensures that <tt>e1.equals(e2)</tt> implies that
         * <tt>e1.hashCode()==e2.hashCode()</tt> for any two Entries
         * <tt>e1</tt> and <tt>e2</tt>, as required by the general
         * contract of <tt>Object.hashCode</tt>.
         *
         * @return the hash code value for this map entry
         */
        public int hashCode(){
            return this.key.hashCode() ^ this.key.hashCode();
        }
    }

    /**
     * Returns a Set view of the mappings contained in this map.
     * The set is backed by the map, so changes to the map are
     * reflected in the set, and vice-versa.  If the map is modified
     * while an iteration over the set is in progress (except through
     * the iterator's own <tt>remove</tt> operation, or through the
     * <tt>setValue</tt> operation on a map entry returned by the
     * iterator) the results of the iteration are undefined.  The set
     * supports element removal, which removes the corresponding
     * mapping from the map, via the <tt>Iterator.remove</tt>,
     * <tt>Set.remove</tt>, <tt>removeAll</tt>, <tt>retainAll</tt> and
     * <tt>clear</tt> operations.  It does not support the
     * <tt>add</tt> or <tt>addAll</tt> operations.
     *
     * @return a set view of the mappings contained in this map
     */
    @Override
    public HSet entrySet() {
        return new EntrySet();
    }

    private class EntrySet implements HSet {

        public EntrySet(){}

        @Override
        public int size() {
            return ht.size();
        }

        @Override
        public boolean isEmpty() {
            return ht.isEmpty();
        }

        @Override
        public boolean contains(Object o){
            if(o==null) throw new NullPointerException();
            if(! (o instanceof MapAdapter.Entry)) throw new IllegalArgumentException();
            Map.Entry entry = (Map.Entry) o;
            //Key To Test
            Object ktt = entry.getKey();
            Object vtt = entry.getValue();
            return ht.containsKey(ktt) && ht.get(ktt).equals(vtt);
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public Object[] toArray(Object[] a) {
            return new Object[0];
        }

        @Override
        public boolean add(Object e) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean remove(Object o) {
            if(o==null) throw new NullPointerException();
            if(! (o instanceof MapAdapter.Entry)) throw new IllegalArgumentException();
            Map.Entry entry = (Map.Entry) o;
            return ht.remove(entry.getKey()) == null;
        }

        @Override
        public boolean containsAll(HCollection c) {
            HIterator it = c.iterator();
            while(it.hasNext()){
                Object tmp = it.next();
                if(!this.contains(tmp)) return false;
            }
            return true;
        }

        @Override
        public boolean addAll(HCollection c) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean retainAll(HCollection c) {
            return false;
        }

        @Override
        public boolean removeAll(HCollection c) {
            return false;
        }

        @Override
        public void clear() {
            ht.clear();
        }

        @Override
        public HIterator iterator() {
            return null;
        }
    }

    /**
     * Returns a Set view of the keys contained in this map.
     * The set is backed by the map, so changes to the map are
     * reflected in the set, and vice-versa.  If the map is modified
     * while an iteration over the set is in progress (except through
     * the iterator's own <tt>remove</tt> operation), the results of
     * the iteration are undefined.  The set supports element removal,
     * which removes the corresponding mapping from the map, via the
     * <tt>Iterator.remove</tt>, <tt>Set.remove</tt>,
     * <tt>removeAll</tt>, <tt>retainAll</tt>, and <tt>clear</tt>
     * operations.  It does not support the <tt>add</tt> or <tt>addAll</tt>
     * operations.
     *
     * @return a set view of the keys contained in this map
     */
    @Override
    public HSet keySet() {
        return null;
    }

    /**
     * Returns a Collection view of the values contained in this map.
     * The collection is backed by the map, so changes to the map are
     * reflected in the collection, and vice-versa.  If the map is
     * modified while an iteration over the collection is in progress
     * (except through the iterator's own <tt>remove</tt> operation),
     * the results of the iteration are undefined.  The collection
     * supports element removal, which removes the corresponding
     * mapping from the map, via the <tt>Iterator.remove</tt>,
     * <tt>Collection.remove</tt>, <tt>removeAll</tt>,
     * <tt>retainAll</tt> and <tt>clear</tt> operations.  It does not
     * support the <tt>add</tt> or <tt>addAll</tt> operations.
     *
     * @return a collection view of the values contained in this map
     */
    @Override
    public HCollection values() {
        return null;
    }


}
