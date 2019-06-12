package bounded_lock_based_queue;

import java.lang.reflect.Array;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class boundedQ<T> {
    private T[] normal_array;

    private int head = 0;
    private int tail = 0;

    private Lock lock_Head = new ReentrantLock();
    private Lock lock_Tail = new ReentrantLock();

    private Condition notEmptyCondition = lock_Head.newCondition();
    private Condition notFullCondition  = lock_Tail.newCondition();

    
    @SuppressWarnings("unchecked")
	public boundedQ(int length) {
        normal_array = (T[]) new Object[length];
        
    }

    public void enq(T item) {
        lock_Tail.lock();
        lock_Head.lock();
        try {
            while (tail - head == normal_array.length) {
                try { notFullCondition.await(); } catch (InterruptedException ie) {}
            }
            normal_array[tail % normal_array.length] = item;

            tail++;

            if (tail - head == 1){
            	
                notEmptyCondition.signal();
            }
        } finally {
            lock_Tail.unlock();
            lock_Head.unlock();
        }
    }

    public T deq() {
        lock_Head.lock();
        lock_Tail.lock();
        try {
            while (tail - head == 0) {
                try { notEmptyCondition.await(); } catch (InterruptedException ie) {}
            }
            T item = normal_array[head % normal_array.length];
            head++;
            if (tail - head == normal_array.length - 1){
                notFullCondition.signal();
            }
            return item;
        } finally {
            lock_Head.unlock();
            lock_Tail.unlock();
        }

    }

}