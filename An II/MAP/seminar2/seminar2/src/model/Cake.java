package model;

public class Cake implements Item {
    protected String name;
    protected int weight;
    protected String color;
    
    public Cake (int price, String color){
        this.name = "Cake";
        this.weight = weight;
        this.color = color;
    }

    @Override
    public double getWeight() {
        // TODO Auto-generated method stub
        return this.weight;
        //throw new UnsupportedOperationException("Unimplemented method 'getWeight'");
    }

    @Override
    public double setWeight() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setWeight'");
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getName'");
    }

    @Override
    public String setName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setName'");
    } 
}
