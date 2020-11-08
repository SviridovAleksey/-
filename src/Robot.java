import java.util.Random;

class Robot extends Participant implements iAct {

    Robot(){
        setKind("Робот");
    }

    @Override
    public void runnig() {
        Random random = new Random();
        setLimit(random.nextInt(1000));
    }

    @Override
    public void jumping() {
        Random random = new Random();
        setLimit(random.nextInt(100));
    }

    @Override
    public void swiming() {
        Random random = new Random();
        setLimit(random.nextInt(5));
    }

}
