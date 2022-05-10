package ui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.icons.FlatSearchIcon;
import json.MetadataProcessor;
import json.UserProcessor;
import logic.Command;
import logic.Logic;
import objects.GameImage;
import objects.Metadata;
import objects.Variables;

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
    private JButton settingsButton;
    private JPanel left;
    private JPanel right;
    private JLabel gameName;
    private JButton refreshButton;
    private JButton playButton;
    private JButton installButton;
    private JButton locateButton;
    private JButton cloudButton;
    private JLabel gameCover;
    private JButton downloadsButton;
    private JLabel developer;
    private JLabel appname;
    private JButton button1;
    private JButton button2;
    private JComboBox<String> filterGames;
    private JButton backHome;
    private JList downloadsList;
    private JPanel downloadsPanel;
    private JPanel gamesPanel;
    private JPanel settingsPanel;
    private JButton github;
    private JButton logoutButton;
    private JList libraryList;
    private JButton button5;
    private JButton button6;
    private JButton applyButton;
    private JButton cancelButton;
    private JButton button9;
    private JButton button10;
    private JButton backHomeSettings;
    private JButton button3;
    private JButton button4;
    private JButton button11;
    private JButton button12;
    private JButton button13;
    private JLabel userName;
    private JLabel legendary;
    private JButton updateButton;
    private JList themesList;

    Main()
    {
        initialise();
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

    private void initialise()
    {
        Metadata[] metadataList = MetadataProcessor.getGamesList().toArray(new Metadata[0]);
        settingsPanel.setVisible(false);
        downloadsPanel.setVisible(false);
        gameCover.setIcon(getIcon("/Cover.jpg", "cover"));
        filterGames.setModel(new DefaultComboBoxModel<String>(Variables.FILTER_GAMES));
        gamesList.setListData(metadataList);
        gamesList.addListSelectionListener(actionEvent -> showInfo());
        settingsButton.addActionListener(actionEvent -> showSettings());
        installButton.addActionListener(actionEvent -> showInstallDialog());
        downloadsButton.addActionListener(actionEvent -> showDownloads());
        backHome.addActionListener(actionEvent -> showGames());
        backHomeSettings.addActionListener(actionEvent -> showGames());
        searchGames.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search");
        searchGames.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSearchIcon());
        if (Objects.equals(UserProcessor.getUser().getName(), "none"))
        {
            logoutButton.setText("Login");
        }
        userName.setText(UserProcessor.getUser().getName());
    }

    private void showGames()
    {
        settingsPanel.setVisible(false);
        downloadsPanel.setVisible(false);
        gamesPanel.setVisible(true);
    }

    private void showSettings()
    {
        legendary.setText(Command.getVersion());
        settingsPanel.setVisible(true);
        gamesPanel.setVisible(false);
    }

    private void showDownloads()
    {
        gamesPanel.setVisible(false);
        downloadsPanel.setVisible(true);
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

    private void showInstallDialog()
    {
        InstallDialog.initialise();
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
                gameCover.setIcon(Logic.getIconFromWeb(new URL(url)));
            }
        }
        catch (MalformedURLException e)
        {
            throw new RuntimeException(e);
        }
    }
    
}
