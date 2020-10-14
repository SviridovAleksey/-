public class Cat extends Animal{
    @Override
    void run (){
        System.out.print(color + " котэ по имени " + name);
        if (limit >= distance) {
            runDistanceTrue(); }
        else {
            runDistanceFalse(limit);
            System.out.println("   Попросили бежать ( " + distance + " )");
        }
    }
    @Override
    void swim(){
        System.out.println(color + " котэ " + name + ", " + age + " годиков не умеет плавать");
    }
    @Override
    void jumpOver(){
        System.out.print(color + " котэ по имени " + name);
        if (limitJump >= heights) {
            jumpOverHeightTrue(); }
        else {
            jumpOverHeightFalse(limitJump);
            System.out.println("   Попросили прыгнуть ( " + heights + " )");
        }
    }
}