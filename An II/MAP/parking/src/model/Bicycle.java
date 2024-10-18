package model;

public class Bicycle implements Vehicle{

    String name;
    String color;
    public Bicycle(String name, String color){
        this.name = name;
        this.color = color;
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
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return "Bike: " + getName() + " of color: " + getColor();
    }

}
