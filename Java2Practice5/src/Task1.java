class Task1 implements Runnable {

    private float[] arr;

    protected Task1 (float[] arr) {
        this.arr = arr;
    }

    @Override
    public void run() {
        long b = System.currentTimeMillis();
        for (int i = 0; i< arr.length; i++) {
            arr[i] = 1;
        }
        for (int i = 0; i< arr.length; i++) {
            arr[i] = (float) (arr[i]*Math.sin(0.2f+i/5)* Math.cos(0.2f+i/5)* Math.cos(0.4f+i/2));
        }
        long a = System.currentTimeMillis();
        System.out.println(a-b + "task1" );
    }
}