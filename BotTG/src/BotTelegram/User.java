package BotTelegram;

/**
 *
 * @author frigerio_matteo
 */
public class User 
{
    Coordinate coordinate;
    String IdChat;
    String Username;
    
    public User()
    {       
        coordinate = new Coordinate();
        IdChat="";
        Username="";
    }

    public User(Coordinate coordinate, String idChat, String username) {
        this.coordinate = coordinate;
        this.IdChat = idChat;
        this.Username = username;
    }

    public Coordinate getCoordinate() 
    {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        
        this.coordinate = coordinate;
    }

    public String getIdChat() 
    {
        return IdChat;
    }

    public void setIdChat(String IdChat) 
    {        
        this.IdChat = IdChat;
    }

    public String getUsername() 
    {
        return Username;
    }

    public void setUsername(String Username) 
    {
        this.Username = Username;
    }
    
    
}
