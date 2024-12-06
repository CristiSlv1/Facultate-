package model.states;
import java.io.BufferedReader;

import model.adt.*;
import model.statements.*;
import model.values.*;

public class PrgState {
    private IStmt statement;
    protected IMyStack<IStmt> exeStack;

    protected IMyDictionary<String, IValue> symTable;
    protected IMyList<String> output;
    protected IStmt originalProgram;
    private MyDictionary<StringValue, BufferedReader> fileTable;
    private IMyHeap heap;
    
    public PrgState(IStmt statemnt){
        this.statement = statemnt;
        this.exeStack = new MyStack<>();
        this.symTable = new MyDictionary<>();
        this.output = new MyList<>();
        this.fileTable = new MyDictionary<>();
        this.heap = new MyHeap();

        exeStack.push(statement);
    }

    public PrgState(IMyStack<IStmt> e, IMyDictionary<String, IValue> dictionary, IMyList<String> list, IStmt InitialStatement, MyDictionary<StringValue, BufferedReader> fileTable, IMyHeap heap){
        this.exeStack = e;
        this.symTable = dictionary;
        this.output = list;
        this.fileTable = fileTable;
        this.heap = heap;
        exeStack.push(InitialStatement);

    }

    public IMyDictionary<StringValue, BufferedReader> getFileTable() {
        return this.fileTable;
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

    public IMyHeap getHeap(){
        return this.heap;
    }


    public String fileTableToString(){
        StringBuilder text = new StringBuilder();
        
        for(StringValue key : this.fileTable.getKeys()){
            text.append(key).append("\n");
        }

        return text.toString();
    }

    public String symTableToString() {
        StringBuilder symbolTableStringBuilder = new StringBuilder();

        for (String key : this.symTable.getKeys()) {
            IValue value = symTable.getValue(key);
            symbolTableStringBuilder.append(String.format("%s -> %s (%s)\n",
                    key,
                    value.toString(),
                    value.getType().toString()
            ));
        }

        return symbolTableStringBuilder.toString();
    }


    public String HeapToString()
    {
        StringBuilder answer = new StringBuilder("");
        for(Integer key: heap.getMap().keySet()){
            answer.append(key).append("(").append(heap.getValue(key).getType().toString())
                    .append(")").append(":-> ").
                    append(heap.getValue(key).toString()).append("\n");
        }
        return answer.toString();
    }

    @Override
    public String toString() {
        return String.format("EXE_STACK\n%s\nSYM_TABLE\n%s\nOUT\n%s\nFILE_TABLE\n%s\nHEAP\n%S\n", exeStack.toString(), symTableToString(), output.toString(), fileTableToString(),HeapToString());
    }

    
}
