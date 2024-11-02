package model.statements;

import exceptions.ADTException;
import exceptions.StatementException;
import model.states.PrgState;

public class NopStmt implements IStmt {

    @Override
    public PrgState execute(PrgState prgState) throws StatementException, ADTException {
        return prgState;
    }

    @Override
    public String toString() {
        return "NopStatements";
    }

}
