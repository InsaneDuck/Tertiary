package objects;

public class User
{
    String name;

    public User()
    {
    }

    public User(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return name;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
