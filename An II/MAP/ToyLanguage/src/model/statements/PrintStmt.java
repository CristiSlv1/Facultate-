package model.statements;

import exceptions.ADTException;
import exceptions.StatementException;
import model.expressions.IExp;
import model.states.PrgState;
import model.values.IValue;

public class PrintStmt implements IStmt {

    private final IExp expression;

    public PrintStmt(IExp exp){
        this.expression = exp;
    }
    
    @Override
    public PrgState execute(PrgState prgState) throws StatementException, ADTException {
        IValue result = expression.eval(prgState.getSymTable());
        prgState.getOutput().add(result.toString());
        return prgState;
    }

    @Override
    public String toString(){
        return "print(" + expression.toString() + ")";
    }

}
