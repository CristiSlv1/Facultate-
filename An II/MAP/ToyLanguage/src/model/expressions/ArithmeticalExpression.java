package model.expressions;

import exceptions.ADTException;
import exceptions.ExpressionException;
import model.adt.IMyDictionary;
import model.types.IntIType;
import model.values.IValue;
import model.values.IntIValue;

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
    public IValue eval(IMyDictionary<String, IValue> symtbl) throws ADTException, ExpressionException {
        IValue valueLeft = left.eval(symtbl);
        IValue valueRight = right.eval(symtbl);
        if(valueLeft.getType().equals(new IntIType())){
            throw new ExpressionException("First value is not int!\n");
        }

        if(valueRight.getType().equals(new IntIType())){
            throw new ExpressionException("Second value is not int!\n");
        }

        IntIValue v1 = (IntIValue) valueLeft;
        IntIValue v2 = (IntIValue) valueRight;
        IValue result;
        switch (this.operator){
            case ADD:
                result = new IntIValue(v1.getVal() + v2.getVal());
                break;
            case SUBTRACT:
                result = new IntIValue(v1.getVal() - v2.getVal());
                break;
            case MULTIPLY:
                result = new IntIValue(v1.getVal() * v2.getVal());
                break;
            case DIVIDE:
                if(v2.getVal() == 0){
                    throw new ExpressionException("Cannot divide by 0\n");
                }
                result = new IntIValue(v1.getVal() / v2.getVal());
                break;
            default:
                throw new ExpressionException("Invalid operator!\n");
        }
        return result;
    }

    @Override
    public String toString(){
        return this.left.toString() + " " + this.operator.toString() + ' ' + this.right.toString() + '\n';
    }

}
