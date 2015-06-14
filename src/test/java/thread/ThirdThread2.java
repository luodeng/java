package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThirdThread2 implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        return "success";
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ThirdThread2 th = new ThirdThread2();
        FutureTask<String> ft = new FutureTask<>(th);
        FutureTask<String> ft2 = new FutureTask<>(th);
        new Thread(ft, "start").start();
        new Thread(ft2, "wocao").start();
        System.out.println(ft.get());
    }
}
