package model.statements;

import exceptions.ADTException;
import exceptions.StatementException;
import model.adt.IMyDictionary;
import model.states.PrgState;
import model.types.IType;

public class NopStmt implements IStmt {

    @Override
    public PrgState execute(PrgState prgState) throws StatementException, ADTException {
        return prgState;
    }

    @Override
    public IStmt deepCopy() {
        return new NopStmt();
    }

    @Override
    public String toString() {
        return "NopStatements";
    }

    @Override
    public IMyDictionary<String, IType> typeCheck(IMyDictionary<String, IType> typeEnv) throws StatementException {
        return typeEnv;
    }
}
