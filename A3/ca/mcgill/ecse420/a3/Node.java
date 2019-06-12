import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Node<NodeType> {
	private Lock lock = new ReentrantLock();
	NodeType item;
	int key;
	Node<NodeType> next;
	public Node(NodeType item) {
		// TODO Auto-generated constructor stub
		this.item = item;
		this.key = item.hashCode();
		//System.out.println("add item "+item);
		
	}
	public Node(int key) {
		// TODO Auto-generated constructor stub
		this.key = key;
		//System.out.println("add key "+key);
	}
	public void lock() {
		// TODO Auto-generated method stub
		
		lock.lock();
		
		//System.out.println("lock item "+this.item+" enter critical area");
		
	}
	public void unlock() {
		// TODO Auto-generated method stub
		//System.out.println("unlock item "+this.item+" about to exits critical area");
		lock.unlock();
		//System.out.println("exited critical area");
	}

}