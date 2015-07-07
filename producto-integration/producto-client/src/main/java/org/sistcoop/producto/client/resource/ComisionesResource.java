package org.sistcoop.producto.client.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sistcoop.producto.representations.idm.ComisionRepresentation;

public interface ComisionesResource {

    @Path("/{comision}")
    public ComisionResource comision(@PathParam("comision") String comision);

    @POST
    public Response create(ComisionRepresentation comisionRepresentation);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ComisionRepresentation> search();

}
