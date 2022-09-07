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

            //Sets console to system's console
            Console console = System.console();

            //Get input for # of teams formed
            String numTeams = console.readLine("\n\nHow many teams would you like to form? ");

            //Read txt file with list of students
            File myObj = new File("C:/Users/busco/OneDrive/Documents/MISM 1st Year/IS Project Management/Project1_Project-Management/Project1/src/students.txt");
            Scanner myReader = new Scanner(myObj);
            List<String> listStudents = new ArrayList<>();
            while (myReader.hasNextLine()) {
                listStudents.add(myReader.nextLine());
            }
            myReader.close();
            
            //Declares number of students and randomly shuffles list
            int numStudents = listStudents.size();
            Collections.shuffle(listStudents);

            //Figures out how many students should be on each team
            int numStudentsPerTeam = numStudents / Integer.parseInt(numTeams);
            int overflow = numStudents - (numStudentsPerTeam * Integer.parseInt(numTeams));
            System.out.println("\nNumber of students on each team: " + numStudentsPerTeam + "\nNumber of teams with an extra student:  "+ overflow);

            //Declares variables needed to find subgroups
            int startGroup = 0;
            int endGroup = numStudentsPerTeam;

            //Creates List of Groups
            ArrayList<ArrayList<String>> listOfGroups = new ArrayList<ArrayList<String>>();

            //Creates groups and adds them to list of groups
            for (int i = 0; i < numStudents; i+=numStudentsPerTeam) {
                //Logic to add the extra members to a groups
                if (i >= (numStudents - overflow)){
                    for (int n = 0; n < overflow; n++) {
                        listOfGroups.get(n).add(listStudents.get(i));
                    }
                    break;
                }                

                //Creates a List of students in a group
                ArrayList<String> group = new ArrayList<String>(listStudents.subList(startGroup, endGroup));

                //Adds the list of students to the list of groups
                listOfGroups.add(group);

                startGroup = endGroup;
                endGroup += numStudentsPerTeam;
            }

            //Outputs the list of groups
            System.out.println("\n           List of Groups          ");
            System.out.println("-------------------------------------");
            listOfGroups.forEach(group -> System.out.println("Group #" + (listOfGroups.indexOf(group) + 1) + " " + group));


            
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
