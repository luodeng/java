package com.roden.java.test;

public class LinkList<T> {
    private class Node {
        private T data;
        private Node next;

        public Node() {

        }

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node header;
    private Node tail;
    private int size;

    public LinkList() {
        header = null;
        tail = null;
    }

    public LinkList(T element) {
        header = new Node(element, null);
        tail = header;
        size++;
    }

    public int lenght() {
        return size;
    }

    public T get(int index) {
        return getNodeByIndex(index).data;
    }

    private Node getNodeByIndex(int index) {
        Node current = header;
        for (int i = 0; i < size && current != null; i++, current = current.next) {
            if (i == index) {
                return current;
            }
        }
        return null;
    }

    public int locate(T element) {
        Node current = header;
        for (int i = 0; i < size && current != null; i++, current = current.next) {
            if (current.data.equals(element)) {
                return i;
            }
        }
        return -1;
    }

    public void insert(T element, int index) {
        if (header == null) {
            add(element);
        } else {
            if (index == 0) {
                addAtHeader(element);
            } else {
                Node prev = getNodeByIndex(index - 1);
                prev.next = new Node(element, prev.next);
                size++;
            }
        }
    }

    public void add(T element) {
        if (header == null) {
            header = new Node(element, null);
            tail = header;
        } else {
            Node newNode = new Node(element, null);
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void addAtHeader(T element) {
        header = new Node(element, header);
        if (tail == null) {
            tail = header;
        }
        size++;
    }

    public T delete(int index) {
        Node del = null;
        if (index == 0) {
            del = header;
            header = header.next;
        } else {
            Node prev = getNodeByIndex(index - 1);
            del = prev.next;
            prev.next = del.next;
            del.next = null;
        }
        size--;
        return del.data;
    }

    public T remove() {
        return delete(size - 1);
    }

}
