import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/** Social Network Class */
public class SocialNetwork {
    public MutableGraph<String> network; /** graph */
    public HashMap<String, String[]> people = new HashMap<>(); /** hashmap with people and location in data */
 
    /** Constructor */
    public SocialNetwork(String filename) {
        Scanner file = null;
        try {
            file = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            System.err.println("Cannot locate file.");
            System.exit(-1);
        }
        //ArrayList<ArrayList<String>> people = new ArrayList<>();
        this.people = new HashMap<>(); 
        this.network = GraphBuilder.undirected().build();

        while(file.hasNextLine()) {
            String line = file.nextLine().toLowerCase();
            String[] fields = line.split("[,]+");
            System.out.println(fields[0]);
            people.put(fields[0], fields);
            for(int i = 0; i<4; i++) {
                //if()
                network.putEdge(fields[0], fields[8+i]);
            }
        }
    }
    
    /** temporarily adds person for sake of interaction, won't add them to the data though so people won't be able to add them ...  */
    public void addPerson(String[] person) {
        this.people.put(person[0], person);
        this.network.addNode(person[0]);
        for(int i = 0; i<4; i++) {
            if(!(person[8+i].equals("na"))) {
                    this.network.putEdge(person[0], person[i+8]);
            }
        }
    }

    //use nodes
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
        SocialNetwork test = new SocialNetwork("Test.csv");
        for(String node : test.network.nodes()) {
            System.out.println(node);
        }

        System.out.println(test.network);

        GraphDisplay d3 = new GraphDisplay(test.network);
    
    }
}
