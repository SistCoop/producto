package org.sistcoop.producto.client.resource;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sistcoop.producto.representations.idm.ProductoCuentaPersonalRepresentation;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/cuentasPersonales")
public interface ProductoCuentaPersonalResource {

	@GET
	@Path("/{id}")
	public ProductoCuentaPersonalRepresentation findById(
			@PathParam("id") 
			@NotNull 
			@Size(min = 1, max = 20) String id);

	@POST
	public Response create(
			@NotNull 
			@Valid ProductoCuentaPersonalRepresentation tipoDocumentoRepresentation);

	@PUT
	@Path("/{id}")
	public void update(@PathParam("id") String id, 
			@NotNull 
			@Valid ProductoCuentaPersonalRepresentation tipoDocumentoRepresentation);

	@DELETE
	@Path("/{id}")
	public void delete(
			@PathParam("id") 
			@NotNull 
			@Size(min = 1, max = 20) String id);
	
	@POST
	@Path("/{id}/desactivar")
	public void desactivar(
			@PathParam("id") 
			@NotNull 
			@Size(min = 1, max = 20) String id);
	
	@GET
	public List<ProductoCuentaPersonalRepresentation> findAll(
			@QueryParam("tipoPersona") 
			@Size(min = 1, max = 20) String tipoPersona,
			
			@QueryParam("estado") Boolean estado);
}
