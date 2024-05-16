package threads;

public class RaceConditionSynchronized {
    public int counter = 0;

    private synchronized void increment(){
        this.counter++;
    }
    public static void main(String[] args) throws InterruptedException {

        RaceConditionSynchronized example1 = new RaceConditionSynchronized();
        RaceConditionSynchronized example2 = new RaceConditionSynchronized();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 200000; i++){
                    example1.increment();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 200000; i++){
                    example1.increment();
                    example2.increment();
                }
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println("Counter 1:" + example1.counter);
        System.out.println("Counter 2:" + example2.counter);

    }
}
