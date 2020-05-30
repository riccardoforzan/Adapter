package interfaces;

public interface HList extends HCollection {

    void add(int index, Object element);

    boolean addAll(int index, HCollection c);

    Object get(int index);

    int indexOf(Object o);

    HListIterator listIterator();

    HListIterator listIterator(int index);

    int lastIndexOf(Object o);

    Object remove (int index);

    Object set(int index, Object element);

    HList subList(int fromIndex, int toIndex);

}
