package logic;

import objects.Legendary;
import objects.Variables;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static java.rmi.server.LogStream.log;

public class Command
{
    public static String executeCommand(String command)
    {
        String result = "";
        String cmd = Legendary.DIRECTORY + command;
        try
        {

            InputStream inputStream = Runtime.getRuntime().exec(cmd, new String[]{}, new File(Legendary.DIRECTORY)).getInputStream();
            Scanner s = new Scanner(inputStream).useDelimiter("\\A");
            result = s.hasNext() ? s.next() : null;
        }
        catch (IOException e)
        {
            try
            {
                Runtime.getRuntime().exec(Legendary.PERMISSIONS, new String[]{}, new File(Legendary.DIRECTORY));
            }
            catch (IOException ex)
            {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        }
        return result;
    }

    public static boolean downloadLegendary()
    {
        try
        {
            URL url = new URL(Variables.LEGENDARY_GITHUB);
        }
        catch (MalformedURLException ex)
        {
            throw new RuntimeException(ex);
        }
        return false;
    }

    public static String execCmd(String cmd)
    {
        String result = null;
        try (InputStream inputStream = Runtime.getRuntime().exec(cmd).getInputStream();
             Scanner s = new Scanner(inputStream).useDelimiter("\\A"))
        {
            result = s.hasNext() ? s.next() : null;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return result;
    }


    public static String getVersion()
    {
        return executeCommand("./legendary --version");
    }

    private void logOutput(InputStream inputStream, String prefix)
    {
        new Thread(() -> {
            Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8);
            while (scanner.hasNextLine())
            {
                synchronized (this)
                {
                    log(prefix + scanner.nextLine());
                }
            }
            scanner.close();
        }).start();
    }

}
