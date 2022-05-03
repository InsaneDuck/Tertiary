package Main;

import com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme;
import json.MetadataProcessor;
import objects.Metadata;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
    }

    public static void main(String[] args)
    {
        FlatOneDarkIJTheme.setup();
        JFrame frame = new JFrame("Main");
        frame.setContentPane(new Main().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(0, 650));
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private void showInfo()
    {
        Metadata metadata = (Metadata) gamesList.getSelectedValue();
        gameName.setText(metadata.getAppTitle());
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
                        default -> null;
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
