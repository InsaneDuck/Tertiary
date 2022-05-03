package json;

import objects.GameImage;
import objects.Metadata;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
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
        if (listOfFiles != null)
        {
            for (File file : listOfFiles)
            {
                metadataList.add(jsonToMetadata(file));
            }
        }
        metadataList.sort(Comparator.comparing(Metadata::getAppTitle));
        return metadataList;
    }

    public static Metadata jsonToMetadata(File file)
    {
        JSONParser jsonParser = new JSONParser();
        JSONObject json = null;
        try
        {
            json = (JSONObject) jsonParser.parse(new FileReader(file));
        }
        catch (IOException | ParseException e)
        {
            throw new RuntimeException(e);
        }
        // getting game name and title
        String appName = (String) json.get("app_name");
        String appTitle = (String) json.get("app_title");
        JSONObject metadata = (JSONObject) json.get("metadata");
        System.out.println(appName);
        System.out.println(appTitle);

        List<GameImage> gameImageList = new ArrayList<>();
        // getting images
        JSONArray images = (JSONArray) metadata.get("keyImages");
        for (Object image : images)
        {
            GameImage gameImage = new GameImage();
            JSONObject imageJSON = (JSONObject) image;
            gameImage.setType((String) imageJSON.get("type"));
            gameImage.setUrl((String) imageJSON.get("url"));
            System.out.println(gameImage.toString());
            gameImageList.add(gameImage);
        }

        return new Metadata(appName, appTitle, gameImageList);
    }

    public void metadataToJSON(Metadata metadata)
    {

    }
}
