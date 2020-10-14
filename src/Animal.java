public abstract class Animal {
    protected String name;
    protected String color;
    protected int age;
    protected int distance;
    protected float heights;
    protected int limit;
    protected float limitJump;

    Animal(){
        this.name = "Неизвестно";
        this.color = "Неизвестно";
        this.age = 0;
        this.distance = 0;
        this.heights = 0;
        this.limit = 0;
        this.limit = 0;
    }

    abstract void run();
    void runDistanceTrue(){
        System.out.println(", " + age + " годиков, бежит " + distance + " метров(а)    Максимально " + limit);
    }
    void runDistanceFalse(int dist){
        System.out.print(", " + age + " годиков, не может бежать больше " + dist + " метров");
    }
    abstract void swim();
    void swimDistanceTrue(){
        System.out.println(", " + age + " годиков, плывет " + distance + " метров(а)    Максимально " + limit);
    }
    void swimDistanceFalse(int dist){
        System.out.print(", " + age + " годиков, не может плавать больше " + dist + " метров(а)");
    }
    abstract void jumpOver();
    void jumpOverHeightTrue(){
        System.out.println(", " + age + " годиков, прыгает через препятствие на " + heights + " метров(а)    Максимально " + limitJump);
    }
    void jumpOverHeightFalse(float dist){
        System.out.print(", " + age + " годиков, не может прыгать через препятствие больше " + dist + " метров(а)");
    }
}