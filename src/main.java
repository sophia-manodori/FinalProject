import java.util.Scanner;

import javax.sound.sampled.SourceDataLine;


import javax.swing.plaf.synth.SynthToolTipUI;

import java.util.ArrayList;

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
    public static void main(String[] args) {
        System.out.println("");
        System.out.println("Welcome to the Interactive Smith Social Network !");
        System.out.println("This network allows you to find friends at Smith College, blah blah blah");
            // I am not sure what to say hehehehe, should we like make it more fun or what
        SocialNetwork socialNetwork = new SocialNetwork("Test.csv");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("");
            System.out.println("Options:");
            System.out.println("1. Display all nodes in the network");
            System.out.println("2. Find mutual friends between two people");
            System.out.println("3. Find people with a specific hobby");
            System.out.println("4. Find mutual hobbies for a person");
            System.out.println("5. Find book recommendations");
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
                    String name1 = scanner.nextLine();
                    System.out.print("Enter the second person's name: ");
                    String name2 = scanner.nextLine();
                    System.out.print("Enter the degree: ");
                    int degree = scanner.nextInt();
                    socialNetwork.findMutualFriends(name1, name2, degree);

                    break;

                case 3:
                    System.out.print("Enter the hobby to search for: ");
                    String hobbie = scanner.nextLine();
                    socialNetwork.findHobbie(hobbie);
                   
                    break;

                case 4:
                    System.out.print("Enter the person's name: ");
                    String person = scanner.nextLine();
                    System.out.print("Enter the degree: ");
                    degree = scanner.nextInt();
                    socialNetwork.findMutualHobbie(person, socialNetwork.people.get(person)[6], degree);

                    break;
                
                case 5: 
                    System.out.println("Enter your favorite movie:");
                    String movie = scanner.nextLine();
                    System.out.println("Enter your favorite tv show");
                    String show = scanner.nextLine();
                    ArrayList<String> recs = socialNetwork.bookRecommender(movie, show);
                    System.out.println("Your recommended books are: " + recs.toString());

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

