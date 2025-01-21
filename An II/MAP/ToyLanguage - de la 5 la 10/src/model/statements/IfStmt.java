package model.statements;

import exceptions.ADTException;
import exceptions.StatementException;
import model.adt.IMyDictionary;
import model.expressions.IExp;
import model.states.PrgState;
import model.types.BoolType;
import model.types.IType;
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
        IValue value = expression.eval(state.getSymTable(), state.getHeap());
        if(!value.getType().equals(new BoolType())){
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
    public IStmt deepCopy() {
        return new IfStmt(this.expression.deepCopy() , this.thenStatement.deepCopy() , this.elseStatement.deepCopy());
    }

    @Override
    public String toString() {
        return "if( " + expression + " ){ " + thenStatement + "} else { " + elseStatement + " }\n";
    }

    @Override
    public IMyDictionary<String, IType> typeCheck(IMyDictionary<String, IType> typeEnv) throws StatementException {
        IType typeExp = expression.typecheck(typeEnv);
        if(typeExp.equals(new BoolType()))
        {
            thenStatement.typeCheck(typeEnv.deepCopy());
            elseStatement.typeCheck(typeEnv.deepCopy());
            return typeEnv;
        }
        else
            throw new StatementException("IF STATEMENT EXCEPTION: The condition of if has not the type bool");
    }

}
