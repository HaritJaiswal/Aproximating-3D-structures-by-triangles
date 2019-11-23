public class TriangleNode implements Comparable<TriangleNode>{
	Triangle triangle;
	CustomArrayList adj_list;
	int visited;
	int distance;

	TriangleNode(Triangle t){
		this.triangle = t;
		adj_list = new CustomArrayList();
		visited = 0;
		distance = Integer.MAX_VALUE;
	}

	public int compareTo(TriangleNode tn){
		if(this.triangle.compareTo(tn.triangle)==0){
			return 0;
		}
		return -1;
	}

	public String toString(){
		return triangle.toString(); 
	}
}