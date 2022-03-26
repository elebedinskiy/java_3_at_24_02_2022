package ru.java;

import java.util.ArrayList;

public class Box <T extends Fruit> {

    // в коробке будут храниться фрукты в листе (например, 3 шт. + 1 шт. + 10 шт. итого 13 фруктов)
    private final ArrayList<T> fruits = new ArrayList<>();

    // добавляем фрукты в коробку. например, яблок 10 штук, апельсинов 5 штук.
    public void add (T incomeFruits){
        this.fruits.add(incomeFruits);
    }

    // посчитаем вес коробки
    public float getWeight(){
        float weight = 0.0f;
        for (int i = 0; i < fruits.size(); i++) {
            weight += fruits.get(i).getWeght();
        }
        return weight;
    }

    // посчитаем кол-во фруктов в коробке
    public int getQuantity() {
        int quantity = 0;
        for (int i = 0; i < fruits.size(); i++) {
            quantity += fruits.get(i).getQuantity();
        }
        return quantity;
    }

    /*
    e. Внутри класса коробка сделать метод compare, который позволяет сравнить
    текущую коробку с той, которую подадут в compare в качестве параметра,
    true - если их веса равны, false в противном случае(коробки с яблоками
    мы можем сравнивать с коробками с апельсинами);
     */

    // <?> - означает, что коробки с яблоками можно сравнивать с коробками с апельсинами
    public boolean compare (Box<?> compareBox){
        return Math.abs(this.getWeight() - compareBox.getWeight()) < 0.000001f;
    }

    /*
    f. Написать метод, который позволяет пересыпать фрукты из текущей коробки
    в другую коробку(помним про сортировку фруктов, нельзя яблоки высыпать
    в коробку с апельсинами), соответственно в текущей коробке фруктов не остается,
    а в другую перекидываются объекты, которые были в этой коробке;
     */
    public void addFruitsFromIncomeBox (Box<T> incomeBox){

        // добавить в содержимое текущей коробки все элементы листа с фруктами
        this.fruits.addAll(incomeBox.getFruits());

        // обнулить кол-во фруктов в пересыпаемой коробке
        incomeBox.getFruits().clear();
    }

    // забираем все фрукты из коробки
    public ArrayList<T> getFruits() {
        return fruits;
    }
}