/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diverse;


import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import convoy.db.Spot;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author KimDrewes
 * Klasse der indeholder  metoder til at udpakke et json Arrays og objekter
 * Bruges ved at 
 */
public class JsonParser {
     
 
   private ArrayList<Spot> parseJSONArray(JSONArray jArray) throws JSONException{
          ArrayList<Spot> spots = new ArrayList<>();
  
        JSONObject jo;
        JSONArray ja = jArray;
            for (int i = 0; i < ja.length(); i++) {
                jo = ja.getJSONObject(i);
                Spot spot = new Spot(jo.getInt("id"),jo.getBoolean("addBlue"),jo.getBoolean("food"),jo.getBoolean("wc"),
                                     jo.getBoolean("bed"),jo.getBoolean("bath"),jo.getBoolean("fuel"),jo.getBoolean("roadtrain"), jo.getDouble("longitude"), jo.getDouble("latitude"),
                                    jo.getString("name"), jo.getInt("lastUpdated"), jo.getBoolean("deleted"));
               spots.add(spot);
            }
  return spots;
    }
   
   
   public  ArrayList<Spot> hentJsonFraServer(){   //Instantierer en client
    Client client = ClientBuilder.newClient();
   
         //TODO INDSÆT SERVERNAVN!
         //Kalder GET - get() på target og accepterer JSON
        Response res = client.target("INDSÆT VORES RESTSERVER").request(MediaType.APPLICATION_JSON).get();
     
        //Sætter svaret til at være en string
        String resultString = res.readEntity(String.class);
        ArrayList<Spot> spots;
    
     try {
         //DAWA rest apiet bliver sendt som et stort JSONarray.
           JSONArray jsonarray = new JSONArray(resultString);
           spots = parseJSONArray(jsonarray);
     }catch(JSONException e){
         e.printStackTrace();
     }
     return null;
   }
}





