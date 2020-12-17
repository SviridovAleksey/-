public class MainTask4 {
    static Object mon = new Object();
    static volatile int currNum = 1;
    static final int num = 10;

    public static void main(String[] args) {

        new Thread(()->{
            try {
                for (int i = 0; i < num; i++) {
                    synchronized (mon) {
                        while (currNum != 1) {
                            mon.wait();
                        }
                        System.out.print("A");
                        currNum = 2;
                        mon.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                for (int i = 0; i < num; i++) {
                    synchronized (mon) {
                        while (currNum != 2) {
                            mon.wait();
                        }
                        System.out.print("B");
                        currNum = 3;
                        mon.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                for (int i = 0; i < num; i++) {
                    synchronized (mon) {
                        while (currNum != 3) {
                            mon.wait();
                        }
                        System.out.print("C ");
                        currNum = 1;
                        mon.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
