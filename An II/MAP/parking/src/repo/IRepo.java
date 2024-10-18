package repo;
import exceptions.RepoExceptions;
import model.Vehicle;

public interface IRepo {
    public void add(Vehicle veh) throws RepoExceptions;
    public void remove(Vehicle veh) throws RepoExceptions;
    public Vehicle[] getAll();
    public int getLength();
    //public Vehicle[] getAllOfColor();
} 