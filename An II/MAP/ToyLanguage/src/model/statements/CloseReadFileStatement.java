package model.statements;
import exceptions.ADTException;
import exceptions.StatementException;
import model.expressions.IExp;
import model.states.PrgState;
import model.types.StringType;
import model.values.IValue;
import model.values.StringValue;

import java.io.BufferedReader;
import java.io.IOException;
public class CloseReadFileStatement implements IStmt {
    private final IExp expression;

    public CloseReadFileStatement(IExp expression) {
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState prgState) throws StatementException, ADTException {
        var table = prgState.getSymTable();
        IValue value = expression.eval(table, prgState.getHeap());

        if(!value.getType().equals(new StringType()))
            throw new StatementException("It is not a String type");

        StringValue filename = (StringValue)  value;
        var fileTable = prgState.getFileTable();

        if(!fileTable.contains(filename))
            throw new StatementException("Variable was not declared");

        try {
            BufferedReader reader = fileTable.getValue(filename);
            reader.close();
        }
        catch (IOException e)
        {
            throw new StatementException("The file can't be closed");
        }
        fileTable.remove(filename);
        return prgState;
    }

    @Override
    public IStmt deepCopy() {
        return new CloseReadFileStatement(this.expression.deepCopy());
    }

    @Override
    public String toString() {
        return "close("+expression.toString()+")";
    }
}
