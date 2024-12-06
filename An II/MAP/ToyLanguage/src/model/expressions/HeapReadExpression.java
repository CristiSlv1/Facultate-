package model.expressions;

import exceptions.ADTException;
import exceptions.ExpressionException;
import model.adt.IMyDictionary;
import model.adt.IMyHeap;
import model.adt.IMyMap;
import model.values.IValue;
import model.values.RefValue;

import java.beans.Expression;

public class HeapReadExpression implements IExp {
    private final IExp expression;

    public HeapReadExpression(IExp expression) {
        this.expression = expression;
    }

    @Override
    public IValue eval(IMyDictionary<String, IValue> symtbl, IMyHeap heap) throws ADTException, ExpressionException {
        IValue value = expression.eval(symtbl, heap);
        if(!(value instanceof RefValue)){
            throw new ExpressionException("Heap Error: value is not a RefValue");

        }
        RefValue refValue = (RefValue) value;
        return heap.getValue(refValue.getAddress());
    }


    @Override
    public IExp deepCopy() {
        return new HeapReadExpression(expression.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("HeapRead(%s)", expression);
    }
}
