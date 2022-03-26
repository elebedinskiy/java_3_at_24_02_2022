package ru.java;

public class Apple extends Fruit{

    private static final float weghtOne = 1.0f; // вес каждого яблока = 1.5

    public Apple(int quantity) {
        super(quantity, weghtOne);
    }

}