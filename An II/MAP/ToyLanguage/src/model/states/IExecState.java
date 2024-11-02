package model.states;

import java.util.EmptyStackException;

import model.statements.IStmt;

public interface IExecState {
    public void push(IStmt statement);
    public IStmt pop() throws EmptyStackException;
    int size();
    boolean isEmpty();
}
