public class TriangleEdge implements Comparable<TriangleEdge>{
	public Triangle t1, t2;

	TriangleEdge(Triangle t1, Triangle t2){
		this.t1 = t1;
		this.t2 = t2;
	}

	public TriangleInterface [] edgeEndPoints(){
		Triangle[] triangle_array = {t1,t2};
		return triangle_array;
	}

	public int compareTo(TriangleEdge te){
		return 0;
	}
}