import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
/*1. Написать метод, которому в качестве аргумента передается не пустой одномерный целочисленный массив. Метод должен
вернуть новый массив, который получен путем вытаскивания из исходного массива элементов, идущих после последней четверки.
Входной массив должен содержать хотя бы одну четверку, иначе в методе необходимо выбросить RuntimeException.
Написать набор тестов для этого метода (по 3-4 варианта входных данных).
Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].

2. Написать метод, который проверяет состав массива из чисел 1 и 4. Если в нем нет хоть одной четверки или единицы,
то метод вернет false; Если есть элементы отличные от 1 и 4 то вернуть false.
[ 1 1 1 4 4 1 4 4 ] -> true
[ 1 1 1 1 1 1 ] -> false
[ 4 4 4 4 ] -> false
[ 1 4 4 1 1 4 3 ] -> false
*/
public class Main {
    public static void main(String[] args) {
//
//        Integer[]  arr = {1, 2, 3, 4, 5, 6, 4, 8, 9, 10};
//        System.out.println(getNewMassTask1(arr));
//
//        Integer[]  arrTask2 = {1, 1, 1, 1, 1, 1, 1, 1, 1, 4};
//        System.out.println(isOneFourTask2(arrTask2));

    TestClass test = new TestClass();
    Class myTest = MyTest.class;
    test.start(myTest.getName());
    }


    public static List getNewMassTask1(Integer[] arr) {

        List<Integer> arrList = new ArrayList<Integer>();
        arrList = Arrays.asList(arr);

        if (!arrList.contains(4)) {
            throw new NumberFormatException("Массив не содержит ни одно четверки");
        }
        int idxLast = arrList.lastIndexOf(4);
        if (idxLast == arrList.size() - 1) {
            return null;

        } else {


            arrList = arrList.subList(idxLast + 1, arrList.size());
            return arrList;
        }
    }

    public static Boolean isOneFourTask2(Integer[] arr) {

        List<Integer> arrList;
        arrList = new LinkedList<Integer>(Arrays.asList(arr));

        Boolean isContains = false;

        if (arrList.contains(1) && arrList.contains(4)) {
            isContains = true;
        }

        List<Integer> conditions = new LinkedList<Integer>();
        conditions.add(1);
        conditions.add(4);
        arrList.removeAll(conditions);

        if (!arrList.isEmpty() || !isContains) {
            return false;
        } else return true;
    }
}


