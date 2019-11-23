public class Triangle implements TriangleInterface, Comparable<Triangle>{
	public Point p1, p2, p3;
	public Edge e1, e2, e3;
	public Point centroid;
	public int time_stamp;

	Triangle(Point p1, Point p2, Point p3, Edge e1, Edge e2, Edge e3){
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;

		this.e1 = e1;
		this.e2 = e2;
		this.e3 = e3;

		//centroid = new Point((p1.getX()+p2.getX()+p3.getX())/3, (p1.getY()+p2.getY()+p3.getY())/3, (p1.getZ()+p2.getZ()+p3.getZ())/3);
		//System.out.println("In constructor of triangle");
		time_stamp = 0;

	}

	public PointInterface [] triangle_coord(){
		Point[] vertices_array = {p1, p2, p3};
		return vertices_array;
	}

	public int compareTo(Triangle obj){
		boolean[] arr = {false,false,false};
		if(this.p1.compareTo(obj.p1)==0 || this.p1.compareTo(obj.p2)==0 || this.p1.compareTo(obj.p3)==0 ){
			arr[0]=true;
		}
		if(this.p2.compareTo(obj.p1)==0 || this.p2.compareTo(obj.p2)==0 || this.p2.compareTo(obj.p3)==0 ){
			arr[1]=true;
		}
		if(this.p3.compareTo(obj.p1)==0 || this.p3.compareTo(obj.p2)==0 || this.p3.compareTo(obj.p3)==0 ){
			arr[2]=true;
		}

		if(arr[0]==true && arr[1]==true && arr[2]==true){
			return 0;
		}
		else{
			return -1;
		}

	}

	public String toString(){
		String s = "["+p1.toString()+", "+p2.toString()+", "+p3.toString()+"]";
		return s;
	}
}