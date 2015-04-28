package org.sistcoop.producto.services.resources.admin;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;

import org.sistcoop.producto.client.resource.ProductoCaracteristicaResource;
import org.sistcoop.producto.models.ProductoCaracteristicaModel;
import org.sistcoop.producto.models.ProductoCaracteristicaProvider;
import org.sistcoop.producto.models.utils.ModelToRepresentation;
import org.sistcoop.producto.representations.idm.ProductoCaracteristicaRepresentation;

@Stateless
public class ProductoCaracteristicaResourceImpl implements ProductoCaracteristicaResource {
	
	@Inject
	private ProductoCaracteristicaProvider productoCaracteristicaProvider;	

	@Override
	public ProductoCaracteristicaRepresentation findById(Integer id) {
		ProductoCaracteristicaModel model = productoCaracteristicaProvider.getProductoCaracteristicaById(id);
		ProductoCaracteristicaRepresentation rep = ModelToRepresentation.toRepresentation(model);
		return rep;
	}

	@Override
	public void update(
			Integer id,
			ProductoCaracteristicaRepresentation productoCaracteristicaRepresentation) {
		
		ProductoCaracteristicaModel model = productoCaracteristicaProvider.getProductoCaracteristicaById(id);
		
		if(model == null) {
			throw new NotFoundException("Caracteristica no encontrada");			
		}
			
		model.setDescripcion(productoCaracteristicaRepresentation.getDescripcion());
		model.setDescripcionDetallada(productoCaracteristicaRepresentation.getDescripcionDetallada());
		model.commit();
		
	}

	@Override
	public void delete(Integer id) {
		ProductoCaracteristicaModel model = productoCaracteristicaProvider.getProductoCaracteristicaById(id);
		
		if(model == null) {
			throw new NotFoundException("Caracteristica no encontrada");			
		}
		
		boolean result = productoCaracteristicaProvider.eliminarProductoCaracteristica(model);
		
		if(!result){
			throw new InternalServerErrorException("Error interno, no se pudo eliminar la caracteristica");
		}
		
	}

}
