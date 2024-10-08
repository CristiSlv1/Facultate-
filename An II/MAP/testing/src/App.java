
public class App {
    
    static void interschimba(int x, int y){
        int tmp = x;
        x = y;
        y = tmp; 
        System.out.println("X: " + x + ", Y: "+ y);
    }
    
    public static void main(String[] args) {
        int x = 2;
        int y = 6;
        interschimba(x,y);
        System.out.println("x = " + x + " y = " + y);

    }
}