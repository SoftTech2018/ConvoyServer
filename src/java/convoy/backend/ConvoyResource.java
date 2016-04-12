/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convoy.backend;

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
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author ministeren
 */
@Path("convoy")
public class ConvoyResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ConvoyResource
     */
    public ConvoyResource() {
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
    @Path("/create/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postCreate(@PathParam("id") String navn){
      return navn;
    }
    
    @PUT
    @Path("/edit/{spot}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String putEdit(@PathParam("spot") String spot){
        
      return spot;
    }
    
    @GET
    @Path("/get_id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getId(@PathParam("id") String navn){
      return navn;
    }
    
    @GET
    @Path("/get_all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll(@PathParam("id") String navn){
      return navn;
    }
    
    @GET
    @Path("/get_last/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getLast(@PathParam("date") String navn){
      return navn;
    }
    
    @GET
    @Path("/get_user/")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUser(@QueryParam("name") String name, @QueryParam("pass") String pass){
      
      return name+" "+pass;
    }
}
