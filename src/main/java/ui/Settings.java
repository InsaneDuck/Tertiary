package ui;

import com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme;
import json.UserProcessor;

import javax.swing.*;
import java.util.Objects;

public class Settings
{
    private JPanel Settings;
    private JButton signoutButton;
    private JList libraryList;
    private JButton button1;
    private JButton button2;
    private JComboBox comboBox1;
    private JButton applyButton;
    private JButton closeButton;
    private JLabel user;

    public Settings()
    {
        if (Objects.equals(UserProcessor.getUser().getName(), "none"))
        {
            signoutButton.setText("Login");
        }
        user.setText(UserProcessor.getUser().getName());
    }

    public void initialise()
    {
        FlatOneDarkIJTheme.setup();
        JFrame frame = new JFrame("Settings");
        frame.setContentPane(new Settings().Settings);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
