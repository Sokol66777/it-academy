import dao.impl.UserDAO;
import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadCSV implements UserDAO {
    @Override
    public List<User> getAllUsers() throws IOException {
        String line;
        List<String> allUsers = new ArrayList<>();
        List<User> users = new ArrayList<>();
        File file = new File("test1.csv");
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            line=reader.readLine();
            while(line!=null){
                allUsers.add(line);
                line=reader.readLine();
            }
            for(String user:allUsers){
                String[] parts= user.split(",");
                users.add(StringToUser.createUser(parts));
            }
        }
        return users;
    }


    @Override
    public User getByUsername(String username) throws IOException {
        String line;
        String [] parts;
        File file = new File("test1.csv");
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            line=reader.readLine();
            while(line!=null){
                parts=line.split(",");
                if(username.equals(parts[1])){
                   return StringToUser.createUser(parts);
                }
                line=reader.readLine();
            }
        }
        return null;
    }

    @Override
    public User getByID(long ID) throws IOException {
        String line;
        String [] parts;
        File file = new File("test1.csv");
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            line=reader.readLine();
            while(line!=null){
                parts=line.split(",");
                if(ID==Integer.parseInt(parts[0])){
                    return StringToUser.createUser(parts);
                }
                line=reader.readLine();
            }
        }
        return null;
    }

    @Override
    public User getByEmail(String email) throws IOException {
        String line;
        String [] parts;
        File file = new File("test1.csv");
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            line=reader.readLine();
            while(line!=null){
                parts=line.split(",");
                if(email.equals(parts[3])){
                    return StringToUser.createUser(parts);
                }
                line=reader.readLine();
            }
        }
        return null;
    }
}
