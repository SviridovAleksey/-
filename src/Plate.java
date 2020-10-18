public class Plate {
    protected int plate;

    Plate(){
        this.plate = 20;
    }

    int foodPlate(int howMuchFood, String name){
        int amount = 0;
        int diff = 0;
        if (plate > 0 && howMuchFood > 0) {
            System.out.println(name + " захотел(а) съесть " + howMuchFood + " еды");
            System.out.println("В тарелке было " + plate + " еды");
            while (plate > 0 && howMuchFood > 0) {
                plate --;
                howMuchFood --;
                amount ++; }
            System.out.println("Было съедено " + amount + " едениц еды, В тарелке осталось " + plate);
            if (howMuchFood>plate) {diff = howMuchFood - plate ;} }
        else if (howMuchFood == 0) {return howMuchFood;}
        else {System.out.println(name + " захотел(а) съесть " + howMuchFood + " еды");
            System.out.println("но тарелка пуста "); return howMuchFood;}
        return diff;
    }

}