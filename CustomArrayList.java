public class CustomArrayList<T extends Comparable<T>> implements Comparable<CustomArrayList<T>>{
	int size;
	Object[] array;
	public int index;

	CustomArrayList(){
		array = new Object[10];
		size = 10;
		index = 0;
	}

	public int compareTo(CustomArrayList<T> list2){
		return -5;
	}
	
	private void increaseSize(){
		Object[] temp = new Object[size*2];
		for(int i=0;i<size;i++){
			temp[i] = array[i];
		}
		array = temp;
		size = size*2;
	}

	public void add(T obj){
		if(index<size){
			array[index] = obj;
			index++;
		}
		else{
			this.increaseSize();
			array[index] = obj;
			index++;
		}
	}

	public T get(int i){
		if(i<0 || i>=size){
			return null;
		}
		else{
			// System.out.println("ola");
			return (T)array[i];
		}
	}

	public int getIndex(T obj){
		for (int k=0;k<index;k++) {
			if(((T)array[k]).compareTo(obj)==0){
				return k;
			}
		}
		return -1;
	}

	public void remove(T obj){
		int i = this.getIndex(obj);
		if(i==-1){
			return;
		}

		for(int k=i;k<index-1;k++){
			// System.out.println(index+" array[k]: "+array[k]+" array[k+1]: "+array[k+1]);
			array[k]=array[k+1];
		}
		if(index>0){
			index--;
		}
		if(index>=0){
			array[index] = null;
		}
	}

}