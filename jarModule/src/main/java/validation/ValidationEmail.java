package validation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ValidationEmail {
    public static boolean validationEmail(String email) throws IOException{
        {
            List<String> allUsers = new ArrayList<>();
            String line;
            File file = new File("test1.csv");
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
                        return false;
                    }
                }
            }

            return true;
        }
    }
}
