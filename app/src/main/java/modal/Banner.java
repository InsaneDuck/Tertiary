package modal;

import javax.swing.*;
import java.awt.*;

public class Banner extends JPanel
{
    private Image image;

    public Banner(String image)
    {
        this(new ImageIcon(image).getImage());
    }

    public Banner(Image image)
    {
        this.image = image;
        Dimension size = new Dimension(image.getWidth(null), image.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }

    public void paintComponent(Graphics g)
    {
        g.drawImage(image, 0, 0, null);
    }
}
