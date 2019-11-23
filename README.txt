////////////////////////////////////////////////////////////////////////////////////////
README for Assignemnt-6
CustomArrayList is made to store vertices, edges, triangles as nodes of graph and triangle_edges, i.e., triangle which are adjacent(have common edge).
A separate chaining hashtable is maintained so as to fasten the searching process for vetrices, edges and traingle_nodes.

int bfs1: 
is iterative breadth first search which returns the no. of components in the graph. Complexity: O(n+m), n is no. of nodes and no.of edges in graph.

boolean bfs2: 
is iterative breadth first search from source vertex to destination and returns true if source and destination are connected. Complexity: O(n+m), n is no. of nodes and no.of edges in graph.

boolean check_valid_triangle: 
finds the cos of the angle between 2 lines using dot product and the finds the tan of the angle. If tan(phi)<0.001, then triangle is not valid or points are collinear. Else, triangle is collinear. Complexity: O(1)

float distance:
calculates eucledian distance between 2 points. Complexity: O(1)

boolean ADD_TRIANGLE:
creates new points, edges, triangles, and triangle_nodes after checking if they don't already exist in their respective data structures. Also populates the face_neighbour list of the edges, incident_edges list and incident_triangles list inside Point class.
If triangles have common edges, then a triangle edge is created between them which will help in connectivity and paths queries.
Moreover, a boundary_edges list is maintained and updated regularly in ADD_TRIANGLE itself for edges having less than 2 face_neighbours.
For edges having more than 2 face_neighbours, mesh_type is set to 3.
Complexity: O(n+m), n is no. of nodes and no.of edges in graph.

int TYPE_MESH:
edge_list is iterated over so as to find if any edge has < 2 face_neighbours and return mesh_type=2. Complexity: O(m), m is no.of edges.

EdgeInterface [] BOUNDARY_EDGES:
boundary_edges list which is maintained in ADD_TRIANGLE contains all the boundary edges. Complexity: O(m), m is no.of edges.

int COUNT_CONNECTED_COMPONENTS:
the graph_vertex_list is iterated over and bfs is done on triangle_nodes. This will mark all the nodes in same components as visited so no. of components can be found this way. Complexity: O(n*(n+m))


TriangleInterface [] NEIGHBORS_OF_TRIANGLE:
the given triangle is found using hash table and the adjacency_list in this gives the neighbours of the triangle. Complexity: O(n)


EdgeInterface [] EDGE_NEIGHBOR_TRIANGLE:
finds the triangle using hashtable and returns the edges of triangle in an array. O(1) in average case due to search in hashtable and O(n) in worst case.


PointInterface [] VERTEX_NEIGHBOR_TRIANGLE:
finds the triangle using hashtable and returns the vertices of triangle in an array.  O(1) in average case due to search in hashtable and O(n) in worst case.

TriangleInterface [] EXTENDED_NEIGHBOR_TRIANGLE:
finds the triangle using hash table and in all its 3 vertices, iterates over the incident triangles list and stores the extended neighbours in another list, checking if its not the triangle given in the query.
It is then sorted and returned in an array. Complexity: O(n), no. of triangles.

TriangleInterface [] INCIDENT_TRIANGLES:
the given point is found using vertex_table(hashtable), then incident_triangles list maintained inside each point object is used to find the required triangles and are sorted befor returning. Complexity: O(n), n is no.of triangles.

PointInterface [] NEIGHBORS_OF_POINT:
the given point is found using vertex_table(hashtable), then incident_edge list maintained inside each point object is used to find the required points which share an edge with the given point and are returned.
Complexity: O(n), n is no. of points in total.

EdgeInterface [] EDGE_NEIGHBORS_OF_POINT:
the given point is found using vertex_table(hashtable), then incident_edge list maintained inside each point object is used to find the required edges  and are returned. Complexity: O(n), no. of points in total.

TriangleInterface [] FACE_NEIGHBORS_OF_POINT:
the given point is found using vertex_table(hashtable), then incident_triangles list maintained inside each point object is used to find the required triangles and are sorted then, returned. Complexity: O(N), N is no. of triangles.

boolean IS_CONNECTED:
The 2 triangles are first searched in hashtable if they exist and if they do, then, bfs2 is done on both which returns boolean true if the triangls are connected. Complexity: O(n+m)

TriangleInterface [] TRIANGLE_NEIGHBOR_OF_EDGE:
the given is checked if it exits, if it does, then, the face_neighbours are returned after sorting by usong the face_neighbours list stored in each edge.
Complexity: O(N), N is no. of triangles.


int MAXIMUM_DIAMETER:
first the maximum size component is found using bfs. Of the maximum size component all the triangles that are part of it are stored in an arraylist.
Then, another bfs is done to find the diameter. The components_list is iterated over and each node's distance with each other is measured but only the maximum is returned by keeping a variable max_distance and updating it, whenever a value larger than its value is found. Complexity: O(n*(n+m)), n is no. of nodes and m is edges.

PointInterface [] CENTROID:
Whole graph_vertex_list is iterated over and bfs is done in each component to find all the points in all triangles of a component. All these points are added to a list, which is then looped so as to find centroid of that component. Each component's centroid is stored in another arraylist which is then sorted before returning in array form. Complexity: O(n*(n+m))

PointInterface CENTROID_OF_COMPONENT:
Here, the given point is used to identify it's component by finding the triangle to which it belongs and thus the triangle node. Again the steps done in CENTROID() function above are repeated but only for a single component this time. Complexity: O(n+m) 

PointInterface [] CLOSEST_COMPONENTS: 
A arraylist of arraylist of points is maintained. The graph_vertex_list is looped over all points in a component are stored in a list and then, the lists are stored in list of lists. The list of lists is iterated  using 4 nested loops so as to calculate distance of each point with other component's points. Comparision is optimised in the sense that is there are 4 components, then 1st is compared with other 3, 2nd is compred with rest 2, 3rd with the last and last with none. Time Complexity: O(n^3) 
