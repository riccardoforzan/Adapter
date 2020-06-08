package adapters;

import interfaces.HCollection;
import interfaces.HIterator;
import interfaces.HSet;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.NoSuchElementException;

/**
 * This implementation of HSet interface DOES NOT ALLOW null values as input.
 * This implementation of HSet doesn't make any guarantees about the order of its elements.
 */
public class SetAdapter implements HSet {

    private final Hashtable ht;

    public SetAdapter(){
        ht = new Hashtable();
    }

    /**
     * Returns the number of elements in this set (its cardinality).  If this
     * set contains more than <tt>Integer.MAX_VALUE</tt> elements, returns
     * <tt>Integer.MAX_VALUE</tt>.
     *
     * @return the number of elements in this set (its cardinality)
     */
    @Override
    public int size() {
        return ht.size();
    }

    /**
     * Returns <tt>true</tt> if this set contains no elements.
     *
     * @return <tt>true</tt> if this set contains no elements
     */
    @Override
    public boolean isEmpty() {
        return ht.isEmpty();
    }

    /**
     * Returns <tt>true</tt> if this set contains the specified element.
     * More formally, returns <tt>true</tt> if and only if this set
     * contains an element <tt>e</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
     *
     * @param o element whose presence in this set is to be tested
     * @return <tt>true</tt> if this set contains the specified element
     * @throws ClassCastException   if the type of the specified element
     *                              is incompatible with this set (optional)
     * @throws NullPointerException if the specified element is null and this
     *                              set does not permit null elements (optional)
     */
    @Override
    public boolean contains(Object o) {
        int hashCode = o.hashCode();
        if(!ht.containsKey(hashCode)) return false;
        return o.equals(ht.get(hashCode));
    }

    /**
     * Returns an array containing all of the elements in this set.
     * This set doesn't make any gaurantees about the order of its elements.
     *
     * <p>The returned array will be "safe" in that no references to it
     * are maintained by this set.  (In other words, this method must
     * allocate a new array even if this set is backed by an array).
     * The caller is thus free to modify the returned array.
     *
     * @return an array containing all the elements in this set
     */
    @Override
    public Object[] toArray() {
        Object[] rv = new Object[ht.size()];
        HIterator it = this.iterator();
        int i=0;
        while(it.hasNext()) rv[i++]=it.next();
        return rv;
    }

    /**
     * Returns an array containing all of the elements in this set; the
     * runtime type of the returned array is that of the specified array.
     * If the set fits in the specified array, it is returned therein.
     * Otherwise, a new array is allocated with the runtime type of the
     * specified array and the size of this set.
     *
     * <p>If this set fits in the specified array with room to spare
     * (i.e., the array has more elements than this set), the element in
     * the array immediately following the end of the set is set to
     * <tt>null</tt>.
     *
     * <p>This set doesn't make any gaurantees about the order of its elements.
     *
     * Note that <tt>toArray(new Object[0])</tt> is identical in function to
     * <tt>toArray()</tt>.
     *
     * @param a the array into which the elements of this set are to be
     *          stored, if it is big enough; otherwise, a new array of the same
     *          runtime type is allocated for this purpose.
     * @return an array containing all the elements in this set
     * @throws ArrayStoreException  if the runtime type of the specified array
     *                              is not a supertype of the runtime type of every element in this
     *                              set
     * @throws NullPointerException if the specified array is null
     */
    @Override
    public Object[] toArray(Object[] a) {
        if(a == null) throw new NullPointerException();
        if(a.length < ht.size()) a = new Object[ht.size()];
        HIterator it = this.iterator();
        int i=0;
        while(it.hasNext()) a[i++]=it.next();
        while(i<a.length) a[i++]=null;
        return a;
    }

    /**
     * Adds the specified element to this set if it is not already present
     * (optional operation).  More formally, adds the specified element
     * <tt>e</tt> to this set if the set contains no element <tt>e2</tt>
     * such that
     * <tt>(e==null&nbsp;?&nbsp;e2==null&nbsp;:&nbsp;e.equals(e2))</tt>.
     * If this set already contains the element, the call leaves the set
     * unchanged and returns <tt>false</tt>.  In combination with the
     * restriction on constructors, this ensures that sets never contain
     * duplicate elements.
     *
     * <p> This set does NOT ALLOW <tt>null</tt> elements.
     *
     * @param e element to be added to this set
     * @return <tt>true</tt> if this set did not already contain the specified
     * element
     * @throws NullPointerException          if the specified element is null
     */
    @Override
    public boolean add(Object e) {
        //Throws NullPointerException if e == null
        if(contains(e)) return false;
        ht.put(e.hashCode(),e);
        return true;
    }

    /**
     * Removes the specified element from this set if it is present
     * (optional operation).  More formally, removes an element <tt>e</tt>
     * such that
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>, if
     * this set contains such an element.  Returns <tt>true</tt> if this set
     * contained the element (or equivalently, if this set changed as a
     * result of the call).  (This set will not contain the element once the
     * call returns.)
     *
     * @param o object to be removed from this set, if present
     * @return <tt>true</tt> if this set contained the specified element
     * @throws NullPointerException          if the specified element is null
     */
    @Override
    public boolean remove(Object o) {
        //Throws NullPointerException if e == null
        if(!this.contains(o)) return false;
        ht.remove(o.hashCode());
        return true;
    }

    /**
     * Returns <tt>true</tt> if this set contains all of the elements of the
     * specified collection.  If the specified collection is also a set, this
     * method returns <tt>true</tt> if it is a <i>subset</i> of this set.
     *
     * @param c collection to be checked for containment in this set
     * @return <tt>true</tt> if this set contains all of the elements of the
     * specified collection
     * @throws NullPointerException if the specified collection contains one
     *                              or more null elements and this set does not permit null
     *                              elements (optional), or if the specified collection is null
     */
    @Override
    public boolean containsAll(HCollection c) {
        //Throws NullPointerException if c == null
        HIterator itc = c.iterator();
        while(itc.hasNext()){
            Object tmp = itc.next();
            //If at least 1 element of c is not contained in this set, return false;
            if(!this.contains(tmp)) return false;
        }
        return true;
    }

    /**
     * Adds all of the elements in the specified collection to this set if
     * they're not already present (optional operation).  If the specified
     * collection is also a set, the <tt>addAll</tt> operation effectively
     * modifies this set so that its value is the <i>union</i> of the two
     * sets.  The behavior of this operation is undefined if the specified
     * collection is modified while the operation is in progress.
     *
     * @param c collection containing elements to be added to this set
     * @return <tt>true</tt> if this set changed as a result of the call
     * @throws NullPointerException          if the specified collection contains one
     *                                       or more null elements and this set does not permit null
     *                                       elements, or if the specified collection is null
     */
    @Override
    public boolean addAll(HCollection c) {
        //Throws NullPointerException if c == null
        HIterator itc = c.iterator();
        boolean isChanged = false;
        while(itc.hasNext()){
            Object tmp = itc.next();
            //Throws NullPointerException if tmp==null
            isChanged |= this.add(tmp);
        }
        return isChanged;
    }

    /**
     * Retains only the elements in this set that are contained in the
     * specified collection (optional operation).  In other words, removes
     * from this set all of its elements that are not contained in the
     * specified collection.  If the specified collection is also a set, this
     * operation effectively modifies this set so that its value is the
     * <i>intersection</i> of the two sets.
     *
     * @param c collection containing elements to be retained in this set
     * @return <tt>true</tt> if this set changed as a result of the call
     * @throws NullPointerException          if this set contains a null element and the
     *                                       specified collection does not permit null elements (optional),
     *                                       or if the specified collection is null
     */
    @Override
    public boolean retainAll(HCollection c) {
        if(c == null) throw new NullPointerException();
        SetAdapter toRemove = new SetAdapter();
        HIterator it = iterator();
        while(it.hasNext()) {
            Object elem = it.next();
            if(!c.contains(elem)) toRemove.add(elem);
        }
        return removeAll(toRemove);
    }

    /**
     * Removes from this set all of its elements that are contained in the
     * specified collection (optional operation).  If the specified
     * collection is also a set, this operation effectively modifies this
     * set so that its value is the <i>asymmetric set difference</i> of
     * the two sets.
     *
     * @param c collection containing elements to be removed from this set
     * @return <tt>true</tt> if this set changed as a result of the call
     * @throws NullPointerException          if this set contains a null element and the
     *                                       specified collection does not permit null elements (optional),
     *                                       or if the specified collection is null
     */
    @Override
    public boolean removeAll(HCollection c) {
        if(c == null) throw new NullPointerException();
        HIterator it = this.iterator();
        boolean isChanged = false;
        while(it.hasNext()) {
            Object tmp = it.next();
            if(c.contains(tmp)) {
                this.remove(tmp);
                isChanged = true;
            }
        }
        return isChanged;
    }

    /**
     * Removes all of the elements from this set (optional operation).
     * The set will be empty after this call returns.
     */
    @Override
    public void clear() {
        ht.clear();
    }

    /**
     * Compares the specified object with this set for equality.  Returns
     * <tt>true</tt> if the specified object is also a set, the two sets
     * have the same size, and every member of the specified set is
     * contained in this set (or equivalently, every member of this set is
     * contained in the specified set).  This definition ensures that the
     * equals method works properly across different implementations of the
     * set interface.
     *
     * @param o object to be compared for equality with this set
     * @return <tt>true</tt> if the specified object is equal to this set
     */
    public boolean equals(Object o){
        if (o == this) return true;
        if (!(o.getClass().equals(this.getClass()))) {
            return false;
        }
        HSet other = (SetAdapter) o;
        if (other.size() != this.size()) return false;
        HIterator it = iterator();
        while(it.hasNext()){
            if (!other.contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the hash code value for this set.  The hash code of a set is
     * defined to be the sum of the hash codes of the elements in the set,
     * where the hash code of a <tt>null</tt> element is defined to be zero.
     * This ensures that <tt>s1.equals(s2)</tt> implies that
     * <tt>s1.hashCode()==s2.hashCode()</tt> for any two sets <tt>s1</tt>
     * and <tt>s2</tt>, as required by the general contract of
     * Object.hashCode().
     *
     * @return the hash code value for this set
     */
    @Override
    public int hashCode(){
        int hashCode = 0;
        HIterator i = iterator();
        while (i.hasNext()) {
            Object tmp = i.next();
            hashCode += tmp.hashCode();
        }
        return hashCode;
    }

    /**
     * Returns an iterator over the elements in this set.
     * The elements are returned in no particular order.
     *
     * @return an iterator over the elements in this set
     */
    @Override
    public HIterator iterator() {
        return new SetIterator();
    }

    class SetIterator implements HIterator{

        Enumeration keys;
        Object current;

        public SetIterator(){
            keys = ht.keys();
            current = null;
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if next() would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return keys.hasMoreElements();
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public Object next() {
            if(!this.hasNext()) throw new NoSuchElementException();
            current = keys.nextElement();
            return ht.get(current);
        }

        /**
         * Removes from the underlying collection the last element returned
         * by this iterator (optional operation).  This method can be called
         * only once per call to next().  The behavior of an iterator
         * is unspecified if the underlying collection is modified while the
         * iteration is in progress in any way other than by calling this
         * method.
         *
         * @throws IllegalStateException         if the {@code next} method has not
         *                                       yet been called, or the {@code remove} method has already
         *                                       been called after the last call to the {@code next}
         *                                       method
         */
        @Override
        public void remove() {
            if(current==null) throw new exception.IllegalStateException();
            ht.remove(current);
            current=null;
        }
    }
}
