package org.sistcoop.producto.services.resources.admin;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import org.sistcoop.producto.client.resource.ProductoCuentaPersonalResource;
import org.sistcoop.producto.representations.idm.ProductoCuentaPersonalRepresentation;

@Stateless
public class ProductoCuentaPersonalResourceImpl implements ProductoCuentaPersonalResource {

	@Override
	public ProductoCuentaPersonalRepresentation findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response create(ProductoCuentaPersonalRepresentation tipoDocumentoRepresentation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(String id, ProductoCuentaPersonalRepresentation tipoDocumentoRepresentation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void desactivar(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ProductoCuentaPersonalRepresentation> findAll(String tipoPersona, Boolean estado) {
		// TODO Auto-generated method stub
		return null;
	}

}
