package ru.java;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        System.out.println("Task #1");
        Integer[] nums1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        swap(nums1, 2, 9);
        System.out.println(Arrays.toString(nums1) + "\n");

        System.out.println("Task #2");
        Integer[] nums2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(arrayToList(nums2) + "\n");

        System.out.println("Task #3");
        System.out.println();
        // проверили, что коробки только для фруктов
        // Box<Integer> integerBox1 = new Box<Integer>(2);

        // создали две коробки
        Box<Apple> boxApple1 = new Box<Apple>();
        Box<Orange> boxOrange1 = new Box<Orange>();
        Box<Apple> boxApple2 = new Box<Apple>();
        Box<Orange> boxOrange2 = new Box<Orange>();

        // положили в коробки фрукты несколько раз для
        boxApple1.add(new Apple(1));
        boxOrange1.add(new Orange(1));
        boxApple1.add(new Apple(9));
        boxOrange1.add(new Orange(9));
        boxApple1.add(new Apple(2));
        boxOrange1.add(new Orange(2));

        boxApple2.add(new Apple(30));
        boxOrange2.add(new Orange(20));

        System.out.println("Коробка Яблоки-1 | вес: " + boxApple1.getWeight() + ", кол-во: " + boxApple1.getQuantity());
        System.out.println("Коробка Апельсины-1 | вес: " + boxOrange1.getWeight() + ", кол-во: " + boxOrange1.getQuantity());
        System.out.println("Коробка Яблоки-2 | вес: " + boxApple2.getWeight() + ", кол-во: " + boxApple2.getQuantity());
        System.out.println("Коробка Апельсины-2 | вес: " + boxOrange2.getWeight() + ", кол-во: " + boxOrange2.getQuantity());
        System.out.println();
        // сравниваем коробки по весу
        System.out.println("Равны ли по весу коробка Яблоки-1 и Яблоки-2? " + boxApple1.compare(boxApple2)); // false
        System.out.println("Равны ли по весу коробка Апельсины-1 и Апельсины-2? " + boxOrange1.compare(boxOrange2)); // false
        System.out.println("Равны ли по весу коробка Яблоки-1 и Апельсины-1? " + boxApple1.compare(boxOrange1)); // false
        System.out.println("Равны ли по весу коробка Апельсины-2 и Яблоки-2? " + boxOrange2.compare(boxApple2)); // true
        System.out.println();

        boxApple2.addFruitsFromIncomeBox(boxApple1);
        boxOrange2.addFruitsFromIncomeBox(boxOrange1);
        // boxOrange2.addFruitsFromIncomeBox(boxApple2); // проверка, что в коробку с апельсинами нельзя пересыпать яблоки
        System.out.println("Коробка Яблоки-1 | вес: " + boxApple1.getWeight() + ", кол-во: " + boxApple1.getQuantity());
        System.out.println("Коробка Апельсины-1 | вес: " + boxOrange1.getWeight() + ", кол-во: " + boxOrange1.getQuantity());
        System.out.println("Коробка Яблоки-2 | вес: " + boxApple2.getWeight() + ", кол-во: " + boxApple2.getQuantity());
        System.out.println("Коробка Апельсины-2 | вес: " + boxOrange2.getWeight() + ", кол-во: " + boxOrange2.getQuantity());
    }

    // 1. Написать метод, который меняет два элемента массива местами.(массив может быть любого ссылочного типа);
    public static void swap (Object[] array, int i, int j){
        Object buffer = array[i];
        array[i] = array[j];
        array[j] = buffer;
    }

    // 2. Написать метод, который преобразует массив в ArrayList;
    public static <T> ArrayList<T> arrayToList (T[] inputArray){
        return new ArrayList<T>(Arrays.asList(inputArray));
    }
}