
public class fine_grainedList<T> {
	
	private Node<T> head;  
	public fine_grainedList() { 
		head = new Node<T>(Integer.MIN_VALUE); 
		head.next = new Node<T>(Integer.MAX_VALUE);
	}
	
	
	public boolean add(T item) { 
		int key = item.hashCode(); 
		head.lock(); 
		Node<T> pred = head; 
		try { 
			Node<T> curr = pred.next; 
			curr.lock(); 
			try { 
				while (curr.key < key) { 
					pred.unlock(); 
					pred = curr; 
					curr = curr.next; 
					curr.lock(); 
				} 
				if (curr.key == key) { 
					return false; 
				} 
				Node<T> newNode = new Node<T>(item); 
				newNode.next = curr; 
				pred.next = newNode; 
				return true; 
			} finally { 
				curr.unlock(); 
			} 
		} finally { 
			pred.unlock(); 
		} 
	} 
	public boolean remove(T item) { 
		Node<T> pred = null, curr = null; 
		int key = item.hashCode(); 
		head.lock(); 
		try { 
			pred = head; 
			curr = pred.next; 
			curr.lock(); 
			try { 
				while (curr.key < key) { 
					pred.unlock(); 
					pred = curr; 
					curr = curr.next; 
					curr.lock(); 
				} 
				if (curr.key == key) { 
					pred.next = curr.next; 
					return true; 
				} 
				return false; 
			} finally { 
				curr.unlock(); 
			} 
		} finally { 
			pred.unlock(); 
		} 
	}
	
	public boolean contains(T item){
		Node<T> last = null, pred = null, curr = null;
		int key = item.hashCode();
		head.lock();
		try{
			pred = head;
			curr = pred.next;
			curr.lock();
			try{
				//System.out.println("key compare in contains: curr.key "+curr.key+" key "+key);
				while(curr.key < key){
					pred.unlock();
					pred = curr;
					curr = curr.next;
					curr.lock();
				}
				return (curr.key == key);
			}finally {
				curr.unlock();
			}
		}finally{
			pred.unlock();
		}
		
		
		//return false;
		
	}
}
