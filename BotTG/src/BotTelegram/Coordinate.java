/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BotTelegram;

/**
 *
 * @author frigerio_matteo
 */
public class Coordinate 
{
    float latitudine;
    float longitudine;
    
    public Coordinate()
    {
        latitudine=0;
        longitudine=0;
    }

    public Coordinate(float lt, float ln) 
    {
        this.latitudine = lt;
        this.longitudine = ln;
    }

    public float getLatitudine() 
    {
        return latitudine;
    }

    public void setLatitudine(float latitudine) 
    {
        this.latitudine = latitudine;
    }

    public float getLongitudine() 
    {
        return longitudine;
    }

    public void setLongitudine(float longitudine) 
    {
        this.longitudine = longitudine;
    }
    
    
}
