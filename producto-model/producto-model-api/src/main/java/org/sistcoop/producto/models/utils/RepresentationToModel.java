package org.sistcoop.producto.models.utils;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoop.producto.models.ProductoCaracteristicaModel;
import org.sistcoop.producto.models.ProductoCaracteristicaProvider;
import org.sistcoop.producto.models.ProductoComisionModel;
import org.sistcoop.producto.models.ProductoComisionProvider;
import org.sistcoop.producto.models.ProductoCreditoModel;
import org.sistcoop.producto.models.ProductoCreditoProvider;
import org.sistcoop.producto.models.ProductoCuentaPersonalModel;
import org.sistcoop.producto.models.ProductoCuentaPersonalProvider;
import org.sistcoop.producto.models.ProductoModel;
import org.sistcoop.producto.models.ProductoTasaModel;
import org.sistcoop.producto.models.ProductoTasaProvider;
import org.sistcoop.producto.models.enums.Frecuencia;
import org.sistcoop.producto.models.enums.TipoPersona;
import org.sistcoop.producto.models.enums.TipoValor;
import org.sistcoop.producto.representations.idm.ProductoCaracteristicaRepresentation;
import org.sistcoop.producto.representations.idm.ProductoComisionRepresentation;
import org.sistcoop.producto.representations.idm.ProductoCreditoRepresentation;
import org.sistcoop.producto.representations.idm.ProductoCuentaPersonalRepresentation;
import org.sistcoop.producto.representations.idm.ProductoTasaRepresentation;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class RepresentationToModel {

	public ProductoCuentaPersonalModel createProductoCuentaPersonal(ProductoCuentaPersonalRepresentation rep, ProductoCuentaPersonalProvider provider) {
		//TODO here
		return null;
	}

	public ProductoCreditoModel createProductoCredito(ProductoCreditoRepresentation rep, ProductoCreditoProvider provider) {

		ProductoCreditoModel model = provider.addProductoCredito(
				rep.getCodigo(),
				rep.getDenominacion(), 
				TipoPersona.valueOf(rep.getTipoPersona()),
				rep.getMoneda(),
				rep.getMontoMinimo(),
				rep.getMontoMaximo());

		return model;
	}

	public ProductoCaracteristicaModel createProductoCaracteristica(
			ProductoCaracteristicaRepresentation productoCaracteristicaRepresentation,
			ProductoModel productoModel,
			ProductoCaracteristicaProvider productoCaracteristicaProvider) {
		
		ProductoCaracteristicaModel model = productoCaracteristicaProvider.addProductoCaracteristica(
				productoModel, 
				productoCaracteristicaRepresentation.getDescripcion(), 
				productoCaracteristicaRepresentation.getDescripcionDetallada());
		
		return model;
	}

	public ProductoTasaModel createProductoTasa(
			ProductoTasaRepresentation productoTasaRepresentation,
			ProductoModel productoModel,
			ProductoTasaProvider productoTasaProvider) {

		ProductoTasaModel model = productoTasaProvider.addProductoTasa(
				productoModel, 
				productoTasaRepresentation.getTasa(), 
				productoTasaRepresentation.getValor());
		
		return model;
		
	}
	
	public ProductoComisionModel createProductoComision(
			ProductoComisionRepresentation productoComisionRepresentation,
			ProductoModel productoModel,
			ProductoComisionProvider productoComisionProvider) {

		ProductoComisionModel model = productoComisionProvider.addProductoComision(
				productoModel, 
				productoComisionRepresentation.getDenominacion(), 
				productoComisionRepresentation.getValor(), 
				TipoValor.valueOf(productoComisionRepresentation.getTipoValor()), 
				Frecuencia.valueOf(productoComisionRepresentation.getFrecuencia()));
		
		return model;
		
	}

}
