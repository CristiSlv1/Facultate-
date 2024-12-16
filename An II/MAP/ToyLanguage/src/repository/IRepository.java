package repository;

import exceptions.RepoException;
import model.states.PrgState;

import java.util.List;

public interface IRepository {
    void addProgram(PrgState program);
    void logPrgStateExec(PrgState prgState) throws RepoException;
    List<PrgState> getStates();
    List<PrgState> getPrgStatesList();
    void setPrgList(List<PrgState> programStates);
    void clearLogFile(PrgState prgState) throws RepoException;
}
