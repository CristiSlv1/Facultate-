package controller;

import java.util.EmptyStackException;

import model.adt.IMyStack;
import model.statements.IStmt;
import model.states.PrgState;
import repository.IRepository;

public class Controller {
    private final IRepository repository;
    private boolean displayFlag;

    public Controller(IRepository repo, boolean flag){
        this.repository = repo;
        this.displayFlag = flag;
    }

    public PrgState executeOneStep(PrgState prgState) throws EmptyStackException{
        IMyStack<IStmt> executionStack = prgState.getExeStack();
        if (executionStack.isEmpty()){
            throw new EmptyStackException();
        }

        IStmt currentStatement = executionStack.pop();
        currentStatement.execute(prgState);
        if(displayFlag){
            displayCurrentState(prgState);
        }
        return prgState;
    }

    public void displayCurrentState(PrgState prgState){
        System.out.println(prgState.toString() + '\n');
    }

    public void executeAllSteps() throws EmptyStackException{
        PrgState currentPrgState = this.repository.getCurrentProgram();
        while(!currentPrgState.getExeStack().isEmpty()){
            executeOneStep(currentPrgState);
        }
    }

    public void addProgram(IStmt statement){
        this.repository.addProgram(new PrgState(statement));
    }

    public IRepository getRepository(){
        return this.repository;
    }

}
