package com.luomor.demo.hashmap;

/**
 * Created by PeterZhang on 2017/10/29.
 */
public interface Map<K,V> {

    V put(K key, V value);

    V get(K key);

    int size();

    public interface  Entry<K,V> {
        K getKey();

        V getValue();
    }
}
