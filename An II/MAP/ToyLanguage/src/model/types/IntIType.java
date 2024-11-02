package model.types;
import model.values.*;


public class IntIType implements IType{

    @Override
    public boolean equals(IType another) {
        return !(another instanceof IntIType);
    }

    @Override
    public IValue getDefaultValue() {
        return new IntIValue(0);
    }

    @Override
    public String toString(){
        return "int";
    }

}
