package telegram.API;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import org.json.JSONException;
import org.json.JSONObject;

 
/**
 *
 * @author frigerio_matteo
 */
public class TelegramApi 
{

    String BotName = "Pubblicita5B_Frigerio_bot";
    String BotKey = "5121803801:AAFBRO1b6QZph0eThuV8fNRVv7W56gctKiU";
    
    public TelegramApi()
    {
        
    }
    
    public TelegramApi(String name, String key)
    {
        BotName=name;
        BotKey=key;
    }

    public String getBotName() 
    {
        return BotName;
    }

    public String getBotKey() 
    {
        return BotKey;
    }

    public JSONObject getUpdates() throws IOException, JSONException 
    {
        String tmp = "";
        
        URL url = new URL("https://api.telegram.org/bot" + BotKey + "/getUpdates");
        
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        
        
        if (connection.getResponseCode() == 200) 
        {
            Scanner scanner = new Scanner(url.openStream());
            
            while (scanner.hasNext()) 
            {
                tmp += scanner.nextLine();
            }
        }
        
        JSONObject json = new JSONObject(tmp);
        
        return json;
    }
    
    public JSONObject getUpdates(int offset) throws IOException, JSONException 
    {
        String tmp = "";
        
        URL url = new URL("https://api.telegram.org/bot" + BotKey + "/getUpdates?offset=" + offset);
        
        HttpURLConnection connect = (HttpURLConnection) url.openConnection();
        connect.setRequestMethod("GET");
        connect.connect();
        
        if (connect.getResponseCode() == 200) 
        {
            Scanner scanner = new Scanner(url.openStream());
            
            while (scanner.hasNext()) 
            {
                tmp += scanner.nextLine();
            }
        }
        
        JSONObject json = new JSONObject(tmp);
        
        return json;
    }

    public void sendMessage(String message, String id) throws MalformedURLException, IOException 
    {
        URL url = new URL("https://api.telegram.org/bot" + BotKey + "/sendMessage?chat_id=" + id + "&text=" + message);
        
        URLConnection con = url.openConnection();
        
        InputStream input = new BufferedInputStream(con.getInputStream());
    }
    
}
