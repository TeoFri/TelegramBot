package telegram.API;

/**
 *
 * @author frigerio_matteo
 */
public class Mex 
{
    int tipo;
    String IdAggiornamento;
    String IdChat;
    String Username;
    String testo;
    
    public Mex(){
        tipo=0;
        IdAggiornamento="";
        IdChat="";
        Username="";
        testo="";
    }
    
    public Mex(int tipo, String IdMex, String IdChat, String username, String testo)
    {
        this.tipo=tipo;
        this.IdAggiornamento=IdMex;
        this.IdChat=IdChat;
        this.Username=username;
        this.testo=testo;
    }

    public int getTipo() 
    {
        return tipo;
    }

    public void setTipo(int tipo) 
    {
        this.tipo = tipo;
    }

    public String getIdAggiornamento() 
    {
        return IdAggiornamento;
    }

    public void setIdAggiornamento(String IdAggiornamento) 
    {
        this.IdAggiornamento = IdAggiornamento;
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

    public String getTesto() 
    {
        return testo;
    }

    public void setTesto(String testo) 
    {
        this.testo = testo;
    }
    
}
