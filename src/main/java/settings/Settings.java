package settings;

import com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme;

import javax.swing.*;

public class Settings
{
    private JPanel Settings;
    private JButton loginSignoutButton;
    private JList libraryList;
    private JButton button1;
    private JButton button2;
    private JComboBox comboBox1;
    private JButton applyButton;
    private JButton closeButton;

    public Settings()
    {

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
