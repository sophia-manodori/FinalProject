import java.util.Scanner;

import javax.sound.sampled.SourceDataLine;


import javax.swing.plaf.synth.SynthToolTipUI;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
/** main class running user interaction */
public class Main {
    public static boolean yesNoReader (String input) {
        if(input.toLowerCase().equals("yes") || input.toLowerCase().equals("y")) {
            return true;
        } else if(input.toLowerCase().equals("no") || input.toLowerCase().equals("n")) {
            return false;
        } else {
            throw new RuntimeException("Unknown answer");
        }
    }
    /** runs user interaction */
    public static void main(String[] args) {
        System.out.println("");
        System.out.println("Welcome to the Interactive Smith Social Network !");
        System.out.println("This network allows you to find friends at Smith College, blah blah blah");
            // I am not sure what to say hehehehe, should we like make it more fun or what
        SocialNetwork socialNetwork = new SocialNetwork("Test.csv");

        //GraphDisplay d3 = new GraphDisplay(socialNetwork.network);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("");
            System.out.println("Options:");
            System.out.println("1. Display all nodes in the network");
            System.out.println("2. Find mutual friends between two people");
            System.out.println("3. Find people with a specific hobby");
            System.out.println("4. Find people with the same hobby");
            System.out.println("5. Find people with the same favorite book");
            System.out.println("6. Find people with the same favorite movie");
            System.out.println("7. Find people with the same favorite TV series");
            System.out.println("8. Find book recommendations");
            System.out.println("9. Find movie recommendations");
            System.out.println("10. Display the social network");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.println("Nodes in the network:");
                    for (String node : socialNetwork.network.nodes()) {
                        System.out.println(node);
                    }
                    break;

                case 2:
                    System.out.print("Enter the first person's name: ");
                    String name1 = scanner.nextLine().toLowerCase();
                    System.out.print("Enter the second person's name: ");
                    String name2 = scanner.nextLine().toLowerCase();
                    System.out.print("Enter the degree: ");
                    int degree = scanner.nextInt();
                    HashSet<String> mutuals = (HashSet<String>)socialNetwork.findMutualFriends(name1, name2, degree);
                     System.out.print("The mutual friends of " + name1 + "and "+ name2 + "are:" + mutuals.toString());

                    break;

                case 3:
                    System.out.print("Enter the hobby to search for: ");
                    String hobbie = scanner.nextLine().toLowerCase();
                    socialNetwork.findHobbie(hobbie);
                   
                    break;

                case 4:
                    System.out.print("What is your name: ");
                    String person = scanner.nextLine().toLowerCase();
                    System.out.println("What is your hobby?");
                    String hobby = scanner.nextLine().toLowerCase();
                    System.out.print("Enter the degree: ");
                    int hobbyDegree = scanner.nextInt();
                    HashSet<String> hobbys = socialNetwork.findMutualHobbie(person, hobby, hobbyDegree);
                    System.out.print("The people of degree " + hobbyDegree + " with the hobby "+hobby +"are:" + hobbys.toString());
                    break;
                case 5:
                     System.out.print("What is your name: ");
                    String name = scanner.nextLine().toLowerCase();
                    System.out.println("What is your favorite book ?");
                    String mutualBook = scanner.nextLine().toLowerCase();
                    System.out.print("Enter the degree: ");
                    degree = scanner.nextInt();
                    HashSet<String> booksPeople = socialNetwork.findMutualBook(name, mutualBook, degree);
                    System.out.print("The people of degree " + degree + " with "+mutualBook+ " as their favorite book are:" + booksPeople.toString());
                    break;


                case 6:   
                    System.out.print("What is your name: ");
                    String moviePerson = scanner.nextLine().toLowerCase();
                    System.out.println("What is your favorite movie?");
                    String mutualMovie = scanner.nextLine().toLowerCase();
                    System.out.print("Enter the degree: ");
                    degree = scanner.nextInt();
                    HashSet<String> moviesPeople = socialNetwork.findMutualBook(moviePerson, mutualMovie, degree);
                    System.out.print("The people of degree " + degree + " with "+mutualMovie+ " as their favorite book are:" + moviesPeople.toString());
                    break;


                case 7:

                    System.out.print("What is your name: ");
                    String SeriesPerson = scanner.nextLine().toLowerCase();
                    System.out.println("What is your favorite series?");
                    String mutualSeries = scanner.nextLine().toLowerCase();
                    System.out.print("Enter the degree: ");
                    degree = scanner.nextInt();
                    HashSet<String> SeriesPeople = socialNetwork.findMutualBook(SeriesPerson, mutualSeries, degree);
                    System.out.print("The people of degree " + degree + " with "+mutualSeries+ " as their favorite book are:" + SeriesPeople.toString());
                    break;


                case 8: 
                    System.out.println("Enter your favorite movie:");
                    String movie = scanner.nextLine().toLowerCase();
                    System.out.println("Enter your favorite tv show");
                    String show = scanner.nextLine().toLowerCase();
                    ArrayList<String> recs = socialNetwork.bookRecommender(movie, show);
                    System.out.println("Your recommended books are: " + recs.toString());
                    break;

                case 9: 
                    System.out.println("Enter your favorite book:");
                    String book = scanner.nextLine().toLowerCase();
                    System.out.println("Enter your favorite tv show");
                    String tvshow = scanner.nextLine().toLowerCase();
                    ArrayList<String> bookrecs = socialNetwork.movieRecommender(tvshow, book);
                    System.out.println("Your recommended books are: " + bookrecs.toString());
                    break;

                case 10: 
                    GraphDisplay d4 = new GraphDisplay(socialNetwork.network);
                    break;
                    
                case 0:
                    System.out.println("Exiting the program.");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            // Ask the user if they want to perform more operations
            System.out.print("Do you want to explore more? (yes/no): ");
            String continueChoice = scanner.next().toLowerCase();
            if (!continueChoice.equals("yes")) {
                System.out.println("Exiting the program.");
                System.exit(0);
            }
        }
        }
    }

