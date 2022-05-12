package objects;

public class Legendary
{
    public static final String DIRECTORY = System.getProperty("user.home") + "/Applications/";
    public static final String LEGENDARY = "./legendary ";
    public static final String FIX_PERMISSIONS = "chmod 755 legendary";
    public static final String VERSION = LEGENDARY + "--version";
    public static final String AUTH = LEGENDARY + "auth";
    public static final String LIST = LEGENDARY + "list";
    public static final String CHECK_GAME_UPDATES = LEGENDARY + "list-installed --check-updates";
    public static final String SYNC = LEGENDARY + "sync-saves";
    public static final String CLEAN_SAVES = LEGENDARY + "clean-saves";
    public static final String CLEANUP = LEGENDARY + "cleanup";
    public static final String SETUP_CROSSOVER = LEGENDARY + "crossover";
    public static final String DOWNLOAD_SAVES = LEGENDARY + "download-saves";


    public static String install(String appName)
    {
        return LEGENDARY + "install " + appName;
    }

    public static String install(String appName, String path)
    {
        return LEGENDARY + "install " + appName + " --game-folder " + path;
    }

    public static String launch(String appTitle)
    {
        return DIRECTORY + LEGENDARY + "launch " + appTitle;
    }

    public static String setDefaultGamesInstallationFolder(String path)
    {
        return LEGENDARY + "--base-path " + path;
    }

}
