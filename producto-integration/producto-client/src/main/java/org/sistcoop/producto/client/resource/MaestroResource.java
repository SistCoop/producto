package org.sistcoop.producto.client.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sistcoop.producto.representations.idm.search.SearchResultsRepresentation;

@Produces(MediaType.APPLICATION_JSON)
@Path("/")
public interface MaestroResource {

    @GET
    @Path("/tipoValores")
    public SearchResultsRepresentation<String> getTipoValores();

    @GET
    @Path("/frecuencias")
    public SearchResultsRepresentation<String> getFrecuencias();

}
