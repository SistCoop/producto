package org.sistcoop.producto.client.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sistcoop.producto.representations.idm.CaracteristicaRepresentation;

@Consumes(MediaType.APPLICATION_JSON)
public interface CaracteristicasResource {

    @Path("/{caracteristica}")
    public CaracteristicaResource caracteristica(@PathParam("caracteristica") String caracteristica);

    @POST
    public Response create(CaracteristicaRepresentation caracteristicaRepresentation);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CaracteristicaRepresentation> search();

}
