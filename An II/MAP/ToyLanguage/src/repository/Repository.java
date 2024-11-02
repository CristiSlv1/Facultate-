package repository;
import java.util.LinkedList;
import java.util.List;
import model.states.PrgState;

public class Repository implements IRepository{
    private final List<PrgState> programs;
    private int currentProgramIndex;

    public Repository(){
        this.programs = new LinkedList<>();
        this.currentProgramIndex = 0;
    }

    @Override
    public PrgState getCurrentProgram() {
        return this.programs.get(this.currentProgramIndex);
    }

    @Override
    public void addProgram(PrgState program) {
        this.programs.add(program);
    }

}
