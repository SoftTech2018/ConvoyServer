/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package convoy.backend;

import brugerautorisation.transport.rmi.ConvoyBackendRmi;
import com.google.gson.Gson;
import convoy.db.Connecter;
import convoy.db.Spot;
import convoy.db.SpotsDAO;
import diverse.SpotsContainer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.JSONException;


/**
 * REST Web Service
 *
 * @author ministeren
 */
@Path("convoy")
public class ConvoyResource {
    
    private Gson gson;
    private Connecter con;
    private ConvoyBackendRmi ba;
    private SpotsDAO dao;
    private ArrayList<Spot> spotList;
    private long spotListUpdated;
    private long limit = 300000; // 300 sekunder = 5 minutter
    String newToken = null;
    
    @Context
    private UriInfo context;
    
    /**
     * Creates a new instance of ConvoyResource
     */
    public ConvoyResource() {
        gson = new Gson();
        con = new Connecter();
        try {
            ba = new ConvoyBackendRmi();
        } catch (Exception ex) {
            Logger.getLogger(ConvoyResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            con.connect("", "");
            dao = new SpotsDAO(con);
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
            Logger.getLogger(ConvoyResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            spotList = dao.getSpots();
            spotListUpdated = System.currentTimeMillis();
        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(ConvoyResource.class.getName()).log(Level.SEVERE, null, ex);
        }
//        createTestData();
    }
    
    // Opret noget testdata
    private void createTestData(){
        spotList = new ArrayList();
        long time = System.currentTimeMillis();
        spotList.add(new Spot(1, false, true, false, true, true, false, false, 12.01486, 55.484564, "Test1", 1, false));
        spotList.add(new Spot(2, false, true, false, true, true, false, false, 12.01486 +0.1, 55.484564, "Test2", time - (time / 6), false));
        spotList.add(new Spot(3, false, true, false, true, true, false, false, 12.01486 +0.2, 55.484564, "Test3", time - (time / 3), true));
        spotList.add(new Spot(4, false, true, false, true, true, false, false, 12.01486 +0.3, 55.484564, "Test4", time + limit, false));
        spotList.add(new Spot(5, false, true, false, true, true, false, false, 12.01486 +0.4, 55.484564, "Test5", time + limit, false));
        spotListUpdated = System.currentTimeMillis();
    }
    
    /**
     * Retrieves representation of an instance of convoy.backend.ConvoyResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        
        return null;
    }
    
//    /**
//     * PUT method for updating or creating an instance of ConvoyResource
//     * @param content representation for the resource
//     */
//    @PUT
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void putJson(String content) {
//    }
    
    @POST
    @Path("/create/spot")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postCreate(String spot){
        Spot addSpot = gson.fromJson(spot, Spot.class);
        try {
            dao.createSpot(addSpot);
        } catch (SQLException ex) {
            Logger.getLogger(ConvoyResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return spot;
    }
    
    @PUT
    @Path("/edit/spot")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String putEdit(String spot){
        Spot input = gson.fromJson(spot, Spot.class);
        System.out.println("id: "+input.getId());
        System.out.println("name: "+input.getName());
        
        try {
            dao.updateSpot(input);
        } catch (SQLException ex) {
            Logger.getLogger(ConvoyResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return spot;
    }
    
    @GET
    @Path("/get_id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getId(@PathParam("id") String id){
        return id;
    }
    
    @GET
    @Path("/get_all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll(){
        // URL: http://localhost:8080/ConvoyServer/webresources/convoy/get_all
        if((System.currentTimeMillis() - spotListUpdated) > limit){ // Hent en "frisk" liste fra serveren hvis der er gået for lang tid
            String json;
            try {
                json = gson.toJson(dao.getSpots());
            } catch (SQLException ex) {
                Logger.getLogger(ConvoyResource.class.getName()).log(Level.SEVERE, null, ex);
                return gson.toJson(spotList); // Returner nuværende liste hvis DB er offline
            }
            return json;
        } else {
            return gson.toJson(spotList);
        }
    }
    
    @GET
    @Path("/get_last/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getLast(@PathParam("date") String navn){
        // URL: http://localhost:8080/ConvoyServer/webresources/convoy/get_last/0
        long date;
        try{
            date = Long.parseLong(navn);
        } catch (Exception e){ // Hvis der er ugyldigt tidspunkt returneres hele databasen med opdateringstidspunktet
            return gson.toJson(new SpotsContainer(spotList, spotListUpdated));
        }
        try {
            SpotsContainer data = new SpotsContainer(dao.getUpdatedSpots(date), System.currentTimeMillis());
            return gson.toJson(data);
        } catch (SQLException ex) { // Hvis databasen fejler returneres hele den cachede database med opdateringstidspunktet
            Logger.getLogger(ConvoyResource.class.getName()).log(Level.SEVERE, null, ex);
            return gson.toJson(new SpotsContainer(spotList, spotListUpdated));
        }
    }
    
    @GET
    @Path("/get_user/")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUser(@QueryParam("name") String name, @QueryParam("pass") String pass){
        try {
            newToken = ba.adminLogin(name, pass);
        } catch (Exception ex) {
            Logger.getLogger(ConvoyResource.class.getName()).log(Level.SEVERE, null, ex);
        }
//        return name+" "+pass;
return newToken;
    }
    
    @GET
    @Path("/get_dawa/{adresse}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getDawa(@PathParam("adresse") String adresse){
        
        Client client = ClientBuilder.newClient();
        JSONArray jsonarray = null;
        
        //Kalder GET - get() på target og accepterer JSON
        Response res = client.target("http://dawa.aws.dk/adresser?q="+adresse).request(MediaType.APPLICATION_JSON).get();
        
        //Sætter svaret til at være en string        
        
        return res.readEntity(String.class);
    }
}
