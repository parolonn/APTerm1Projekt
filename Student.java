
import java.util.Scanner;
public class Student {
    Scanner input = new Scanner(System.in);
    String[] nameIndex = new String[100];
    


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

    }

    public void Update(){

    }

    public void List(){
        
    }

}
