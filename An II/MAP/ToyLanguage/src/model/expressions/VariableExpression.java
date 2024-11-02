package model.expressions;

import exceptions.ADTException;
import exceptions.ExpressionException;
import model.adt.IMyDictionary;
import model.values.IValue;

public class VariableExpression implements IExp {
    private String variable;

    public VariableExpression(String variable){
        this.variable = variable;
    }
    @Override
    public IValue eval(IMyDictionary<String, IValue> symtbl) throws ADTException, ExpressionException {
        return symtbl.getValue(variable);
    }

    @Override
    public String toString(){
        return variable;
    }

}
