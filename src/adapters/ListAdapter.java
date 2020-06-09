package adapters;

import interfaces.HCollection;
import interfaces.HIterator;
import interfaces.HList;
import interfaces.HListIterator;

import java.util.NoSuchElementException;
import java.util.Vector;

/**
 * This implementation of HList interface DOES NOT ALLOW null values as input
 */
public class ListAdapter implements HList {

    private final Vector ve;

    public ListAdapter(){
        ve = new Vector();
    }

    /**
     * Check if a collection can be a valid parameter for this class.
     * A valid collection is not null and does not contains null values
     * @param tt collection to test
     * @return return true if the collection is valid, false otherwise
     */
    private static boolean isCollectionValid(HCollection tt){
        if(tt==null) return false;
        boolean containsNull;
        try{
            containsNull = tt.contains(null);
        }catch (NullPointerException npe){
            containsNull = false;
        }
        return !containsNull;
    }

    /**
     * Returns the number of elements in this list.  If this list contains
     * more than <tt>Integer.MAX_VALUE</tt> elements, returns
     * <tt>Integer.MAX_VALUE</tt>.
     *
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return ve.size();
    }

    /**
     * Returns <tt>true</tt> if this list contains no elements.
     *
     * @return <tt>true</tt> if this list contains no elements
     */
    @Override
    public boolean isEmpty() {
        return ve.isEmpty();
    }

    /**
     * Returns <tt>true</tt> if this list contains the specified element.
     * More formally, returns <tt>true</tt> if and only if this list contains
     * at least one element <tt>e</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
     *
     * @param o element whose presence in this list is to be tested
     * @return <tt>true</tt> if this list contains the specified element
     * @throws NullPointerException if the specified element is null
     */
    @Override
    public boolean contains(Object o) {
        if(o==null) throw new NullPointerException();
        return ve.contains(o);
    }

    /**
     * Returns an array containing all of the elements in this list in proper
     * sequence (from first to last element).
     *
     * <p>The returned array will be "safe" in that no references to it are
     * maintained by this list.  (In other words, this method must
     * allocate a new array even if this list is backed by an array).
     * The caller is thus free to modify the returned array.
     *
     * @return an array containing all of the elements in this list in proper
     * sequence
     */
    @Override
    public Object[] toArray() {
        Object[] rv = new Object[this.size()];
        ve.copyInto(rv);
        return rv;
    }

    /**
     * Returns an array containing all of the elements in this list in
     * proper sequence (from first to last element); the runtime type of
     * the returned array is that of the specified array.  If the list fits
     * in the specified array, it is returned therein.  Otherwise, a new
     * array is allocated with the runtime type of the specified array and
     * the size of this list.
     *
     * <p>If the list fits in the specified array with room to spare (i.e.,
     * the array has more elements than the list), the element in the array
     * immediately following the end of the list is set to <tt>null</tt>.
     *
     * Note that <tt>toArray(new Object[0])</tt> is identical in function to
     * <tt>toArray()</tt>.
     *
     * @param a the array into which the elements of this list are to
     *          be stored, if it is big enough; otherwise, a new array of the
     *          same runtime type is allocated for this purpose.
     * @return an array containing the elements of this list
     * @throws NullPointerException if the specified array is null
     */
    @Override
    public Object[] toArray(Object[] a) {
        //If a == null throws NullPointerException
        if(a.length<this.size()) a = new Object[this.size()];
        //If a.length>size() all positions after are set to null by default
        ve.copyInto(a);
        return a;
    }

    /**
     * Appends the specified element to the end of this list (optional
     * operation).
     *
     * <p>Lists that support this operation may place limitations on what
     * elements may be added to this list.  In particular, some
     * lists will refuse to add null elements, and others will impose
     * restrictions on the type of elements that may be added.  List
     * classes should clearly specify in their documentation any restrictions
     * on what elements may be added.
     *
     * @param o element to be appended to this list
     * @return <tt>true</tt> (as specified by Collection.add())
     * @throws NullPointerException          if the specified element is null
     */
    @Override
    public boolean add(Object o) {
        //Throws NullPointerException if o == null
        add(size(),o);
        return true;
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present (optional operation).  If this list does not contain
     * the element, it is unchanged.  More formally, removes the element with
     * the lowest index <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>
     * (if such an element exists).  Returns <tt>true</tt> if this list
     * contained the specified element (or equivalently, if this list changed
     * as a result of the call).
     *
     * @param o element to be removed from this list, if present
     * @return <tt>true</tt> if this list contained the specified element
     * @throws NullPointerException          if the specified element is null
     */
    @Override
    public boolean remove(Object o) {
        if(o==null) throw new NullPointerException();
        return ve.remove(o);
    }

    /**
     * Returns <tt>true</tt> if this list contains all of the elements of the
     * specified collection.
     *
     * @param c collection to be checked for containment in this list
     * @return <tt>true</tt> if this list contains all of the elements of the
     * specified collection
     * @throws NullPointerException if the specified collection contains one
     *                              or more null elements or if the specified collection is null
     */
    @Override
    public boolean containsAll(HCollection c) {
        if(!ListAdapter.isCollectionValid(c)) throw new NullPointerException();
        HIterator itc = c.iterator();
        while(itc.hasNext()){
            Object tmp = itc.next();
            //If at least 1 element of c is not contained in this list, return false;
            if(!ve.contains(tmp)) return false;
        }
        return true;
    }

    /**
     * Appends all of the elements in the specified collection to the end of
     * this list, in the order that they are returned by the specified
     * collection's iterator (optional operation).  The behavior of this
     * operation is undefined if the specified collection is modified while
     * the operation is in progress.  (Note that this will occur if the
     * specified collection is this list, and it's nonempty.)
     *
     * @param c collection containing elements to be added to this list
     * @return <tt>true</tt> if this list changed as a result of the call
     * @throws NullPointerException          if the specified collection contains one
     *                                       or more null elements or if the specified collection is null
     */
    @Override
    public boolean addAll(HCollection c) {
        //Throws NullPointerException if c == null or c contains one or more null elements
        return this.addAll(size(),c);
    }

    /**
     * Inserts all of the elements in the specified collection into this
     * list at the specified position (optional operation).  Shifts the
     * element currently at that position (if any) and any subsequent
     * elements to the right (increases their indices).  The new elements
     * will appear in this list in the order that they are returned by the
     * specified collection's iterator.  The behavior of this operation is
     * undefined if the specified collection is modified while the
     * operation is in progress.  (Note that this will occur if the specified
     * collection is this list, and it's nonempty.)
     *
     * @param index index at which to insert the first element from the
     *              specified collection
     * @param c     collection containing elements to be added to this list
     * @return <tt>true</tt> if this list changed as a result of the call
     * @throws NullPointerException          if the specified collection contains one
     *                                       or more null elements or if the specified collection is null
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       (<tt>index &lt; 0 || index &gt; size()</tt>)
     */
    @Override
    public boolean addAll(int index, HCollection c) {
        if(index<0 || index>size()) throw new IndexOutOfBoundsException();
        if(!ListAdapter.isCollectionValid(c)) throw new NullPointerException();

        HIterator itc = c.iterator();
        boolean isChanged = false;
        while(itc.hasNext()){
            Object tmp = itc.next();
            //Throws NullPointerException if tmp == null
            add(index,tmp);
            index++;
            isChanged = true;
        }
        return isChanged;
    }

    /**
     * Removes from this list all of its elements that are contained in the
     * specified collection (optional operation).
     *
     * @param c collection containing elements to be removed from this list
     * @return <tt>true</tt> if this list changed as a result of the call
     * @throws NullPointerException          if this list contains a null element
     *                                       or if the specified collection is null
     */
    @Override
    public boolean removeAll(HCollection c) {
        if(!ListAdapter.isCollectionValid(c)) throw new NullPointerException();

        boolean isChanged = false;
        int i=0;
        while(i<this.size()){
            Object tmp = this.get(i);
            if(c.contains(tmp)){
                this.remove(tmp);
                isChanged = true;
            } else {
                i++;
            }
        }
        return isChanged;
    }

    /**
     * Retains only the elements in this list that are contained in the
     * specified collection (optional operation).  In other words, removes
     * from this list all of its elements that are not contained in the
     * specified collection.
     *
     * @param c collection containing elements to be retained in this list
     * @return <tt>true</tt> if this list changed as a result of the call
     * @throws NullPointerException          if this list contains a null element
     *                                       or if the specified collection is null
     */
    @Override
    public boolean retainAll(HCollection c) {
        if(!ListAdapter.isCollectionValid(c)) throw new NullPointerException();

        ListAdapter toRemove = new ListAdapter();
        for(int i=0;i<this.size();i++){
            Object tmp = this.get(i);
            if(!c.contains(tmp))toRemove.add(tmp);
        }
        return this.removeAll(toRemove);
    }

    /**
     * Removes all of the elements from this list (optional operation).
     * The list will be empty after this call returns.
     */
    @Override
    public void clear() {
        ve.removeAllElements();
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    @Override
    public Object get(int index) {
        if(index < 0 || index >= size()) throw new IndexOutOfBoundsException();
        return ve.elementAt(index);
    }

    /**
     * Replaces the element at the specified position in this list with the
     * specified element (optional operation).
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws NullPointerException          if the specified element is null
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    @Override
    public Object set(int index, Object element) {
        if(element == null) throw new NullPointerException();
        //Method get(index) throws IndexOutOfBoundException if (index<0 || index>=size())
        Object rv = this.get(index);
        ve.setElementAt(element,index);
        return rv;
    }

    /**
     * Inserts the specified element at the specified position in this list
     * (optional operation).  Shifts the element currently at that position
     * (if any) and any subsequent elements to the right (adds one to their
     * indices).
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws NullPointerException          if the specified element is null
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       (<tt>index &lt; 0 || index &gt; size()</tt>)
     */
    @Override
    public void add(int index, Object element) {
        if(element==null) throw new NullPointerException();
        if(index<0 || index>this.size()) throw new IndexOutOfBoundsException();
        ve.insertElementAt(element,index);
    }

    /**
     * Removes the element at the specified position in this list (optional
     * operation).  Shifts any subsequent elements to the left (subtracts one
     * from their indices).  Returns the element that was removed from the
     * list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    @Override
    public Object remove(int index) {
        //Method get(index) throws IndexOutOfBoundException if (index<0 || index>size())
        Object rv = this.get(index);
        ve.removeElementAt(index);
        return rv;
    }

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the lowest index <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
     * or -1 if there is no such index.
     *
     * @param o element to search for
     * @return the index of the first occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     * @throws NullPointerException if the specified element is null
     */
    @Override
    public int indexOf(Object o) {
        if(o==null) throw new NullPointerException();
        return ve.indexOf(o);
    }

    /**
     * Returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the highest index <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
     * or -1 if there is no such index.
     *
     * @param o element to search for
     * @return the index of the last occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     * @throws NullPointerException if the specified element is null
     */
    @Override
    public int lastIndexOf(Object o) {
        if(o==null) throw new NullPointerException();
        return ve.lastIndexOf(o);
    }

    /**
     * Compares the specified object with this list for equality.  Returns
     * <tt>true</tt> if and only if the specified object is also a list, both
     * lists have the same size, and all corresponding pairs of elements in
     * the two lists are <i>equal</i>.  (Two elements <tt>e1</tt> and
     * <tt>e2</tt> are <i>equal</i> if <tt>(e1==null ? e2==null :
     * e1.equals(e2))</tt>.)  In other words, two lists are defined to be
     * equal if they contain the same elements in the same order.  This
     * definition ensures that the equals method works properly across
     * different implementations of the <tt>List</tt> interface.
     *
     * @param o the object to be compared for equality with this list
     * @return <tt>true</tt> if the specified object is equal to this list
     */
    public boolean equals(Object o){
        if(o==this) return true;
        if(o==null) return false;
        if(!(o instanceof HList)) return false;
        HList other = (HList) o;
        if(other.size() != this.size()) return false;

        for(int i=0;i<size();i++){
            boolean ae = this.get(i).equals(other.get(i));
            if(!ae) return false;
        }
        return true;
    }

    /**
     * Returns the hash code value for this list.  The hash code of a list
     * is defined to be the result of the following calculation:
     * <pre>
     *  int hashCode = 1;
     *  for (E e : list)
     *      hashCode = 31*hashCode + (e==null ? 0 : e.hashCode());
     * </pre>
     * This ensures that <tt>list1.equals(list2)</tt> implies that
     * <tt>list1.hashCode()==list2.hashCode()</tt> for any two lists,
     * <tt>list1</tt> and <tt>list2</tt>, as required by the general
     * contract of Object.hashCode().
     *
     * @return the hash code value for this list
     */
    public int hashCode(){
        int hashCode = 1;
        for(int i=0;i<size();i++){
            Object tmp = this.get(i);
            hashCode = 31 * hashCode  + tmp.hashCode();
        }
        return hashCode;
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * @return an iterator over the elements in this list in proper sequence
     */
    @Override
    public HIterator iterator() {
        return new ListIterator(0,0,this.size());
    }

    /**
     * Returns a list iterator over the elements in this list (in proper
     * sequence).
     *
     * @return a list iterator over the elements in this list (in proper
     * sequence)
     */
    @Override
    public HListIterator listIterator() {
        return this.listIterator(0);
    }

    /**
     * Returns a list iterator over the elements in this list (in proper
     * sequence), starting at the specified position in the list.
     * The specified index indicates the first element that would be
     * returned by an initial call to ListIterator.next() next.
     * An initial call to ListIterator.previous() previous would
     * return the element with the specified index minus one.
     *
     * @param index index of the first element to be returned from the
     *              list iterator (by a call to ListIterator.next() next)
     * @return a list iterator over the elements in this list (in proper
     * sequence), starting at the specified position in the list
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index > size()})
     */
    @Override
    public HListIterator listIterator(int index) {
        if(index<0 || index>size()) throw new IndexOutOfBoundsException();
        return new ListIterator(0,index,size());
    }

    private class ListIterator implements HListIterator{

        private int cursor;
        private int actual;

        private int upperLimit;
        private final int lowerLimit;

        public ListIterator(int lowerLimit, int index, int upperLimit){
            //Starting point
            this.actual = -1;
            this.cursor = index;

            this.upperLimit = upperLimit;
            this.lowerLimit = lowerLimit;
        }

        @Override
        public boolean hasNext() {
            return cursor < upperLimit;
        }

        @Override
        public Object next() {
            if(!this.hasNext()) throw new NoSuchElementException();
            actual = cursor++;
            return ve.get(actual);
        }

        @Override
        public boolean hasPrevious() {
            return cursor > lowerLimit;
        }

        @Override
        public Object previous() {
            if(!this.hasPrevious()) throw new NoSuchElementException();
            actual = --cursor;
            return ve.get(actual);
        }

        @Override
        public int nextIndex() {
            return cursor - lowerLimit;
        }

        @Override
        public int previousIndex() {
            return cursor - lowerLimit - 1;
        }

        @Override
        public void remove() {
            if(actual==-1) throw new exceptions.IllegalStateException();
            ve.remove(actual);
            if(actual != cursor) cursor --;
            upperLimit--;
            actual = -1;
        }

        @Override
        public void set(Object o) {
            if(o==null) throw new IllegalArgumentException("A null for Object o is not allowed");
            if(actual==-1) throw new exceptions.IllegalStateException();
            ve.set(actual,o);

        }

        @Override
        public void add(Object o) {
            if(o==null) throw new IllegalArgumentException("A null for Object o is not allowed");
            ve.add(cursor++,o);
            upperLimit++;
            actual = -1;
        }

    }

    /**
     * Returns a view of the portion of this list between the specified
     * <tt>fromIndex</tt>, inclusive, and <tt>toIndex</tt>, exclusive.  (If
     * <tt>fromIndex</tt> and <tt>toIndex</tt> are equal, the returned list is
     * empty.)  The returned list is backed by this list, so non-structural
     * changes in the returned list are reflected in this list, and vice-versa.
     * The returned list supports all of the optional list operations supported
     * by this list.<p>
     * <p>
     * This method eliminates the need for explicit range operations (of
     * the sort that commonly exist for arrays).  Any operation that expects
     * a list can be used as a range operation by passing a subList view
     * instead of a whole list.  For example, the following idiom
     * removes a range of elements from a list:
     * <pre>
     *      list.subList(from, to).clear();
     * </pre>
     * Similar idioms may be constructed for <tt>indexOf</tt> and
     * <tt>lastIndexOf</tt>, and all of the algorithms in the
     * <tt>Collections</tt> class can be applied to a subList.<p>
     * <p>
     * The semantics of the list returned by this method become undefined if
     * the backing list (i.e., this list) is <i>structurally modified</i> in
     * any way other than via the returned list.  (Structural modifications are
     * those that change the size of this list, or otherwise perturb it in such
     * a fashion that iterations in progress may yield incorrect results.)
     *
     * @param fromIndex low endpoint (inclusive) of the subList
     * @param toIndex   high endpoint (exclusive) of the subList
     * @return a view of the specified range within this list
     * @throws IndexOutOfBoundsException for an illegal endpoint index value
     *                                   (<tt>fromIndex &lt; 0 || toIndex &gt; size ||
     *                                   fromIndex &gt; toIndex</tt>)
     */
    @Override
    public HList subList(int fromIndex, int toIndex) {
        if(fromIndex < 0 || fromIndex > toIndex || toIndex > size()) throw new IndexOutOfBoundsException();
        return new SubList(fromIndex, toIndex);
    }

    class SubList implements HList {

        private final SubList parent;
        private final int fromIndex;
        private int toIndex;

        public SubList(int fromIndex, int toIndex) {
            parent = null;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
        }

        public SubList(int fromIndex, int toIndex, SubList parent) {
            this.parent = parent;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
        }

        @Override
        public int size() {
            return toIndex - fromIndex;
        }

        @Override
        public boolean isEmpty() {
            return fromIndex == toIndex;
        }

        @Override
        public boolean contains(Object o) {
            return indexOf(o) != -1;
        }

        @Override
        public HIterator iterator() {
            return new SubListIterator(fromIndex, fromIndex, toIndex);
        }

        @Override
        public Object[] toArray() {
            Object[] arr = new Object[size()];
            for (int i = 0; i < size(); i++) arr[i] = get(i);
            return arr;
        }

        @Override
        public Object[] toArray(Object[] a) {
            Object[] rv;
            if (a.length < size()) {
                rv = new Object[size()];
            } else {
                rv = a;
            }
            int i=0;
            while(i < size()){
                rv[i] = get(i);
                i++;
            }
            while(i<rv.length) rv[i++]=null;
            return rv;
        }

        @Override
        public boolean add(Object o) {
            //Throws NullPointerException if o == null
            this.add(this.size(), o);
            return true;
        }

        @Override
        public boolean remove(Object o) {
            //Throws NullPointerException if o == null
            int index = this.indexOf(o);
            if (index < 0) return false;
            remove(index);
            return true;
        }

        @Override
        public boolean containsAll(HCollection c) {
            if(!ListAdapter.isCollectionValid(c)) throw new NullPointerException();
            HIterator itc = c.iterator();
            while (itc.hasNext()) {
                Object tmp = itc.next();
                //If at least 1 element of c is not contained in this list, return false;
                if (!this.contains(tmp)) return false;
            }
            return true;
        }

        @Override
        public boolean addAll(HCollection c) {
            return addAll(size(), c);
        }

        @Override
        public boolean addAll(int index, HCollection c) {
            if (index < 0 || index > this.size()) throw new IndexOutOfBoundsException();
            //ListAdapter.this.addAll() throws NullPointerException if c == null or contains null elements
            boolean isChanged = ListAdapter.this.addAll(this.fromIndex + index, c);
            int cSize = c.size();
            if (this.parent != null) this.parent.toIndex += cSize;
            toIndex += cSize;
            return isChanged;
        }

        @Override
        public boolean removeAll(HCollection c) {
            if(!ListAdapter.isCollectionValid(c)) throw new NullPointerException();
            boolean isChanged = false;
            int i = 0;
            while (i < this.size()) {
                Object tmp = this.get(i);
                if (c.contains(tmp)) {
                    this.remove(tmp);
                    isChanged = true;
                } else {
                    i++;
                }
            }
            return isChanged;
        }

        @Override
        public boolean retainAll(HCollection c) {
            if(!ListAdapter.isCollectionValid(c)) throw new NullPointerException();
            ListAdapter toRemove = new ListAdapter();
            for (int i = 0; i < this.size(); i++) {
                Object tmp = this.get(i);
                if (!c.contains(tmp)) toRemove.add(tmp);
            }
            return this.removeAll(toRemove);
        }

        @Override
        public void clear() {
            int size = this.size();
            while (fromIndex < toIndex) {
                --toIndex;
                ListAdapter.this.remove(toIndex);
            }
            if (parent != null) parent.toIndex -= size;
        }

        @Override
        public Object get(int index) {
            if (index < 0 || index >= size()) throw new IndexOutOfBoundsException();
            return ListAdapter.this.get(fromIndex + index);
        }

        @Override
        public Object set(int index, Object element) {
            if (index < 0 || index >= size()) throw new IndexOutOfBoundsException();
            //ListAdapter.set(index,element) throws new NullPointerException if element==null
            return ListAdapter.this.set(this.fromIndex + index, element);
        }

        @Override
        public void add(int index, Object element) {
            if (index < 0 || index > size()) throw new IndexOutOfBoundsException();
            //ListAdapter.add() throws NullPointerException if element == null
            ListAdapter.this.add(this.fromIndex + index, element);
            if(parent!=null) parent.toIndex++;
            ++toIndex;
        }

        @Override
        public Object remove(int index) {
            if (index < 0 || index >= size()) throw new IndexOutOfBoundsException();
            Object rv = ListAdapter.this.remove(this.fromIndex + index);
            if (parent != null) this.parent.toIndex--;
            --toIndex;
            return rv;
        }

        @Override
        public int indexOf(Object o) {
            if (o == null) throw new NullPointerException();
            for (int i = 0; i < this.size(); i++) {
                Object tmp = this.get(i);
                if (o.equals(tmp)) return i;
            }
            return -1;
        }

        @Override
        public int lastIndexOf(Object o) {
            if (o == null) throw new NullPointerException();
            int rv = -1;
            for (int i = 0; i < this.size(); i++) {
                Object tmp = this.get(i);
                if (o.equals(tmp)) rv = i;
            }
            return rv;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) return true;
            if (o == null || !(o instanceof HList)) return false;
            HList other = (HList) o;
            if (other.size() != this.size()) return false;

            for (int i = 0; i < size(); i++) {
                boolean ae = this.get(i).equals(other.get(i));
                if (!ae) return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            int hashCode = 1;
            for (int i = 0; i < size(); i++) {
                Object tmp = this.get(i);
                hashCode = 31 * hashCode + tmp.hashCode();
            }
            return hashCode;
        }

        @Override
        public HListIterator listIterator() {
            return new SubListIterator(fromIndex, fromIndex, toIndex);
        }

        @Override
        public HListIterator listIterator(int index) {
            if (index < 0 || index >= size()) throw new IndexOutOfBoundsException();
            return new SubListIterator(fromIndex, fromIndex + index, toIndex);
        }

        @Override
        public HList subList(int fromIndex, int toIndex) {
            if (fromIndex < 0 || fromIndex > toIndex || toIndex > size()) throw new IndexOutOfBoundsException();
            return new SubList(this.fromIndex + fromIndex, this.fromIndex + toIndex, this);
        }

        class SubListIterator extends ListIterator {

            public SubListIterator(int lowerBound, int index, int upperBond) {
                super(lowerBound, index, upperBond);
            }

            @Override
            public void add(Object o) {
                super.add(o);
                toIndex++;
            }

            @Override
            public void remove() {
                super.remove();
                toIndex--;
            }

        }
    }

}
