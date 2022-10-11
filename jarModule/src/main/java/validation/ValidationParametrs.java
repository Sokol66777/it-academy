package validation;

import Exceptions.RepeatedDataException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ValidationParametrs {

    public static final String FILE_PATH = "D:\\projects\\it-academy\\test1.csv";

    public static boolean validationEmail(String email) throws IOException, RepeatedDataException {
        {
            List<String> allUsers = new ArrayList<>();
            String line;
            File file = new File(FILE_PATH);
            try(BufferedReader reader = new BufferedReader(new FileReader(file))){
                line=reader.readLine();
                while(line!=null){
                    if(!line.equals("")) {
                        allUsers.add(line);
                    }
                    line=reader.readLine();
                }
                for(String user:allUsers){
                    String[] parts= user.split(",");
                    if(parts[3].equals(email)){
                        throw new RepeatedDataException("This email used other user, input other email");
                    }
                }
            }

            return true;
        }
    }

    public static boolean validationPassword(String password) throws RepeatedDataException {
        if(!password.matches("(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}")){
            throw new RepeatedDataException("Password is not valid");
        }

        return true;
    }

    public static boolean validationUsername(String username) throws IOException, RepeatedDataException {
        List<String> allUsers = new ArrayList<>();
        String line;
        File file = new File(FILE_PATH);
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            line=reader.readLine();
            while(line!=null){
                if(!line.equals("")) {
                    allUsers.add(line);
                }
                line=reader.readLine();
            }

                for (String user : allUsers) {
                    String[] parts = user.split(",");
                    if (parts[1].equals(username)) {
                        throw new RepeatedDataException("This username used other user, input other username");
                    }
                }

        }

        return true;
    }


}
