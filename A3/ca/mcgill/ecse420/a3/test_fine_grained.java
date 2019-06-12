
public class test_fine_grained {
	public static void main(String[] args){
		fine_grainedList<Integer> list = new fine_grainedList<>();
		boolean add, remove, contains;
		
		for(int i=0;i<100;i++){
			add = list.add(i);
			System.out.println("add: "+i+" is "+add);
		}
		
		for(int i=0;i<100;i=i+2){
			remove = list.remove(i);
			System.out.println("remove: "+i+" is "+remove);
		}
		for(int i = 7;i<100;i=i+7){
			contains = list.contains(i);
			System.out.println("contains: "+i+" is "+contains);
		}
		
		/*
		add = list.add(15);
		System.out.println("add: "+15+" is "+add);
		contains = list.contains(15);
		System.out.println("contains: "+15+" is "+contains);
		remove = list.remove(15);
		System.out.println("remove: "+15+" is "+remove);
		contains = list.contains(15);
		System.out.println("contains: "+15+" is "+contains);
		*/
		
	}
}
