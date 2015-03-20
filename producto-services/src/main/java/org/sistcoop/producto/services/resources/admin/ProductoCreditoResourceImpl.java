package org.sistcoop.producto.services.resources.admin;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.sistcoop.producto.client.resource.ProductoCreditoResource;
import org.sistcoop.producto.models.ProductoCreditoModel;
import org.sistcoop.producto.models.ProductoCreditoProvider;
import org.sistcoop.producto.models.utils.ModelToRepresentation;
import org.sistcoop.producto.representations.idm.ProductoCreditoRepresentation;

@Stateless
public class ProductoCreditoResourceImpl implements ProductoCreditoResource {

	@Inject
	private ProductoCreditoProvider productoCreditoProvider;

	@Override
	public ProductoCreditoRepresentation findById(Integer id) {
		ProductoCreditoModel model = productoCreditoProvider.getProductoById(id);
		ProductoCreditoRepresentation rep = ModelToRepresentation.toRepresentation(model);
		return rep;
	}

	@Override
	public Response create(ProductoCreditoRepresentation productoCreditoRepresentation) {
		// ProductoCreditoModel model =
		// representationToModel.createProductoCredito(productoCreditoRepresentation,
		// productoCreditoProvider);
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
