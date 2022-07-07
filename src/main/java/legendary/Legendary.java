package legendary;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Legendary
{
    public static final String LEGENDARY_GITHUB_LINUX = "https://github.com/derrod/legendary/releases/latest/download/legendary";
    public static final String LEGENDARY_GITHUB_WINDOWS = "https://github.com/derrod/legendary/releases/latest/download/legendary.exe";
    public static final String HOME_DIRECTORY = System.getProperty("user.home");
    public static final String LEGENDARY_DIRECTORY = HOME_DIRECTORY + "/Applications/";
    public static final String FIX_PERMISSIONS = "chmod 755 legendary";

    public static boolean checkForLegendary()
    {
        File f = new File(Legendary.LEGENDARY_DIRECTORY + "legendary");
        return f.exists() && !f.isDirectory();
    }

    public static String getVersion()
    {
        if (checkForLegendary())
        {
            return executeCommand("--version");
        }
        return "legendary not found";
    }

    public static void downloadSaves()
    {
        if (checkForLegendary())
        {
            executeCommand("download-saves");
        }
    }

    public static String install(String appName)
    {
        return executeCommand("install " + appName);
    }

    public static String install(String appName, String path)
    {
        return executeCommand("install " + appName + " --game-folder " + path);
    }

    public static void auth()
    {
        executeCommand("auth");
    }


    public static String getGamesList()
    {
        return executeCommand("list");
    }

    public static void launch(String appTitle)
    {
        executeCommand("launch " + appTitle);
    }

    public static void checkGameUpdates()
    {
        executeCommand("list-installed --check-updates");
    }

    public static void sync()
    {
        executeCommand("sync-saves");
    }

    public static void cleanSaves()
    {
        executeCommand("clean-saves");
    }

    public static void cleanUp()
    {
        executeCommand("cleanup");
    }

    public static void setupCrossover()
    {
        executeCommand("crossover");
    }

    public static void downloadLegendary()
    {
        try
        {
            //Runtime.getRuntime().exec("mkdir Applications", new String[]{}, new File(HOME_DIRECTORY));
            FileUtils.copyURLToFile(new URL(LEGENDARY_GITHUB_LINUX), new File(LEGENDARY_DIRECTORY + "legendary"));
            Runtime.getRuntime().exec(Legendary.FIX_PERMISSIONS, new String[]{}, new File(LEGENDARY_DIRECTORY));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static String setDefaultGamesInstallationFolder(String path)
    {
        return "--base-path " + path;
    }

    public static String executeCommand(String command)
    {
        String result = "";
        String cmd = "./legendary " + command;
        try
        {
            InputStream inputStream = Runtime.getRuntime().exec(cmd, new String[]{}, new File(Legendary.LEGENDARY_DIRECTORY)).getInputStream();
            System.out.println("Executing :" + cmd);
            Scanner s = new Scanner(inputStream).useDelimiter("\\A");
            result = s.hasNext() ? s.next() : null;
        }
        //catch legendary not found exception
        catch (IOException e)
        {
            //downloadLegendary();
            e.printStackTrace();
        }
        return result;
    }


}
