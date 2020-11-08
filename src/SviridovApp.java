import java.util.Scanner;
import java.util.Scanner;

public class SviridovApp {
    public static void main(String args[]) {
        byte variableByte;
        short variableShort;
        int variableInt;
        final int variableIntFinal = 10000;
        long variableLong;
        float variableFloat;
        double variableDouble;
        char variableChar;
        boolean variableBoolean;
        Scanner num = new Scanner(System.in);
        int a; int b; int c; int d; float result3; String additive; String name; int god;
        System.out.println("Введите а: ");
        a = num.nextInt();
        System.out.println("Введите b: ");
        b = num.nextInt();
        System.out.println("Введите c: ");
        c = num.nextInt();
        System.out.println("Введите d: ");
        d = num.nextInt();
        result3 = task3(a, b, c, d);
        System.out.println("Результат вычисления a * (b + (c / d)) = " + result3);
        if (task4(a,b)) { additive = "в пределах"; }
        else { additive = "не в пределах"; }
        System.out.println("a + b " + additive + " от 10 до 20");
        task5(c);
        if (task6(d)) { System.out.println("Число d отрицательное "); }
        else { System.out.println("Число d положительное "); }
        System.out.println("Введите имя");
        name = num.next();
        task7(name);
        System.out.println("Введи год для проверки");
        god = num.nextInt();
        task8(god);
    }

    public static float task3(float a, float b, float c, float d) {
        float result = a * (b + (c / d));
        return result;
    }

    public static boolean task4(int x1,int x2) {
        boolean bool;
        if (10 <= x1+x2 && x1+x2 <=20) { bool = true; }
        else { bool = false; }
        return bool;
    }

    public static void task5(int c) {
        if (c<0) { System.out.println("Число 'c' меньше 0"); }
        else { System.out.println("Число 'c' больше 0"); }
    }

    public static boolean task6(int d){
        boolean bool;
        if (d<0) { bool = true; }
        else { bool = false; }
        return bool;
    }

    public static void task7(String name) {
        System.out.println("Привет " + name);
    }

    public  static void task8(float god) {
        float dop; float dop2;
        float dni = (god * 365.25f)%1;
        if (god > 99) {
            dop = god % 400f;
            dop2 = god % 100f + dop; }
        else {
            god = god * 100f;
            dop = god % 400f;
            dop2 = god % 100f + dop; }
        if (dni == 0 && dop2 == 0)  { System.out.println("Год високосный"); }
        else { System.out.println("Год не високосный"); }

    }


}