package ca.mcgill.ecse420.a2;


import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class filterLock implements Lock {
	private AtomicInteger[] level;
	private AtomicInteger[] victim;
	private int n;
	public filterLock(int n){
		this.n = n;
		level = new AtomicInteger[n];
		victim = new AtomicInteger[n];
		for(int i = 0; i<n;i++){
			level[i] = new AtomicInteger();
			victim[i] = new AtomicInteger();
		}
	}
	
	@Override
	public void lock() {
		// TODO Auto-generated method stub
		int me = ThreadID.get();
		for (int i = 1;i<n;i++){
			level[me].set(i);
			victim[i].set(me);
			for (int k = 0; k < n; k++) {
			      while ((k != me) && (level[k].get() >= i && victim[i].get() == me)) {
			           //spin wait
			      }
			}
		}
		
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean tryLock() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tryLock(long arg0, TimeUnit arg1) throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void unlock() {
		// TODO Auto-generated method stub
		int me = ThreadID.get();
		level[me].set(0);
	}
	
}
