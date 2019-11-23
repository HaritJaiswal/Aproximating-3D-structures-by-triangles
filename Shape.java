public class Shape implements ShapeInterface
{	int timer = 0;
	int mesh_type = 1;
	int hashtableSize = 8011;
	CustomArrayList<Point> vertex_list = new CustomArrayList();
	CustomArrayList<Edge> edge_list = new CustomArrayList();
	CustomArrayList<Edge> boundary_edges = new CustomArrayList();
	SC_HashTable<Point> vertex_table = new SC_HashTable();
	SC_HashTable<Edge> edge_table = new SC_HashTable();
	CustomArrayList<Point> faltu = new CustomArrayList();

	CustomArrayList<TriangleNode> graph_vertex_list = new CustomArrayList();// TRIANGLES AS NODES
	CustomArrayList<TriangleEdge> graph_edge_list = new CustomArrayList();// COMMON EDGES BETWEEN TRIANGLES => TRIANGLE EDGE
	SC_HashTable<TriangleNode> graph_vertex_table = new SC_HashTable();

	// int point_hash(Point p){
	// 	float h = (p.x * 1823) + (p.y * 2393) + (p.z * 4111);
	// 	h=Math.abs(h);
	// 	int x = (int)Math.round(h);
	// 	return x%hashtableSize;
	// }

	// int edge_hash(Edge e){
	// 	float h = (5273*this.point_hash(e.p1))+(3547*this.point_hash(e.p2));
	// 	h=h/2;
	// 	h=h*3;
	// 	h=Math.abs(h);
	// 	int x = (int)Math.round(h);
	// 	return x%hashtableSize;
	// }

	// int triangle_node_hash(TriangleNode node){
	// 	Triangle t = node.triangle;
	// 	float h = (4013*this.point_hash(t.p1))+(1327*this.point_hash(t.p2))+(3011*this.point_hash(t.p2));
	// 	h=h/2;
	// 	h=h*13;
	// 	h=Math.abs(h);
	// 	int x = (int)Math.round(h);
	// 	return x%hashtableSize;
	// }

	public void MergeSort(Edge[] a, int n) {
	    if (n<2 || a==null) {
	        return;
	    }
	    int mid = n/2;
	    Edge[] l = new Edge[mid];
	    Edge[] r = new Edge[n-mid];
	 
	    for (int i=0;i<mid;i++){
	        l[i] = a[i];
	    }
	    for (int i=mid;i<n;i++){
	        r[i - mid] = a[i];
	    }
	    MergeSort(l, mid);
	    MergeSort(r, n - mid);
	 
	    merge(a, l, r, mid, n - mid);
	}

	private void merge(Edge[] a, Edge[] l, Edge[] r, int left_index, int right_index){
	    int i = 0, j = 0, k = 0;
	    while (i < left_index && j < right_index) {
	        if (l[i].length<=r[j].length) {
	            a[k++] = l[i++];
	        }
	        else {
	            a[k++] = r[j++];
	        }
	    }

	    while (i < left_index) {
	        a[k++] = l[i++];
	    }
	    while (j < right_index) {
	        a[k++] = r[j++];
	    }
	}

	public void MergeSort2(Triangle[] a, int n) {
	    if (n<2 || a==null) {
	        return;
	    }
	    int mid = n/2;
	    Triangle[] l = new Triangle[mid];
	    Triangle[] r = new Triangle[n-mid];
	 
	    for (int i=0;i<mid;i++){
	        l[i] = a[i];
	    }
	    for (int i=mid;i<n;i++){
	        r[i - mid] = a[i];
	    }
	    MergeSort2(l, mid);
	    MergeSort2(r, n - mid);
	 
	    merge2(a, l, r, mid, n - mid);
	}

	private void merge2(Triangle[] a, Triangle[] l, Triangle[] r, int left_index, int right_index){
	    int i = 0, j = 0, k = 0;
	    while (i < left_index && j < right_index) {
	        if (l[i].time_stamp<=r[j].time_stamp) {
	            a[k++] = l[i++];
	        }
	        else {
	            a[k++] = r[j++];
	        }
	    }

	    while (i < left_index) {
	        a[k++] = l[i++];
	    }
	    while (j < right_index) {
	        a[k++] = r[j++];
	    }
	}


	public void MergeSort3(Point[] a, int n) {
	    if (n<2 || a==null) {
	        return;
	    }
	    int mid = n/2;
	    Point[] l = new Point[mid];
	    Point[] r = new Point[n-mid];
	 
	    for (int i=0;i<mid;i++){
	        l[i] = a[i];
	    }
	    for (int i=mid;i<n;i++){
	        r[i - mid] = a[i];
	    }
	    MergeSort3(l, mid);
	    MergeSort3(r, n - mid);
	 
	    merge3(a, l, r, mid, n - mid);
	}

	private void merge3(Point[] a, Point[] l, Point[] r, int left_index, int right_index){
	    int i = 0, j = 0, k = 0;
	    while (i < left_index && j < right_index) {
	        if (l[i].compareTo(r[j])<=0) {
	            a[k++] = l[i++];
	        }
	        else {
	            a[k++] = r[j++];
	        }
	    }

	    while (i < left_index) {
	        a[k++] = l[i++];
	    }
	    while (j < right_index) {
	        a[k++] = r[j++];
	    }
	}

	int bfs1(CustomArrayList<TriangleNode> graph_vertex_list, TriangleNode u){
		int max=0;
		Queue<TriangleNode> q = new Queue();
		q.insert(u);
		u.visited = 1;
		max++;

		// for(int x=0;x<u.adj_list.index;x++){
		// 	TriangleNode nn = (TriangleNode)u.adj_list.get(x);
		// 	System.out.println("PPPPPPP "+nn+" nn.visited: "+nn.visited);
		// }

		while(!q.isEmpty() && q!=null){
			// System.out.println("Entered for of bfs ");
			TriangleNode v = q.remove();
			// System.out.println("Entered for of bfs 0");
			// System.out.println("VVVVV: "+((TriangleNode)v).visited);
			if(v!=null){
				// System.out.println("Entered for of bfs1 ");
				for(int i=0;i<v.adj_list.index;i++){
					TriangleNode tn = (TriangleNode)v.adj_list.get(i);
					// System.out.println("Entered for of bfs2");
					// System.out.println("((TriangleNode)v).adj_list.index: "+((TriangleNode)v).adj_list.index);
					// System.out.println("(TriangleNode)v.adj_list.get(i)).visited: "+((TriangleNode)v.adj_list.get(i)).visited);
					if(tn.visited==0){
						// System.out.println("Hi: "+(TriangleNode)v.adj_list.get(i));
						tn.visited = 1;
						max++;
						q.insert(tn);	
					}
				}
			}
		}
		return max;
	}

	boolean bfs2(CustomArrayList<TriangleNode> graph_vertex_list, TriangleNode u, TriangleNode x){
		Queue<TriangleNode> q = new Queue();
		q.insert(u);
		u.visited = 1;
		// for(int x=0;x<u.adj_list.index;x++){
		// 	TriangleNode nn = (TriangleNode)u.adj_list.get(x);
		// 	System.out.println("PPPPPPP "+nn+" nn.visited: "+nn.visited);
		// }

		while(!q.isEmpty() && q!=null){
			// System.out.println("Entered for of bfs ");
			TriangleNode v = q.remove();
			// System.out.println("Entered for of bfs 0");
			// System.out.println("VVVVV: "+((TriangleNode)v).visited);
			if(v!=null){
				// System.out.println("Entered for of bfs1 ");
				for(int i=0;i<v.adj_list.index;i++){
					TriangleNode tn = ((TriangleNode)v.adj_list.get(i)); 
					if(tn.compareTo(x)==0){
						return true;
					}
					// System.out.println("Entered for of bfs2");
					// System.out.println("((TriangleNode)v).adj_list.index: "+((TriangleNode)v).adj_list.index);
					// System.out.println("(TriangleNode)v.adj_list.get(i)).visited: "+((TriangleNode)v.adj_list.get(i)).visited);
					if(tn.visited==0){
						// System.out.println("Hi: "+(TriangleNode)v.adj_list.get(i));
						tn.visited = 1;
						q.insert(tn);	
					}
				}
			}
		}

		return false;
	}

	//INPUT [x1,y1,z1,x2,y2,z2,x3,y3,z3]
	boolean check_valid_triangle(float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3){
		float a1 = x2-x1;
		float b1 = y2-y1;
		float c1 = z2-z1;

		float a2 = x3-x1;
		float b2 = y3-y1;
		float c2 = z3-z1;

		float product = a1*a2+b1*b2+c1*c2;
		product = Math.abs(product);
		float e1 = (float)Math.sqrt(a1*a1+b1*b1+c1*c1); 
    	float e2 = (float)Math.sqrt(a2*a2+b2*b2+c2*c2);

		float cos = product/((float)Math.sqrt(e1*e2));
		float tan = (float)Math.tan((float)Math.acos(cos));
		tan = Math.abs(tan);

		if(tan<0.001){
			return false;
		}
		else{
			return true;
		}
	}

	public static float distance(Point p1, Point p2){
		if(p1==null || p2==null){
			return -1;
		}
		float y = (p2.getX()-p1.getX())*(p2.getX()-p1.getX())+(p2.getY()-p1.getY())*(p2.getY()-p1.getY())+(p2.getZ()-p1.getZ())*(p2.getZ()-p1.getZ());
		return (float)y;
	}

	//INPUT [x1,y1,z1,x2,y2,z2,x3,y3,z3]
	public boolean ADD_TRIANGLE(float [] triangle_coord){
		float x1 = triangle_coord[0];
		float y1 = triangle_coord[1];
		float z1 = triangle_coord[2];

		float x2 = triangle_coord[3];
		float y2 = triangle_coord[4];
		float z2 = triangle_coord[5];

		float x3 = triangle_coord[6];
		float y3 = triangle_coord[7];
		float z3 = triangle_coord[8];

		if(!check_valid_triangle(x1,y1,z1,x2,y2,z2,x3,y3,z3)){
			// System.out.println("line 157");
			// System.out.println("false");
			return false;
		}

		Point p1 = new Point(x1,y1,z1);
		Point p2 = new Point(x2,y2,z2);
		Point p3 = new Point(x3,y3,z3);
		// System.out.println("line 175");

		// int a1 = this.vertex_list.getIndex(p1);
		if(!this.vertex_table.contains(p1)){
			this.vertex_list.add(p1);
			this.vertex_table.insert(p1);
		}
		else{
			p1 = (Point)this.vertex_table.get(p1);
		}

		// int b1 = this.vertex_list.getIndex(p2);
		if(!this.vertex_table.contains(p2)){
			this.vertex_list.add(p2);
			this.vertex_table.insert(p2);
		}
		else{
			p2 = (Point)this.vertex_table.get(p2); 
		}

		// int c1 = this.vertex_list.getIndex(p3);
		if(!this.vertex_table.contains(p3)){
			this.vertex_list.add(p3);
			this.vertex_table.insert(p3);
		}
		else{
			p3 = (Point)this.vertex_table.get(p3);
		}

		Edge e1 = null;
		Edge e2 = null;
		Edge e3 = null;

		if(p1.compareTo(p2)<=0){
			e1 = new Edge(p1,p2);
		}
		else{
			e1 = new Edge(p2,p1);
		}
		if(p2.compareTo(p3)<=0){
			e2 = new Edge(p2,p3);
		}
		else{
			e2 = new Edge(p3,p2);
		}
		if(p3.compareTo(p1)<=0){
			e3 = new Edge(p3,p1);
		}
		else{
			e3 = new Edge(p1,p3);
		}
		// System.out.println("line 204");

		// int a2 = this.edge_list.getIndex(e1);
		if(!this.edge_table.contains(e1)){
			this.edge_list.add(e1);
			this.edge_table.insert(e1);
		}
		else{
			e1 = (Edge)this.edge_table.get(e1);
			// System.out.println("else "+e1);
		}

		// int b2 = this.edge_list.getIndex(e2);
		if(!this.edge_table.contains(e2)){
			this.edge_list.add(e2);
			this.edge_table.insert(e2);
		}
		else{
			e2 = (Edge)this.edge_table.get(e2);
		}

		// int c2 = this.edge_list.getIndex(e3);
		if(!this.edge_table.contains(e3)){
			this.edge_list.add(e3);
			this.edge_table.insert(e3);
		}
		else{
			e3 = (Edge)this.edge_table.get(e3);
		}
		// System.out.println("line 229");
		Point[] point_arr = {p1,p2,p3};
		this.MergeSort3(point_arr,3);

		Triangle t = new Triangle(point_arr[0],point_arr[1],point_arr[2],e1,e2,e3);
		// System.out.println("line 232");
		TriangleNode node = new TriangleNode(t);

		if(!this.graph_vertex_table.contains(node)){
			// System.out.println("this.graph_vertex_list.index: "+this.graph_vertex_list.index+ " node: "+ node);
			this.graph_vertex_list.add(node);
			this.graph_vertex_table.insert(node);
			// System.out.println("node.visited.. "+node.visited);
		}
		// System.out.println("line 233");
		t.time_stamp = timer;
		timer++;
		// System.out.println("line 235");
		// System.out.println(e1.boundary_edge);
		// System.out.println("line 237");

		if(e1!=null){
			for (int i=0;i<e1.face_neighbours.index;i++) {
				// System.out.println("in for in Triangle_add");
				if(e1.face_neighbours.get(i)!=null && (Triangle)e1.face_neighbours.get(i)!=t){
					// System.out.println("in for in Triangle_add");
					TriangleEdge te = new TriangleEdge(t,(Triangle)e1.face_neighbours.get(i));
					this.graph_edge_list.add(te);
					TriangleNode adj_node1 = new TriangleNode((Triangle)e1.face_neighbours.get(i));

					// int d1 = this.graph_vertex_list.getIndex(adj_node1);
					if(!this.graph_vertex_table.contains(adj_node1)){
						// System.out.println("this.graph_vertex_list.index: "+this.graph_vertex_list.index+" adj_node1: "+adj_node1);
						this.graph_vertex_list.add(adj_node1);
						this.graph_vertex_table.insert(adj_node1);
						node.adj_list.add(adj_node1);
						adj_node1.adj_list.add(node);

					}
					else{
						
						adj_node1 = this.graph_vertex_table.get(adj_node1);
						// System.out.println("else "+" adj_node1: "+adj_node1+" visited: "+adj_node1.visited);
						node.adj_list.add(adj_node1);
						adj_node1.adj_list.add(node);
						// System.out.println("NANANANANNANA  "+((TriangleNode)node.adj_list.get(0)).visited);
					}
				}
			}
		}

		if(e2!=null){
			for (int i=0;i<e2.face_neighbours.index;i++) {
				if(e2.face_neighbours.get(i)!=null && (Triangle)e2.face_neighbours.get(i)!=t){
					TriangleEdge te = new TriangleEdge(t,(Triangle)e2.face_neighbours.get(i));
					this.graph_edge_list.add(te);
					TriangleNode adj_node2 = new TriangleNode((Triangle)e2.face_neighbours.get(i));
					
					// int d2 = this.graph_vertex_list.getIndex(adj_node2);
					if(!this.graph_vertex_table.contains(adj_node2)){
						// System.out.println("this.graph_vertex_list.index: "+this.graph_vertex_list.index+" adj_node2: "+adj_node2);
						this.graph_vertex_list.add(adj_node2);
						this.graph_vertex_table.insert(adj_node2);
						node.adj_list.add(adj_node2);
						adj_node2.adj_list.add(node);
					}
					else{
						
						adj_node2 = this.graph_vertex_table.get(adj_node2);
						// System.out.println("else "+" adj_node2: "+adj_node2+" visited: "+adj_node2.visited);
						node.adj_list.add(adj_node2);
						adj_node2.adj_list.add(node);
					}
				}
			}
		}

		if(e3!=null){
			for (int i=0;i<e3.face_neighbours.index;i++) {
				if(e3.face_neighbours.get(i)!=null && (Triangle)e3.face_neighbours.get(i)!=t){
					TriangleEdge te = new TriangleEdge(t,(Triangle)e3.face_neighbours.get(i));
					this.graph_edge_list.add(te);
					TriangleNode adj_node3 = new TriangleNode((Triangle)e3.face_neighbours.get(i));
					
					// int d3 = this.graph_vertex_list.getIndex(adj_node3);
					if(!this.graph_vertex_table.contains(adj_node3)){
						// System.out.println("this.graph_vertex_list.index: "+this.graph_vertex_list.index+" adj_node3: "+adj_node3);
						this.graph_vertex_list.add(adj_node3);
						this.graph_vertex_table.insert(adj_node3);
						node.adj_list.add(adj_node3);
						adj_node3.adj_list.add(node);
					}
					else{

						adj_node3 = this.graph_vertex_table.get(adj_node3);
						// System.out.println("else "+" adj_node3: "+adj_node3+" visited: "+adj_node3.visited);
						node.adj_list.add(adj_node3);
						adj_node3.adj_list.add(node);
					}
				}
			}
		}	

		if(p1.incident_edges.getIndex(e1)==-1){
			p1.incident_edges.add(e1);
		}
		if(p1.incident_edges.getIndex(e3)==-1){
			p1.incident_edges.add(e3);
		}
		if(p2.incident_edges.getIndex(e1)==-1){
			p2.incident_edges.add(e1);
		}
		if(p2.incident_edges.getIndex(e2)==-1){
			p2.incident_edges.add(e2);
		}
		if(p3.incident_edges.getIndex(e2)==-1){
			p3.incident_edges.add(e2);
		}
		if(p3.incident_edges.getIndex(e3)==-1){
			p3.incident_edges.add(e3);
		}

		p1.incident_triangles.add(t);
		p2.incident_triangles.add(t);
		p3.incident_triangles.add(t);

		e1.face_neighbours.add(t);
		e2.face_neighbours.add(t);
		e3.face_neighbours.add(t);


		if (e1.face_neighbours.index<2) {
			e1.boundary_edge = true;
			this.boundary_edges.add(e1);
		}
		else{
			e1.boundary_edge = false;
			this.boundary_edges.remove(e1);

		}

		if (e2.face_neighbours.index<2) {
			e2.boundary_edge = true;
			this.boundary_edges.add(e2);
		}
		else{
			e2.boundary_edge = false;
			this.boundary_edges.remove(e2);

		}

		if (e3.face_neighbours.index<2) {
			e3.boundary_edge = true;
			this.boundary_edges.add(e3);
		}
		else{
			e3.boundary_edge = false;
			this.boundary_edges.remove(e3);

		}

		if(e1.face_neighbours.index>2 || e2.face_neighbours.index>2 || e3.face_neighbours.index>2 ){
			mesh_type = 3;
		}
		// System.out.println("true");
		return true;
	}

	public int TYPE_MESH(){
		if(mesh_type==3){
			return mesh_type;
		}
		// System.out.println("this.graph_vertex_list.index: "+this.graph_vertex_list.index);
		// System.out.println("this.graph_edge_list.index: "+this.graph_edge_list.index);			
		// System.out.println("this.vertex_list.index: "+this.vertex_list.index);
		// System.out.println("this.edge_list.index: "+this.edge_list.index);
		
		for(int i=0;i<this.edge_list.index;i++){
			// System.out.println("All edges: "+((Edge)this.edge_list.get(i)).edgeEndPoints());
			if(((Edge)this.edge_list.get(i)).face_neighbours.index<2){
				// System.out.println(((Edge)this.edge_list.get(i)).edgeEndPoints());
				mesh_type = 2;
				return mesh_type;
			}
		}

		mesh_type = 1;
		// TriangleNode tn = this.graph_vertex_list.get(2);
		// for(int k=0;k<tn.adj_list.index;k++){
		// 	System.out.println(((TriangleNode)tn.adj_list.get(k)).visited+" SNOOOOOOp");
		// }
		return mesh_type;
	}

	public EdgeInterface [] BOUNDARY_EDGES(){
		if (this.boundary_edges==null || this.boundary_edges.index==0) {
			// System.out.println("null");
			return null;
		}
		Edge[] Bound = new Edge[this.boundary_edges.index];
		for(int i=0;i<this.boundary_edges.index;i++){
			Bound[i] = (Edge)this.boundary_edges.get(i);
		}
		this.MergeSort(Bound, Bound.length);
		// System.out.println("in boundary_edges, after merge");
		// for(int x=0;x<Bound.length;x++){
		// 	Edge e = Bound[x];
		// 	PointInterface[] arr = e.edgeEndPoints();
		// 	System.out.println(arr[0] +", " + arr[1]);
		// }
		return Bound;
	}

	public int COUNT_CONNECTED_COMPONENTS(){
		int components = 0;
		if(this.graph_vertex_list!=null){
			
			for(int i=0;i<this.graph_vertex_list.index;i++){
				TriangleNode harit = (TriangleNode)this.graph_vertex_list.get(i);
				// System.out.println("harit.adj_list.index: "+harit.adj_list.index);
					// for(int x=0;x<harit.adj_list.index;x++){
					// 	TriangleNode tx = ((TriangleNode)harit.adj_list.get(x));
					// 	System.out.println("tx:=== "+tx+"||||| tx.visited : "+tx.visited+" ----------");
					// }
					if(harit.visited ==1){
						// System.out.println("connected components: Bye");
						continue;
					}
					else{
						// System.out.println("connected components: Suck. "+harit+" (TriangleNode)this.graph_vertex_list.get(i).visited: "+(harit).visited);
						// for(int x=0;x<tn.adj_list.index;x++){
						// 	TriangleNode tx = ((TriangleNode)tn.adj_list.get(x));
						// 	System.out.println("tx:=== "+tx+"|||||"+tx.visited+" ----------");
						// }
						this.bfs1(this.graph_vertex_list, harit);
						// System.out.println("connected components: in for 2");
						components++;
					}
			}

			for(int i=0;i<this.graph_vertex_list.index;i++){
				((TriangleNode)this.graph_vertex_list.get(i)).visited = 0;
			}
		}

		return components;
	}

	//INPUT [x1,y1,z1,x2,y2,z2,x3,y3,z3]
	public TriangleInterface [] NEIGHBORS_OF_TRIANGLE(float [] triangle_coord){
		float x1 = triangle_coord[0];
		float y1 = triangle_coord[1];
		float z1 = triangle_coord[2];

		float x2 = triangle_coord[3];
		float y2 = triangle_coord[4];
		float z2 = triangle_coord[5];

		float x3 = triangle_coord[6];
		float y3 = triangle_coord[7];
		float z3 = triangle_coord[8];

		Point p1 = new Point(x1,y1,z1);
		Point p2 = new Point(x2,y2,z2);
		Point p3 = new Point(x3,y3,z3);

		Edge e1 = null;
		Edge e2 = null;
		Edge e3 = null;

		if(p1.compareTo(p2)<=0){
			e1 = new Edge(p1,p2);
		}
		else{
			e1 = new Edge(p2,p1);
		}
		if(p2.compareTo(p3)<=0){
			e2 = new Edge(p2,p3);
		}
		else{
			e2 = new Edge(p3,p2);
		}
		if(p3.compareTo(p1)<=0){
			e3 = new Edge(p3,p1);
		}
		else{
			e3 = new Edge(p1,p3);
		}

		Point[] point_arr = {p1,p2,p3};
		this.MergeSort3(point_arr,3);

		Triangle t = new Triangle(point_arr[0],point_arr[1],point_arr[2],e1,e2,e3);
		TriangleNode node = new TriangleNode(t);

		if(!this.graph_vertex_table.contains(node)){
			return null;
		}
		else{
			node  = this.graph_vertex_table.get(node);
			t = node.triangle;
		}

		Triangle[] neighbour_array = new Triangle[node.adj_list.index];
		for(int k=0;k<node.adj_list.index;k++){
			TriangleNode t_node = (TriangleNode)node.adj_list.get(k);
			if(t_node!=null){
				neighbour_array[k] = (Triangle)t_node.triangle;
			}
		}

		this.MergeSort2(neighbour_array, neighbour_array.length);
		// for(int i=0;i<neighbour_array.length;i++){
		// 	System.out.println(neighbour_array[i].triangle_coord()[0]+", "+neighbour_array[i].triangle_coord()[1]+", "+neighbour_array[i].triangle_coord()[2]);
		// }
		return neighbour_array;

	}

	//INPUT [x1,y1,z1,x2,y2,z2,x3,y3,z3]
	public EdgeInterface [] EDGE_NEIGHBOR_TRIANGLE(float [] triangle_coord){
		float x1 = triangle_coord[0];
		float y1 = triangle_coord[1];
		float z1 = triangle_coord[2];

		float x2 = triangle_coord[3];
		float y2 = triangle_coord[4];
		float z2 = triangle_coord[5];

		float x3 = triangle_coord[6];
		float y3 = triangle_coord[7];
		float z3 = triangle_coord[8];

		Point p1 = new Point(x1,y1,z1);
		Point p2 = new Point(x2,y2,z2);
		Point p3 = new Point(x3,y3,z3);

		Edge e1 = null;
		Edge e2 = null;
		Edge e3 = null;

		if(p1.compareTo(p2)<=0){
			e1 = new Edge(p1,p2);
		}
		else{
			e1 = new Edge(p2,p1);
		}
		if(p2.compareTo(p3)<=0){
			e2 = new Edge(p2,p3);
		}
		else{
			e2 = new Edge(p3,p2);
		}
		if(p3.compareTo(p1)<=0){
			e3 = new Edge(p3,p1);
		}
		else{
			e3 = new Edge(p1,p3);
		}

		Point[] point_arr = {p1,p2,p3};
		this.MergeSort3(point_arr,3);

		Triangle t = new Triangle(point_arr[0],point_arr[1],point_arr[2],e1,e2,e3);
		TriangleNode node = new TriangleNode(t);

		if(!this.graph_vertex_table.contains(node)){
			return null;
		}
		else{
			node  = this.graph_vertex_table.get(node);
			t = node.triangle;
		}


		Edge[] edge_array = {t.e1, t.e2, t.e3};
		// for(int i=0;i<edge_array.length;i++){
		// 	System.out.println(edge_array[i].edgeEndPoints()[0]+", "+edge_array[i].edgeEndPoints()[1]);
		// }
		return edge_array;
	}

//INPUT [x1,y1,z1,x2,y2,z2,x3,y3,z3]
 public PointInterface [] VERTEX_NEIGHBOR_TRIANGLE(float [] triangle_coord){
	 	float x1 = triangle_coord[0];
		float y1 = triangle_coord[1];
		float z1 = triangle_coord[2];

		float x2 = triangle_coord[3];
		float y2 = triangle_coord[4];
		float z2 = triangle_coord[5];

		float x3 = triangle_coord[6];
		float y3 = triangle_coord[7];
		float z3 = triangle_coord[8];

		Point p1 = new Point(x1,y1,z1);
		Point p2 = new Point(x2,y2,z2);
		Point p3 = new Point(x3,y3,z3);

	
		Edge e1 = null;
		Edge e2 = null;
		Edge e3 = null;

		if(p1.compareTo(p2)<=0){
			e1 = new Edge(p1,p2);
		}
		else{
			e1 = new Edge(p2,p1);
		}
		if(p2.compareTo(p3)<=0){
			e2 = new Edge(p2,p3);
		}
		else{
			e2 = new Edge(p3,p2);
		}
		if(p3.compareTo(p1)<=0){
			e3 = new Edge(p3,p1);
		}
		else{
			e3 = new Edge(p1,p3);
		}

		Point[] point_arr = {p1,p2,p3};
		this.MergeSort3(point_arr,3);

		Triangle t = new Triangle(point_arr[0],point_arr[1],point_arr[2],e1,e2,e3);
		TriangleNode node = new TriangleNode(t);

		if(!this.graph_vertex_table.contains(node)){
			return null;
		}
		else{
			node  = this.graph_vertex_table.get(node);
			t = node.triangle;
		}


		PointInterface[] point_array = t.triangle_coord();
		
		// System.out.println(point_array[0]+", "+point_array[1]+", "+point_array[2]);
		
		return point_array;
 }


//INPUT [x1,y1,z1,x2,y2,z2,x3,y3,z3]
 public TriangleInterface [] EXTENDED_NEIGHBOR_TRIANGLE(float [] triangle_coord){
	 	float x1 = triangle_coord[0];
		float y1 = triangle_coord[1];
		float z1 = triangle_coord[2];

		float x2 = triangle_coord[3];
		float y2 = triangle_coord[4];
		float z2 = triangle_coord[5];

		float x3 = triangle_coord[6];
		float y3 = triangle_coord[7];
		float z3 = triangle_coord[8];

		Point p1 = new Point(x1,y1,z1);
		Point p2 = new Point(x2,y2,z2);
		Point p3 = new Point(x3,y3,z3);

		
		Edge e1 = null;
		Edge e2 = null;
		Edge e3 = null;

		if(p1.compareTo(p2)<=0){
			e1 = new Edge(p1,p2);
		}
		else{
			e1 = new Edge(p2,p1);
		}
		if(p2.compareTo(p3)<=0){
			e2 = new Edge(p2,p3);
		}
		else{
			e2 = new Edge(p3,p2);
		}
		if(p3.compareTo(p1)<=0){
			e3 = new Edge(p3,p1);
		}
		else{
			e3 = new Edge(p1,p3);
		}

		Point[] point_arr = {p1,p2,p3};
		this.MergeSort3(point_arr,3);

		Triangle t = new Triangle(point_arr[0],point_arr[1],point_arr[2],e1,e2,e3);
		TriangleNode node = new TriangleNode(t);

		if(!this.graph_vertex_table.contains(node)){
			return null;
		}
		else{
			node  = this.graph_vertex_table.get(node);
			t = node.triangle;
		}



		PointInterface[] vertex_array = t.triangle_coord();
		CustomArrayList<Triangle> extended_triangle_list = new CustomArrayList();

		for(int i=0;i<vertex_array.length;i++){
			Point p = (Point)vertex_array[i];
			for(int j=0;j<p.incident_triangles.index;j++){
				Triangle temp = p.incident_triangles.get(j);
				if(temp!=null && temp.compareTo(t)!=0 && extended_triangle_list.getIndex(temp)==-1){
					extended_triangle_list.add(temp);
				}
			}
		}
		// System.out.println("extended_triangle_list.index: "+extended_triangle_list.index);
		if(extended_triangle_list==null || extended_triangle_list.index==0){
			// System.out.println("null");
			return null;
		}

		Triangle[] extended_triangle_array = new Triangle[extended_triangle_list.index];
		for(int i=0;i<extended_triangle_list.index;i++){
			extended_triangle_array[i] = extended_triangle_list.get(i);
		}
		// System.out.println("extended_triangle_array.length: "+extended_triangle_array.length);
		this.MergeSort2(extended_triangle_array, extended_triangle_array.length);
		// for(int x=0;x<extended_triangle_array.length;x++){
		// 	System.out.println(extended_triangle_array[x]);
		// }
		return extended_triangle_array;

 }


//INPUT [x,y,z]
 public TriangleInterface [] INCIDENT_TRIANGLES(float [] point_coordinates){
 	Point p = new Point(point_coordinates[0], point_coordinates[1], point_coordinates[2]); 
 	// int m = this.vertex_list.getIndex(p);
 	if(!this.vertex_table.contains(p)){
 		return null;
 	}
 	else{
 		p = this.vertex_table.get(p);
 		Triangle[] arr = new Triangle[p.incident_triangles.index];
 		for(int i=0;i<p.incident_triangles.index;i++){
 			arr[i] = p.incident_triangles.get(i);
 		}
 		if(arr.length==0 || arr==null){
 			return null;
 		} 

 		this.MergeSort2(arr, arr.length);
 		// for(int k=0;k<arr.length;k++){
	 	// 	System.out.println(arr[k]);
 		// }
 		return arr;

 	}
 }


// INPUT [x,y,z]
 public PointInterface [] NEIGHBORS_OF_POINT(float [] point_coordinates){
 	Point p = new Point(point_coordinates[0], point_coordinates[1], point_coordinates[2]); 
 	// int m = this.vertex_list.getIndex(p);
 	if(!this.vertex_table.contains(p)){
 		return null;
 	}
 	else{
 		p = this.vertex_table.get(p);
 		Point[] arr = new Point[p.incident_edges.index];
 		for(int i=0;i<p.incident_edges.index;i++){
 			Edge e = p.incident_edges.get(i);
 			if(e.p1.compareTo(p)!=0){
 				arr[i] = e.p1;
 			}
 			else{
 				arr[i] = e.p2;
 			}
 		}
 		// for(int k=0;k<arr.length;k++){
	 	// 	System.out.println(arr[k]);
 		// }
 		return arr;

 	}
 }


// INPUT[x,y,z]
 public EdgeInterface [] EDGE_NEIGHBORS_OF_POINT(float [] point_coordinates){
 	Point p = new Point(point_coordinates[0], point_coordinates[1], point_coordinates[2]); 
 	// int m = this.vertex_list.getIndex(p);
 	if(!this.vertex_table.contains(p)){
 		return null;
 	}
 	else{
 		p = this.vertex_table.get(p);
 		Edge[] arr = new Edge[p.incident_edges.index];
 		for(int i=0;i<p.incident_edges.index;i++){
 			Edge e = p.incident_edges.get(i);
 			arr[i] = e;
 		}
 		// for(int k=0;k<arr.length;k++){
	 	// 	System.out.println(arr[k]);
 		// }
 		return arr;
 	}

 }


// INPUT[x,y,z]
 public TriangleInterface [] FACE_NEIGHBORS_OF_POINT(float [] point_coordinates){ 
 	Point p = new Point(point_coordinates[0], point_coordinates[1], point_coordinates[2]); 
 	// int m = this.vertex_list.getIndex(p);
 	if(!this.vertex_table.contains(p)){
 		return null;
 	}
 	else{
 		p = this.vertex_table.get(p);
 		Triangle[] arr = new Triangle[p.incident_triangles.index];
 		for(int i=0;i<p.incident_triangles.index;i++){
 			arr[i] = p.incident_triangles.get(i);
 		}
 		this.MergeSort2(arr, arr.length);
 		// for(int k=0;k<arr.length;k++){
	 	// 	System.out.println(arr[k]);
 		// }
 		return arr;
 	}
 }



// INPUT // [xa1,ya1,za1,xa2,ya2,za2,xa3,ya3,za3 , xb1,yb1,zb1,xb2,yb2,zb2,xb3,yb3,zb3]   where xa1,ya1,za1,xa2,ya2,za2,xa3,ya3,za3 are 3 coordinates of first triangle and xb1,yb1,zb1,xb2,yb2,zb2,xb3,yb3,zb3 are coordinates of second triangle as given in specificaition.
 public boolean IS_CONNECTED(float [] triangle_coord_1, float [] triangle_coord_2){
	 	float xa1 = triangle_coord_1[0];
		float ya1 = triangle_coord_1[1];
		float za1 = triangle_coord_1[2];

		float xa2 = triangle_coord_1[3];
		float ya2 = triangle_coord_1[4];
		float za2 = triangle_coord_1[5];

		float xa3 = triangle_coord_1[6];
		float ya3 = triangle_coord_1[7];
		float za3 = triangle_coord_1[8];

		Point pa1 = new Point(xa1,ya1,za1);
		Point pa2 = new Point(xa2,ya2,za2);
		Point pa3 = new Point(xa3,ya3,za3);

		
		Edge ea1 = null;
		Edge ea2 = null;
		Edge ea3 = null;

		if(pa1.compareTo(pa2)<=0){
			ea1 = new Edge(pa1,pa2);
		}
		else{
			ea1 = new Edge(pa2,pa1);
		}
		if(pa2.compareTo(pa3)<=0){
			ea2 = new Edge(pa2,pa3);
		}
		else{
			ea2 = new Edge(pa3,pa2);
		}
		if(pa3.compareTo(pa1)<=0){
			ea3 = new Edge(pa3,pa1);
		}
		else{
			ea3 = new Edge(pa1,pa3);
		}

		Point[] point_arr1 = {pa1,pa2,pa3};
		this.MergeSort3(point_arr1,3);

		Triangle t1= new Triangle(point_arr1[0],point_arr1[1],point_arr1[2],ea1,ea2,ea3);
		TriangleNode node1 = new TriangleNode(t1);

		if(!this.graph_vertex_table.contains(node1)){
			return false;
		}
		else{
			node1  = this.graph_vertex_table.get(node1);
			t1 = node1.triangle;
		}


		float xb1 = triangle_coord_2[0];
		float yb1 = triangle_coord_2[1];
		float zb1 = triangle_coord_2[2];

		float xb2 = triangle_coord_2[3];
		float yb2 = triangle_coord_2[4];
		float zb2 = triangle_coord_2[5];

		float xb3 = triangle_coord_2[6];
		float yb3 = triangle_coord_2[7];
		float zb3 = triangle_coord_2[8];

		Point pb1 = new Point(xb1,yb1,zb1);
		Point pb2 = new Point(xb2,yb2,zb2);
		Point pb3 = new Point(xb3,yb3,zb3);

		Edge eb1 = null;
		Edge eb2 = null;
		Edge eb3 = null;

		if(pb1.compareTo(pb2)<=0){
			eb1 = new Edge(pb1,pb2);
		}
		else{
			eb1 = new Edge(pb2,pb1);
		}
		if(pb2.compareTo(pb3)<=0){
			eb2 = new Edge(pb2,pb3);
		}
		else{
			eb2 = new Edge(pb3,pb2);
		}
		if(pb3.compareTo(pb1)<=0){
			eb3 = new Edge(pb3,pb1);
		}
		else{
			eb3 = new Edge(pb1,pb3);
		}

		Point[] point_arr2 = {pb1,pb2,pb3};
		this.MergeSort3(point_arr2,3);

		Triangle t2= new Triangle(point_arr2[0],point_arr2[1],point_arr2[2],eb1,eb2,eb3);
		TriangleNode node2 = new TriangleNode(t2);

		if(!this.graph_vertex_table.contains(node2)){
			return false;
		}
		else{
			node2  = this.graph_vertex_table.get(node2);
			t2 = node2.triangle;
		}

		if(t1.compareTo(t2)==0){
			return true;
		}
		boolean ans = this.bfs2(this.graph_vertex_list,node1,node2);

		for(int k=0;k<this.graph_vertex_list.index;k++){
			((TriangleNode)this.graph_vertex_list.get(k)).visited = 0;
		}
		// System.out.println("graph_vertex_list.index:    "+graph_vertex_list.index);
		return ans;

 }


// INPUT [x1,y1,z1,x2,y2,z2] // where (x1,y1,z1) refers to first end point of edge and  (x2,y2,z2) refers to the second.
 public TriangleInterface [] TRIANGLE_NEIGHBOR_OF_EDGE(float [] edge_coordinates){ 
 	float x1 = edge_coordinates[0];
 	float y1 = edge_coordinates[1];
 	float z1 = edge_coordinates[2];

 	float x2 = edge_coordinates[3];
 	float y2 = edge_coordinates[4];
 	float z2 = edge_coordinates[5];

 	Point p1 = new Point(x1,y1,z1);
 	Point p2 = new Point(x2,y2,z2);

 	Edge e1 = null;

 	if(p1.compareTo(p2)<=0){
 		e1 = new Edge(p1,p2);
 	}
 	else{
 		e1 = new Edge(p2,p1);
 	}
 	

 	if(!this.edge_table.contains(e1)){
 		return null;
 	}
 	else{
 		e1 = this.edge_table.get(e1);
 		Triangle[] array = new Triangle[e1.face_neighbours.index];
 		for(int i=0;i<e1.face_neighbours.index;i++){
 			array[i] = e1.face_neighbours.get(i);
 		}
 		if(array.length==0 || array==null){
 			return null;
 		}
 		this.MergeSort2(array, array.length);
 		// for(int i=0;i<array.length;i++){
 		// 	System.out.println(array[i]);
 		// }
 		return array;
 	}
 }


public int MAXIMUM_DIAMETER(){
	if(this.graph_vertex_list.index==0 || this.graph_vertex_list.index==1){
		return 0;
	}

	int comp_size = 0;
	TriangleNode req = null;	
	CustomArrayList<TriangleNode> req_component_nodes_list = null;
	for(int i=0;i<this.graph_vertex_list.index;i++){
		TriangleNode harit = (TriangleNode)this.graph_vertex_list.get(i);
		// System.out.println("harit.adj_list.index: "+harit.adj_list.index);
			// for(int x=0;x<harit.adj_list.index;x++){
			// 	TriangleNode tx = ((TriangleNode)harit.adj_list.get(x));
			// 	System.out.println("tx:=== "+tx+"||||| tx.visited : "+tx.visited+" ----------");
			// }
			if(harit.visited==1){
				// System.out.println("connected components: Bye");
				continue;
			}
			else{
				int max = 0;
				CustomArrayList<TriangleNode> component_nodes_list = new CustomArrayList();
				component_nodes_list.add(harit);
				Queue<TriangleNode> q = new Queue();
				q.insert(harit);
				harit.visited = 1;
				max++;

				while(!q.isEmpty() && q!=null){
					// System.out.println("Entered for of bfs ");
					TriangleNode v = q.remove();
					// System.out.println("Entered for of bfs 0");
					// System.out.println("VVVVV: "+((TriangleNode)v).visited);
					if(v!=null){
						// System.out.println("Entered for of bfs1 ");
						for(int j=0;j<v.adj_list.index;j++){
							TriangleNode tn = (TriangleNode)v.adj_list.get(j);
							// System.out.println("Entered for of bfs2");
							// System.out.println("((TriangleNode)v).adj_list.index: "+((TriangleNode)v).adj_list.index);
							// System.out.println("(TriangleNode)v.adj_list.get(i)).visited: "+((TriangleNode)v.adj_list.get(i)).visited);
							if(tn.visited==0){
								// System.out.println("Hi: "+(TriangleNode)v.adj_list.get(i));
								tn.visited = 1;
								component_nodes_list.add(tn);
								max++;
								q.insert(tn);	
							}
						}
					}
				}
				if(max>comp_size){
					comp_size = max;
					req = (TriangleNode)this.graph_vertex_list.get(i);
					req_component_nodes_list = component_nodes_list;
				}
			}
	}

	for(int i=0;i<this.graph_vertex_list.index;i++){
		((TriangleNode)this.graph_vertex_list.get(i)).visited = 0;
	}
	
	// BFS STARTS----------------------------------------------------
	int max_distance = 0;
	for(int x=0;x<req_component_nodes_list.index;x++){
		TriangleNode zac = req_component_nodes_list.get(x);
		Queue<TriangleNode> q = new Queue();
		q.insert(zac);
		zac.visited = 1;
		zac.distance = 0;

		while(!q.isEmpty() && q!=null){
			TriangleNode v = q.remove();
			// System.out.println("Entered for of bfs 0");
			// System.out.println("VVVVV: "+((TriangleNode)v).visited);
			if(v!=null){
				// System.out.println("Entered for of bfs1 ");
				for(int j=0;j<v.adj_list.index;j++){
					TriangleNode tn = (TriangleNode)v.adj_list.get(j);
					// System.out.println("Entered for of bfs2");
					// System.out.println("((TriangleNode)v).adj_list.index: "+((TriangleNode)v).adj_list.index);
					// System.out.println("(TriangleNode)v.adj_list.get(i)).visited: "+((TriangleNode)v.adj_list.get(i)).visited);
					if(tn.visited==0){
						// System.out.println("Hi: "+(TriangleNode)v.adj_list.get(i));
						tn.visited = 1;
						tn.distance = v.distance+1;
						if(tn.distance>max_distance){
							max_distance = tn.distance;
						}
						q.insert(tn);	
					}
				}
			}
		}
		for(int k=0;k<this.graph_vertex_list.index;k++){
			((TriangleNode)this.graph_vertex_list.get(k)).visited = 0;
		}
	}
	
	// System.out.println(max_distance);
	return max_distance;

}



public PointInterface [] CENTROID (){
	int components = 0;
	CustomArrayList<Point> centroid_list = new CustomArrayList();
	TriangleNode req = null;	
	for(int i=0;i<this.graph_vertex_list.index;i++){
		TriangleNode harit = (TriangleNode)this.graph_vertex_list.get(i);
		// System.out.println("harit.adj_list.index: "+harit.adj_list.index);
			// for(int x=0;x<harit.adj_list.index;x++){
			// 	TriangleNode tx = ((TriangleNode)harit.adj_list.get(x));
			// 	System.out.println("tx:=== "+tx+"||||| tx.visited : "+tx.visited+" ----------");
			// }
			if(harit.visited==1){
				// System.out.println("connected components: Bye");
				continue;
			}
			else{
				// System.out.println("connected components: Suck. "+harit+" (TriangleNode)this.graph_vertex_list.get(i).visited: "+(harit).visited);
				// for(int x=0;x<tn.adj_list.index;x++){
				// 	TriangleNode tx = ((TriangleNode)tn.adj_list.get(x));
				// 	System.out.println("tx:=== "+tx+"|||||"+tx.visited+" ----------");
				// }
				components++;
				// int rahat = 1;
				Queue<TriangleNode> q = new Queue();
				CustomArrayList<Point> centroid_vertices_list = new CustomArrayList();
				q.insert(harit);
				harit.visited = 1;
				centroid_vertices_list.add(harit.triangle.p1);
				centroid_vertices_list.add(harit.triangle.p2);
				centroid_vertices_list.add(harit.triangle.p3);
				// if(harit.triangle.p1.x==2769 || harit.triangle.p2.x==2769 || harit.triangle.p3.x ==2769){
				// 	System.out.println("SUCKKKKKSSS **** "+harit);
				// }
			
				while(!q.isEmpty() && q!=null){
					TriangleNode v = q.remove();
					if(v!=null){
						// System.out.println("Entered for of bfs1 ");
						for(int k=0;k<v.adj_list.index;k++){
							TriangleNode tn = (TriangleNode)v.adj_list.get(k);
							// System.out.println("Entered for of bfs2");
							// System.out.println("((TriangleNode)v).adj_list.index: "+((TriangleNode)v).adj_list.index);
							// System.out.println("(TriangleNode)v.adj_list.get(i)).visited: "+((TriangleNode)v.adj_list.get(i)).visited);
							if(tn.visited==0){
								// System.out.println("Hi: SAXXY "+tn );
								// rahat++;
								tn.visited = 1;
								if(centroid_vertices_list.getIndex(tn.triangle.p1)==-1){
									centroid_vertices_list.add(tn.triangle.p1);
								}
								if(centroid_vertices_list.getIndex(tn.triangle.p2)==-1){
									centroid_vertices_list.add(tn.triangle.p2);
								}
								if(centroid_vertices_list.getIndex(tn.triangle.p3)==-1){
									centroid_vertices_list.add(tn.triangle.p3);
								}
								// if(tn.triangle.p1.x==2769 || tn.triangle.p2.x==2769 || tn.triangle.p3.x ==2769){
								// 	System.out.println("SUCKKKKKSSS **** "+tn);
								// }
								q.insert(tn);	
							}
						}
					}
				}

				int total_vertices = centroid_vertices_list.index;
				float x_coord = 0;
				float y_coord = 0;
				float z_coord = 0;
				for(int m=0;m<total_vertices;m++){
					Point peter = centroid_vertices_list.get(m);
					x_coord+=peter.getX();
					y_coord+=peter.getY();
					z_coord+=peter.getZ();
				}

				Point centroid = new Point(x_coord/total_vertices, y_coord/total_vertices, z_coord/total_vertices);
				centroid_list.add(centroid);
				// if(centroid.x==5000.0){
				// 	faltu = centroid_vertices_list;
				// }
				// System.out.println(rahat+"  "+x_coord+" "+total_vertices+" --------- "+centroid);				
		}
	}

	for(int i=0;i<this.graph_vertex_list.index;i++){
		((TriangleNode)this.graph_vertex_list.get(i)).visited = 0;
	}

	Point[] centroid_array = new Point[components];
	for(int j=0;j<components;j++){
		centroid_array[j] = centroid_list.get(j);
	}
	this.MergeSort3(centroid_array, centroid_array.length);
	// for(int i=0;i<centroid_array.length;i++){
	// 	System.out.println(centroid_array[i]);
	// }
	return centroid_array;

}

// INPUT [x,y,z]
public PointInterface CENTROID_OF_COMPONENT (float [] point_coordinates){
	Point p = new Point(point_coordinates[0],point_coordinates[1],point_coordinates[2]);
	// int a = this.vertex_list.getIndex(p);
	if(!this.vertex_table.contains(p)){
		return null;
	}
	else{
		p = this.vertex_table.get(p);
	}

	Triangle t = p.incident_triangles.get(0);
	TriangleNode node = new TriangleNode(t);
	node = this.graph_vertex_table.get(node);
	// System.out.println("SUCKKKKKSSS:  "+node);
	
	// BFS STARTS__________________________

	Queue<TriangleNode> q = new Queue();
	// int rahat=1;
	CustomArrayList<Point> centroid_vertices = new CustomArrayList();
		q.insert(node);
		node.visited = 1;
		centroid_vertices.add(node.triangle.p1);
		centroid_vertices.add(node.triangle.p2);
		centroid_vertices.add(node.triangle.p3);
	
		while(!q.isEmpty() && q!=null){
			TriangleNode v = q.remove();
			if(v!=null){
				// System.out.println("Entered for of bfs1 ");
				for(int i=0;i<v.adj_list.index;i++){
					TriangleNode tn = (TriangleNode)v.adj_list.get(i);
					// System.out.println("Entered for of bfs2");
					// System.out.println("((TriangleNode)v).adj_list.index: "+((TriangleNode)v).adj_list.index);
					// System.out.println("(TriangleNode)v.adj_list.get(i)).visited: "+((TriangleNode)v.adj_list.get(i)).visited);
					if(tn.visited==0){
						// System.out.println("Hi: SAXXY "+tn);
						// rahat++;
						tn.visited = 1;
						if(centroid_vertices.getIndex(tn.triangle.p1)==-1){
							centroid_vertices.add(tn.triangle.p1);
						}
						if(centroid_vertices.getIndex(tn.triangle.p2)==-1){
							centroid_vertices.add(tn.triangle.p2);
						}
						if(centroid_vertices.getIndex(tn.triangle.p3)==-1){
							centroid_vertices.add(tn.triangle.p3);
						}
						q.insert(tn);	
					}
				}
			}
		}

		int total_vertices = centroid_vertices.index;
		float x_coord = 0;
		float y_coord = 0;
		float z_coord = 0;
		for(int i=0;i<total_vertices;i++){
			Point peter = centroid_vertices.get(i);
			x_coord+=peter.getX();
			y_coord+=peter.getY();
			z_coord+=peter.getZ();
		}

		Point centroid = new Point(x_coord/total_vertices, y_coord/total_vertices, z_coord/total_vertices);
		for(int i=0;i<this.graph_vertex_list.index;i++){
			((TriangleNode)this.graph_vertex_list.get(i)).visited = 0;
		}
		// System.out.println(rahat+"  "+centroid+" "+total_vertices+" "+x_coord);
		// Point[] faltu_array = new Point[total_vertices];
		// for(int j=0;j<faltu.index;j++){
		// 	faltu_array[j]=faltu.get(j);
		// }	
		// Point[] check_array = new Point[total_vertices];
		// for(int j=0;j<centroid_vertices.index;j++){
		// 	check_array[j]=centroid_vertices.get(j);
		// }	
		// this.MergeSort3(faltu_array,total_vertices);
		// this.MergeSort3(check_array, total_vertices);
		// System.out.println("check kara raha hun");
		// for(int z=0;z<total_vertices;z++){
		// 	if(faltu_array[z].compareTo(check_array[z])!=0){
		// 		System.out.println("faltu_array "+faltu_array[z]+"   "+"check_array "+check_array[z]);
		// 	}
		// }
		// System.out.println(centroid);
		return centroid;
}


public PointInterface [] CLOSEST_COMPONENTS(){
	int components = 0;
	CustomArrayList<CustomArrayList<Point>> list_of_list = new CustomArrayList();

	for(int i=0;i<this.graph_vertex_list.index;i++){
		TriangleNode harit = (TriangleNode)this.graph_vertex_list.get(i);
		if(harit.visited==1){
			continue;
		}
		else{
			CustomArrayList<Point> component_nodes_list = new CustomArrayList();
			component_nodes_list.add(harit.triangle.p1);
			component_nodes_list.add(harit.triangle.p2);
			component_nodes_list.add(harit.triangle.p3);
			Queue<TriangleNode> q = new Queue();
			q.insert(harit);
			harit.visited = 1;

			while(!q.isEmpty() && q!=null){
				TriangleNode v = q.remove();
				if(v!=null){
					for(int j=0;j<v.adj_list.index;j++){
						TriangleNode tn = (TriangleNode)v.adj_list.get(j);
						if(tn.visited==0){
							tn.visited = 1;
							
							if(component_nodes_list.getIndex(tn.triangle.p1)==-1){
								component_nodes_list.add(tn.triangle.p1);
							}
							if(component_nodes_list.getIndex(tn.triangle.p2)==-1){
								component_nodes_list.add(tn.triangle.p2);
							}
							if(component_nodes_list.getIndex(tn.triangle.p3)==-1){
								component_nodes_list.add(tn.triangle.p3);
							}
							q.insert(tn);	
						}
					}
				}
			}
			list_of_list.add(component_nodes_list);
			components++;
		}
	}

	for(int i=0;i<this.graph_vertex_list.index;i++){
		((TriangleNode)this.graph_vertex_list.get(i)).visited = 0;
	}

	//////////////////////////////////////////////////////
	// Point hu1 = new Point(0,1,0);
	// Point hu2 = new Point(3,4,5);
	// Point hu3 = new Point(9,10,12);

	// for(int counter1=0;counter1<list_of_list.index;counter1++){
	// 	for(int counter2=0;counter2<list_of_list.get(counter1).index;counter2++){
	// 		Point bye = list_of_list.get(counter1).get(counter2);
	// 		if(hu1.compareTo(bye)==0){
	// 			System.out.println(hu1+" "+counter1+" "+counter2);
	// 		}
	// 		if(hu2.compareTo(bye)==0){
	// 			System.out.println(hu2+" "+counter1+" "+counter2);
	// 		}
	// 		if(hu3.compareTo(bye)==0){
	// 			System.out.println(hu3+" "+counter1+" "+counter2);
	// 		}
	// 	}
	// }
	/////////////////////////////////////////////////////

	float min_distance = Float.MAX_VALUE;
	Point ans1 = null;
	Point ans2 = null;
	for(int m=0;m<components;m++){
		CustomArrayList<Point> comp1 = list_of_list.get(m);
		for(int n=m+1;n<components;n++){
			CustomArrayList<Point> comp2 = list_of_list.get(n);
			for(int q1=0;q1<comp1.index;q1++){
				Point p1 = comp1.get(q1);
				for (int q2=0;q2<comp2.index;q2++){
					Point p2 = comp2.get(q2);
					
					float temp = Shape.distance(p1,p2);
					if(temp<min_distance){
						min_distance = temp ;
						ans1 = p1;
						ans2 = p2;
					}
					
				}
			}
		}
	}
	Point[] request = {ans1,ans2};
	// System.out.println(request[0]+", "+request[1]);
	return request;
}


}

