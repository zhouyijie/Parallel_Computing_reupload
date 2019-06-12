package ca.mcgill.ecse420.a2;

public class bakery_test {
	public static void main(String[] args) throws Exception{
		final runner_bakery runner_bakery = new runner_bakery();
		
		Thread t1 = new Thread(new Runnable(){
			public void run(){
				try{
					runner_bakery.firstThread();
				}catch (InterruptedException e){
					e.printStackTrace();
				}
			}
		});
		Thread t2 = new Thread(new Runnable(){
			public void run(){
				try{
					runner_bakery.secondThread();
				}catch (InterruptedException e){
					e.printStackTrace();
				}
			}
		});
		Thread t3 = new Thread(new Runnable(){
			public void run(){
				try{
					runner_bakery.thirdThread();
				}catch (InterruptedException e){
					e.printStackTrace();
				}
			}
		});
		Thread t4 = new Thread(new Runnable(){
			public void run(){
				try{
					runner_bakery.fourthThread();
				}catch (InterruptedException e){
					e.printStackTrace();
				}
			}
		});
		Thread t5 = new Thread(new Runnable(){
			public void run(){
				try{
					runner_bakery.fifthThread();
				}catch (InterruptedException e){
					e.printStackTrace();
				}
			}
		});
		Thread t6 = new Thread(new Runnable(){
			public void run(){
				try{
					runner_bakery.sixthThread();
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
		
		runner_bakery.finished();
		
	}
}
