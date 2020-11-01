public class Main {

    public static void main(String[] args) throws MyArraySizeException {
//        String[][] arr = new String[4][4];
        String[][] arr = {{"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}};
        System.out.println("Начало");
        try {
            task(arr);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MyArraySizeException(e);
        } catch (NumberFormatException e) {
            throw new MyArrayDataException(e);
        } finally {
            System.out.println("Конец");
        }
        System.out.println("Продолженное выполнение(если закоментировать MyArray)");
    }

    protected static void task(String[][] arr) {
        if (arr.length != 4 || arr[0].length != 4) {
            throw new ArrayIndexOutOfBoundsException(" Массив должен быть 4х4");
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                try {
                    sum = Integer.parseInt(arr[i][j]) + sum;
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("Не допустимый символ, координаты - " + i + " " + j); }
            }
        }
        System.out.println(sum);
    }
}