package modal;

public class GameImage
{
    String type;
    String url;

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    @Override
    public String toString()
    {
        return "GameImage{" +
                "type='" + type + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
