package BotTelegram;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author frigerio_matteo
 */
public class BotTelegram 
{
    public static void main(String[] args) 
    {
        Condivisa c =  new Condivisa();
        ThreadInvio ThInvio = new ThreadInvio(c);
        ThreadRicevo ThRicevo = new ThreadRicevo(c);
        
        ThRicevo.start();
        ThInvio.start();
        

        try {
            ThRicevo.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(BotTelegram.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ThInvio.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(BotTelegram.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
