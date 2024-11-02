package repo;
import exceptions.RepoExceptions;
import model.Vehicle;

public class Repo implements IRepo{

    private Vehicle[] vehicles;
    public final static int SIZE = 10;
    private int length = 0;

    public Repo(){
        vehicles = new Vehicle[SIZE];
    }

        
    @Override
    public void add(Vehicle veh) throws RepoExceptions {
    for (int i = 0; i < this.length; i++) {
        //this.vehicle[i].equals(veh)
        if (this.vehicles[i].getName().equals(veh.getName()) &&
            this.vehicles[i].getColor().equals(veh.getColor())) {
            throw new RepoExceptions("Vehicle already exists in the repository!");
        }
    }

    if (this.length >= SIZE) {
        throw new RepoExceptions("Repository is full. Cannot add more vehicles.");
    }

    this.vehicles[this.length++] = veh;
    System.out.println("Vehicle added!");
    }

    @Override
    public void remove(Vehicle veh) throws RepoExceptions{
        int startLength = this.length;
        for(int i = 0; i < this.length; i++){
            if(this.vehicles[i].getName().equals(veh.getName()) && this.vehicles[i].getColor().equals(veh.getColor())){
                for(int j = i; j < this.length-1; j++){
                    this.vehicles[j] = this.vehicles[j+1];
                }
                this.length--;
            }
        }
        if (startLength == this.length){
            throw new RepoExceptions("Vehicle not found!");
            //System.out.println("Vehicle not found!");
        }
        else{
            System.out.println("Vehicle removed!");
        }
    }

    @Override
    public Vehicle[] getAll() {
        return this.vehicles;
    }

    @Override
    public int getLength(){
        return this.length;
    }
}
