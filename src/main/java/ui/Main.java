package ui;

import json.MetadataProcessor;
import objects.GameImage;
import objects.Logic;
import objects.Metadata;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class Main
{
    private JPanel Main;
    private JTextField searchGames;
    private JList<Metadata> gamesList;
    private JButton settings;
    private JPanel left;
    private JPanel right;
    private JLabel gameName;
    private JButton refresh;
    private JButton playButton;
    private JButton installButton;
    private JButton locateButton;
    private JButton cloudButton;
    private JLabel gameCover;
    private JButton downloads;
    private JLabel developer;
    private JLabel appname;
    private JButton button1;
    private JButton button2;
    private JComboBox filterGames;

    Main()
    {
        gameCover.setIcon(getIcon("/Cover.jpg", "cover"));
        //banner.setIcon(getIcon("/Banner.jpg", "banner"));
        Metadata[] metadataList = MetadataProcessor.getGamesList().toArray(new Metadata[0]);
        gamesList.setListData(metadataList);
        gamesList.addListSelectionListener(listener -> showInfo());
        settings.addActionListener(actionEvent -> settings());
        installButton.addActionListener(install -> showInstallDialog());
    }

    public static void main(String[] args)
    {
        Logic.setConfiguration(Logic.readConfig());
        JFrame frame = new JFrame("Tertiary");
        frame.setContentPane(new Main().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(0, 650));
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private void showInstallDialog()
    {
        InstallDialog.initialise();
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
        developer.setText(metadata.getDeveloper());
        appname.setText(metadata.getAppName());
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
                gameCover.setIcon(getIconFromWeb(new URL(url)));
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
