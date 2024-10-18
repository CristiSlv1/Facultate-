import controller.*;
import exceptions.*;
import repo.*;
import view.*;

public class Main {
    public static void main(String args[]){
        IRepo repo = new Repo();
        Controller controller = new Controller(repo);
        View view = new View(controller);
        try {
            view.run();
        } catch (RepoExceptions e) {
            System.out.println("Repo exception: " + e.getMessage());
        }
    }
}
