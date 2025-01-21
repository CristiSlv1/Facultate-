package model.expressions;

import exceptions.ADTException;
import exceptions.ExpressionException;
import model.adt.IMyDictionary;
import model.adt.IMyHeap;
import model.types.BoolType;
import model.types.IType;
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

    @Override
    public IType typecheck(IMyDictionary<String, IType> typeEnv) throws ExpressionException{
        IType type1 , type2;
        type1 = left.typecheck(typeEnv);
        type2 = right.typecheck(typeEnv);
        if(!type1.equals(new BoolType()))
            throw new ExpressionException("LOGICAL EXPRESSION EXCEPTION: left expression is not of type BoolType");
        if(!type2.equals(new BoolType()))
            throw new ExpressionException("LOGICAL EXPRESSION EXCEPTION: right expression is not of type BoolType");
        return new BoolType();
    }

}
