package BotTelegram;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import telegram.API.*;

/**
 *
 * @author frigerio_matteo
 */
public class ThreadInvio extends Thread 
{

    TelegramApi api;
    Condivisa condivisa;
    CoordintateXml coordinate;

    public ThreadInvio(Condivisa c) 
    {
        api = new TelegramApi();
        condivisa = c;
        coordinate = new CoordintateXml();
    }

    @Override
    public void run() 
    {
        while (true) 
        {
            try {
                sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadInvio.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (condivisa.hasMex()) 
            {
                Mex mex = condivisa.getMex();
                
                String testo = "";
                String messaggio = mex.getTesto();

                if (mex.getTipo() == 1) 
                {                  
                    if (!messaggio.equals("")) 
                    {
                        if (messaggio.startsWith("/")) 
                        {
                            if (messaggio.toUpperCase().startsWith("/CITTA ")) 
                            {
                                String query = messaggio.substring(7);
                                
                                try {
                                    if (coordinate.getXMLToCSV(query, mex.getIdChat(), mex.getUsername())) 
                                    {
                                        testo = "posizione trovata";
                                    } 
                                    else 
                                    {
                                        testo = "posizione non trovata";
                                    }
                                    
                                } catch (IOException ex) {
                                    Logger.getLogger(ThreadInvio.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }                          
                            else 
                            {
                                testo = "comando non valido";
                            }                        

                        
                        if (!testo.equals("")) 
                        {
                            try {
                                api.sendMessage(testo, mex.getIdChat());
                            } catch (IOException ex) {
                                Logger.getLogger(ThreadInvio.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                } 
                else if (mex.getTipo() == 2) 
                {
                    testo=condivisa.getContenuto();
                    try {
                        api.sendMessage(testo, mex.getIdChat());
                    } catch (IOException ex) {
                        Logger.getLogger(ThreadInvio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }

        }
        }
    }
}
