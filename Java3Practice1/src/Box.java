import java.util.ArrayList;
import java.util.Collections;

public class Box <T extends Fruit>{

    private ArrayList<T> boxFill = new ArrayList<>();

    public void addFruitInBox (T fruit) {
        boxFill.add(fruit);
        System.out.println(boxFill);
    }

    public void addFruitInBox(T[] fruit) {
        Collections.addAll(boxFill,fruit);
        System.out.println(boxFill);
    }

    public float getWeight () {
        float size = boxFill.size();
        return size * boxFill.get(0).getWeight();
    }

    public Boolean compareTo(Box<?> extendsBox) {
        if (extendsBox.getWeight() == this.getWeight()) {
            return true;
        }
        else {return false;}
    }

    public ArrayList<T> getBox (){
        return boxFill;
    }

    public void removeAllFruit() {
        boxFill.clear();
    }

    public void shiftBox (Box<T> extendsBox){
        boxFill.addAll(extendsBox.getBox());
        extendsBox.removeAllFruit();
    }
}
