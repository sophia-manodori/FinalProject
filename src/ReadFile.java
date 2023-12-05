import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {

    public static int fileLength (String filename) {
        Scanner file = null;
        try {
          file = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
          System.err.println("Cannot locate file.");
          System.exit(-1);
        }
        int i = 0;
        while(file.hasNextLine()) {
        i+=1;
        file.nextLine();
        }
        file.close();
        return i;
      }

    public static String[][] read(String filename, int n) {
        Scanner file = null;
        try {
            file = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            System.err.println("Cannot locate file.");
            System.exit(-1);
        }
        //ArrayList<ArrayList<String>> people = new ArrayList<>();
        String[][] people = new String[n][];

        for(int i = 0; i<n; i++) {
            String line = file.nextLine();
            String[] fields = line.split("[,]+");
            people[i] = fields; 
        }
        return people;

    } 

    public static void main(String[] args) {
        int n = fileLength("Test.csv");
        String[][] data = read("Test.csv", n);
        System.out.println(data[0][8]);
    }
}
