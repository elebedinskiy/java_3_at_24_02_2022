package ru.java;

public class Fruit {

    private int quantity;
    private float weght;

    Fruit(int quantity, float weghtOne){
        this.quantity = quantity;
        this.weght = weghtOne * quantity; // weghtOne будем определять в классах наследниках как константу
    }

    public int getQuantity() {
        return quantity;
    }

    public float getWeght() {
        return weght;
    }

}