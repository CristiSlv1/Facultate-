package model.values;

import model.types.*;

public class IntIValue  implements IValue{

    private final int val;

    public IntIValue(int v){
        this.val = v;
    }

    @Override
    public IType getType() {
        return new IntIType();
    }
    public int getVal(){
        return this.val;
    }
    @Override
    public boolean equals(IValue value) {
        return value.getType() instanceof IntIType && ((IntIValue) value).getVal() == this.getVal();
    }

    @Override
    public String toString(){
        return String.valueOf(val);
    }

}
