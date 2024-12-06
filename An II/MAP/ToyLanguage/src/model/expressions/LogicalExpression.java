package model.expressions;

import exceptions.ADTException;
import exceptions.ExpressionException;
import model.adt.IMyDictionary;
import model.adt.IMyHeap;
import model.types.BoolType;
import model.values.BoolValue;
import model.values.IValue;

public class LogicalExpression implements IExp {

    private final IExp left;
    private final IExp right;
    private final LogicalOperator operator;

    private LogicalExpression(IExp left, IExp right, LogicalOperator operator){
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public IValue eval(IMyDictionary<String, IValue> symtbl, IMyHeap heap) throws ADTException, ExpressionException {
        IValue evaluatedExpressionLeft = left.eval(symtbl, heap);
        IValue evaluatedExpressionRight = right.eval(symtbl, heap);

        if(evaluatedExpressionLeft.getType().equals(new BoolType())){
            throw new ExpressionException("Left expression is not a boolean type!");
        }

        if(evaluatedExpressionRight.getType().equals(new BoolType())){
            throw new ExpressionException("Right expression is not a boolean type!");
        }

        return switch (operator) {
            case AND ->
                    new BoolValue(((BoolValue) evaluatedExpressionLeft).getVal() && ((BoolValue) evaluatedExpressionRight).getVal());
            case OR ->
                    new BoolValue(((BoolValue) evaluatedExpressionLeft).getVal() || ((BoolValue) evaluatedExpressionRight).getVal());
            default -> throw new ExpressionException("Unknown operator");
        };
    }

    @Override
    public IExp deepCopy() {
        return new LogicalExpression(this.left.deepCopy() , this.right.deepCopy() , this.operator);
    }

    @Override
    public String toString() {
        return left.toString() + " " + operator.toString() + " " + right.toString();
    }

}
