/*Christopher Marchitto, Eleanor Perrigo, Maxius Golomb, Nick Haseman
Term 1 Final
the purpose of this program to be skyward.
*/
import java.util.Scanner;
public class StudentM{
    public static void main(String[] args) {
        //call class object and call menu method. this makes for a very abstract main but a messy alternative class. 
        Student sus = new Student();
        Scanner input = new Scanner(System.in);
        Menu(input, sus);
    }

    public static void Menu(Scanner input, Student sus){
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
                sus.Create();
                break;
                case 2:
                if(sus.DoStudentsExist() == true)
                sus.UpdateMenu();
                else
                System.out.println("A student does not exist (yet). Please create one.");
                break;
                case 3:
                if(sus.DoStudentsExist() == true)
                sus.List();
                else
                System.out.println("A student does not exist (yet) Please create one.");
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
}