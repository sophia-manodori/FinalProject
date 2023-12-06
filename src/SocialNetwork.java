import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import java.util.HashMap;

/** Social Network Class */
public class SocialNetwork {
    public MutableGraph<String[]> network; /** graph */
    public HashMap<String, Integer> people = new HashMap<>(); /** hashmap with people and location in data */
    public String[][] data;
 
    /** Constructor */
    public SocialNetwork() {
        this.network = GraphBuilder.undirected().build();
    }
    
    /** */
    public void CreateNetwork(String filename) {
        int n = ReadFile.fileLength(filename);
        this.data = ReadFile.read(filename, n);

        for(int i = 0; i<n; i++) {
            this.network.addNode(data[i]);
            System.out.println(data[i][0]);
            people.put(data[i][0], i);
        }
        for(int i = 0; i<n-8; i++) {
            if(!(data[i][8].equals("NA")) && people.containsKey(data[i][8])) {
                this.network.putEdge(data[i], data[people.get(data[i][8])]);
            }
            if(!(data[i].equals("NA")) && people.containsKey(data[i][9])) {
                this.network.putEdge(data[i], data[people.get(data[i][9])]);
            }
            if(!(data[i][10].equals("NA")) && people.containsKey(data[i][10])) {
                this.network.putEdge(data[i], data[people.get(data[i][10])]);
            }
            if(!(data[i][10].equals("NA")) && people.containsKey(data[i][11])) {
                this.network.putEdge(data[i], data[people.get(data[i][11])]);
            }
        }
    }

    public void addPerson(String[] person) {
        this.people.put(person[0], people.size());
        this.network.addNode(person);
        if(!(person[8].equals("NA")) && people.containsKey(person[8])) {
                this.network.putEdge(person, data[people.get(person[8])]);
        }
        if(!(person[9].equals("NA")) && people.containsKey(person[9])) {
                this.network.putEdge(person, data[people.get(person[9])]);
        }
        if(!(person[10].equals("NA")) && people.containsKey(person[10])) {
                this.network.putEdge(person, data[people.get(person[9])]);
        }
    }

    public void findFriends(String name, int degree) {

    }

    public void findMutualMovie(String name, int degree) {

    }

     public void findMutualHobbie(String name, int degree) {

    }

     public void findMutualTVSeries(String name, int degree) {

    }

      public void findMutualBook(String name, int degree) {

    }

    public void findMutualFriends(String name1, String name2, int degree) {

    }

    public static void main(String[] args) {
        SocialNetwork test = new SocialNetwork();
        test.CreateNetwork("Test.csv");
        System.out.println(test.data[0][1]);
    }
}
