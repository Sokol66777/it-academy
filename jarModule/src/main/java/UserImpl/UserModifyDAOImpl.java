package UserImpl;

import UserImpl.StringToUser;
import dao.UserModifyDAO;
import model.User;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserModifyDAOImpl implements UserModifyDAO {

    private static final String FILE_PATH = "D:\\projects\\it-academy\\test1.csv";

    @Override
    public void AddUser(User user) throws IOException {


        File file = new File(FILE_PATH);
        try (FileWriter pw = new FileWriter(file, true)) {
            pw.write(user.toString() + "\n");
        }

    }

    @Override
    public void DeleteUser(String username) throws IOException {
        String parts[];
        List<String> allUsers = new ArrayList<>();
        String line;
        File file = new File(FILE_PATH);
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


    }
}
