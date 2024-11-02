package model.statements;

import exceptions.ADTException;
import exceptions.StatementException;
import model.expressions.IExp;
import model.states.PrgState;
import model.types.BoolIType;
import model.values.BoolValue;
import model.values.IValue;

public class IfStmt implements IStmt{
    private final IExp expression;
    private final IStmt thenStatement;
    private final IStmt elseStatement;

    public IfStmt(IExp e , IStmt t , IStmt el)
    {
        this.expression = e;
        this.thenStatement = t;
        this.elseStatement = el;
    }

    @Override
    public PrgState execute(PrgState state) throws StatementException, ADTException {
        IValue value = expression.eval(state.getSymTable());
        if(value.getType().equals(new BoolIType())){
            throw new StatementException("Expression is not boolean!\n");
        }
        if(((BoolValue)value).getVal()){
            state.getExeStack().push(thenStatement);
        }
        else{
            state.getExeStack().push(elseStatement);
        }
        return state;
    }

    @Override
    public String toString() {
        return "if( " + expression + " ){ " + thenStatement + "} else { " + elseStatement + " }\n";
    }

}
