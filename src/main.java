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

        boolean play = true; 
        while(true) {
            System.out.println("intro");
            String response = input.nextLine();
            if(response.equals("find mutual hobbie")) {
                System.out.println("what hobbie?");
                String hobbie = input.nextLine();
                ArrayList<String> list = test.findHobbie(hobbie);
                System.out.println("The people with the hobbie" + hobbie + " are: " + list.toString());
            }
            
        }
    }
}
