package CalculatorApp;

import java.util.EmptyStackException;

public class ArrayStack <T> implements StackInterface <T> {

    private T[] stackArray;
    private int stackLength;
    private int capacity;
    private static final int DEFAULT_CAPACITY = 50;

    public ArrayStack(){
        this(DEFAULT_CAPACITY);
    }

    public ArrayStack (int capacity){
        T[] tempArray = (T[]) new Object[capacity];
        stackArray = tempArray;
        stackLength = 0;
        this.capacity = capacity;
    }

    public boolean isFull(){
        return (stackLength == capacity);
    }

    @Override
    public void push(T anEntry) {
        if (isFull())
            throw new EmptyStackException();
        stackArray[stackLength] = anEntry;
        this.stackLength ++;
    }

    @Override
    public T pop() {
        if (isEmpty())
            throw new EmptyStackException();
        T outData = stackArray[stackLength - 1];
        stackArray[stackLength - 1] = null;
        stackLength --;
        return outData;
    }

    @Override
    public T peek() {
        if (isEmpty())
            throw new EmptyStackException();
        T outData = stackArray[stackLength - 1];
        return outData;
    }

    @Override
    public boolean isEmpty() {
        return stackLength == 0;
    }

    @Override
    public void clear() {
        for (int i = 0 ; i < stackLength; i ++){
            stackLength--;
        }
    }
}
