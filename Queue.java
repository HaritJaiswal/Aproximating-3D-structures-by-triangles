public class Queue<T extends Comparable<T>>{
	CustomArrayList<T> list;
	int size;

	Queue(){
		list = new CustomArrayList();
		size = 0;
	}

	public boolean isEmpty(){
		if(list==null || list.index==0 || size==0){
			return true;
		}
		else{
			return false;
		}
	} 

	public void insert(T obj){
		list.add(obj);
		size++;
	}

	public T peek(){
		if(list==null || list.index==0 || size==0){
			return null;
		}
		return (T)list.get(0);
	}

	public T remove(){
		if(list==null || list.index==0 || size==0){
			return null;
		}
		// System.out.println("harit");
		T obj = list.get(0);
		// System.out.println("harit1");
		list.remove(obj);
		// System.out.println("harit2");
		if(size>0){
			size--;
		}
		return (T)obj;
	}

}