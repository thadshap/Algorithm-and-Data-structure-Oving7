import java.util.ArrayList;

public class Vertex<I> {
    private ArrayList<Edge> edges;
    I data;
    int distance;
    private boolean isDone, isVisited;
    Edge parentEdge;

    public Vertex() {
        this.edges = new ArrayList<>();
        this.distance = Integer.MAX_VALUE;
        isDone = false;
        isVisited = false;
    }

    public Edge getParentEdge() {
        return parentEdge;
    }

    public void setParentEdge(Edge parentEdge) {
        this.parentEdge = parentEdge;
    }

    public boolean isVisited(){return isVisited;}

    public void setIsVisited(boolean visited){isVisited = visited;}

    public void add (Edge edge){
        edges.add(edge);
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public String toString(){
        return data.toString();
    }
}
