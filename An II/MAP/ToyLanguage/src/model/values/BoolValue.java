package model.values;

import model.types.BoolIType;
import model.types.IType;

public class BoolValue implements IValue{
    
    boolean val;

    public BoolValue(boolean v){
        this.val = v;
    }
    @Override
    public IType getType() {
        return new BoolIType();
    }

    public boolean getVal(){
        return val;
    }

    @Override
    public boolean equals(IValue value) {
        return value instanceof BoolValue && ((BoolValue)value).val == this.val;
    }

    @Override
    public String toString(){
        return "bool";
    }
}
