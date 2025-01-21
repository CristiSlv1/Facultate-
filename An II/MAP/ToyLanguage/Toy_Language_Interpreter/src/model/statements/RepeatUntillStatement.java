package model.statements;

import java.io.IOException;
import model.states.PrgState;

public class RepeatUntillStatement implements IStmt{
    @Override
    public PrgState execute(PrgState prgState) throws StatementException, ADTException, IOException {
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return null;
    }

    @Override
    public IMyDictionary<String, IType> typeCheck(IMyDictionary<String, IType> typeEnv) throws StatementException {
        return null;
    }
}
