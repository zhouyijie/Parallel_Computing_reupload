package ca.mcgill.ecse420.a1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Question3_fairlock {
	public static void main(String[] args) throws Exception{
		 Philosopher_fairlock[] philosophers = new Philosopher_fairlock[5];
	     ReentrantLock[] forks = new ReentrantLock[philosophers.length];
	     
	     for (int j = 0; j < forks.length; j++) {
	         forks[j] = new ReentrantLock(true);
	     }
	
	     for (int i = 0; i < philosophers.length; i++) {
	         ReentrantLock leftFork = forks[i];
	         ReentrantLock rightFork = forks[(i + 1) % forks.length];
	         if(i == philosophers.length-1){
	        	 philosophers[i] = new Philosopher_fairlock(rightFork, leftFork);
	         }
	         else{
	        	 philosophers[i] = new Philosopher_fairlock(leftFork, rightFork);
	         }
	         Thread t = new Thread(philosophers[i], "Philosopher " + (i + 1));
	         t.start();
	     }
	}
}
