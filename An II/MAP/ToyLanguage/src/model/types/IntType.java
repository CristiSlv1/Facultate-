package model.types;
import model.values.*;


public class IntType implements IType{

    @Override
    public boolean equals(IType another) {
        return another instanceof IntType;
    }

    @Override
    public IValue getDefaultValue() {
        return new IntValue(0);
    }

    @Override
    public String toString(){
        return "int";
    }

}