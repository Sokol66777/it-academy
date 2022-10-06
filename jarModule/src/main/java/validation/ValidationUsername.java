package validation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ValidationUsername {
    public static boolean validationUsername(String username) throws IOException {
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
                if(parts[1].equals(username)){
                    return false;
                }
            }
        }

        return true;
    }
}
