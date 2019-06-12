package ca.mcgill.ecse420.a1;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Philosopher_deadlockFree implements Runnable {
	
	private Object leftFork;
	private Object rightFork;
	
	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();
	
	private void doAction(String action) throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + " " + action);
		Thread.sleep(((int) (Math.random() * 100)));
	}
	


	public Philosopher_deadlockFree(Object leftFork, Object rightFork) throws InterruptedException{
		this.leftFork = leftFork;
		this.rightFork = rightFork;
		
		
		
	}
	


	private void put_down_right() throws InterruptedException {
		// TODO Auto-generated method stub
		doAction(System.nanoTime() + ": put down right fork");
	}



	private void put_down_left() throws InterruptedException {
		// TODO Auto-generated method stub
		doAction(System.nanoTime() + ": put down left fork, back to thinking");
	}






	private void pick_up_right() throws InterruptedException {
		// TODO Auto-generated method stub
		doAction(System.nanoTime() + ": pick up right fork - eating");
	}



	private void pick_up_left() throws InterruptedException {
		// TODO Auto-generated method stub
		doAction(System.nanoTime() + ": pick up left fork");
	}



	private void think() throws InterruptedException {
		// TODO Auto-generated method stub
		doAction(System.nanoTime() + ": Thinking");
	}



	@Override
	public void run() {
		try{
			while(true){
				
				think();
				synchronized(leftFork){
					pick_up_left();
					synchronized(rightFork){
						pick_up_right();
						put_down_right();
					}
					
					put_down_left();
					
				}
			}
		}catch(InterruptedException e){
			Thread.currentThread().interrupt();
			return;
		}
	}
}
