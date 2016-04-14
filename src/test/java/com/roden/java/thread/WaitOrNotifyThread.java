package com.roden.java.thread;
/**
 * wait() 必须在synchronized 函数或者代码块里面
 * wait() 会让已经获得synchronized 函数或者代码块控制权的Thread暂时休息，并且丧失控制权
 * 这个时候，由于该线程丧失控制权并且进入等待，其他线程就能取得控制权，并且在适当情况下调用notifyAll()来唤醒wait()的线程。
 * 需要注意的是，被唤醒的线程由于已经丧失了控制权，所以需要等待唤醒它的线程结束操作，从而才能重新获得控制权。
 * 所以特别注意的是，notify()并不是让当前线程马上让出控制权，而只是让其他wait()当中的线程唤醒而已。
 * @description : 线程队列与等待，实现供求(producer-consumer)关系
 * @author Mr.Jin
 *
 */
class WaitOrNotifyThread {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue q = new Queue();
		Producer p = new Producer(q);
		Consumer c = new Consumer(q);
		p.start();
		c.start();
	}
}
class Producer extends Thread{
	Queue q;
	
	Producer(Queue q){
		this.q = q;
	}
	
	public void run(){
		for(int i=1;i<=10;i++){
			q.setProduct("ProductName_"+i);
			System.out.println("SET:ProductName_"+i);
		}
	}
}
class Consumer extends Thread{
	Queue q;
	
	Consumer(Queue q){
		this.q = q;
	}
	
	public void run(){
		while(true){
			System.out.println("GET:"+q.getProduct());
		}
	}
}
class Queue{
	public String productName;
	public boolean hasProduct = false;
	
	public synchronized void setProduct(String string) {
		if(hasProduct){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		productName = string;
		hasProduct = true;
		notify();
	}
	public synchronized String getProduct() {
		if(!hasProduct){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		hasProduct = false;
		notify();//唤醒生产者，但生产者并不能立即获得Queue的控制权。必须等getProduct用完才能执行控制。
		return productName;
	}  
	
}
