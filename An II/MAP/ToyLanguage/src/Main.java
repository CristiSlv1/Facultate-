import controller.Controller;
import repository.IRepository;
import repository.Repository;
import view.View;

public class Main {
    public static void main(String[] args) {
        IRepository repository = new Repository();
        Controller controller = new Controller(repository, false);
        View view = new View(controller);
        view.run();
    }
}

// schimba in visual, fisierele ".class" sa fie facute in alta parte, preferabil in bin