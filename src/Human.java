import java.util.Random;

class Human extends Participant implements iAct{

    Human (){
        setKind("Человек");
    }

    @Override
    public void runnig() {
        Random random = new Random();
        setLimit(random.nextInt(700));
    }

    @Override
    public void jumping() {
        Random random = new Random();
        setLimit(random.nextInt(10));
    }

    @Override
    public void swiming() {
        Random random = new Random();
        setLimit(random.nextInt(50));
    }

}
