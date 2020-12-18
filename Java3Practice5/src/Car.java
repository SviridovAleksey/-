import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;
    private CyclicBarrier cd;
    private CountDownLatch cdlEnd;
    private CountDownLatch cdlStart;
    private List<Car> whoWinner;

    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed, CyclicBarrier cd, CountDownLatch cdl, List whoWinner, CountDownLatch cdlStart) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.cd = cd;
        this.cdlEnd = cdl;
        this.whoWinner = whoWinner;
        this.cdlStart = cdlStart;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            cdlStart.countDown();
            cd.await();

        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        whoWinner.add(this);
        cdlEnd.countDown();
        if(whoWinner.get(0).equals(this)) {
            System.out.println(whoWinner.get(0).getName() + " WIN");
        }
    }
}