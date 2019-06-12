package ca.mcgill.ecse420.a2;



public class filter_test {
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
		Thread t3 = new Thread(new Runnable(){
			public void run(){
				try{
					runner_deadlockFree.thirdThread();
				}catch (InterruptedException e){
					e.printStackTrace();
				}
			}
		});
		Thread t4 = new Thread(new Runnable(){
			public void run(){
				try{
					runner_deadlockFree.fourthThread();
				}catch (InterruptedException e){
					e.printStackTrace();
				}
			}
		});
		Thread t5 = new Thread(new Runnable(){
			public void run(){
				try{
					runner_deadlockFree.fifthThread();
				}catch (InterruptedException e){
					e.printStackTrace();
				}
			}
		});
		Thread t6 = new Thread(new Runnable(){
			public void run(){
				try{
					runner_deadlockFree.sixthThread();
				}catch (InterruptedException e){
					e.printStackTrace();
				}
			}
		});
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		t5.join();
		t6.join();
		
		runner_deadlockFree.finished();
	}
}
