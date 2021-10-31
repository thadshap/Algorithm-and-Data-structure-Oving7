public class Edge {
    Vertex from, to;
    int capacity, flow;
    Edge reverse;

    public Edge(Vertex from, Vertex to, int flow, int capacity){
        this.to = to;
        this.from = from;
        this.capacity = capacity;
        this.flow = flow;
    }
    
    public void setReverse(Edge e){
        reverse = e;
    }
}
