package model.statements;

import exceptions.ADTException;
import exceptions.StatementException;
import model.adt.IMyDictionary;
import model.states.PrgState;
import model.types.IType;

public class CompStmt implements IStmt{
    private final IStmt statement1;
    private final IStmt statement2;

    public CompStmt(IStmt f, IStmt s){
        this.statement1 = f;
        this.statement2 = s;
    }

    @Override
    public PrgState execute(PrgState prgState) throws StatementException, ADTException {
        prgState.getExeStack().push(statement2);
        prgState.getExeStack().push(statement1);
        return prgState;

    }
    
    @Override
    public IStmt deepCopy() {
        return new CompStmt(this.statement1.deepCopy() , this.statement2.deepCopy());
    }

    
    @Override
    public String toString(){
        return "(" + statement1.toString() + ";" + statement2.toString() + ")";
    }


    @Override
    public IMyDictionary<String, IType> typeCheck(IMyDictionary<String, IType> typeEnv) throws StatementException {
        return statement2.typeCheck(statement1.typeCheck(typeEnv));
    }
}
