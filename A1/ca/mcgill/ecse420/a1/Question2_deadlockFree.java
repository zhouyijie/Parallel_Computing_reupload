package ca.mcgill.ecse420.a1;

public class Question2_deadlockFree {
	public static void main(String[] args) throws Exception{
		final runner_deadlockFree runner_deadlockFree = new runner_deadlockFree();
		
		Thread t1 = new Thread(new Runnable(){
			public void run(){
				try{
					runner_deadlockFree.firstThread();
				}catch (InterruptedException e){
					e.printStackTrace();
				}
			}
		});
		Thread t2 = new Thread(new Runnable(){
			public void run(){
				try{
					runner_deadlockFree.secondThread();
				}catch (InterruptedException e){
					e.printStackTrace();
				}
			}
		});
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		runner_deadlockFree.finished();
	}
}
