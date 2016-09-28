package com.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("SampleRestService")
public class SampleRestService {
    // http://localhost:8080/JaxRsProject/rest/SampleRestService/Tushar
    @GET
    @Path("{name}")
    // @Produces(MediaType.TEXT_PLAIN)
    public Response getUserByName(@PathParam("name") String name) {

        return Response.status(200).entity("getUserByName is called, name : " + name).build();

    }
}
