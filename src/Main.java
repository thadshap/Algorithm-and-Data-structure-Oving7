import java.io.BufferedReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ReadFile file = new ReadFile("http://www.iie.ntnu.no/fag/_alg/v-graf/flytgraf1");
        BufferedReader buffer = file.reader();
        Graph graph = new Graph(buffer);
        Vertex<Integer> start = graph.getVertex(0);
        Vertex<Integer> end = graph.getVertex(7);
        System.out.println("Økning : Flytøkende vei");
        System.out.println("Maksimal flyt ble " + graph.edmondKarpAlgorithm(start, end));
    }
}
