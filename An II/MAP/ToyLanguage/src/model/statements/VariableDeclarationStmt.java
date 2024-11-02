package model.statements;

import exceptions.ADTException;
import exceptions.ExpressionException;
import exceptions.StatementException;
import model.states.PrgState;
import model.types.IType;

public class VariableDeclarationStmt implements IStmt{

    private final String name;
    private final IType type;

    public VariableDeclarationStmt(String n, IType t){
        this.name = n;
        this.type = t;
    }

    @Override
    public PrgState execute(PrgState prgState) throws StatementException, ADTException {
        if(prgState.getSymTable().contains(this.name)){
            throw new ExpressionException("Variable with the same name already exists!\n");
        }
        prgState.getSymTable().insert(this.name, this.type.getDefaultValue());
        return prgState;
    }

    @Override
    public String toString(){
        return this.name;
    }

}
