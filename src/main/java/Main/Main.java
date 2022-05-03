package Main;

import com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme;
import json.MetadataProcessor;
import objects.GameImage;
import objects.Metadata;
import settings.Settings;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

public class Main
{
    private JPanel Main;
    private JTextField searchField;
    private JList gamesList;
    private JButton settingsButton;
    private JButton playButton;
    private JButton installButton;
    private JButton locateButton;
    private JButton cloudButton;
    private JPanel left;
    private JPanel right;
    private JLabel banner;
    private JLabel cover;
    private JLabel gameName;
    private JButton refreshButton;

    Main()
    {
        cover.setIcon(getIcon("/Cover.jpg", "cover"));
        //banner.setIcon(getIcon("/Banner.jpg", "banner"));
        List<Metadata> metadataList = MetadataProcessor.getGamesList();
        gamesList.setListData(metadataList.toArray());
        gamesList.addListSelectionListener(listener -> showInfo());
        settingsButton.addActionListener(actionEvent -> settings());
    }

    public static void main(String[] args)
    {
        FlatOneDarkIJTheme.setup();
        JFrame frame = new JFrame("Tertiary");
        frame.setContentPane(new Main().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(0, 650));
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private void settings()
    {
        Settings settings = new Settings();
        settings.initialise();
    }

    private void showInfo()
    {
        Metadata metadata = (Metadata) gamesList.getSelectedValue();
        String name = String.format("<html><div WIDTH=%d>%s</div></html>", 600, metadata.getAppTitle());
        gameName.setText(name);
        String url = null;
        for (GameImage gameImage : metadata.getGameImages())
        {
            //DieselGameBoxTall
            if (Objects.equals(gameImage.getType(), "DieselGameBoxTall"))
            {
                url = gameImage.getUrl();
            }
        }
        try
        {
            if (url != null)
            {
                cover.setIcon(getIconFromWeb(new URL(url)));
            }
        }
        catch (MalformedURLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public ImageIcon getIcon(String imageLocation, String type)
    {
        ImageIcon image = null;
        try
        {
            BufferedImage bufferedImage = ImageIO.read(Objects.requireNonNull(getClass().getResource(imageLocation)));
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

    public ImageIcon getIconFromWeb(URL url)
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
