package logic;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static java.rmi.server.LogStream.log;

public class Command
{
    public static String executeCommand(String command)
    {
        String result = "";
        try
        {
            Runtime.getRuntime().exec(command);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        return result;
    }

    //todo implement logout
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
