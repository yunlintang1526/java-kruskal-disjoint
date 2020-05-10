/*
 * Name: Yunlin Tang
 * PID: a14664383
 */

/**
 * disjoint set class which helps with the Kruskal's algorithm to trace
 * the vertices' path
 *
 * @author Yunlin Tang
 * @since ${12.9}
 */
public class DisjointSet {

    /**
     * the constructor of DisjoinSet
     */
    public DisjointSet() {}


    /**
     * find the root(sentinel node) of the current vertex
     * @param v the given vertex need to be found its root
     * @return the root of the given vertex
     */
    public Vertex find(Vertex v) {

        // if the given vertex is its own root, return the vertex
        if(v.getRoot().equals(v)){
            return v;
        }

        // loop on the path of given root, find the sentinel node
        Vertex curr = v.getRoot();
        while(!curr.getRoot().equals(curr)){
            curr = curr.getRoot();
        }

        return curr;
    }


    /**
     * union the v1's set and v2's set by changing their root
     * @param v1 the given vertex one
     * @param v2 the given vertex two
     */
    public void union(Vertex v1, Vertex v2) {

        // find the two vertices' path root
        Vertex r1 = find(v1);
        Vertex r2 = find(v2);

        // if the two roots are equal, just return
        if(r1.equals(r2)){
            return;
        } else if(r1.getSize()>r2.getSize()){
            // if the r1 path size is greater than r2
            // just change the vertex 2's root to vertex 1
            r2.setRoot(r1);
            // increment the size of r1 set
            r1.setSize(r2.getSize());
        } else {
            r1.setRoot(r2);
            r2.setSize(r1.getSize());
        }
        
    }
}
