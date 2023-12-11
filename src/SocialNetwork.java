import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.awt.Color;
import java.util.Set;
import java.util.HashSet;

/** Social Network Class */
public class SocialNetwork {
    public MutableGraph<String> network;
    /** graph */
    public HashMap<String, String[]> people = new HashMap<>();

    /** hashmap with people and location in data */

    /** Constructor */
    public SocialNetwork(String filename) {
        Scanner file = null;
        try {
            file = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            System.err.println("Cannot locate file.");
            System.exit(-1);
        }
        // ArrayList<ArrayList<String>> people = new ArrayList<>();
        this.people = new HashMap<>();
        this.network = GraphBuilder.undirected().build();

        while (file.hasNextLine()) {
            String line = file.nextLine().toLowerCase();
            String[] fields = line.split("[,]+");
            //System.out.println(fields[0]);
            people.put(fields[0], fields);

            for (int i = 0; i < 8; i++) {
                // if()
                if (!(fields[8 + i].equals("na"))) {
                    network.putEdge(fields[0], fields[8 + i]);
                }
            }
        }
    }

    public int numberFriends(String name) {
        Set<String> nodes = network.adjacentNodes(name);
        return nodes.size();
    }

    /**
     * temporarily adds person for sake of interaction, won't add them to the data
     * though so people won't be able to add them ...
     */
    public void addPerson(String[] person) {
        this.people.put(person[0], person);
        this.network.addNode(person[0]);
        for (int i = 0; i < 8; i++) {
            if (!(person[8 + i].equals("na"))) {
                this.network.putEdge(person[0], person[i + 8]);
            }
        }
    }
     /** takes a tv show and book and returns the favorite movies of the people who had that favorite tv show and book  */
    public ArrayList<String> movieRecommender(String tvShow, String book) {
        ArrayList<String> recs = new ArrayList<>();
        for(String node : this.people.keySet()) {
            if(this.people.get(node)[3].equals(tvShow) || this.people.get(node)[5].equals(book)) {
                recs.add(people.get(node)[4]);
            }
        } 
        return recs;
    }

    public ArrayList<String> bookRecommender(String movie, String tvshow) {
        ArrayList<String> recs = new ArrayList<>();
        for(String node : this.people.keySet()) {
            if(this.people.get(node)[3].equals(tvshow) || this.people.get(node)[4].equals(movie)) {
                recs.add(people.get(node)[5]);
            }
        } 
        return recs;
    }

    public ArrayList<String> tvRecommender(String movie, String book) {
        ArrayList<String> recs = new ArrayList<>();
        for(String node : this.people.keySet()) {
            if(this.people.get(node)[5].equals(book) || this.people.get(node)[4].equals(movie)) {
                recs.add(people.get(node)[3]);
            }
        } 
        return recs;
    }

    /**
     * returns arraylist of people with a certain major to then highlight in
     * displayed network
     */
    public ArrayList<String> highlightMajor(String major) {
        ArrayList<String> names = new ArrayList<>();
        for (String node : network.nodes()) {
            if (people.containsKey(node) && people.get(node)[2].equals(major)) {
                names.add(node);
            }
        }
        return names;
    }

    // use nodes
    public void findFriends(String name, int degree) {

    }

    public void findMutualMovie(String name1, String name2, int degree) {

    }

    public ArrayList<String> findMutualHobbie(String name, String yourHobbie, int degree) {
        ArrayList<String> names = new ArrayList<>();
        if (!network.nodes().contains(name.toLowerCase())) {
            throw new RuntimeException("Name '" + name + "' does not exist in the network.");
        }
        //String yourHobbie = people.get(name)[6];

        for (String node : network.successors(name)) {
            if (people.containsKey(node)) {
                String hobbie = people.get(node)[6];
                System.out.println("hobbie:" + hobbie + " of " + node);
                if (hobbie.equals(yourHobbie)) {
                    names.add(node);
                }
                if (degree > 1) {
                    ArrayList<String> list = findMutualHobbie(node, yourHobbie, degree - 1);
                    names.addAll(list);
                }
            }
        }
        return names;
    }

    public ArrayList<String> findHobbie(String hobbie) {
        ArrayList<String> names = new ArrayList<>();
        for (String node : network.nodes()) {
            if (people.containsKey(node)) {
                if (people.get(node)[6].equals(hobbie)) {
                    names.add(node);
                }
            }
        }

        System.out.println(" People who like " + hobbie + " are: " + names);
        return names;
    }

    public void findMutualTVSeries(String name, int degree) {

    }

    public void findMutualBook(String name, int degree) {

    }

    public Set<String> findMutualFriends(String name1, String name2, int degree) {

        // checks that the degree is more than 0
        if (degree <= 0) {
            System.out.println("The degree should be greater than 0.");
        }
        // check if the names exist in the network
        if (!network.nodes().contains(name1.toLowerCase()) || !network.nodes().contains(name2.toLowerCase())) {

            if (!network.nodes().contains(name1.toLowerCase())) {
                System.out.println("Name '" + name1 + "' does not exist in the network.");
            }

            if (!network.nodes().contains(name2.toLowerCase())) {
                System.out.println("Name '" + name2 + "' does not exist in the network.");
            }
            return null;
        } else {
//this needs to be a set
            Set<String> mutualFriends = new HashSet<>();
            //Set<String> traversed = new HashSet<>();
            // I think this is now finding everyone who has the name2 as a friend
            for (String node : network.successors(name1)) {
                for (String friend : network.successors(node)) {
                    //System.out.println(friend);
                    if (friend.equals(name2) && !friend.equals(name1)) {
                        mutualFriends.add(node);
                    }
                }
                if(degree>1) {
                        Set<String> friends = findMutualFriends(node, name2, degree-1);
                        System.out.println(friends.toString());
                        mutualFriends.addAll(friends);
                    }

            }
            System.out.println(
                    "Mutual friends of degree " + degree + " for " + name1 + " and " + name2 + ": " + mutualFriends);
            return mutualFriends;
        }
    }

    public static void main(String[] args) {
         SocialNetwork test = new SocialNetwork("Test.csv");
        // for(String node : test.network.nodes()) {
        // System.out.println(node);
        // }
            ArrayList<String> hobbies = test.findMutualHobbie("karen bekhazj", "reading", 4);
            System.out.println(hobbies.toString());

        // System.out.println(test.network);

        //GraphDisplay d3 = new GraphDisplay(test.network);

        // ArrayList<String> majors = test.highlightMajor("computer science");
        // for(int i = 0; i< majors.size(); i++) {
        // System.out.println(majors.get(i));
        // d3.setColor(majors.get(i), Color.RED);
        // }
        // System.out.println(test.network.successors("lucia qin").toString());
        // System.out.println(test.network.successors("lily smetzer").toString());
        // //test finding mutual friends method
        //test.findMutualFriends("lucia qin","lily smetzer", 1);
        //test.findMutualFriends("lucia qin","lily smetzer", 2);
        test.findMutualFriends("lucia qin","hala maloul", 1);
            test.findMutualFriends("lucia qin","hala maloul", 4);
        ArrayList<String> books = test.bookRecommender("but i’m a cheerleader", "avatar the last air bender");
        System.out.println(books.toString());


        // test.findHobbie("reading");

        // program
    }

}
