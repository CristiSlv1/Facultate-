package model.values;
import model.types.IntType;
import model.types.IType;

public class IntValue implements IValue
{
    private final int val;

    public IntValue(int v)
    {
        this.val =v;
    }

    @Override
    public IType getType()
    {
        return new IntType();
    }

    public int getVal()
    {
        return this.val;
    }

    @Override
    public String toString()
    {
        return String.valueOf(this.val);
    }

    public boolean equals(IValue value)
    {
        return value.getType() instanceof IntType && ((IntValue) value).getVal() == this.getVal();
    }
}
