package json;

import modal.User;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class UserProcessor
{
    public static User getUser()
    {
        User user = new User("none");
        String home = System.getProperty("user.home");
        String directory = "/.config/legendary/user.json";
        File userJson = new File(home + directory);
        JSONParser jsonParser = new JSONParser();
        JSONObject json = null;
        try
        {
            json = (JSONObject) jsonParser.parse(new FileReader(userJson));
        }
        catch (IOException | ParseException e)
        {
            //todo returns empty user
            return user;
        }
        String name = (String) json.get("displayName");
        user.setName(name);
        return user;
    }
}
