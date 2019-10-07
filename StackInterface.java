package CalculatorApp;

public interface StackInterface <T>{
    //adds a new entry to the top of the stack
    public void push (T anEntry);

    //removes and returns the top entry
    public T pop();

    //returns tehe top entry; null if the stack is empty
    public T peek();

    //detects an empty stack
    public boolean isEmpty();

    //removes all entries from the stack
    public void clear();

}
