package ui;

import com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme;

import javax.swing.*;
import java.io.File;

public class InstallDialog extends JPanel
{
    private JPanel install;
    private JTextField installLocation;
    private JButton okButton;
    private JButton cancelButton;
    private JButton selectFolder;

    InstallDialog()
    {
        selectFolder.addActionListener(actionEvent -> openDialog());
    }

    public static void initialise()
    {
        FlatOneDarkIJTheme.setup();
        JFrame frame = new JFrame("Install Game");
        frame.setContentPane(new InstallDialog().install);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private void openDialog()
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select Installation folder");
        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
        {
            installLocation.setText(chooser.getSelectedFile().toString());
        }
    }


}
