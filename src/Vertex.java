/*
 * Name: Yunlin Tang
 * PID: a14664383
 */
import java.util.ArrayList;

/**
 * constructing a class which is the vertex represented in the graph.java
 *
 * @author Yunlin Tang
 * @since ${12.9}
 */
public class Vertex {

    private String name; // the name of this vertex
    private int x; // the x coordinates of this vertex on map
    private int y; // the y coordinates of this vertex on map

    private Vertex root; // the root of the path which this vertex belongs to
    private int size; // size of the path

    private static final int SQUARE = 2; // square of the number

    public ArrayList<Edge> adjacentEdges; // the adjacent edges of this vertex


    /**
     * the constructor of the Vertex class
     * @param name the key of the vertex
     * @param x x-coordinate of the vertex
     * @param y y-coordinate of the vertex
     */
    public Vertex(String name, int x, int y) {
        // initialize all the related and useful variables of the vertex object
        this.name = name;
        this.x = x;
        this.y = y;
        adjacentEdges = new ArrayList<>();
        root = this; // initialize the root of itself
        size = 1; // initialize the size of current path
    }

    /**
     * getter for the name of this vertex
     * @return the name
     */
    public String getName() {
        return name;
    }


    /**
     * getter for the x-coordinate of this vertex
     * @return x-coordinate
     */
    public int getX() {
        return x;
    }


    /**
     * getter for the y-coordinate of this vertex
     * @return y-coordinate
     */
    public int getY() {
        return y;
    }


    /**
     * setter for the root of this vertex
     * @param v the root of this vertex
     */
    public void setRoot(Vertex v){
        root = v;
    }


    /**
     * getter of the root for this vertex
     * @return the root
     */
    public Vertex getRoot(){
        return root;
    }


    /**
     * setter of the size for this vertex
     * @param s the increment amount
     */
    public void setSize(int s){
        size+=s;
    }


    /**
     * getter of the size for the vertex
     * @return size of this vertex
     */
    public int getSize(){
        return size;
    }


    /**
     * process the distance from this vertex to the given vertex
     * @param o the given vertex(target)
     * @return the distance
     */
    public double getDistanceTo(Vertex o) {
        double squareDis = Math.pow(x - o.getX(), SQUARE)
                + Math.pow(y - o.getY(), SQUARE);
        return Math.sqrt(squareDis);
    }

    /**
     * add edge to the adjacent edges list
     * @param edge the adjacent edges
     */
    public void addEdge(Edge edge) {
        adjacentEdges.add(edge);
    }


    /**
     * return the string representation of this current vertex
     * @return string representation
     */
    public String toString() {
        return name + " (" + x + ", " + y + ")";
    }

}