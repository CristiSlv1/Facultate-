package model;

public class Car implements Vehicle {

    protected String color;
    protected String name;
    
    public Car(String color, String name){
        this.color = color;
        this.name = name;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public void setName(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return "Car: " + getName() + ", color: " + color;
    }
}
