import java.util.Random;

public class Animal {
    protected String name;
    protected String color;
    protected int age;
    protected int distance;
    protected float heights;
    protected int limit;
    protected float limitJump;
    protected int hungry;

    Animal(){
        this.name = "Неизвестно";
        this.color = "Неизвестно";
        this.age = 0;
        this.distance = 0;
        this.heights = 0;
        this.limit = 0;
        this.limitJump = 0;
        this.hungry = 0;
    }

    void run(){
        if (correct(distance,limit, limitJump)){
            System.out.println(color + " " + name + ", " + age + " годиков, бежит " + distance + " метров(а)    Максимально " + limit);
        }
        else{
            System.out.println(color + " " + name + ", " + age + " годиков, не может бежать больше " + limit + " метров" + " попросили " + distance);
        }}
    void swim(){
        if (correct(distance,limit, limitJump)) {
            System.out.println(color + " " + name + ", " + age + " годиков, плывет " + distance + " метров(а)    Максимально " + limit);
        }
        else{
            System.out.println(color + " " + name + ", " + age + " годиков, не может плавать больше " + limit + " метров(а)" + " попросили " + distance);
        }}
    void jumpOver() {
        if (correct(distance,limit, limitJump)) {
            System.out.println(color + " " + name + ", " + age + " годиков, прыгает через препятствие на " + heights + " метров(а)    Максимально " + limitJump);
        }
        else {
            System.out.println(color + " " + name + ", " + age + " годиков, не может прыгать через препятствие больше " + heights + " метров(а)"  + " попросили " + limitJump);
        }}
    boolean correct (float dist, float limit, float limitJump){
        return dist <= limit || dist <= limitJump;
    }

    int hungry () {
        Random random = new Random();
        int hunger = random.nextInt(15);
        return hunger;
    }
}