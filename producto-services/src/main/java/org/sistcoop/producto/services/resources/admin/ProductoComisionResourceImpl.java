package org.sistcoop.producto.services.resources.admin;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;

import org.sistcoop.producto.client.resource.ProductoComisionResource;
import org.sistcoop.producto.models.ProductoComisionModel;
import org.sistcoop.producto.models.ProductoComisionProvider;
import org.sistcoop.producto.models.enums.Frecuencia;
import org.sistcoop.producto.models.enums.TipoValor;
import org.sistcoop.producto.models.utils.ModelToRepresentation;
import org.sistcoop.producto.representations.idm.ProductoComisionRepresentation;

@Stateless
public class ProductoComisionResourceImpl implements ProductoComisionResource {
	
	@Inject
	private ProductoComisionProvider productoComisionProvider;		

	@Override
	public ProductoComisionRepresentation findById(Integer id) {
		ProductoComisionModel model = productoComisionProvider.getProductoComisionById(id);
		ProductoComisionRepresentation rep = ModelToRepresentation.toRepresentation(model);
		return rep;
	}

	@Override
	public void update(Integer id,
			ProductoComisionRepresentation productoComisionRepresentation) {

		ProductoComisionModel model = productoComisionProvider.getProductoComisionById(id);
		
		if(model == null) {
			throw new NotFoundException("Comision no encontrada");			
		}
			
		model.setDenominacion(productoComisionRepresentation.getDenominacion());
		model.setValor(productoComisionRepresentation.getValor());
		model.setTipoValor(TipoValor.valueOf(productoComisionRepresentation.getTipoValor()));
		model.setFrecuencia(Frecuencia.valueOf(productoComisionRepresentation.getFrecuencia()));
		model.commit();
		
	}

	@Override
	public void delete(Integer id) {

		ProductoComisionModel model = productoComisionProvider.getProductoComisionById(id);
		
		if(model == null) {
			throw new NotFoundException("Comision no encontrada");			
		}
		
		boolean result = productoComisionProvider.eliminarProductoComision(model);
		
		if(!result){
			throw new InternalServerErrorException("Error interno, no se pudo eliminar la comision");
		}
	}

}
