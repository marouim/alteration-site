package com.alteration;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/beers")
@Produces(MediaType.APPLICATION_JSON)
public class BeerResource {

  @Inject
  DataStore dataStore;

  @GET
  public List<Beer> all() {
    return dataStore.getBeers();
  }

  @GET
  @Path("/{slug}")
  public Response bySlug(@PathParam("slug") String slug) {
    return dataStore.getBeerBySlug(slug)
      .map(beer -> Response.ok(beer).build())
      .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());
  }
}
