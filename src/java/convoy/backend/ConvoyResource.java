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
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
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
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of ConvoyResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @GET
    @Path("/admin")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUserJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }
}
