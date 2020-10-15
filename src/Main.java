import java.util.Random;

public class Main {
    public static String[] nameCat = {"Барсик", "Кот", "Рыжик", "Страшила", "Соня", "Васька"};
    public static  String[] nameDog = {"Джек", "Ерошка", "Жорик", "Зимбо", "Исай", "Кельвин"};
    public static  String[] color = {"Рыжий", "Белый", "Малиновый", "Красный", "Зеленый", "Синий"};


    public static void main(String[] args) {
        int who;
        int action;
        Random random = new Random();
        for (int i=0; i < 5; i++) {
            who = random.nextInt(2);
            action = random.nextInt(3);
            fillingAnimal(action, who);
        }
    }
    public static void fillingAnimal(int action, int who) {
        Random random = new Random();
        Animal animal;
        Dog dog = new Dog();
        Cat cat = new Cat();
        if (who>0) {animal = dog; animal.name = nameDog[random.nextInt(nameDog.length - 1)];}
        else {animal = cat; animal.name = nameCat[random.nextInt(nameDog.length - 1)];}
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
    }






}

