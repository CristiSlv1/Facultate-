package model.expressions;

import exceptions.ADTException;
import exceptions.ExpressionException;
import model.adt.IMyDictionary;
import model.types.BoolIType;
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
    public IValue eval(IMyDictionary<String, IValue> symtbl) throws ADTException, ExpressionException {
        IValue evaluatedExpressionLeft = left.eval(symtbl);
        IValue evaluatedExpressionRight = right.eval(symtbl);
        
        if(evaluatedExpressionLeft.getType().equals(new BoolIType())){
            throw new ExpressionException("Left expression is not a boolean type!\n");
        }

        if(evaluatedExpressionRight.getType().equals(new BoolIType())){
            throw new ExpressionException("Right expression is not a boolean type!\n");
        }

        return switch(operator){
            case LogicalOperator.AND -> new BoolValue(((BoolValue) evaluatedExpressionLeft).getVal() && ((BoolValue) evaluatedExpressionRight).getVal());
            case LogicalOperator.OR -> new BoolValue(((BoolValue) evaluatedExpressionLeft).getVal() ||  ((BoolValue) evaluatedExpressionRight).getVal());
            default -> throw new ExpressionException("Unknown operator!\n");
        };
    }

    @Override
    public String toString() {
        return left.toString() + " " + operator.toString() + " " + right.toString() + "\n";
    }

}
