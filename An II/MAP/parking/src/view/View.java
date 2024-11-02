package view;
import controller.*;
import exceptions.*;
import model.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class View {
    Controller controller;
    String color;
    private Scanner scanner;

    public View(Controller controller){
        this.controller = controller;
    }

    public String readColor(){
        scanner = new Scanner(System.in);
        String color;
        while(true) {
            System.out.print("Enter color: ");
            try {
                color = scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
                scanner.next();
            }
        }
        //System.out.println("color: " + color);
    return color;
    }

    public void addVehicle() throws RepoExceptions{
        scanner = new Scanner(System.in);
        System.out.println("Enter vehicle type: ");
        String type = scanner.nextLine();
        System.out.println("Enter vehicle name: ");
        String name = scanner.nextLine();
        System.out.println("Enter vehicle color: ");
        String color = scanner.nextLine();

        if(type.equals("car")){
            controller.add(new Car(color,name));
        }
        else if (type.equals("bicycle")){
            controller.add(new Bicycle(name, color));
        }

        else if (type.equals("motorcycle")){
            controller.add(new Motorcycle(name, color));
        }
        else{
            System.out.println("Invalid vehicle type");
        }
        //scanner.close();
    }

    public void removeVehicle() throws RepoExceptions{
        scanner = new Scanner(System.in);
        System.out.println("Enter vehicle type: ");
        String type = scanner.nextLine();
        System.out.println("Enter vehicle name: ");
        String name = scanner.nextLine();
        System.out.println("Enter vehicle color: ");
        String color = scanner.nextLine();

        if(type.equals("car")){
            controller.remove(new Car(color,name));
        }
        else if (type.equals("bicycle")){
            controller.remove(new Bicycle(color, name));
        }

        else if (type.equals("motorcycle")){
            controller.remove(new Motorcycle(color, name));
        }
        else{
            System.out.println("Invalid vehicle type");
        }
        //scanner.close();
    }

    public void display() {
        Vehicle[] filteredItems = controller.filterByColor(color);
        //Vehicle[] vehicles = controller.getAllVehicles();
        for(Vehicle item : filteredItems) {
            //System.out.println("1");
            System.out.println(item);
        }
    }

    public void displayMenu() {
        System.out.println("Choose option:");
        System.out.println("1. Populate in-memory Vehicles");
        //System.out.println("2. Display Vehicles");
        System.out.println("2. Filter Vehicles");
        System.out.println("3. Add a Vehicle");
        System.out.println("4. Remove a Vehicle");

        System.out.println("5. Exit");
    }

    private void populate() throws RepoExceptions {
        controller.add(new Car("red", "Mercedes"));
        controller.add(new Car("blue", "BMW"));
        controller.add(new Car("green", "Toyota"));
        controller.add(new Bicycle("red", "BMX"));
        controller.add(new Motorcycle("red", "Yamaha"));
        controller.add(new Car("blue", "Mercedes"));
        controller.add(new Car("red", "BMW"));
        controller.add(new Car("red", "Toyota"));
        controller.add(new Bicycle("blue", "BMX"));
        controller.add(new Motorcycle("blue", "Yamaha"));
    }

    public void run() throws RepoExceptions {
        while(true) {
            try{
                displayMenu();
                scanner = new Scanner(System.in);
                int option = scanner.nextInt();
                switch(option) {
                    case 1: // add
                        populate();
                        break;
                    //case 2: // display
                        //display();
                        //break;
                    case 2: // filter
                        color = readColor();
                        display();
                        break;
                    case 3: // add
                        addVehicle();
                        break;
                    case 4: // remove
                        removeVehicle();
                        break;
                    case 5:
                        scanner.close();
                        System.exit(0);
                }
                //scanner.close();
            }catch(RepoExceptions e){
                System.out.println("Error: " + e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number for the menu option.");
                scanner.next();
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }
}

