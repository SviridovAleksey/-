public class Dog extends Animal {
    @Override
    void run (){
        System.out.print(color + " cобакен по имени " + name);
        if (limit >= distance) {
            runDistanceTrue(); }
        else {
            runDistanceFalse(limit);
            System.out.println("   Попросили бежать ( " + distance + " )");
        }
    }
    @Override
    void swim(){
        System.out.print(color + " cобакен по имени " + name);
        if (limit >= distance) {
            swimDistanceTrue(); }
        else {
            swimDistanceFalse(limit);
            System.out.println("   Попросили плыть ( " + distance + " )");
        }
    }
    @Override
    void jumpOver(){
        System.out.print(color + " cобакен по имени " + name);
        if (limitJump >= heights) {
            jumpOverHeightTrue(); }
        else {
            jumpOverHeightFalse(limitJump);
            System.out.println("   Попросили прыгнуть ( " + heights + " )");
        }
    }
}