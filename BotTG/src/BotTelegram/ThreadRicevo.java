package BotTelegram;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import telegram.API.*;

/**
 *
 * @author frigerio_matteo
 */
public class ThreadRicevo extends Thread
{
    boolean primo;
    int  offset;
    
    TelegramApi api;
    JsonMex parser;
    Condivisa condivisa;
    
    
    
    public ThreadRicevo(Condivisa c)
    {
        primo=true;
        offset=0;
        
        api = new TelegramApi();
        parser = new JsonMex();
        condivisa=c;
        
    }
    
    @Override
    public void run()
    {
        while(true)
        {
            try {                
                JSONObject json = api.getUpdates(offset);//passo offset
                List<Mex> messaggi = parser.Parser(json);
                if(!messaggi.isEmpty()){//controllo vuoto
                    //con offset cancello i messaggi precedenti da getUpdates
                    offset =Integer.parseInt(messaggi.get(messaggi.size()-1).getIdAggiornamento())+1;//prendo il valore di id e lo setto come offset
                    if(!primo)
                        condivisa.AddMex(messaggi); 
                }
                
            } catch (IOException ex) {
                Logger.getLogger(ThreadRicevo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JSONException ex) {
                Logger.getLogger(ThreadRicevo.class.getName()).log(Level.SEVERE, null, ex);
            }
            
                       
            try {
                if(!primo)
                    Thread.sleep(2000);
                else
                    Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadRicevo.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(primo == true)
            {
                primo = false;
            }
        }
    }
}
