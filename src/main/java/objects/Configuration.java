package objects;

public class Configuration
{
    String theme;
    String accent;

    boolean loggedIn;

    public Configuration()
    {
    }

    public Configuration(String theme, String accent, boolean loggedIn)
    {
        this.theme = theme;
        this.accent = accent;
        this.loggedIn = loggedIn;
    }

    public boolean isLoggedIn()
    {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn)
    {
        this.loggedIn = loggedIn;
    }

    public String getTheme()
    {
        return theme;
    }

    public void setTheme(String theme)
    {
        this.theme = theme;
    }

    public String getAccent()
    {
        return accent;
    }

    public void setAccent(String accent)
    {
        this.accent = accent;
    }


}
