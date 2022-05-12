package logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme;
import objects.Configuration;
import objects.Legendary;
import objects.Metadata;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Logic
{
    public static String configurationToJson(Configuration configuration)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        try
        {
            return objectMapper.writeValueAsString(configuration);
        }
        catch (JsonProcessingException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static Configuration jsonToConfiguration(File file)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        Configuration configuration = new Configuration();
        try
        {
            configuration = objectMapper.readValue(file, Configuration.class);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        return configuration;
    }

    public static Configuration readConfig()
    {
        Configuration configuration = new Configuration();
        String home = System.getProperty("user.home");
        String directory = "/.config/tertiary/configuration.json";
        File configurationFile = new File(home + directory);
        try
        {

            if (configurationFile.getParentFile().mkdirs() || configurationFile.createNewFile())
            {
                configuration.setTheme("One Dark");
                configuration.setAccent("purple");
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(configurationFile, configuration);
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        configuration = jsonToConfiguration(configurationFile);
        return configuration;
    }

    public static Configuration getConfiguration()
    {

        return new Configuration();
    }

    public static void setConfiguration(Configuration configuration)
    {
        switch (configuration.getTheme())
        {
            case "One Dark" -> FlatOneDarkIJTheme.setup();
            case "Dark purple" -> FlatDarkPurpleIJTheme.setup();
        }
    }

    public static void launchGames(Metadata metadata)
    {
        Command.execCmd(Legendary.launch(metadata.getAppName()));
    }

    public static ImageIcon getIconFromWeb(URL url)
    {
        ImageIcon imageIcon;
        try
        {
            BufferedImage bufferedImage = ImageIO.read(url);
            Image image = bufferedImage.getScaledInstance(180, 240, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(image);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        imageIcon.getImage().flush();
        return imageIcon;
    }
}
