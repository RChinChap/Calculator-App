package CalculatorApp;

import java.util.EmptyStackException;

public class LinkedStack<T> implements StackInterface<T> {

    private Node topNode;

    public LinkedStack(){
        topNode = null;
    }

    @Override
    public void push(T anEntry) {
        Node dataNode = new Node(anEntry);
        dataNode.setNext(topNode);
        topNode = dataNode;
    }

    @Override
    public T pop() {
        if (isEmpty())
            throw new EmptyStackException();
        T outData = topNode.getData();
        topNode = topNode.getNext();
        return outData;
    }

    @Override
    public T peek() {
        if(isEmpty())
            throw new EmptyStackException();
        return topNode.getData();
    }

    @Override
    public boolean isEmpty() {
        return (topNode == null);
    };

    @Override
    public void clear() {
        topNode = null;
    }

    private class Node {
        private T data;
        private Node next;

        public Node() {
            this(null);
        }

        public Node(T anEntry) {
            data = anEntry;
            next = null;
        }

        public void setData(T dataPortion) {
            data = dataPortion;
        }

        public T getData() {
            return data;
        }

        public void setNext(Node nextNode) {
            next = nextNode;
        }

        public Node getNext() {
            return next;
        }
    }
}



