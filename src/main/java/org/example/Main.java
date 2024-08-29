package org.example;

import org.example.custom.collections.CustomArrayList;
import org.example.custom.collections.CustomLinkedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        CustomArrayList<Integer> customArrayList = new CustomArrayList<>(Arrays.asList(18, 15, 17, 16));
        CustomLinkedList<Integer> customLinkedList = new CustomLinkedList<>(Arrays.asList(21, 22, 19, 20));
        ArrayList<Integer> arrayList = new ArrayList<Integer>(Arrays.asList(3, 4, 5, 1, 2));
        LinkedList<Integer> linkedList = new LinkedList<>(Arrays.asList(3, 4, 5, 1, 2));


        System.out.println(customArrayList);
        customArrayList.bubbleSort();
        System.out.println(customArrayList);
        System.out.println(linkedList);
        CustomArrayList.sort(linkedList);
        System.out.println(linkedList);

        System.out.println(customLinkedList);
        customLinkedList.bubbleSort();
        System.out.println(customLinkedList);
        System.out.println(arrayList);
        CustomLinkedList.sort(arrayList);
        System.out.println(arrayList);

    }
}