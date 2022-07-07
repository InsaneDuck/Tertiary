package ui;

import com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme;
import legendary.Legendary;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Splash extends JFrame
{
    private JProgressBar downloadProgress;
    private JPanel splash;

    public void initialise()
    {
        FlatOneDarkIJTheme.setup();
        this.setTitle("Downloading......");
        this.setContentPane(new Splash().splash);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        downloadLegendary();
    }


    private void downloadLegendary()
    {
        SwingWorker worker = new SwingWorker()
        {
            @Override
            protected Object doInBackground()
            {
                try
                {
                    InputStream inputStream = new URL(Legendary.LEGENDARY_GITHUB_LINUX).openStream();
                    ProgressMonitorInputStream progressMonitorInputStream = new ProgressMonitorInputStream(Splash.this, "", inputStream);
                    int downloadSize = inputStream.available();
                    ProgressMonitor progressMonitor = progressMonitorInputStream.getProgressMonitor();
                    progressMonitor.setMillisToDecideToPopup(0);
                    progressMonitor.setMillisToPopup(0);
                    progressMonitor.setMinimum(0);
                    progressMonitor.setMaximum(downloadSize);

                    FileUtils.copyInputStreamToFile(inputStream, new File(Legendary.LEGENDARY_DIRECTORY + "legendary"));
                    Runtime.getRuntime().exec(Legendary.FIX_PERMISSIONS, new String[]{}, new File(Legendary.LEGENDARY_DIRECTORY));
                }
                catch (IOException e)
                {
                    throw new RuntimeException(e);
                }
                return null;
            }

            @Override
            protected void done()
            {
                Splash.this.setVisible(false);
            }

        };
        worker.execute();
    }
}
