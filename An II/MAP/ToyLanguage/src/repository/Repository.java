package repository;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import exceptions.LoadFileException;
import exceptions.RepoException;
import model.states.PrgState;

public class Repository implements IRepository{
    private List<PrgState> programs;
    private final String filename;

    public Repository(String file){
        this.programs = new LinkedList<>();
        this.filename = file;
    }

    @Override
    public List<PrgState> getStates() {
        return this.programs;
    }

    @Override
    public void clearLogFile(PrgState prgState) throws RepoException{
        try{
            PrintWriter writer = new PrintWriter(new FileWriter(filename));
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("File does not exist");
        }
    }
    @Override
    public List<PrgState> getPrgStatesList() {
        return this.programs;
    }

    @Override
    public void setPrgList(List<PrgState> programStates) {
        this.programs.clear();
        this.programs = programStates;
    }

    @Override
    public void addProgram(PrgState program) {
        this.programs.add(program);
    }



    @Override
    public void logPrgStateExec(PrgState prgState) throws RepoException {
        try{
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)));
            writer.println(prgState.toString());
            writer.close();
        }catch(IOException e){
            throw new LoadFileException("Error loading file");
        }

    }

}