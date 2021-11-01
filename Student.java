
import java.util.Scanner;
public class Student {
    Scanner input = new Scanner(System.in);
    String[] nameIndex = new String[100];
    int[] assignmentsAssigned = new int[100];
    int[] attendanceAssigned = new int[100];
    float[][] gradePercent = new float[100][100];
    String[][] assignmentName = new String[100][100];
    String[][] absenceData = new String[100][100];
    boolean[] assignmentAdded = new boolean[100];
    boolean[] attendanceAdded = new boolean[100];

    int currentStudent = 0;
    public Student(){
        for(int count = 0; count < 100; count++){
        assignmentsAssigned[count] = 0;
        attendanceAssigned[count] = 0;
        for(int count2 = 0; count2 < 100; count2++){
            assignmentName[count][count2] = ("");
            gradePercent[count][count2] = 0;
            absenceData[count][count2] = ("");
        }
        }
    }
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
                UpdateMenu();
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
        System.out.println("Enter student name (NO SPACES, USE UNDERSCORES):");
        nameIndex[currentStudent] = input.next();
        System.out.println("Student " + nameIndex[currentStudent] + " added!");
        currentStudent++;
    }

    public void UpdateMenu(){
        int student = StudentSelect();
        int choice = 0;
        do{
            System.out.println("Choose data set to edit:\n1) Assignments / Grades\n2) Absences\n(NOTE: data sets can only be added to or edited. They CANNOT be deleted.)");
            choice = input.nextInt();
            switch(choice){
                case 1:
                UpdateAssignments(student);
                break;
                case 2:
                UpdateAbsences(student);
                break;
                default:
                System.out.println("That is not a proper selection. Please try again.");
                break;
            }
        }while(choice < 1 && choice > 2);

    }

    public void UpdateAbsences(int student){
        boolean change = false;
        boolean dayNoHitch = false;
        int month, day, year, choice = 0;
        String status;
        while(change == false){
            System.out.println("Choose an attendance entry to edit:");
            for(int updateAbsCount = 0; updateAbsCount < 100; updateAbsCount++){
                if(absenceData[student][updateAbsCount].length() > 0)
                System.out.println(updateAbsCount + ") " + absenceData[student][updateAbsCount]);
            }
            System.out.println("101) NEW ENTRY");
            choice = input.nextInt();
            if(choice == 101){
                do{
                System.out.println("Enter month:");
                month = input.nextInt();
                if(month < 1 || month > 12)
                System.out.println("That is not a valid month. Please try again.");
                }while(month < 1 && month > 12);
                do{
                    System.out.println("Enter year:");
                    year = input.nextInt();
                    if(year < 0)
                    System.out.println("That is not a valid year. Please try again.");
                }while(year < 0);
                do{
                    System.out.print("Enter day:");
                    day = input.nextInt();
                    switch(month){
                        case 1:
                        case 3:
                        case 5:
                        case 7:
                        case 8:
                        case 10:
                        case 12:
                        if(day > 0 && day >= 31)
                        dayNoHitch = true;
                        else
                        System.out.println("That is not a valid day for this month. Please try again.");
                        break;
                        case 4:
                        case 6:
                        case 9:
                        case 11:
                        if(day > 0 && day >= 30)
                        dayNoHitch = true;
                        else 
                        System.out.println("That is not a valid day for this month. Please try again.");
                        break;
                        case 2:
                        if(year % 4 == 0){
                            if(day > 0 && day <= 29)
                            dayNoHitch = true;
                            else
                            System.out.println("That is not a valid day for this month. Please try again.");
                        }
                        else{
                            if(day > 0 && day <= 28)
                            dayNoHitch = true;
                            else
                            System.out.println("That is not a valid day for this month. Please try again.");
                        }
                        break;
                    }
                }while(dayNoHitch == false);
                System.out.println("Enter attendance status (one word, such as absent or present)");
                status = input.next();
                absenceData[student][attendanceAssigned[student]] = (month + "/" + day + "/" + year + ": " + status);
                attendanceAssigned[student]++;
                attendanceAdded[student] = true;
                change = true;
            }
            else if(assignmentName[student][choice].length() > 0){
                do{
                    System.out.println("Enter month:");
                    month = input.nextInt();
                    if(month < 1 || month > 12)
                    System.out.println("That is not a valid month. Please try again.");
                    }while(month < 1 && month > 12);
                    do{
                        System.out.println("Enter year:");
                        year = input.nextInt();
                        if(year < 0)
                        System.out.println("That is not a valid year. Please try again.");
                    }while(year < 0);
                    do{
                        System.out.print("Enter day:");
                        day = input.nextInt();
                        switch(month){
                            case 1:
                            case 3:
                            case 5:
                            case 7:
                            case 8:
                            case 10:
                            case 12:
                            if(day > 0 && day <= 31)
                            dayNoHitch = true;
                            else
                            System.out.println("That is not a valid day for this month. Please try again.");
                            break;
                            case 4:
                            case 6:
                            case 9:
                            case 11:
                            if(day > 0 && day <= 30)
                            dayNoHitch = true;
                            else 
                            System.out.println("That is not a valid day for this month. Please try again.");
                            break;
                            case 2:
                            if(year % 4 == 0){
                                if(day > 0 && day <= 29)
                                dayNoHitch = true;
                                else
                                System.out.println("That is not a valid day for this month. Please try again.");
                            }
                            else{
                                if(day > 0 && day <= 28)
                                dayNoHitch = true;
                                else
                                System.out.println("That is not a valid day for this month. Please try again.");
                            }
                            break;
                        }
                    }while(dayNoHitch == false);
                    System.out.println("Enter attendance status:");
                    status = input.nextLine();
                    absenceData[student][choice] = (month + "/" + day + "/" + year + ": " + status);
                    change = true;
            }
            else
            System.out.println("That is not a valid answer. Please try again.");
        }   
        attendanceAdded[student] = true;

    }

    public void UpdateAssignments(int student){
        boolean change = false;
        int choice = 0;
        while(change == false){
            System.out.println("Choose an assignment to edit:");
            if(assignmentAdded[student] == true){
                for(int updateAssCount = 0; updateAssCount < 100; updateAssCount++){
                    if(assignmentName[student][updateAssCount].length() > 0)
                    System.out.println(updateAssCount + ") " + assignmentName[student][updateAssCount] + ": " + gradePercent[student][updateAssCount]);
                }
            }
            System.out.println("101) NEW ENTRY");
            choice = input.nextInt();
            if(choice == 101){
                System.out.println("Enter new assignment name (use underscores for spaces):");
                //assignmentName[student][assignmentsAssigned[student]] = " ";
                assignmentName[student][assignmentsAssigned[student]] = input.next();
                System.out.println("Enter assignment percent (enter from 0-100 (ex: 45.6 percent)");
                gradePercent[student][assignmentsAssigned[student]] = input.nextFloat();
                assignmentsAssigned[student]++;
                change = true;
            }
            else if(assignmentName[student][choice].length() > 0){
                System.out.println("Enter new assignment name:");
                assignmentName[student][assignmentsAssigned[student]] = input.nextLine();
                System.out.println("Enter assignment percent (ex: 45.6 percent)");
                gradePercent[student][assignmentsAssigned[student]] = input.nextFloat();
                change = true;
            }
            else
            System.out.println("That is not a valid answer. Please try again.");
        }   
        assignmentAdded[student] = true;
    }

    public int StudentSelect(){
        int StudentSelect = 0;
        do{
            System.out.println("Select Student:");
            for(int usCount = 0; usCount < currentStudent; usCount++){
                if (nameIndex[usCount].length() > 0)
                    System.out.println(usCount + ") " + nameIndex[usCount]);
            }
            StudentSelect = input.nextInt();
        }while(StudentSelect < 0 && StudentSelect > currentStudent);
        return StudentSelect;
    }

    public void List(){
        int listStudentSelect = StudentSelect();
        if(assignmentAdded[listStudentSelect] == true){
            System.out.println("Info on " + nameIndex[listStudentSelect] + ": \nAssignments:");
            //lol
            for(int assCount = 0; assCount < 100; assCount++){
                if(assignmentName[listStudentSelect][assCount].length() > 0)
                System.out.println(assignmentName[listStudentSelect][assCount] + ": " + gradePercent[listStudentSelect][assCount]);
            }
        }
        if(attendanceAdded[listStudentSelect] == true){
            System.out.println("Attendance (date format mm/dd/yyyy):");
            for(int absCount = 0; absCount < 100; absCount++){
                if(absenceData[listStudentSelect][absCount].length() > 0)
                System.out.println(absenceData[listStudentSelect][absCount]);
            }
        }
        else
        System.out.println("Some data not available for " + nameIndex[listStudentSelect]);
    }

}
