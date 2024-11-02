package model.states;
import model.adt.*;
import model.statements.*;
import model.values.*;

public class PrgState {
    private IStmt statement;
    protected IMyStack<IStmt> exeStack;

    protected IMyDictionary<String, IValue> symTable;
    protected IMyList<String> output;
    protected IStmt originalProgram;

    public PrgState(IStmt statemnt){
        this.statement = statemnt;
        this.exeStack = new MyStack<>();
        this.symTable = new MyDictionary<>();
        this.output = new MyList<>();

        exeStack.push(statement);
    }

    public PrgState(IMyStack<IStmt> e, IMyDictionary<String, IValue> dictionary, IMyList<String> list, IStmt InitialStatement){
        this.exeStack = e;
        this.symTable = dictionary;
        this.output = list;
        
        exeStack.push(InitialStatement);
    }

    public IMyStack<IStmt> getExeStack(){
        return this.exeStack;
    }

    public void setExeStack(IMyStack<IStmt> exeStack){
        this.exeStack = exeStack;
    }

    public IMyDictionary<String, IValue> getSymTable() {
        return symTable;
    }

    public void setSymTable(IMyDictionary<String, IValue> symTable){
        this.symTable = symTable;
    }

    public IMyList<String> getOutput(){
        return this.output;
    }

    @Override
    public String toString() {
        return symTable.toString() + " " + exeStack.toString() + " " + output.toString();
    }
}
