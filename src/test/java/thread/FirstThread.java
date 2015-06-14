package thread;

public class FirstThread extends Thread {
    private int i;

    public void run() {
        // 此处i为实例属性
        for (; i < 100; i++) {
            System.out.println(getName() + " " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 20) {
                new FirstThread().start();
                new FirstThread().start();
            }
        }
    }
}
