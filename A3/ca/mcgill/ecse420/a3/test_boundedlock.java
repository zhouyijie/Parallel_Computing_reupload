package bounded_lock_based_queue;

public class test_boundedlock {
	public static void main(String[] args){
		boundedQ<Integer> queue = new boundedQ<>(10);
		int a;
		for(int i=0;i<10;i++){
			queue.enq(i);
		}
		for(int i=0;i<10;i++){
			a = queue.deq();
			System.out.println("dep: "+a);
		}
		
		
	}
}
