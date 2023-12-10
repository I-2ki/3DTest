package app;

import app.Vector;

public class Main {
    public static void main(String... args){
        Vector vector1 = new Vector(1,2,4);
        Vector vector2 = new Vector(1,2);
        System.out.println(Vector.crossProduct(vector1, vector2).unitVector().scale());;
    }
}