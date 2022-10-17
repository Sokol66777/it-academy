package validation;

import Exceptions.RepeatedDataException;
import model.Constants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Constants;

public class ValidationParametrs {


    public static void validationEmail(String email) throws IOException, RepeatedDataException {
        {
            List<String> allUsers = new ArrayList<>();
            String line;
            File file = new File(Constants.FILE_PATH);
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
        }
    }

    public static void validationPassword(String password) throws RepeatedDataException {
        if(!password.matches("(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}")){
            throw new RepeatedDataException("Password is not valid");
        }

    }

    public static void validationUsername(String username) throws IOException, RepeatedDataException {
        List<String> allUsers = new ArrayList<>();
        String line;
        File file = new File(Constants.FILE_PATH);
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

    }


}
