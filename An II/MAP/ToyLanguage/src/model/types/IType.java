package model.types;
import model.values.*;

public interface IType {
    boolean equals(IType another);
    IValue getDefaultValue();
}
