package validation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ValidationID {
    public static boolean validationID(long id) throws IOException {
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
                if(Integer.parseInt(parts[0])==id){
                    return false;
                }
            }
        }

        return true;
    }
}
