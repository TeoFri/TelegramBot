package telegram.API;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author frigerio_matteo
 */
public class JsonMex 
{
    
    public JsonMex()
    {
            
    }
    
    public List<Mex> Parser(JSONObject json)
    {
        List<Mex> messaggi=new ArrayList<>();
        JSONArray jArray = json.getJSONArray("result");
        
        for (int i = 0; i < jArray.length(); i++) 
        {
            String IdMessaggio=jArray.getJSONObject(i).get("update_id").toString();     
            String IdChat=jArray.getJSONObject(i).getJSONObject("message").getJSONObject("chat").get("id").toString();
            String Username=jArray.getJSONObject(i).getJSONObject("message").getJSONObject("chat").get("first_name").toString();
            String testo = jArray.getJSONObject(i).getJSONObject("message").get("text").toString();
            
            messaggi.add(new Mex(1,IdMessaggio,IdChat,Username,testo));  
        }
        
        return messaggi;
    }
}
