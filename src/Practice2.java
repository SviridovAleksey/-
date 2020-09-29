import java.util.*;


public class Practice2 {
    public static void main(String[] args) {
        task1();
        task2();
        task3();
        task4();
        task5();
        System.out.println("Задание 6");
        System.out.println("Введите размер однмерного массива");
        Scanner input = new Scanner(System.in);
        int arrLength = input.nextInt();
        int arr[] = new int[arrLength];
        for (int i = 0; i < arr.length; i++) {
            arr[i]= (int) (Math.random()*10);
        }
        // int arr[] = {1,10,1,2,10};
        if (task6(arr)) { System.out.println("В массиве есть место, где левая и правая часть равны"); }
        else { System.out.println("В массиве нет места, где левая и правая часть равны"); }
        System.out.println("Задание 7");
        System.out.println("Введите кол-во сдвигов внутри массива");
        int n = input.nextInt();
        task7(arr,n);
    }

    public static void task1() {
        System.out.println("Задание 1");
        Scanner input = new Scanner(System.in);
        System.out.println("Введите размер первого одномерного массива");
        int firstArray = input.nextInt();
        byte[] arr = new byte[firstArray];
        System.out.print("[");
        for (byte i = 0; i < arr.length; i++) {
            arr[i] = (byte) (Math.random() * 2);
            if (arr[i] == 1) {
                arr[i] = 0;
            } else {
                arr[i] = 1;
            }
            System.out.print(" " + arr[i]);
        }
        System.out.println(" ]");
    }

    public static void task2() {
        int[] arr = new int[9];
        System.out.println("Задание 2");
        System.out.print("[");
        for (int i = 0; i < (arr.length - 1); i++) {
            arr[i + 1] = arr[i] + 3;
            System.out.print(" " + arr[i]);
        }
        System.out.println(" ]");
    }

    public static void task3() {
        int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println("Задание 3");
        System.out.print("Исходный [");
        for (int j = 0; j < arr.length; j++) {
            System.out.print(" " + arr[j]);}
        System.out.println(" ]");
        System.out.print("Преобразованный [");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) { arr[i] = arr[i] * 2;}
            System.out.print(" " + arr[i]);
        }
        System.out.println(" ]");
    }
    public static void task4() {
        System.out.println("Задание 4");
        Scanner input = new Scanner(System.in);
        System.out.println("Введите размер квадратного двумерного массива");
        int arrLength = input.nextInt();
        int[][] arr = new int [arrLength][arrLength];
        for (int k = 0; k < arrLength; k++) {
            arr[k][k] = 1;
            arr[k][arr[k].length - k-1] = 1;
        }
        for (int i = 0; i < arr.length; i++)  {
            for (int j = 0; j< arr[i].length; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();

        }
    }

    public static void task5() {
        System.out.println("Задание 5");
        System.out.println("Введите размер одномерного массива");
        Scanner input = new Scanner(System.in);
        int arrLength = input.nextInt();
        int arr[] = new int [arrLength];
        int max = arr[0]; int min = 1001;
        System.out.print("[ ");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100);
            System.out.print(arr[i]+" ");
            // без интернета
            if (arr[i] > max) { max = arr[i];}
            if (arr[i] < min ) { min = arr[i];}
        }
        System.out.println("]");
        System.out.println("Максимальное значение в массиве "+ max);
        System.out.println("Минимальное значение в массиве "+ min);
        // с интернетом
        OptionalInt maxi = Arrays.stream(arr).max();
        OptionalInt mini = Arrays.stream(arr).min();
        System.out.println("Максимум " + maxi.getAsInt());
        System.out.println("Минимум " + mini.getAsInt());
    }

    public static boolean task6(int arr[]){
        boolean bool; float sum = 0; float prov = 0;
        System.out.println("Сгенерированный массив :");
        System.out.print("[ ");
        for (int j = 0; j < arr.length; j++) {
            System.out.print(arr[j] + " ");
            sum = sum + arr[j];
        }
        System.out.println("]");
        for (int k = 0; k < arr.length; k++ ) {
            if ( prov < (sum/2)) {  prov = arr[k] + prov; }
        }
        if (sum % prov == 0) { bool=true; }
        else {bool = false; };
        return bool;
    }

    public static void task7(int arr[], int n){
        int store;
        System.out.print("[ ");
        // положительный сдвиг
        if (n>=0){
            for (int i = 0; i < n; i++) {
                store = arr[arr.length-1];
                for (int j = arr.length - 1; j >= 0; j--) {
                    if (j>0) { arr[j] = arr[j-1];}
                    else {arr[0] = store;}
                } } }
        // отрицательный сдвиг
        else {
            for (int i = n; i < 0; i++) {
                store = arr[0];
                for (int j = 0; j < arr.length; j++) {
                    if (j < (arr.length - 1)) { arr[j] = arr[j+1];}
                    else {arr[arr.length - 1] = store;} }}}
        for (int k = 0; k<arr.length; k++){
            System.out.print(arr[k]+" ");
        }
        System.out.println("]");
    }
}
