package controller;
import exceptions.RepoExceptions;
import model.Vehicle;
import repo.IRepo;
import repo.Repo;

public class Controller {
    IRepo repo;

    public Controller(IRepo repo){
        this.repo = repo;
    }

    public Vehicle[] getAllVehicles(){
        Vehicle[] vehicles = repo.getAll();
        return vehicles;
    }

    public Vehicle[] filterByColor(String color){
            Vehicle[] filteredVehicles = new Vehicle[Repo.SIZE];
            Vehicle[] allVehicles = repo.getAll();
            int actualPos = 0;
            for(int pos = 0; pos < repo.getLength(); pos++){
                if(allVehicles[pos] != null && allVehicles[pos].getColor().equals(color)){
                    filteredVehicles[actualPos++] = allVehicles[pos];
                }
            }
            return filteredVehicles;
    }

    public void add(Vehicle veh) throws RepoExceptions{
        repo.add(veh);
    }

    public void remove(Vehicle veh) throws RepoExceptions{
        repo.remove(veh);
    }
}
