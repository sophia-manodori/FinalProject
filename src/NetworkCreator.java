import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;

public class NetworkCreator {
    MutableGraph<String[]> network =
    GraphBuilder.undirected().build();

    public void createNetwork(String filename) {
        int n = ReadFile.fileLength(filename);
        String[][] data = ReadFile.read(filename, n);

        for(int i = 0; i<n; i++) {
            this.network.addNode(data[0]);

        }
    }
}
