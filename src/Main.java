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
            if (who > 0) {fillingDog(action);}
            else {fillingCat(action);}
        }


    }
    public static void fillingDog(int action) {
        Random random = new Random();
        Dog dog = new Dog();
        dog.name = nameDog[random.nextInt(nameDog.length - 1)];
        dog.color = color[random.nextInt(color.length - 1)];
        dog.age = random.nextInt(19) + 1;
        switch (action) {
            case (0):
                dog.distance = random.nextInt(700);
                dog.limit = random.nextInt(700);
                dog.run();
                break;

            case (1):
                dog.distance = random.nextInt(20);
                dog.limit = random.nextInt(20);
                dog.swim();
                break;

            case (2):
                dog.heights = (float) (Math.random() * 2f);
                dog.limitJump = (float) (Math.random() * 2f);
                dog.jumpOver();
                break;
        }
    }

    public static void fillingCat(int action){
        Random random = new Random();
        Cat cat = new Cat();
        cat.name = nameCat[random.nextInt(nameDog.length - 1)];
        cat.color = color[random.nextInt(color.length - 1)];
        cat.age = random.nextInt(19)+1;
        switch (action) {
            case (0) :
                cat.distance = random.nextInt(700);
                cat.limit = random.nextInt(700);
                cat.run(); break;

            case (1) :
                cat.distance = random.nextInt(20);
                cat.limit = random.nextInt(20);
                cat.swim(); break;

            case (2) :
                cat.heights = (float) (Math.random() * 4f);
                cat.limitJump = (float) (Math.random() * 4f);
                cat.jumpOver(); break;

        }
    }
}

