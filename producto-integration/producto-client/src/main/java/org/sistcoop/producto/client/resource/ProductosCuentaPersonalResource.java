package org.sistcoop.producto.client.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sistcoop.producto.representations.idm.ProductoCuentaPersonalRepresentation;
import org.sistcoop.producto.representations.idm.search.SearchResultsRepresentation;

@Path("/productosCuentaPersonal")
@Consumes(MediaType.APPLICATION_JSON)
public interface ProductosCuentaPersonalResource {

    @Path("/{producto}")
    public ProductoCuentaPersonalResource producto(@PathParam("producto") String producto);

    @POST
    public Response create(ProductoCuentaPersonalRepresentation representation);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public SearchResultsRepresentation<ProductoCuentaPersonalRepresentation> search(
            @QueryParam("tipoCuenta") String tipoCuenta, @QueryParam("tipoPersona") String tipoPersona,
            @QueryParam("moneda") String moneda,
            @QueryParam("estado") @DefaultValue(value = "true") boolean estado,
            @QueryParam("filterText") @DefaultValue(value = "") String filterText,
            @QueryParam("page") @DefaultValue(value = "-1") int page,
            @QueryParam("pageSize") @DefaultValue(value = "-1") int pageSize);

}
