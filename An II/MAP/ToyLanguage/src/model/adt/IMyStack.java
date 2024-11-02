package model.adt;
import java.util.Stack;

import exceptions.*;


public interface IMyStack<T> {
    void push(T v);
    T pop() throws EmptyStackException;
    int getSize();
    Stack<T> getStack();
    boolean isEmpty();
}
