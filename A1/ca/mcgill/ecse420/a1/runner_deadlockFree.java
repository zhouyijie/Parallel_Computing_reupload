package ca.mcgill.ecse420.a1;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class runner_deadlockFree {
	private Account acc1 = new Account();
	private Account acc2 = new Account();
	
	private Lock Lock1 = new ReentrantLock();
	private Lock Lock2 = new ReentrantLock();
	
	private void acquireLocks (Lock firstLock, Lock secondLock) throws InterruptedException{
		while(true){
			boolean gotFirstLock = false;
			boolean gotSecondLock = false;
			try{
				gotFirstLock = firstLock.tryLock();
				gotSecondLock = secondLock.tryLock();
				
			}finally{
				if(gotFirstLock && gotSecondLock){
					return;
				}
				if(gotFirstLock){
					firstLock.unlock();
				}
				if(gotSecondLock){
					secondLock.unlock();
				}
				Thread.sleep(1);
			}
		}
	}
	
	public void firstThread() throws InterruptedException{
		
		Random random = new Random();
		for(int i =0;i<1000;i++){
			
			acquireLocks(Lock1,Lock2);
			//Lock1.lock();
			//Lock2.lock();
			try{
				Account.transfer(acc1, acc2, random.nextInt(100));
			}
			finally{
				Lock1.unlock();
				Lock2.unlock();
			}
		}
	}
	
	public void secondThread() throws InterruptedException{
		
		Random random = new Random();
		for(int i =0;i<1000;i++){
			
			acquireLocks(Lock2,Lock1);
			//Lock2.lock();
			//Lock1.lock();
			try{
				Account.transfer(acc2, acc1, random.nextInt(100));
			}
			finally{
				Lock1.unlock();
				Lock2.unlock();
			}
			
		}
		
	}
	
	public void finished(){
		
		System.out.println("Account 1 balance: "+acc1.getBalance());
		System.out.println("Account 2 balance: "+acc2.getBalance());
		System.out.println("Total balance: "+(acc1.getBalance()+acc2.getBalance()));
		
	}
}
