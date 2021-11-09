/*TO DO:
- add a case for no students added and other methods being called
- add a deletion functionality
- add schedules
- comment the attendance code - DONE!!!
- take a break (joke!!!!!!!!!!!!!!!!!!!!!)
*/
//import scanner
import java.util.Scanner;
public class Student {
    //initialize scanner
    Scanner input = new Scanner(System.in);
    //yeah, it's a lot of arrays, but i swear they all do something. they're all capable of holding 100 students, because for our purposes that's more than enough. this can be easily scaled, but there's no need to right now.
    //this one holds the names of every student.
    String[] nameIndex = new String[100];
    String[][] schedule = new String[100][4];
    //this one holds the number of assignments assigned to every student (utilizing parallel array concepts. it's used a lot here)
    int[] assignmentsAssigned = new int[100];
    //this holds the number of attendance records assigned to every student. note that the way that these are implemented makes it so deleting isn't possible (unless we will it into existence later)
    int[] attendanceAssigned = new int[100];
    //this 2d array holds the grade percentages of every student. the 2d array logic is that each row is a student's data, and each column lines up with a specific assignment.
    float[][] gradePercent = new float[100][100];
    //this one follows the same logic, but holds the assignment names.
    String[][] assignmentName = new String[100][100];
    //again, same logic, holds all of the data on attendence/absences
    String[][] absenceData = new String[100][100];
    //these next two boolean arrays are to prevent errors later on in the code.
    boolean[] assignmentAdded = new boolean[100];
    boolean[] attendanceAdded = new boolean[100];

    //this integer is to keep track of where on the array we are for names.
    int currentStudent = 0;

    //constructor for when the class object is created.
    public Student(){
        //this whole constructor essentially assigns 0 or a zero-length string to all values for certain arrays. this is to ensure that null pointer exceptions do not occur when they are first called.
        for(int count = 0; count < 100; count++){
        assignmentsAssigned[count] = 0;
        attendanceAssigned[count] = 0;
        for(int count2 = 0; count2 < 100; count2++){
            assignmentName[count][count2] = ("");
            gradePercent[count][count2] = 0;
            absenceData[count][count2] = ("");
            if (count2 < 4){
            schedule[count][count2] = ("");
            }
        }
        }
    }

    public void Menu(){
        //initalize exit boolean
        boolean exit = false;
        //initialize selector variable
        int choice;
        //while loop to trap the user in the menu.
        while(exit == false){
            //print out options, take in input
            System.out.println("Welcome to the Student Update System (SUS)!\nSelect Option:\n1) Create Student\n2) Update Student\n3) List Student Data\n4) Exit");
            choice = input.nextInt();
            //use a switch to divert the user to their desired method.
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
                //this case gives a goodbye message and sets the exit boolean to true, then exits the loop.
                System.out.println("Thank you for using SUS. Have a good day.");
                exit = true;
                break;
                default:
                System.out.println("That is not a valid choice. Please try again.");
                break;
            }
        }
    }

    //this method creates a new student.
    public void Create(){
        System.out.println("Enter student name:");
        input.nextLine();
        nameIndex[currentStudent] = input.nextLine();
        System.out.println("Student " + nameIndex[currentStudent] + " added!");
        //adds 1 to the current student integer, which moves the next student input into the next available spot.
        currentStudent++;
    }

    public void UpdateMenu(){
        //get the student in question from the student select method.
        int student = StudentSelect();
        //initialize choice variable
        int choice = 0;
        //do loop to trap the user into picking one option.
        do{
            //take input
            System.out.println("Choose data set to edit:\n1) Assignments / Grades\n2) Absences\n3) Schedule\n(NOTE: data sets can only be added to or edited. They CANNOT be deleted.)");
            choice = input.nextInt();
            //switch to divert the user into their desired edit. each method called pulls in the student number.
            switch(choice){
                case 1:
                UpdateAssignments(student);
                break;
                case 2:
                UpdateAbsences(student);
                break;
                case 3:
                UpdateSchedule(student);
                break;
                default:
                System.out.println("That is not a proper selection. Please try again.");
                break;
            }
        //while condition ensures that the user has to have a 1 or 2 before the loop is exited.
        }while(choice < 1 && choice > 2);

    }

    public void UpdateSchedule(int student){
        int choice = 0;
        boolean exit = false;
        while(exit == false){
            System.out.println("Choose a class to edit (select block #):");
            for(int schedC = 0; schedC < 4; schedC++){
                if(schedule[student][schedC].length() < 1)
                System.out.println("Block " + (schedC + 1) + ") EMPTY");
                else
                System.out.println("Block " + (schedC + 1) + ") " + schedule[student][schedC]);
            }
            choice = input.nextInt();
            input.nextLine();
            if(choice > 0 && choice < 5){
                System.out.println("Enter new class name:");
                schedule[student][choice - 1] = input.nextLine();
                System.out.println("Class " + schedule[student][choice - 1] + " added.");
                exit = true;
            }
            else
            System.out.println("That is not a valid selection. Please try again.");
        }
    }
    
    //this method updates a student's absences.
    public void UpdateAbsences(int student){
        //initialize exit boolean
        boolean change = false;
        //initialize day checker boolean
        boolean dayNoHitch = false;
        //initialize various ints
        int month, day, year, choice = 0;
        //string for listing the attendance status.
        String status;
        //while loop to trap the user
        while(change == false){
            //ask for input
            System.out.println("Choose an attendance entry to edit:");
            //print out attendance records if they exist
            for(int updateAbsCount = 0; updateAbsCount < 100; updateAbsCount++){
                if(absenceData[student][updateAbsCount].length() > 0)
                System.out.println(updateAbsCount + ") " + absenceData[student][updateAbsCount]);
            }
            System.out.println("101) NEW ENTRY");
            //take input
            choice = input.nextInt();
            //if case for a new entry
            if(choice == 101){
                do{
                //enters in month, checks if it's in [1,12]. if not, it restarts the loop.
                System.out.println("Enter month:");
                month = input.nextInt();
                if(month < 1 || month > 12)
                System.out.println("That is not a valid month. Please try again.");
                }while(month < 1 || month > 12);
                do{
                    //enters in year, checks if it's > 0. if not, it restarts the loop.
                    System.out.println("Enter year:");
                    year = input.nextInt();
                    if(year < 0)
                    System.out.println("That is not a valid year. Please try again.");
                }while(year < 0);
                do{
                    //loop that will check for the day entered. if it matches with the switch declarations, the exit boolean is true and the loop is cleared.
                    System.out.println("Enter day:");
                    day = input.nextInt();
                    switch(month){
                        //these cases are for the 31 day months.
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
                        //these cases are for the 30 day months.
                        case 4:
                        case 6:
                        case 9:
                        case 11:
                        if(day > 0 && day <= 30)
                        dayNoHitch = true;
                        else 
                        System.out.println("That is not a valid day for this month. Please try again.");
                        break;
                        //this case is for february. i hate february.
                        case 2:
                        //checks for leap year. if yes, then 29 day range; if no, 28 day range.
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
                //once the date loop is cleared, the user enters in a string for the attendance record.
                System.out.println("Enter attendance status:");
                input.nextLine();
                status = input.nextLine();
                int schedChoice = 0;
                while(schedChoice < 1 || schedChoice > 5){
                System.out.println("Enter relevant class: ");
                for(int runningOut = 0; runningOut < 4; runningOut++){
                    if(schedule[student][runningOut].length() < 1)
                        System.out.println("Block " + (runningOut + 1) + ") EMPTY");
                        else
                        System.out.println("Block " + (runningOut + 1) + ") " + schedule[student][runningOut]);
                }
                System.out.println("5) All day");
                schedChoice = input.nextInt();
                if(schedChoice < 1 || schedChoice > 5)
                System.out.println("That is not a valid selection. Please try again.");
                }
                //this is all assigned to a string in the attendance array.
                if(schedChoice == 5)
                absenceData[student][attendanceAssigned[student]] = (month + "/" + day + "/" + year + ": " + status);
                else
                absenceData[student][attendanceAssigned[student]] = (schedule[student][schedChoice - 1] + "- " +  month + "/" + day + "/" + year + ": " + status);
                //moves to the next available spot in the array
                attendanceAssigned[student]++;
                //indicates that there is attendance in for this student, which unlocks listing functionality for this student. NULL POINTERS!
                attendanceAdded[student] = true;
                //change exit boolean to true
                change = true;
            }
            //this entire block is for editing a preexisting attendance record. it is basically the same as the previous block. you can't make me comment the whole thing again.
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
                    input.nextLine();
                    status = input.nextLine();
                    int schedChoice2 = 0;
                    while(schedChoice2 < 1 || schedChoice2 > 5){
                    System.out.println("Enter relevant class: ");
                    for(int runningOut = 0; runningOut < 4; runningOut++){
                        if(schedule[student][runningOut].length() < 1)
                            System.out.println("Block " + (runningOut + 1) + ") EMPTY");
                            else
                            System.out.println("Block " + (runningOut + 1) + ") " + schedule[student][runningOut]);
                    }
                    System.out.println("5) All day");
                    schedChoice2 = input.nextInt();
                    if(schedChoice2 < 1 || schedChoice2 > 5)
                    System.out.println("That is not a valid selection. Please try again.");
                    }
                    //this is all assigned to a string in the attendance array.
                    if(schedChoice2 == 5)
                    absenceData[student][attendanceAssigned[student]] = (month + "/" + day + "/" + year + ": " + status);
                    else
                    absenceData[student][attendanceAssigned[student]] = (schedule[student][schedChoice2] + "- " +  month + "/" + day + "/" + year + ": " + status);
                    //the only difference is that it does not move to a new spot on the array, as an existing set was edited. nothing was added, so there's no need to move to a new spot.
                    change = true;
            }
            else
            System.out.println("That is not a valid answer. Please try again.");
        }   
        attendanceAdded[student] = true;

    }

    public void UpdateAssignments(int student){
        //initialize exit loop boolean
        boolean change = false;
        //initialize choice variable
        int choice = 0;
        //while loop to trap the user
        while(change == false){
            //outputs a list of all the possible items that can be edited
            System.out.println("Choose an assignment to edit:");
            //if an assignment has not been made yet, then this code section here (within this if) will not run. this is to prevent a null pointer error.
            if(assignmentAdded[student] == true){
                for(int updateAssCount = 0; updateAssCount < 100; updateAssCount++){
                    if(assignmentName[student][updateAssCount].length() > 0)
                    System.out.println(updateAssCount + ") " + assignmentName[student][updateAssCount] + ": " + gradePercent[student][updateAssCount]);
                }
            }
            //this will always print. it is for if the user wants to add a new entry.
            System.out.println("101) NEW ENTRY");
            choice = input.nextInt();
            //if ther user wants to make a new entry, they are directed here
            if(choice == 101){
                //take the assignment name in
                System.out.println("Enter new assignment name:");
                //the assignment here is decided by first getting the student for the row, then the current available array spot for that student, which is held in that assignmentsAssigned array.
                input.nextLine();
                assignmentName[student][assignmentsAssigned[student]] = input.nextLine();
                //take in a float for the percentage
                System.out.println("Enter assignment percent (enter from 0-100 (ex: 45.6 percent)");
                //assignment here is the exact same as the previous one, except that it is simply different arrays.
                gradePercent[student][assignmentsAssigned[student]] = input.nextFloat();
                //increases the number of the value in that array spot assigned to that student. it ensures that the next assignment will be in the next column over from the previous assignment.
                assignmentsAssigned[student]++;
                //exits the loop
                change = true;
            }
            //this if condition ensures that there actually is something in the selected spot to be edited. note how all values in this array were assigned to "" earlier, which is a 0 length string. if something is in the spot, the length will be > 0, which means the statement is true.
            else if(assignmentName[student][choice].length() > 0){
                //this section is essentially the exact same as the procedure for the new entry, so i won't comment all the lines here. the key difference is that there is no  assignmentsAssigned[student]++ line; we are editing a preexisting line so we don't need to move to an available spot. 
                System.out.println("Enter new assignment name (one word):");
                input.nextLine();
                assignmentName[student][choice] = input.nextLine();
                System.out.println("Enter assignment percent (ex: 45.6 percent)");
                gradePercent[student][choice] = input.nextFloat();
                change = true;
            }
            else
            System.out.println("That is not a valid answer. Please try again.");
        }   
        //this tells the program that this student now has at least one item in the assignment array, which will open up just a tad more functionality in the program. this safeguard is in place to, yet again, prevent null pointer errors. 
        assignmentAdded[student] = true;
    }

    //this method allows other methods to easily get a student.
    public int StudentSelect(){
        //initialize return variable
        int StudentSelect = 0;
        //do loop to trap the user
        do{
            System.out.println("Select Student:");
            //prints all the users. if a user exists then they will have a string length > 0.
            for(int usCount = 0; usCount < currentStudent; usCount++){
                if (nameIndex[usCount].length() > 0)
                    System.out.println(usCount + ") " + nameIndex[usCount]);
            }
            //take in input
            StudentSelect = input.nextInt();
        }while(StudentSelect < 0 && StudentSelect > currentStudent);
        //return input
        return StudentSelect;
    }

    public void List(){
        //get desired student
        int listStudentSelect = StudentSelect();
        //checks if values have been added to assignments to prevent null pointer errors
        if(assignmentAdded[listStudentSelect] == true){
            System.out.println("Info on " + nameIndex[listStudentSelect] + ": \nAssignments:");
            //prints out assignments if they exist (again using string length test)
            //ass lol
            for(int assCount = 0; assCount < 100; assCount++){
                if(assignmentName[listStudentSelect][assCount].length() > 0)
                System.out.println(assignmentName[listStudentSelect][assCount] + ": " + gradePercent[listStudentSelect][assCount]);
            }
        }
        //this is the exact same thing as the assignments but for the student's attendence.
        if(attendanceAdded[listStudentSelect] == true){
            System.out.println("Attendance (date format mm/dd/yyyy):");
            //abs lol
            for(int absCount = 0; absCount < 100; absCount++){
                if(absenceData[listStudentSelect][absCount].length() > 0)
                System.out.println(absenceData[listStudentSelect][absCount]);
            }
        }

        for(int runningOut = 0; runningOut < 4; runningOut++){
            if(schedule[listStudentSelect][runningOut].length() < 1)
                System.out.println("Block " + (runningOut + 1) + ") EMPTY");
                else
                System.out.println("Block " + (runningOut + 1) + ") " + schedule[listStudentSelect][runningOut]);
        }
    }

}
