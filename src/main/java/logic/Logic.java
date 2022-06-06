package logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import objects.Configuration;
import objects.Themes;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

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

    //method that returns configuration if one exists or creates new one if no config exists
    public static Configuration readConfig()
    {
        Configuration configuration = new Configuration("One Dark", "purple", false);
        ObjectMapper mapper = new ObjectMapper();
        String home = System.getProperty("user.home");
        String directory = "/.config/tertiary/configuration.json";
        File configurationFile = new File(home + directory);
        try
        {
            //if configuration doesn't exist then create one with default config
            if (configurationFile.getParentFile().mkdirs() || configurationFile.createNewFile())
            {
                mapper.writeValue(configurationFile, configuration);
            }
            return mapper.readValue(configurationFile, Configuration.class);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void updateConfig(Configuration configuration)
    {
        ObjectMapper objectMapper = new ObjectMapper();
        String home = System.getProperty("user.home");
        String directory = "/.config/tertiary/configuration.json";
        File configurationFile = new File(home + directory);
        try
        {
            objectMapper.writeValue(configurationFile, configuration);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }


    public static void setConfiguration(Configuration configuration)
    {
        Themes.setTheme(configuration.getTheme());
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

    public static ImageIcon getIcon(String imageLocation, String type)
    {
        ImageIcon image = null;
        try
        {
            BufferedImage bufferedImage = ImageIO.read(Objects.requireNonNull(Logic.class.getResource(imageLocation)));
            Image temp = switch (type)
                    {
                        case "cover" -> bufferedImage.getScaledInstance(180, 240, Image.SCALE_SMOOTH);
                        case "banner" -> bufferedImage.getScaledInstance(800, 450, Image.SCALE_SMOOTH);
                        default -> ImageIO.read(new URL(imageLocation));
                    };
            image = new ImageIcon(temp);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        image.getImage().flush();
        return image;
    }
}
