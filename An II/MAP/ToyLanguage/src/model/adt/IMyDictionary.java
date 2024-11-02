package model.adt;
import exceptions.*;

public interface IMyDictionary<K,V> {
    void insert(K key, V value);
    V getValue(K key) throws KeyNoFoundException;
    void remove(K key) throws KeyNoFoundException;
    boolean contains(K key);
}
