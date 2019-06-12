package ca.mcgill.ecse420.a1;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Philosopher_fairlock implements Runnable {
	
	private ReentrantLock leftFork;
	private ReentrantLock rightFork;
	
	
	private void doAction(String action) throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + " " + action);
		Thread.sleep(((int) (Math.random() * 100)));
	}
	


	public Philosopher_fairlock(ReentrantLock leftFork, ReentrantLock rightFork) throws InterruptedException{
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
				leftFork.lock();
				try{
					pick_up_left();
					rightFork.lock();
					try{
						pick_up_right();
						put_down_right();
					}finally{
						rightFork.unlock();
					}
					
					put_down_left();
					
				}finally{
					leftFork.unlock();
				}
			}
		}catch(InterruptedException e){
			Thread.currentThread().interrupt();
			return;
		}
	}
}
