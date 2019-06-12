package ca.mcgill.ecse420.a1;
//with deadlock
public class Question3 {
	public static void main(String[] args) throws Exception{
		 Philosopher[] philosophers = new Philosopher[5];
	     Object[] forks = new Object[philosophers.length];
	     
	     for (int j = 0; j < forks.length; j++) {
	         forks[j] = new Object();
	     }
	
	     for (int i = 0; i < philosophers.length; i++) {
	         Object leftFork = forks[i];
	         Object rightFork = forks[(i + 1) % forks.length];
	         
	         philosophers[i] = new Philosopher(leftFork, rightFork);
	          
	         Thread t = new Thread(philosophers[i], "Philosopher " + (i + 1));
	         t.start();
	     }
	}
}


