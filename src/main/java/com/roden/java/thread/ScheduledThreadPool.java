package com.roden.java.thread;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
 
public class ScheduledThreadPool {
 
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
 
        //schedule to run after sometime
        System.out.println("Current Time = "+new Date());
        for(int i=0; i<3; i++){
            Thread.sleep(1000);
            WorkerThread worker = new WorkerThread(i+"");
            scheduledThreadPool.schedule(worker, 10, TimeUnit.SECONDS);
        }
 System.out.println("dkdkkd");
        //add some delay to let some threads spawn by scheduler
        Thread.sleep(30000);
 
        scheduledThreadPool.shutdown();
        while(!scheduledThreadPool.isTerminated()){
            //wait for all tasks to finish
        }
        System.out.println("Finished all threads");
    }
 
}
class WorkerThread implements Runnable{
	 
	private String orderId;
 
    public WorkerThread(String orderId){
        this.orderId=orderId;
    }
 
    @Override
    public void run() {
        //System.out.println(Thread.currentThread().getName()+" Start. Time = "+new Date());
        processCommand();
        //System.out.println(Thread.currentThread().getName()+" End. Time = "+new Date());
    }
 
    private void processCommand() {
        try {
        	System.out.println(orderId);
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
 
    @Override
    public String toString(){
        return this.orderId;
    }
}