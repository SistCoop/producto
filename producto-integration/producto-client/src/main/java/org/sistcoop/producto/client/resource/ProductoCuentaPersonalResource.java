package org.sistcoop.producto.client.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sistcoop.producto.representations.idm.ProductoCuentaPersonalRepresentation;

public interface ProductoCuentaPersonalResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ProductoCuentaPersonalRepresentation producto();

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(ProductoCuentaPersonalRepresentation representation);

    @DELETE
    public void remove();

    @POST
    @Path("/disable")
    public void disable();
    
    @Path("/caracteristicas")
    public CaracteristicasResource caracteristicas();

    @Path("/tasas")
    public TasasResource tasas();

    @Path("/comisiones")
    public ComisionesResource comisiones();

}
