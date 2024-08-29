package org.example.custom.collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CustomArrayList<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final float LOAD_FACTOR = 0.75f;
    public Object[] data;
    private int size = 0;

    public CustomArrayList() {
        this.data = new Object[DEFAULT_SIZE];
    }

    public CustomArrayList(Collection<? extends T> collection) {
        int initialCapacity = Math.max(DEFAULT_SIZE, collection.size());
        this.data = new Object[initialCapacity];
        addAll(collection);
    }

    public static <T extends Comparable<T>> void sort(List<T> list) {
        boolean swapped;
        int size = list.size();
        for (int i = 0; i < size - 1; i++) {
            swapped = false;
            for (int j = 0; j < size - 1 - i; j++) {
                if (list.get(j).compareTo(list.get(j + 1)) > 0) {

                    T temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }

    private boolean isFull() {
        return size > 0 && size >= data.length * LOAD_FACTOR;
    }

    private boolean isTooLarge() {
        return size > 0 && size <= data.length / 2;
    }

    private void increaseCapacity() {
        data = Arrays.copyOf(data, data.length * 2);
    }

    private void decreaseCapacity() {
        int newCapacity = Math.max(DEFAULT_SIZE, data.length / 2);
        if (newCapacity != data.length) {
            data = Arrays.copyOf(data, newCapacity);
        }
    }

    private void isIndexExists(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Provided index is out of bound!");
        }
    }

    public void add(T element) {
        if (isFull()) {
            increaseCapacity();
        }
        this.data[size++] = element;
    }

    public void addAll(Collection<? extends T> collection) {
        if (collection.size() > 0) {
            int requiredCapacity = size + collection.size();
            if (requiredCapacity > data.length * LOAD_FACTOR) {
                data = Arrays.copyOf(data, requiredCapacity * 2);
            }
            for (T element : collection) {
                this.data[size++] = element;
            }
        }
    }

    public T remove(int index) {
        isIndexExists(index); // check the correctness of the passed index
        T removed = (T) data[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(data, index + 1, data, index, numMoved); // shift array elements
        }
        data[--size] = null;

        if (isTooLarge()) {
            decreaseCapacity();
        }
        return removed;
    }

    public T get(int index) {
        isIndexExists(index);
        return (T) this.data[index];
    }

    public int size() {
        return this.size;
    }

    public void set(int index, T value) {
        isIndexExists(index);
        this.data[index] = value;
    }

    public void bubbleSort() {
        boolean swapped;
        for (int i = 0; i < size - 1; i++) {
            swapped = false;
            for (int j = 0; j < size - 1 - i; j++) {
                if (((Comparable<T>) data[j]).compareTo((T) data[j + 1]) > 0) {

                    T temp = (T) data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                    swapped = true;
                }
            }
            // If there isn't swapping, exit sorting
            if (!swapped) {
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "CustomArrayList{" + "data=" + Arrays.toString(Arrays.copyOfRange(data, 0, size)) + '}';
    }
}
