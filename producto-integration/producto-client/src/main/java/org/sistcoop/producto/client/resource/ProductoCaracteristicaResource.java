package org.sistcoop.producto.client.resource;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sistcoop.producto.representations.idm.ProductoCaracteristicaRepresentation;

@Path("/productoCaracteristicas")
public interface ProductoCaracteristicaResource {

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ProductoCaracteristicaRepresentation findById(
			@PathParam("id") 
			@NotNull 
			@Min(1) Integer id);

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(
			@PathParam("id") 
			@NotNull 
			@Min(value = 1) Integer id,

			@NotNull @Valid ProductoCaracteristicaRepresentation productoCaracteristicaRepresentation);

	@DELETE
	@Path("/{id}")
	public void delete(
			@PathParam("id") 
			@NotNull
			@Min(1) Integer id);

}
