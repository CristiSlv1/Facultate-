package model.adt;

import java.util.HashMap;
import java.util.Map;

import exceptions.KeyNoFoundException;

public class MyDictionary<K,V> implements IMyDictionary<K,V>{

    private final Map<K,V> map;

    public MyDictionary(){
        this.map = new HashMap<K,V>();
    }

    @Override
    public void insert(K key, V value) {
        this.map.put(key, value);
    }

    @Override
    public V getValue(K key) throws KeyNoFoundException {
        if(!contains(key)){
            throw new KeyNoFoundException("Key not found");
        }
        return this.map.get(key);
    }

    @Override
    public void remove(Object key) throws KeyNoFoundException {
        if(!contains(key)){
            throw new KeyNoFoundException("Key not found");
        }
        this.map.remove(key);
    }

    @Override
    public boolean contains(Object key) {
        return this.map.containsKey(key);
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        for(K key : this.map.keySet()){
            str.append(key).append("-> ").append(this.map.get(key)).append("\n");
        }
        return "My dictionary contains: " + str;
    }
}
