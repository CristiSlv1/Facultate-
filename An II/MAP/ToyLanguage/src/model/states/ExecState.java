package model.states;
import java.util.EmptyStackException;
import model.statements.IStmt;
import model.adt.*;

public class ExecState implements IExecState{
    private MyStack<IStmt> execStack;

    public ExecState(){
        this.execStack = new MyStack<>();
    }
    @Override
    public void push(IStmt statement) {
        this.execStack.push(statement);
    }

    @Override
    public IStmt pop() throws EmptyStackException {
        return this.execStack.pop();
    }

    @Override
    public int size() {
        return this.execStack.getSize();
    }

    @Override
    public boolean isEmpty() {
        return this.execStack.isEmpty();
    }

    public MyStack<IStmt> getExeStack(){
        return this.execStack;
    }

    @Override
    public String toString(){
        return super.toString();
    }

}
