package ui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.icons.FlatSearchIcon;
import json.MetadataProcessor;
import json.UserProcessor;
import legendary.Legendary;
import logic.Logic;
import objects.*;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class Main
{
    private JPanel Main;
    private JTextField searchGames;
    private JList<Metadata> gamesList;
    private JPanel left;
    private JPanel right;
    private JLabel gameName;
    private JButton playButton;
    private JButton installButton;
    private JButton locateButton;
    private JButton cloudButton;
    private JLabel gameCover;
    private JLabel developer;
    private JLabel appName;
    private JButton addDLC;
    private JButton removeDLC;
    private JComboBox<String> filterGames;
    private JList downloadsList;
    private JPanel Downloads;
    private JPanel Library;
    private JPanel Settings;
    private JButton github;
    private JButton logoutButton;
    private JList libraryList;
    private JButton button5;
    private JButton button6;
    private JButton applyButton;
    private JButton cancelButton;
    private JButton button9;
    private JButton button10;
    private JButton button3;
    private JButton button4;
    private JButton button11;
    private JButton button12;
    private JButton button13;
    private JLabel userName;
    private JLabel legendary;
    private JButton checkUpdatesButton;
    private JList<String> themesList;
    private JTabbedPane Tabs;
    private JPanel statusBar;
    private JList dlcList;
    private JPanel debug;
    private JButton button1;
    private JTextArea textArea1;


    Main()
    {
        uiProperties();
        listeners();
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

    private void uiProperties()
    {
        //setting default icons
        gameCover.setIcon(Logic.getIcon("/Cover.jpg", "cover"));
        searchGames.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search");
        searchGames.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSearchIcon());
        themesList.setListData(Themes.THEMES_LIST);
    }

    private void initialise()
    {
        //getting list of games
        Metadata[] metadataList = MetadataProcessor.getGamesList().toArray(new Metadata[0]);
        //setting filter options
        filterGames.setModel(new DefaultComboBoxModel<>(Variables.FILTER_GAMES));
        //setting games list to UI
        gamesList.setListData(metadataList);

        if (Objects.equals(UserProcessor.getUser().getName(), "none"))
        {
            logoutButton.setText("Login");
        }
        //get username and set it in UI
        userName.setText(UserProcessor.getUser().getName());
        //get legendary installed version
        legendary.setText(Legendary.getVersion());
    }

    private void listeners()
    {
        gamesList.addListSelectionListener(actionEvent -> showInfo());
        installButton.addActionListener(actionEvent -> showInstallDialog());
        playButton.addActionListener(actionEvent -> launchGame());
        themesList.addListSelectionListener(actionEvent -> changeTheme());
    }

    private void changeTheme()
    {
        Themes.setTheme(themesList.getSelectedValue());
        Configuration configuration = Logic.readConfig();
        configuration.setTheme(themesList.getSelectedValue());
        Logic.updateConfig(configuration);
    }

    private void launchGame()
    {
        Metadata metadata = gamesList.getSelectedValue();
        Legendary.launch(metadata.getAppName());
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
        appName.setText(metadata.getAppName());
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
