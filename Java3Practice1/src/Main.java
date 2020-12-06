import java.util.ArrayList;
import java.util.Collections;

/*
1. Написать метод, который меняет два элемента массива местами.(массив может быть любого ссылочного типа);
2. Написать метод, который преобразует массив в ArrayList;
3. Большая задача:
a. Есть классы Fruit -> Apple, Orange;(больше фруктов не надо)
b. Класс Box в который можно складывать фрукты, коробки условно сортируются по типу фрукта, поэтому в одну коробку
   нельзя сложить и яблоки, и апельсины;
c. Для хранения фруктов внутри коробки можете использовать ArrayList;
d. Сделать метод getWeight() который высчитывает вес коробки, зная количество фруктов и вес одного фрукта
(вес яблока - 1.0f, апельсина - 1.5f, не важно в каких это единицах);
e. Внутри класса коробка сделать метод compare, который позволяет сравнить текущую коробку с той, которую подадут
   в compare в качестве параметра, true - если их веса равны, false в противном случае(коробки с яблоками мы можем
   сравнивать с коробками с апельсинами);
f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую коробку(помним про сортировку
   фруктов, нельзя яблоки высыпать в коробку с апельсинами), соответственно в текущей коробке фруктов не остается,
   а в другую перекидываются объекты, которые были в этой коробке;
g. Не забываем про метод добавления фрукта в коробку.
*/

public class Main {
    public static void main(String[] args) {
        String[] array = {"1","2","3","4","5","6"};
        Fruit[] fruits = {new Apple(), new Orange(), new Orange(), new Apple()};

        Apple[] heapApple = {new Apple(), new Apple(), new Apple(), new Apple(), new Apple(), new Apple(),
                new Apple(), new Apple(), new Apple()};
        Orange[] heapOrange = {new Orange(), new Orange(), new Orange(), new Orange(), new Orange(), new Orange()};
        replace(array, 0,4);
        replace(fruits, 0, 2);

        Box<Apple> appleBox = new Box<>();
        Box<Orange> orangeBox = new Box<>();
        Box<Orange> emptyBox = new Box<>();

        appleBox.addFruitInBox(heapApple);
        orangeBox.addFruitInBox(heapOrange);

        System.out.println(appleBox.getWeight());
        System.out.println(orangeBox.getWeight());

        System.out.println(appleBox.compareTo(orangeBox));

        emptyBox.shiftBox(orangeBox);
        System.out.println(orangeBox.getBox());
        System.out.println(emptyBox.getBox());

        orangeBox.addFruitInBox(new Orange());

    }

    private static void replace(Object[] array, int indexOne, int indexTwo) {
        if (indexOne <= array.length && indexOne >= 0 && indexTwo <= array.length && indexTwo >= 0) {
            ArrayList<Object> arrayList = transformation(array);
            System.out.println(arrayList);
            Collections.swap(arrayList,indexOne,indexTwo);
            System.out.println(arrayList);
        }

    }

    private static ArrayList<Object> transformation (Object[] array) {
        ArrayList<Object> arrayList = new ArrayList<Object>();
        Collections.addAll(arrayList, array);
        return arrayList;
    }

}
