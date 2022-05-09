package com.test;

import java.util.LinkedList;

public class MyMap<K, V> {

    private LinkedList[] arr = new LinkedList[1000];

    private int size;

    public void put(K key, V value) {
        Entry entry = new Entry<>(key, value);

        int hash = key.hashCode();
        hash = hash < 0 ? -hash : hash;
        int index = hash % arr.length;

        if (arr[index] == null) {
            LinkedList list = new LinkedList<>();
            arr[index] = list;
            list.add(entry);
        } else {
            LinkedList list = arr[index];
            for (int i = 0; i < list.size(); i++) {
                Entry e = (Entry) list.get(i);
                if (e.key.equals(key)) {
                    e.value = value;
                    return;
                }
            }
            list.add(entry);
        }
        size++;
    }

    public Object get(K key) {
        int index = key.hashCode() % arr.length;
        if (arr[index] != null) {
            LinkedList list = arr[index];
            for (int i = 0; i < list.size(); i++) {
                Entry en = (Entry) list.get(i);
                if (en.key.equals(key)) {
                    return en.value;
                }

            }
        }
        return null;
    }

    public int size() {
        return size;
    }
    
    class Entry<K, V> {

        K key;

        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }
}
