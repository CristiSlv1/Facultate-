package model.expressions;
import exceptions.*;
import model.adt.*;
import model.values.*;

public interface IExp {
    IValue eval(IMyDictionary<String, IValue> symtbl, IMyHeap heap) throws ADTException, ExpressionException;

    IExp deepCopy();
}
