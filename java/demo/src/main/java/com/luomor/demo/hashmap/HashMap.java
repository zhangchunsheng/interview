package com.luomor.demo.hashmap;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created by PeterZhang on 2017/10/29.
 */
public class HashMap<K,V> implements Map<K,V> {

    //默认容量
    private static int defaultLength = 16;
    //默认加载因子
    private static double defualtLoaderFactor = 0.75;

    private Entry<K,V> table[] = null;
    private int size = 0;

    public HashMap() {
        this(defaultLength,defualtLoaderFactor);
    }

    public HashMap(int length,double loaderFactor) {
        defaultLength = length;
        defualtLoaderFactor = loaderFactor;
        table = new Entry[defaultLength];
    }

    private int hash(K k) {
        int m = defaultLength;
        int i = k.hashCode() % m;
        return i > 0 ? i : -i;
    }

    public V put(K key, V value) {
        int index = hash(key);

        Entry<K,V> entry = table[index];
        if(entry == null) {
            table[index] = new Entry(key,value,null);
        }else {
            table[index] = new Entry(key,value,entry);
            System.out.println("oldVlaue=" + table[index].next.getValue());
        }
        size ++;
        return table[index].getValue();
    }

    public V get(K key) {
        int index = hash(key);
        if(table[index] == null) {
            return  null;
        }
        return find(table[index],key);
    }

    private V find(Entry<K, V> entry, K key) {

        if(key == entry.getKey()||key.equals(entry.getKey())) {
            if(entry.next != null) {
                System.out.println("oldValue1=" + entry.next.getValue());
            }
            return entry.getValue();
        }else {
            //不相等的时候，就直接递归去取下一个值
            if(entry.next != null) {
                System.out.println("oldValue2=" + entry.next.getValue());
                return find(entry.next,key);
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    class Entry<K,V> implements Map.Entry<K,V> {
        K k;
        V v;

        Entry<K,V> next;

        public Entry(K k,V v,Entry<K,V> next) {
            this.k = k;
            this.v = v;
            this.next = next;
        }

        public K getKey() {
            return k;
        }

        public V getValue() {
            return v;
        }
    }

    public void testHashSet() {
        java.util.Map<String, String> map = new java.util.HashMap();

        Set set = new HashSet();
        set.add("1");//map.put(e, PRESENT)==null;

        java.util.Map linked = new LinkedHashMap();
        linked.put("1", "1");
        linked.get("1");

        Set tree = new TreeSet();
        tree.add("1");

        java.util.Map table = new Hashtable();
        java.util.Map concurrent = new ConcurrentHashMap();

        List list = new ArrayList();
        List linkedList = new LinkedList();
        List vector = new Vector();

        System.out.println(AbstractQueuedSynchronizer.class);
    }

}
