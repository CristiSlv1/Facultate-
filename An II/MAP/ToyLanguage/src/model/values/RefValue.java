package model.values;

import model.types.IType;
import model.types.RefType;

public class RefValue implements IValue{
    private final int address;
    private final IType locationType;

    public RefValue(int v, IType location)
    {
        this.address = v;
        this.locationType = location;
    }

    public IType getLocationType()
    {
        return new RefType(locationType);
    }

    @Override
    public IType getType()
    {
        return new RefType(locationType);
    }

    public int getAddress()
    {
        return this.address;
    }

    @Override
    public String toString()
    {
        return locationType.toString();
    }

    public boolean equals(IValue obj)
    {
        return obj instanceof RefValue && ((RefValue)obj).getLocationType().equals(this.locationType) && ((RefValue)obj).getAddress() == this.address;
    }
}
