package model.expressions;

import exceptions.ADTException;
import exceptions.ExpressionException;
import model.adt.IMyDictionary;
import model.adt.IMyHeap;
import model.types.IType;
import model.values.IValue;

public class ValueExpression implements IExp{

    private final IValue value;

    public ValueExpression(IValue value){
        this.value = value;
    }

    @Override
    public IValue eval(IMyDictionary<String, IValue> symtbl, IMyHeap heap) throws ADTException, ExpressionException {
        return value;
    }

    public IType getType(IMyDictionary<String, IType> typeTable){
        return value.getType();
    }

    @Override
    public IExp deepCopy() {
        return new ValueExpression(this.value);
    }
    
    @Override
    public String toString() {
        return String.format("%s",value.toString());
    }

}
