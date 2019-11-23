public class SC_HashTable<T extends Comparable<T>>{
   int hashtableSize;
   int elementsNumber;
   CustomArrayList<T>[] arr;

   SC_HashTable(){
      this.hashtableSize = 8011;
      this.arr = new CustomArrayList[hashtableSize];
      this.elementsNumber = 0;
   }
    

private static long djb2(String str, int hashtableSize) { 
    long hash = 5381; 
    for (int i = 0; i < str.length(); i++) { 
        hash = ((hash << 5) + hash) + str.charAt(i); 
    } 
    return Math.abs(hash) % hashtableSize; 
}

public void insert(T obj){
   int index = (int)this.djb2(obj.toString(),hashtableSize);
   //System.out.println("In hash_table insert - index "+index +" "+ key);
   if(index<hashtableSize && arr[index]==null){
      CustomArrayList<T> list = new CustomArrayList();
      list.add(obj);
      arr[index]=list;
      elementsNumber++;
   }

   else{
      if(!this.contains(obj)){
         arr[index].add(obj);
         elementsNumber++;
      }
      else{
         return;
      }
   }
}

// public int update(String key, Object obj){
//    int index = (int)this.djb2(key.toString(),hashtableSize);
//    int update_count = 0;

//    if(arr[index].contains(key)){
//      Node node = arr[index].root;

//      while(node!=null){
//       if(key.compareTo(node.value)<0){
//             node = node.left;
//             update_count++;
//          }
//          else if(key.compareTo(node.value)>0){
//             node = node.right;
//             update_count++;
//          }
//          else{
//             if(key.toString().compareTo(node.key.toString())==0){
//                node.value = obj;
//                return ++update_count;
//             }
//             else{
//                node = node.right;
//                update_count++;
//             }
//          }
//      }
//    }
//    return -1;

// }

// public int delete(String key){
//    int index = (int)this.djb2(key.toString(),hashtableSize);
//    if(arr[index].contains(key)){
//       int x = arr[index].delete(key);
//       return x;
//    }
//    return -1;
// }

public boolean contains(T obj){
   int index = (int)this.djb2(obj.toString(),hashtableSize);
      if(index<hashtableSize && arr[index]!=null && arr[index].getIndex(obj)!=-1){
         return true;
      }
      else{
         return false;
      }
}

public T get(T obj){
   int index = (int)this.djb2(obj.toString(),hashtableSize);

   if(this.contains(obj)){
      int x = arr[index].getIndex(obj);
      return arr[index].get(x);
   }

   return null;
}

}
