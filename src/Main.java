import java.util.Random;
import java.util.Scanner;

public class Main {
    public static String[] nameCat = {"Барсик", "Кот", "Рыжик", "Страшила", "Соня", "Васька"};
    public static  String[] nameDog = {"Джек", "Ерошка", "Жорик", "Зимбо", "Исай", "Кельвин"};
    public static  String[] color = {"Рыжий", "Белый", "Малиновый", "Красный", "Зеленый", "Синий"};
    public static String[] kindOfAnimal = {"Собакен ", "Коте "};
    public static String[] amountAnimal;
    public static int[] amountHungry;

    public static void main(String[] args) {
        int who;
        int action;
        int numberAnimal = 5;
        amountAnimal = new String[numberAnimal];
        amountHungry = new int[numberAnimal];
        Random random = new Random();
        Plate plate = new Plate();
        for (int i=0; i < numberAnimal; i++) {
            who = random.nextInt(2);
            action = random.nextInt(3);
            fillingAnimal(action, who, i, plate);
            System.out.println("");
        }
        while (output() && check(plate)) {
            feed(plate);
        }
        System.out.println("В тарелке осталось (не доели) :" + plate.plate);

    }
    public static void fillingAnimal(int action, int who, int i, Plate plate) {
        Random random = new Random();
        Animal animal;
        Dog dog = new Dog();
        Cat cat = new Cat();
        if (who == 0) {animal = dog; animal.name =kindOfAnimal[who] + nameDog[random.nextInt(nameDog.length - 1)];}
        else {animal = cat; animal.name =kindOfAnimal[who] + nameCat[random.nextInt(nameDog.length - 1)];}
        animal.color = color[random.nextInt(color.length - 1)];
        animal.age = random.nextInt(19) + 1;
        switch (action) {
            case (0):
                animal.distance = random.nextInt(700);
                animal.limit = random.nextInt(700);
                animal.run();
                break;

            case (1):
                animal.distance = random.nextInt(20);
                animal.limit = random.nextInt(20);
                animal.swim();
                break;

            case (2):
                animal.heights = (float) (Math.random() * 2f);
                animal.limitJump = (float) (Math.random() * 2f);
                animal.jumpOver();
                break;
        }
        amountAnimal[i] = animal.name;
        amountHungry[i] = plate.foodPlate(animal.hungry(), animal.name);
    }

    public static boolean output(){
        int chek = 0;
        System.out.println("");
        for (int i = 0; i < amountAnimal.length; i++) {
            System.out.print(amountAnimal[i] + " голоден на " + amountHungry[i] + " еды");
            if (amountHungry[i] > 0) {chek ++; System.out.println(" (голоден)");}
            else {System.out.println(" (сыт)");}
        }
        System.out.println("");
        if (chek == 0) {System.out.println("Все животные накормлены, спасибо, до свидания."); return false;}
        else {System.out.println("Есть голодные животные"); return true;}
    }

    public static void feed(Plate plate){
        for (int i = 0; i < amountHungry.length; i++ ){
            amountHungry[i] = plate.foodPlate(amountHungry[i], amountAnimal[i]);}

    }

    public static boolean check(Plate plate){
        int chek;
        System.out.println("Добавить еще еды в тарелку ?  1 - да / любая другая - нет");
        chek = checkInt();
        if (chek == 1) {
            System.out.println("Сколько ?");
            plate.plate = checkInt(); return true;}
        else { System.out.println("До свидания, жадина"); return false;}
    }

    public static int checkInt() {
        boolean check = false;
        int chek = 0;
        while (!check) {
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                chek = scanner.nextInt();
                check = true;
            } else {check = false;
                System.out.println("Введите число:"); }
        }
        return chek;
    }
}