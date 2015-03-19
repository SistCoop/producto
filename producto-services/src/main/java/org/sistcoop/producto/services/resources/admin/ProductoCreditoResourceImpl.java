package org.sistcoop.producto.services.resources.admin;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import org.sistcoop.producto.client.resource.ProductoCreditoResource;
import org.sistcoop.producto.representations.idm.ProductoCreditoRepresentation;

@Stateless
public class ProductoCreditoResourceImpl implements ProductoCreditoResource {

	@Override
	public ProductoCreditoRepresentation findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response create(ProductoCreditoRepresentation tipoDocumentoRepresentation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(String id, ProductoCreditoRepresentation tipoDocumentoRepresentation) {
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
	public List<ProductoCreditoRepresentation> findAll(String tipoPersona, Boolean estado) {
		// TODO Auto-generated method stub
		return null;
	}

}
