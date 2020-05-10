/*
 * Name: Yunlin Tang
 * PID: a14664383
 */
import java.util.*;

/**
 * the graph class which helps display.java to process algorithm
 *
 * @author Yunlin Tang
 * @since ${12.8}
 */
public class Graph {

    // create all useful structure
    private HashMap<String, Vertex> vertices;
    private ArrayList<Edge> allUndirectedEdges;
    private ArrayList<Edge> resultMST;
    private boolean edgesGiven;

    private final static int SQUARE = 2; // square of the number

    /**
     * Constructor for Graph
     * @param edgesGiven indicates if the user will provide self-defined edges
     */
    public Graph(boolean edgesGiven) {
        // initialize all the useful related variables and structure
        this.edgesGiven = edgesGiven;
        vertices = new HashMap<>();
        allUndirectedEdges = new ArrayList<>();
        resultMST = new ArrayList<>();
    }

    /**
     * Adds a vertex to the graph. Throws IllegalArgumentException if given vertex
     * already exist in the graph.
     *
     * @param v vertex to be added to the graph
     * @throws IllegalArgumentException if two vertices with the same name are added.
     */
    public void addVertex(Vertex v) throws IllegalArgumentException {
        // if the graph already contains the vertex, throw exception
        if(vertices.containsValue(v)){
            throw new IllegalArgumentException();
        }

        // get the key of the vertex
        String name = v.getName();
        // add the vertex to the graph
        vertices.put(name, v);

    }

    /**
     * Gets a collection of all the vertices in the graph
     *
     * @return collection of all the vertices in the graph
     */
    public Collection<Vertex> getVertices() {

        return vertices.values();
    }

    /**
     * Adds a directed edge from vertex u to vertex v,
     * Throws IllegalArgumentException if one of
     * the vertex does not exist. If edgesGiven is false, directly return at first.
     *
     * @param nameU name of vertex u
     * @param nameV name of vertex v
     * @param weight weight of the edge between vertex u and v
     * @throws IllegalArgumentException if one of the vertex does not exist
     */
    public void addEdge(String nameU, String nameV, Double weight) throws IllegalArgumentException {
        // if edgesGiven is false, just return
        if(!edgesGiven){
            System.out.println("should not add edges!");
            return;
        }

        // if the graph does not contain the given vertex, throw exception
        if(!vertices.containsKey(nameU) || !vertices.containsKey(nameV)){
            throw new IllegalArgumentException();
        }

        // get the two vertices
        Vertex u = vertices.get(nameU);
        Vertex v = vertices.get(nameV);
        // create new edge with specific weight
        Edge e = new Edge(u,v,weight);
        // add edge to the source vertex
        u.addEdge(e);
    }

    /**
     * Adds an undirected edge between vertex u and vertex v by adding a directed
     * edge from u to v, then a directed edge from v to u. Then updates the allUndirectedEdges.
     * If edgesGiven is false, directly return at first.
     *
     * @param nameU name of vertex u
     * @param nameV name of vertex v
     * @param weight  weight of the edge between vertex u and v
     */
    public void addUndirectedEdge(String nameU, String nameV, double weight) {

        if(!edgesGiven){
            System.out.println("Should not add undirected edge!");
            return;
        }

        // add two edges between the two vertices
        addEdge(nameU,nameV,weight);
        addEdge(nameV,nameU,weight);
        // get the keys of two vertices
        Vertex u = vertices.get(nameU);
        Vertex v = vertices.get(nameV);
        // add one undirected edge between two vertices
        allUndirectedEdges.add(new Edge(u, v, weight));

    }

    /**
     * Calculates the euclidean distance for all edges in the graph and all edges in 
     * allUndirectedEdges. If edgesGiven is false, directly return at first.
     */
    public void computeAllEuclideanDistances() {

        if(!edgesGiven){
            System.out.println("cannot process right now!");
            return;
        }

        // set the distance for all edges in the graph
        Collection<Vertex> allV = vertices.values();
        for(Vertex v: allV){
            for(Edge e: v.adjacentEdges){
                e.setDistance(e.getSource().getDistanceTo(e.getTarget()));
            }

        }

        // set the distance for all undirected edges
        for(Edge e: allUndirectedEdges){
            e.setDistance(e.getSource().getDistanceTo(e.getTarget()));
        }

    }

    /**
     * Populate all possible edges from all vertices in the graph. Only works
     * when edgesGiven
     * is false. If edgesGiven is true, directly return at first.
     */
    public void populateAllEdges() {

        if(edgesGiven){
            System.out.println("should not populate right now!");
            return;
        }

        // get all the vertices from the graph
        Collection<Vertex> vCollection = getVertices();
        Vertex[] vertices = vCollection.toArray(new Vertex[vCollection.size()]);

        // add n(n-1)/2 undirected edges to the list
        for(int i = 0; i < vertices.length-1; i++){
            for(int j = i+1; j < vertices.length; j++){
                // calculate the distance as the new edge weight
                double dist = Math.sqrt(
                        Math.pow(vertices[i].getX()-vertices[j].getX(),SQUARE)+
                        Math.pow(vertices[i].getY()-vertices[j].getY(),SQUARE));
                allUndirectedEdges.add(new Edge(vertices[i], vertices[j],dist));
            }
        }
        
    }

    /**
     * using Kruskal's algorithm to compute the result MST
     */
    public ArrayList<Edge> runKruskalsAlg() {
        // if resultMST is already computed, return the resultMST at first

        if(resultMST.equals(null)){
            return resultMST;
        }

        // sort the list by the edge's weight
        Collections.sort(allUndirectedEdges,
                Comparator.comparingDouble(e -> e.getDistance()));

        DisjointSet set = new DisjointSet();

        // loop on the list
        for(Edge e: allUndirectedEdges){
            // if the edge is not in the set(not creating a circle)
            // add the edge to the result list
            if(!set.find(e.getTarget()).equals(set.find(e.getSource()))) {
                // union two vertices
                set.union(e.getSource(), e.getTarget());
                resultMST.add(e);
                // if the size of result list contains enough vertices
                // return the result list
                if (resultMST.size() == getVertices().size()) {
                    return resultMST;
                }
            }
        }
        return resultMST;
    }
}
