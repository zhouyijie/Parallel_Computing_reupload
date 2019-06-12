package ca.mcgill.ecse420.a2;

import java.util.Random;


public class runner_deadlockFree {
	private Account acc1 = new Account();
	private Account acc2 = new Account();
	private Account acc3 = new Account();
	private Account acc4 = new Account();
	private Account acc5 = new Account();
	private Account acc6 = new Account();
	
	private filterLock Lock = new filterLock(6);
	
	
	
	
	public void firstThread() throws InterruptedException{
		
		Random random = new Random();
		for(int i =0;i<1000;i++){
			
			Lock.lock();
			Account.transfer(acc1, acc2, random.nextInt(100));
			Account.transfer(acc1, acc3, random.nextInt(100));
			Account.transfer(acc1, acc4, random.nextInt(100));
			Account.transfer(acc1, acc5, random.nextInt(100));
			Account.transfer(acc1, acc6, random.nextInt(100));
			
			Lock.unlock();
			
		}
	}
	
	public void secondThread() throws InterruptedException{
		
		Random random = new Random();
		for(int i =0;i<1000;i++){
			
			Lock.lock();
			Account.transfer(acc2, acc1, random.nextInt(100));
			Account.transfer(acc2, acc3, random.nextInt(100));
			Account.transfer(acc2, acc4, random.nextInt(100));
			Account.transfer(acc2, acc5, random.nextInt(100));
			Account.transfer(acc2, acc6, random.nextInt(100));
			
			Lock.unlock();
			
		}
		
	}
	public void thirdThread() throws InterruptedException{
		
		Random random = new Random();
		for(int i =0;i<1000;i++){
			
			Lock.lock();
			Account.transfer(acc3, acc1, random.nextInt(100));
			Account.transfer(acc3, acc2, random.nextInt(100));
			Account.transfer(acc3, acc4, random.nextInt(100));
			Account.transfer(acc3, acc5, random.nextInt(100));
			Account.transfer(acc3, acc6, random.nextInt(100));
			
			Lock.unlock();
			
		}
		
	}
	public void fourthThread() throws InterruptedException{
		
		Random random = new Random();
		for(int i =0;i<1000;i++){
			
			Lock.lock();
			Account.transfer(acc4, acc1, random.nextInt(100));
			Account.transfer(acc4, acc2, random.nextInt(100));
			Account.transfer(acc4, acc3, random.nextInt(100));
			Account.transfer(acc4, acc5, random.nextInt(100));
			Account.transfer(acc4, acc6, random.nextInt(100));
			
			Lock.unlock();
			
		}
		
	}
	public void fifthThread() throws InterruptedException{
		
		Random random = new Random();
		for(int i =0;i<1000;i++){
			
			Lock.lock();
			Account.transfer(acc5, acc1, random.nextInt(100));
			Account.transfer(acc5, acc2, random.nextInt(100));
			Account.transfer(acc5, acc3, random.nextInt(100));
			Account.transfer(acc5, acc4, random.nextInt(100));
			Account.transfer(acc5, acc6, random.nextInt(100));
			
			Lock.unlock();
			
		}
		
	}
	public void sixthThread() throws InterruptedException{
		
		Random random = new Random();
		for(int i =0;i<1000;i++){
			
			Lock.lock();
			Account.transfer(acc6, acc1, random.nextInt(100));
			Account.transfer(acc6, acc2, random.nextInt(100));
			Account.transfer(acc6, acc3, random.nextInt(100));
			Account.transfer(acc6, acc4, random.nextInt(100));
			Account.transfer(acc6, acc5, random.nextInt(100));
			
			Lock.unlock();
			
		}
		
	}
	
	public void finished(){
		
		System.out.println("Account 1 balance: "+acc1.getBalance());
		System.out.println("Account 2 balance: "+acc2.getBalance());
		System.out.println("Account 3 balance: "+acc3.getBalance());
		System.out.println("Account 4 balance: "+acc4.getBalance());
		System.out.println("Account 5 balance: "+acc5.getBalance());
		System.out.println("Account 6 balance: "+acc6.getBalance());
		
		System.out.println("Total balance: "+(acc1.getBalance()+acc2.getBalance()+acc3.getBalance()+acc4.getBalance()+acc5.getBalance()+acc6.getBalance()));
		
	}
}
