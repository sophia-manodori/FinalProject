import java.util.ArrayList;
import java.util.HashSet;
import java.awt.Color;

public class Demo {
    public static void main(String[] args) {
            //this reads and creates the network 
            SocialNetwork smith = new SocialNetwork("Test.csv");
            
            //displays the graph and highlights the nodes with a given major!
            GraphDisplay d3 = new GraphDisplay(smith.network);

            ArrayList<String> majors = smith.highlightMajor("computer science");
            for(int i = 0; i< majors.size(); i++) {
                d3.setColor(majors.get(i), Color.RED);
            }
            
            //You can find mutual friends 
            HashSet<String> friends= (HashSet<String>)smith.findMutualFriends("lucia qin","lily smetzer", 1);
            System.out.println(friends.toString());

            //you can find people with the same hobbie, favorite book, movie or tv show
            HashSet<String> muthob = smith.findMutualHobbie("sophia manodori", "reading", 3);
            System.out.println(muthob);

            HashSet<String> mutbook = smith.findMutualBook("sophia manodori", "good omens", 3);
            System.out.println(mutbook);

            HashSet<String> mutmovie = smith.findMutualMovie("sophia manodori", "cocaine bear", 3);
            System.out.println(mutmovie);

            HashSet<String> muttv = smith.findMutualTVSeries("sophia manodori", "ted lasso", 3);
            System.out.println(muttv);

            //you can find all people who have a hobbie, book, tv or movie
            ArrayList<String> hobbies = smith.findHobbie("reading");

            ArrayList<String> peoplemovies= smith.findMovie("pride and prejudice");

            ArrayList<String> peoplebooks= smith.findBook("good omens");


            ArrayList<String> peopletv= smith.findTv("ted lasso");


            //you can find book,tv show and movie recommendations 
            ArrayList<String> books = smith.bookRecommender("but i’m a cheerleader", "avatar the last air bender");
            System.out.println(books.toString());

            ArrayList<String> movies = smith.movieRecommender("ted lasso", "to kill a mockingbird");
            System.out.println(movies.toString());

            ArrayList<String> tvshows = smith.tvRecommender("the grand budapest hotel", "all quiet on the western front");
            System.out.println(tvshows.toString());

            //display just your friend group
            smith.subFriends("hala maloul");


    }  
}
