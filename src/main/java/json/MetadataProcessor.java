package json;

import objects.GameImage;
import objects.Metadata;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MetadataProcessor
{
    public static List<Metadata> getGamesList()
    {
        List<Metadata> metadataList = new ArrayList<>();
        String home = System.getProperty("user.home");
        String directory = "/.config/legendary/metadata";
        File folder = new File(home + directory);
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles)
        {
            try
            {
                metadataList.add(jsonToMetadata(new FileReader(file)));
            }
            catch (FileNotFoundException e)
            {
                throw new RuntimeException(e);
            }
        }
        metadataList.sort(Comparator.comparing(Metadata::getAppTitle));
        return metadataList;
    }

    public static Metadata jsonToMetadata(FileReader jsonFile)
    {
        //new FileReader("JSONExample.json")
        // parsing json
        Object object = null;
        try
        {
            object = new JSONParser().parse(jsonFile);
        }
        catch (IOException | ParseException e)
        {
            throw new RuntimeException(e);
        }

        // typecasting obj to JSONObject
        JSONObject json = (JSONObject) object;

        // getting game name and title
        String appName = (String) json.get("app_name");
        String appTitle = (String) json.get("app_title");

        System.out.println(appName);
        System.out.println(appTitle);

        // getting images
        List<GameImage> gameImageList = new ArrayList<>();

//        JSONArray images = (JSONArray) json.get("keyImages");
//        for (Object o : images)
//        {
//            GameImage gameImage = new GameImage();
//            JSONObject imageJSON = (JSONObject) o;
//            gameImage.setType((String) imageJSON.get("type"));
//            gameImage.setUrl((String) imageJSON.get("url"));
//            gameImageList.add(gameImage);
//        }

        return new Metadata(appName, appTitle, gameImageList);
    }

    public void metadataToJSON(Metadata metadata)
    {

    }
}
