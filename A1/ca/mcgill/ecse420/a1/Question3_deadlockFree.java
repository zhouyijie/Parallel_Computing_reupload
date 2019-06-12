package ca.mcgill.ecse420.a1;

public class Question3_deadlockFree {
	public static void main(String[] args) throws Exception{
		 Philosopher_deadlockFree[] philosophers = new Philosopher_deadlockFree[5];
	     Object[] forks = new Object[philosophers.length];
	     
	     for (int j = 0; j < forks.length; j++) {
	         forks[j] = new Object();
	     }
	
	     for (int i = 0; i < philosophers.length; i++) {
	         Object leftFork = forks[i];
	         Object rightFork = forks[(i + 1) % forks.length];
	         if( i == philosophers.length-1){
	        	 philosophers[i] = new Philosopher_deadlockFree(rightFork, leftFork);
	         }else{
	        	 philosophers[i] = new Philosopher_deadlockFree(leftFork, rightFork);
	         }
	         Thread t = new Thread(philosophers[i], "Philosopher " + (i + 1));
	         t.start();
	     }
	}
}
