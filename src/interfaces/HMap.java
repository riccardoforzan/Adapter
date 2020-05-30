package interfaces;

public interface HMap {

    void clear();

    boolean containsKey(Object o);

    boolean containsValue(Object o);

    HSet entrySet();

    boolean equals(Object o);

    Object get(Object o);

    int hashCode();

    boolean isEmpty();

    HSet keySet();

    Object put(Object o, Object o2);

    void putAll(HMap map);

    Object remove(Object o);

    int size();

    HCollection values();

}
