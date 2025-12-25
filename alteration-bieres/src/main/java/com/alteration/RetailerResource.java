package com.alteration;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/retailers")
@Produces(MediaType.APPLICATION_JSON)
public class RetailerResource {

  @Inject
  DataStore dataStore;

  @GET
  public List<Retailer> all() {
    return dataStore.getRetailers();
  }

  @GET
  @Path("/{id}")
  public Response byId(@PathParam("id") String id) {
    return dataStore.getRetailerById(id)
      .<Response>map(Response::ok)
      .orElseGet(() -> Response.status(Response.Status.NOT_FOUND))
      .build();
  }
}
