package BotTelegram;

import java.util.ArrayList;
import java.util.List;
import telegram.API.*;
/**
 *
 * @author frigerio_matteo
 */
public class Condivisa 
{
    List<Mex> messaggi;
    int IdMex;
    String contenuto;
    
    public Condivisa()
    {
        messaggi=new ArrayList<Mex>();
        
        IdMex=0;
        contenuto="";
    }
    
    public Condivisa(List<Mex> messaggi, int id, String contenutoPubb)
    {
        this.messaggi=messaggi;
        IdMex=id;
        this.contenuto=contenutoPubb;
    }
    
    public void AddMex(List<Mex> messaggi)
    {
        this.messaggi.addAll(messaggi);
    }
    
    public void setID(int id)
    {
        IdMex=id;
    }
    
    public int getID()
    {
        return IdMex;
    }
    
    public void setIdNuovo(int id)
    {
        if(IdMex<id)
        {
            IdMex=id;
        }
    }
    
    public Mex getMex()
    {
        if(!messaggi.isEmpty())
        {
            return messaggi.remove(0);
        }
        else
        {
            return new Mex();
        }
    }
    
    public boolean hasMex()
    {
        if(messaggi.isEmpty())
        {
            return false;   
        }
        else
        {
            return true;
        }
    }

    public String getContenuto() 
    {
        return contenuto;
    }

    public void setContenuto(String contenuto) 
    {
        this.contenuto = contenuto;
    }
   
}
