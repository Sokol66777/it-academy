import model.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int point;
        long usersID;
        User user = new User();
        String usersName;
        String usersEmail;
       ReadCSV readCSV = new ReadCSV();
       UserModifyDAOImpl userModify = new UserModifyDAOImpl();
       do {

           printMenu();
           System.out.println("select point");
           point=sc.nextInt();
           sc.nextLine();
           switch(point){
               case 1:
                   List<User> users=readCSV.getAllUsers();
                   for(User usera:users){
                       System.out.println(usera.toString());
                   }
                   break;
               case 2:
                   System.out.println("Write username");
                   usersName=sc.nextLine();
                   user=readCSV.getByUsername(usersName);
                   if(user!=null){
                       System.out.println(user.toString());
                   }
                   else{
                       System.out.println("Not found users with that name");
                    }
                   break;
               case 3:
                   System.out.println("Write users ID");
                   usersID=sc.nextInt();
                   user = readCSV.getByID(usersID);
                   if(user!=null){
                       System.out.println(user.toString());
                   }
                   else{
                       System.out.println("Not found users with that ID");
                   }
                   break;
               case 4:
                   System.out.println("Write users email");
                   usersEmail=sc.nextLine();
                   user=readCSV.getByEmail(usersEmail);
                   if(user!=null){
                       System.out.println(user.toString());
                   }
                   else{
                       System.out.println("Not found users with that ID");
                   }
                   break;
               case 5:
                   System.out.println("Input users ID");
                   System.out.println("Input username");
                   System.out.println("Input password");
                   System.out.println("Input email");
                   System.out.println("Input role");
                   userModify.AddUser();
                   break;
               case 6:
                   System.out.println("Input username witch you want detete");
                   usersName=sc.nextLine();
                   userModify.DeleteUser(usersName);
                   break;
               case 7:
                   System.out.println("Input username witch you want modify");
                   usersName=sc.nextLine();
                   userModify.ModifyUser(usersName);



           }
       }while (point!=8);
       sc.close();

    }
    public static void printMenu(){
        System.out.println("Menu");
        System.out.println("1: Get all users");
        System.out.println("2: Get user by Username");
        System.out.println("3: Get user by ID");
        System.out.println("4: Get user by email");
        System.out.println("5: Create new user");
        System.out.println("6: Delete user");
        System.out.println("7: Modify user");
        System.out.println("8: Exit");
    }

}
