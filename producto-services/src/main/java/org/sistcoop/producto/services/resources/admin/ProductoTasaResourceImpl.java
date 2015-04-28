package org.sistcoop.producto.services.resources.admin;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;

import org.sistcoop.producto.client.resource.ProductoTasaResource;
import org.sistcoop.producto.models.ProductoTasaModel;
import org.sistcoop.producto.models.ProductoTasaProvider;
import org.sistcoop.producto.models.utils.ModelToRepresentation;
import org.sistcoop.producto.representations.idm.ProductoTasaRepresentation;

@Stateless
public class ProductoTasaResourceImpl implements ProductoTasaResource {

	@Inject
	private ProductoTasaProvider productoTasaProvider;

	@Override
	public ProductoTasaRepresentation findById(Integer id) {
		ProductoTasaModel model = productoTasaProvider.getProductoTasaById(id);
		ProductoTasaRepresentation rep = ModelToRepresentation.toRepresentation(model);
		return rep;
	}

	@Override
	public void update(Integer id,
			ProductoTasaRepresentation productoTasaRepresentation) {

		ProductoTasaModel model = productoTasaProvider.getProductoTasaById(id);

		if (model == null) {
			throw new NotFoundException("Tasa no encontrada");
		}

		model.setValor(productoTasaRepresentation.getValor());
		model.commit();
	}

	@Override
	public void delete(Integer id) {
		ProductoTasaModel model = productoTasaProvider.getProductoTasaById(id);

		if (model == null) {
			throw new NotFoundException("Tasa no encontrada");
		}

		boolean result = productoTasaProvider.eliminarProductoTasa(model);

		if (!result) {
			throw new InternalServerErrorException("Error interno, no se pudo eliminar la tasa");
		}
	}

}
