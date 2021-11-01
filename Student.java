
import java.util.Scanner;
public class Student {
    Scanner input = new Scanner(System.in);
    String[] nameIndex = new String[100];
    int[][] gradePercent = new int[100][100];
    String[][] assignmentName = new String[100][100];
    String[][] absenceData = new String[100][100];
    int currentStudent = 0;

    public void Menu(){
        boolean exit = false;
        int choice;
        while(exit == false){
            System.out.println("Welcome to the Student Update System (SUS)!\nSelect Option:\n1) Create Student\n2) Update Student\n3) List Student Data\n4) Exit");
            choice = input.nextInt();
            switch(choice){
                case 1:
                Create();
                break;
                case 2:
                Update();
                break;
                case 3:
                List();
                break;
                case 4:
                System.out.println("Thank you for using SUS. Have a good day.");
                exit = true;
                break;
                default:
                System.out.println("That is not a valid choice. Please try again.");
                break;
            }
        }
    }

    public void Create(){
        System.out.println("Enter student name:");
        nameIndex[currentStudent] = input.nextLine();
        System.out.println("Student " + nameIndex[currentStudent] + " added!");
        currentStudent++;
    }

    public void Update(){
        int student = StudentSelect();
        
    }

    public int StudentSelect(){
        int StudentSelect = 0;
        do{
            System.out.println("Select Student:");
            for(int usCount = 0; usCount < 100; usCount++){
                if (nameIndex[usCount].length() > 0)
                    System.out.println(usCount + ") " + nameIndex[usCount]);
            }
            StudentSelect = input.nextInt();
        }while(StudentSelect < 0 && StudentSelect > currentStudent);
        return StudentSelect;
    }

    public void List(){
        int listStudentSelect = StudentSelect();
        System.out.println("Info on " + nameIndex[listStudentSelect] + ": \nAssignments:");
        //lol
        for(int assCount = 0; assCount < 100; assCount++){
            if(assignmentName[listStudentSelect][assCount].length() > 0)
            System.out.println(assignmentName[listStudentSelect][assCount] + ": " + gradePercent[listStudentSelect][assCount]);
        }
        System.out.print("Absences:");
        for(int absCount = 0; absCount < 100; absCount++){
            if(absenceData[listStudentSelect][absCount].length() > 0)
            System.out.println(absenceData[listStudentSelect][absCount]);
        }
    }

}
