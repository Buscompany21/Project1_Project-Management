import java.io.Console;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files


public class App {
    public static void main(String[] args) {
        try {
            Console console = System.console();
            String numTeams = console.readLine("How many teams would you like to form? ");
            File myObj = new File("C:/Users/busco/OneDrive/Documents/MISM 1st Year/IS Project Management/Project1_Project-Management/Project1/src/students.txt");
            Scanner myReader = new Scanner(myObj);
            List<String> listStudents = new ArrayList<>();
            while (myReader.hasNextLine()) {
                listStudents.add(myReader.nextLine());
            }
            myReader.close();
            
            int numStudents = listStudents.size();
            Collections.shuffle(listStudents);

            // System.out.println(listStudents);
            // System.out.println(numTeams);
            // System.out.println(numStudents);
            
            int numStudentsPerTeam = numStudents / Integer.parseInt(numTeams);

            int startGroup = 0;
            int endGroup = numStudentsPerTeam;
            int groupNum = 1;

            //Creates List of Groups
            ArrayList<ArrayList<String>> listOfGroups = new ArrayList<ArrayList<String>>();


            for (int i = 0; i < numStudents; i+=numStudentsPerTeam) {
                //Creates a List of students in a group
                ArrayList<String> group = new ArrayList<String>(listStudents.subList(startGroup, endGroup));

                //Adds the list of students to the list of groups
                listOfGroups.add(group);

                System.out.println("Group " + groupNum + ": " + group);
                startGroup = endGroup;
                endGroup += numStudentsPerTeam;
                groupNum++;
            }

            System.out.println("\n           List of Groups          ");
            System.out.println("-------------------------------------");
            System.out.println(listOfGroups);

            
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
