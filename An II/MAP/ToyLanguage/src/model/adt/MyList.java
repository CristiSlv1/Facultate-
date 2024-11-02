package model.adt;

import java.util.List;

public class MyList<T> implements IMyList<T> {

    private final List<T> list;

    public MyList(){
        this.list = new java.util.ArrayList<>();
    }

    @Override
    public void add(T element) {
        list.add(element);
    }

    @Override
    public List<T> getAll() {
        return this.list;
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        for(T element : this.list){
            str.append(element).append("\n");
        }
        return "My list contains: " + str;
    }
}
