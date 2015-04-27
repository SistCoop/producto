package org.sistcoop.producto.client.resource;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
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

import org.hibernate.validator.constraints.NotBlank;
import org.sistcoop.producto.representations.idm.ProductoCreditoRepresentation;


@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/creditos")
public interface ProductoCreditoResource {
	
	@GET
	@Path("/{id}")
	public ProductoCreditoRepresentation findById(
			@PathParam("id") 
			@NotNull 
			@Min(1) Integer id);
	
	@GET
	@Path("/buscar/{codigo}")
	public ProductoCreditoRepresentation findByCodigo(
			@PathParam("codigo") 
			@NotNull 
			@NotBlank
			@Size(min = 1, max = 20) String codigo);
	
	@GET
	@Path("/buscar/{denominacion}")
	public ProductoCreditoRepresentation findByDenominacion(
			@PathParam("denominacion") 
			@NotNull 
			@NotBlank
			@Size(min = 1, max = 100) String denominacion);

	@POST
	public Response create(
			@NotNull 
			@Valid ProductoCreditoRepresentation productoCreditoRepresentation);

	@PUT
	@Path("/{id}")
	public void update(
			@PathParam("id")
			@NotNull 
			@Min(value = 1) int id,
			
			@NotNull 
			@Valid ProductoCreditoRepresentation productoCreditoRepresentacion);

	@DELETE
	@Path("/{id}/delete")
	public void delete(
			@PathParam("id") 
			@NotNull 
			@Size(min = 1, max = 20) int id);
	
	@POST
	@Path("/{id}/desactivar")
	public void desactivar(
			@PathParam("id") 
			@NotNull 
			@Size(min = 1, max = 20) int id);
	
	@GET
	public List<ProductoCreditoRepresentation> findAll(
			@QueryParam("filterText")
			@Size(min = 0, max = 100) String filterText, 
			
			@QueryParam("firstResult") 
			@Min(value = 0) Integer firstResult, 
			
			@QueryParam("maxResults") 
			@Min(value = 1) Integer maxResults,
			
			@QueryParam("tipoPersona") 
			@Size(min = 1, max = 20) String tipoPersona,
			
			@QueryParam("monedas") 
			List<String> monedas,
			
			@QueryParam("estado") Boolean estado);
}
