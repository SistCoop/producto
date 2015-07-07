package org.sistcoop.producto.client.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sistcoop.producto.representations.idm.CaracteristicaRepresentation;

public interface CaracteristicaResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CaracteristicaRepresentation caracteristica();

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(CaracteristicaRepresentation caracteristicaRepresentation);

    @DELETE
    public void remove();

}
