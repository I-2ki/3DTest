package app;

import java.util.*;

public class Vector {
    ArrayList<Double> elements = new ArrayList<>();

    Vector(double... elements) {
        for (double element : elements) {
            this.elements.add(element);
        }
    }

    Vector(ArrayList<Double> elements) {
        this.elements = elements;
    }

    void log() {
        System.out.println(this.elements);
    }

    double scale() {
        double squareSum = 0;
        for (double element : elements) {
            squareSum += Math.pow(element, 2);
        }
        return Math.sqrt(squareSum);
    }

    Vector rescaledVector(double scaleFactor){
        ArrayList<Double> result = new ArrayList<Double>();
        for(double element:elements){
            result.add(element * scaleFactor);
        }

        return new Vector(result);
    }

    Vector unitVector(){
        return this.rescaledVector(1 / this.scale());
    }

    int dimension() {
        return elements.size();
    }

    void rescaleDimension(int newDimension) {
        if (newDimension < 0)
            throw new IllegalArgumentException("Invalid Vector dimension. Please check rescale method.");

        if (this.dimension() == newDimension)
            return;

        if (this.dimension() < newDimension) {
            final int NUM_ADD_DIMENTION = newDimension - this.dimension();
            for (int i = 0; i < NUM_ADD_DIMENTION; i++) {
                elements.add(0.0);
            }
        }

        if (this.dimension() > newDimension) {
            final int NUM_REMOVE_DIMENTION = this.dimension() - newDimension;
            for (int i = 0; i < NUM_REMOVE_DIMENTION; i++) {
                elements.remove(this.dimension() - 1);
            }
        }
    }

    // 以下、外積内積は次元の高い方に合わせます
    static double dotProduct(Vector vector1, Vector vector2) {
        final int HIGHER_DIMENTION = Math.max(vector1.dimension(), vector2.dimension());

        vector1.rescaleDimension(HIGHER_DIMENTION);
        vector2.rescaleDimension(HIGHER_DIMENTION);

        double result = 0;
        for (int i = 0; i < vector1.dimension(); i++) {
            result += vector1.elements.get(i) * vector2.elements.get(i);
        }
        return result;
    }

    static Vector crossProduct(Vector vector1, Vector vector2) {
        final int HIGHER_DIMENTION = Math.max(vector1.dimension(), vector2.dimension());

        vector1.rescaleDimension(HIGHER_DIMENTION);
        vector2.rescaleDimension(HIGHER_DIMENTION);

        ArrayList<Double> result = new ArrayList<Double>();
        for (int i = 0; i < vector1.dimension(); i++) {

            final int POINT_INDEX_1 = (i + 1) % vector1.dimension();
            final int POINT_INDEX_2 = (POINT_INDEX_1 + 1) % vector1.dimension();

            double element = vector1.elements.get(POINT_INDEX_1) * vector2.elements.get(POINT_INDEX_2) - vector2.elements.get(POINT_INDEX_1) * vector1.elements.get(POINT_INDEX_2);

            if (i % 2 == 1)
                element *= -1;

            result.add(element);
        }

        return new Vector(result);
    }
}
