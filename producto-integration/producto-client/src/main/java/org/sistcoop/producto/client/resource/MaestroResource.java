package org.sistcoop.producto.client.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sistcoop.producto.representations.idm.FrecuenciaRepresentation;
import org.sistcoop.producto.representations.idm.TipoValorRepresentation;

@Produces(MediaType.APPLICATION_JSON)
@Path("/")
public interface MaestroResource {

	@GET
	@Path("/tipoValores")
	public List<TipoValorRepresentation> getTipoValores();

	@GET
	@Path("/frecuencias")
	public List<FrecuenciaRepresentation> getFrecuencias();

}
