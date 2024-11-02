package model.adt;

import java.util.Stack;

import exceptions.EmptyStackException;

public class MyStack<T> implements IMyStack<T> {

    private final Stack<T> stack;

    public MyStack(){
        this.stack = new Stack<>();
    }

    @Override
    public void push(T v) {
        stack.push(v);
    }

    @Override
    public T pop() throws EmptyStackException {
        if(stack.isEmpty()){
            throw new EmptyStackException("Stack is empty!\n");
        }
        return stack.pop();
    }

    @Override
    public int getSize() {
        return stack.size();
    }

    @Override
    public Stack<T> getStack() {
        return this.stack;
    }

    @Override
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();

        for(T element : this.stack){
            str.append(element).append("\n");
        }

        return "My stack contains: " + str;
    }

}
