import java.util.Scanner;
import java.util.ArrayList;

public class main {
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
        Scanner input = new Scanner(System.in);
        
        SocialNetwork test = new SocialNetwork("Test.csv");
        //maybe check here if we want to highlight a major?
        boolean play = true; 
        while(true) {
            System.out.println("intro"); //this will be where we list the options 
            String response = input.nextLine();
            //below is the option of finding all people with the same hobbie
            if(response.equals("find mutual hobbie")) {
                System.out.println("what hobbie?");
                String hobbie = input.nextLine();
                ArrayList<String> list = test.findHobbie(hobbie);
                System.out.println("The people with the hobbie" + hobbie + " are: " + list.toString());
            } //other options will go here
            else {
                System.out.println("please enter a valid option");
            }
            //to quit the loop after an iteration of the interaction loop
            System.out.println("would you like to continue?");
            String quit = input.nextLine();
            if(!yesNoReader(quit)) {
                play = false;
            }
        }
    }
}
