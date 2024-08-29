package org.example.custom.collections;

import java.util.Collection;
import java.util.List;

public class CustomLinkedList<T> {
    private Node<T> head;
    private int size;

    public CustomLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public CustomLinkedList(Collection<? extends T> collection) {
        this();
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

    private void isIndexExists(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Provided index is out of bound!");
        }
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public T get(int index) {
        isIndexExists(index);
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public T remove(int index) {
        isIndexExists(index);
        Node<T> removedNode;
        if (index == 0) {
            removedNode = head;
            head = head.next;
        } else {
            Node<T> previous = head;
            for (int i = 0; i < index - 1; i++) {
                previous = previous.next;
            }
            removedNode = previous.next;
            previous.next = removedNode.next;
        }
        size--;
        return removedNode.data;
    }

    public void remove() {
        head = null;
        size = 0;
    }

    public void addAll(Collection<? extends T> collection) {
        for (T item : collection) {
            add(item);
        }
    }

    public int size() {
        return size;
    }

    public void display() {
        Node<T> current = head;
        if (head == null) {
            System.out.println("There is no elements in the list!");
            return;
        }
        while (current.next != null) {
            System.out.print(current.data + " => ");
            current = current.next;
        }
        System.out.println(current.data);
    }

    public void bubbleSort() {
        if (size < 2) {
            return;
        }

        boolean swapped;
        do {
            swapped = false;
            Node<T> current = head;
            Node<T> previous = null;

            while (current != null && current.next != null) {
                if (((Comparable<T>) current.data).compareTo(current.next.data) > 0) {
                    // Replacement logic
                    T temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                    swapped = true;
                }
                previous = current;
                current = current.next;
            }
        } while (swapped);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CustomLinkedList[data=");
        Node<T> current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    private class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
}


