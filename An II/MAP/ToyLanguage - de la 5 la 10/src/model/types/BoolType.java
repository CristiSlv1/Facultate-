package model.types;

import model.values.*;


public class BoolType implements IType{

    @Override
    public boolean equals(IType another) {
        return another instanceof BoolType;
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
