public class Edge implements EdgeInterface, Comparable<Edge>{
	public Point p1, p2;
	public CustomArrayList<Triangle> face_neighbours;
	public boolean boundary_edge;
	public float length;

	Edge(Point p1, Point p2){
		this.p1 = p1;
		this.p2 = p2;
		face_neighbours = new CustomArrayList();
		boundary_edge = false;
		this.length = Shape.distance(p1,p2); 
	}

	public PointInterface [] edgeEndPoints(){
		Point[] point_array = {p1,p2};
		return point_array;
	}

	public int compareTo(Edge e){
		if(this.p1.compareTo(e.p1)==0 || this.p1.compareTo(e.p2)==0){
			if(this.p2.compareTo(e.p1)==0 || this.p2.compareTo(e.p2)==0){
				return 0;
			}
		}
		return -1;
	}

	public String toString(){
		String s = "["+p1.toString()+", "+p2.toString()+"]";
		return s;
	}
}