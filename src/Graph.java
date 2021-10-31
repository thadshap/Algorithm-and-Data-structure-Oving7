import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Graph {
    int N, K; // N = vertex, K = edge
    Vertex<Integer>[] vertices;

    public Graph(BufferedReader reader) throws IOException {
        createGraph(reader);
    }

    /**
     * 
     * @param value
     * @return
     */
    public Vertex<Integer> getVertex(int value) {
        for (Vertex<Integer> v : vertices){
            if (v.data == value) return v;
        }
        return null;
    }

    private void createGraph(BufferedReader br) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        vertices = new Vertex[N];
        for (int i = 0; i<N; i++){
            vertices[i]=new Vertex<Integer>();
            vertices[i].data = i;
        }
        K = Integer.parseInt(st.nextToken());
        for (int i = 0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int capacity = Integer.parseInt(st.nextToken());
            Edge e = new Edge(vertices[from], vertices[to],0, capacity);
            Edge eReverse = new Edge(vertices[to], vertices[from],0,0);
            e.setReverse(eReverse);
            eReverse.setReverse(e);
            vertices[from].add(e);
            vertices[to].add(eReverse);
        }
    }

    public boolean bfs(Vertex<Integer> s, Vertex<Integer> t){
        for (Vertex<Integer> vertex : vertices) {
            vertex.setIsVisited(false);
        }

        LinkedList<Vertex<Integer>> queue = new LinkedList<>();
        queue.add(s);

        while (!queue.isEmpty()){
            Vertex<Integer> u = queue.poll();
            for (Edge e : u.getEdges()){
                Vertex<Integer> v = e.to;
                if (!v.isVisited() && e.capacity > e.flow && e.capacity!=e.flow) {
                    v.setParentEdge(e);
                    if(v == t){
                        return true;
                    }
                    queue.add(v);
                    v.setIsVisited(true);
                }
            }
        }
        return false;
    }

    public int edmondKarpAlgorithm(Vertex<Integer> s, Vertex<Integer> t) {
        int max_flow = 0;

        while (bfs(s, t)) {
            int path_flow = Integer.MAX_VALUE;
            for (Vertex<Integer> v = t; v != s; v = v.getParentEdge().from) {
                path_flow = Math.min(path_flow, v.getParentEdge().capacity-v.getParentEdge().flow);
            }

            for (Vertex<Integer> v = t; v != s; v = v.getParentEdge().from) {
                Edge e = v.getParentEdge();

                e.flow += path_flow;
                e.reverse.capacity = e.capacity;
                e.reverse.flow += (e.capacity-path_flow);

            }
            max_flow += path_flow;

            printGraph(path_flow,s,t);
        }
        return max_flow;
    }

    public void printGraph(int path_flow, Vertex<Integer> s, Vertex<Integer> t) {
        Stack<Vertex<Integer>> path = new Stack<>();
        Vertex<Integer> v = t;
        for ( ;v != s; v = v.getParentEdge().from) {
            path.push(v);
        }
        path.push(v);

        System.out.printf("%6s",path_flow);
        System.out.printf("%3s", "");
        while (!path.isEmpty()){
            Vertex<Integer> vertex = path.pop();
            System.out.print(vertex.data + " ");
        }
        System.out.println();
    }
}

