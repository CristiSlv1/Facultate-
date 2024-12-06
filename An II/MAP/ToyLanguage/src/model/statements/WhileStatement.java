package model.statements;

import exceptions.ADTException;
import exceptions.StatementException;
import model.expressions.IExp;
import model.states.PrgState;
import model.types.BoolType;
import model.values.BoolValue;
import model.values.IValue;

import java.io.IOException;

public class WhileStatement implements IStmt {

    private final IExp expression;
    private final IStmt statement;

    public WhileStatement(IExp expression, IStmt statement) {
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public PrgState execute(PrgState prgState) throws StatementException, ADTException, IOException {
        IValue value = expression.eval(prgState.getSymTable(), prgState.getHeap());
        if(!value.getType().equals(new BoolType())){
            throw new StatementException(String.format("While stmt error: %s is not of type boolean", value));
        }

        BoolValue boolValue = (BoolValue) value;
        if(boolValue.getVal()){
            prgState.getExeStack().push(this);
            prgState.getExeStack().push(statement);
        }
        return prgState;
    }

    @Override
    public IStmt deepCopy() {
        return new WhileStatement(expression.deepCopy(), statement.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("while(%s) {  %s  }", expression, statement);
    }
}
