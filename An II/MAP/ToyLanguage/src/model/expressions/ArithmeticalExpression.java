package model.expressions;

import exceptions.ExpressionException;
import exceptions.KeyNotFoundException;
import model.adt.IMyDictionary;
import model.adt.IMyHeap;
import model.adt.IMyMap;
import model.types.IntType;
import model.values.IValue;
import model.values.IntValue;

public class ArithmeticalExpression implements IExp{

    private IExp left;
    private IExp right;
    private final ArithmeticalOperator operator;

    public ArithmeticalExpression(IExp l, ArithmeticalOperator operator, IExp r){
        this.left = l;
        this.right = r;
        this.operator = operator;
    }

    public IExp getLeft(){
        return this.left;
    }

    public void setLeft(IExp left){
        this.left = left;
    }

    public IExp getRight(){
        return this.right;
    }

    public void setRight(IExp right){
        this.right = right;
    }


    @Override
    public IValue eval(IMyDictionary<String, IValue> symTable, IMyHeap heap) throws ExpressionException, KeyNotFoundException {

        IValue value1 = this.left.eval(symTable, heap);
        IValue value2 = this.right.eval(symTable, heap);

        if (!value1.getType().equals(new IntType()))
            throw new ExpressionException("First value must be int");
        if (!value2.getType().equals(new IntType()))
            throw new ExpressionException("Second value must be int");


        int intValue1 = ((IntValue) value1).getVal();
        int intValue2 = ((IntValue) value2).getVal();

        switch (this.operator) {
            case ADD -> {
                return new IntValue(intValue1 + intValue2);
            }
            case SUBTRACT -> {
                return new IntValue(intValue1 - intValue2);
            }
            case MULTIPLY -> {
                return new IntValue(intValue1 * intValue2);
            }
            case DIVIDE -> {
                if (intValue2 == 0)
                    throw new ExpressionException("Division by 0");
                return new IntValue(intValue1 / intValue2);
            }
            default -> {
                throw new ExpressionException("Invalid operator");
            }
        }
    }

    @Override
    public String toString(){
        return this.left.toString() + " " + this.operator.toString() + ' ' + this.right.toString() ;
    }

    @Override
    public IExp deepCopy() {
        return new ArithmeticalExpression(this.left.deepCopy() , this.operator,this.right.deepCopy());
    }
}
