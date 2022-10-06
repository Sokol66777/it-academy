import dao.impl.UserModifyDAO;
import model.User;
import validation.ValidationEmail;
import validation.ValidationID;
import validation.ValidationPassword;
import validation.ValidationUsername;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserModifyDAOImpl implements UserModifyDAO {
    @Override
    public void AddUser() throws IOException {
        long id;
        String username;
        String password;
        String email;
        String role;
        Scanner sc = new Scanner(System.in);
        id = sc.nextInt();
        sc.nextLine();
        username = sc.nextLine();
        password = sc.nextLine();
        email = sc.nextLine();
        role = sc.nextLine();
        while (ValidationID.validationID(id) != true) {
            System.out.println("this ID used other user, input other ID");
            id = sc.nextInt();
            sc.nextLine();
        }
        while (ValidationUsername.validationUsername(username) != true) {
            System.out.println("this username used other user, input other username");
            username = sc.nextLine();
        }
        while (ValidationEmail.validationEmail(email) != true) {
            System.out.println("this email used other user, input other email");
            email = sc.nextLine();
        }
        while (ValidationPassword.validationPassword(password)!=true){
            System.out.println("Uncorrect password. Please input new password.");
            password=sc.nextLine();
        }

        User user = new User(id, username, password, email, role);
        File file = new File("test1.csv");
        try (FileWriter pw = new FileWriter(file, true)) {
            pw.write(user.toString() + "\n");
        }

    }

    @Override
    public void DeleteUser(String username) throws IOException {
        String parts[];
        List<String> allUsers = new ArrayList<>();
        String line;
        File file = new File("test1.csv");
        try (BufferedReader bf = new BufferedReader(new FileReader(file))) {
            line = bf.readLine();
            while (line != null) {
                if (line != "") {
                    allUsers.add(line);
                }

                line = bf.readLine();
            }
        }
        try (FileWriter fw = new FileWriter(file)) {
            fw.write("");
        }
        try (FileWriter fw = new FileWriter(file, true)) {
            for (String user : allUsers) {
                parts = user.split(",");
                if (!parts[1].equals(username)) {
                    fw.write(user + "\n");
                }
            }

        }

    }

    @Override
    public void ModifyUser(String username) throws IOException {
        int flag = 0;
        String password;
        String email;
        int point;
        User needModifyUser = new User();
        String parts[];
        List<String> allUsers = new ArrayList<>();
        String line;
        Scanner sc = new Scanner(System.in);
        File file = new File("test1.csv");
        try (BufferedReader bf = new BufferedReader(new FileReader(file))) {
            line = bf.readLine();
            while (line != null) {
                if (line != "") {
                    allUsers.add(line);
                }

                line = bf.readLine();
            }
        }
        try (FileWriter fw = new FileWriter(file)) {
            fw.write("");
        }
        try (FileWriter fw = new FileWriter(file, true)) {
            for (String user : allUsers) {
                parts = user.split(",");
                if (!parts[1].equals(username)) {
                    fw.write(user + "\n");
                } else {
                    flag = 1;
                    needModifyUser = StringToUser.createUser(parts);
                }
            }

        }
        if (flag == 1) {
            System.out.println("What you want modify");
            System.out.println("1: Username");
            System.out.println("2: Password");
            System.out.println("3: Email");
            point = sc.nextInt();
            switch (point) {
                case 1:
                    sc.nextLine();
                    System.out.println("Input new username");
                    username = sc.nextLine();
                    while (ValidationUsername.validationUsername(username) != true) {
                        System.out.println("this username used other user, input other username");
                        username = sc.nextLine();
                    }
                    needModifyUser.setUsername(username);
                    try (FileWriter fw = new FileWriter(file, true)) {
                        fw.write(needModifyUser.toString() + "\n");
                    }
                    break;
                case 2:
                    sc.nextLine();
                    System.out.println("Input new password");
                    password = sc.nextLine();
                    while (ValidationPassword.validationPassword(password)!=true){
                        System.out.println("Uncorrect password. Please input new password.");
                        password=sc.nextLine();
                    }
                    needModifyUser.setPassword(password);
                    try (FileWriter fw = new FileWriter(file, true)) {
                        fw.write(needModifyUser.toString() + "\n");
                    }
                    break;
                case 3:
                    sc.nextLine();
                    System.out.println("Input new email");
                    email = sc.nextLine();
                    while (ValidationEmail.validationEmail(email) != true) {
                        System.out.println("this email used other user, input other email");
                        email = sc.nextLine();
                    }
                    needModifyUser.setEmail(email);
                    try (FileWriter fw = new FileWriter(file, true)) {
                        fw.write(needModifyUser.toString() + "\n");
                    }
                    break;
            }

        }
        else {
            System.out.println("not found this user");
        }
    }
}
