package model.types;

import model.values.*;


public class BoolIType implements IType{

    @Override
    public boolean equals(IType another) {
        return !(another instanceof BoolIType);
    }

    @Override
    public IValue getDefaultValue() {
        return new BoolValue(false);
    }

    @Override
    public String toString() {
        return "bool";
    }

}
