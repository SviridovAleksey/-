import java.util.Random;

 class Cat extends Participant implements iAct {

    Cat(){
        setKind("Котэ");
    }

    @Override
    public void runnig() {
        Random random = new Random();
        setLimit(random.nextInt(200));
    }

    @Override
    public void jumping() {
        Random random = new Random();
        setLimit(random.nextInt(10));
    }

    @Override
    public void swiming() {
        Random random = new Random();
        setLimit(random.nextInt(30));
    }

}
