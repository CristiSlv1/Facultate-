package repository;

import exceptions.RepoException;
import model.states.PrgState;

public interface IRepository {
    PrgState getCurrentProgram();
    void addProgram(PrgState program);
    void lodPrgStateExec() throws RepoException;
}
