public class Point implements PointInterface, Comparable<Point>{
	float x, y, z;
	public CustomArrayList<Edge> incident_edges;
	public CustomArrayList<Triangle> incident_triangles;

	public Point(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
		incident_edges = new CustomArrayList();
		incident_triangles = new CustomArrayList();
	}

	public float getX(){
		return x;
	}
    public float getY(){
    	return y;
    }
    public float getZ(){
    	return z;
    }

    public float [] getXYZcoordinate(){
    	float[] array = {x,y,z};
    	return array;
    }

    public int compareTo(Point p){
    	if(this.x<p.getX()){
            return -1;
        }
        else if(this.x>p.getX()){
            return 1;
        }
        else{
            if(this.y<p.getY()){
                return -1;
            }
            else if(this.y>p.getY()){
                return 1;
            }
            else{
                if(this.z<p.getZ()){
                    return -1;
                }
                else if(this.z>p.getZ()){
                    return 1;
                }
                else{
                    return 0;
                }
            }
        }
    	
    }

    public String toString(){
    	String s = "("+x+", "+y+", "+z+")";
    	return s;
    }
}