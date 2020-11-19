import java.util.Arrays;

public class Main {

    static final int size = 10000000;
    static final int half = size/2;
    static float[]  arr = new float[size];
    static private float[] arr1 = new float[half];
    static private float[] arr2= new float[half];


    public static void main(String[] args) {
        new Thread(() -> new Task1(arr).run()).start();
        try {
            task2();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void task2 () throws InterruptedException {
        long b = System.currentTimeMillis();
        System.arraycopy(arr,0,arr1,0,half);
        System.arraycopy(arr,half,arr2,0,half);
        Thread thread1 = new Thread(new task2Performance (arr1));
        Thread thread2 = new Thread(new task2Performance (arr2));
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.arraycopy(arr1,0,arr,0,half);
        System.arraycopy(arr2,0,arr,half,half);
        long a = System.currentTimeMillis();
        System.out.println(a-b + "task2");
    }


    static class task2Performance implements Runnable {

        private float[] arr;

        protected task2Performance  (float[] arr) {
            this.arr = arr;

        }

        @Override
        public void run() {
            Arrays.fill(arr, 1);
            for (int i = 0; i< arr.length; i++) {
                arr[i] = 1;
            }
            for (int i = 0; i< arr.length; i++) {
                arr[i] = (float) (arr[i]*Math.sin(0.2f+i/5)* Math.cos(0.2f+i/5)* Math.cos(0.4f+i/2));
            }
        }
    }


}