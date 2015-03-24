package org.sistcoop.producto.models.utils;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoop.producto.models.ProductoCreditoModel;
import org.sistcoop.producto.models.ProductoCreditoProvider;
import org.sistcoop.producto.models.ProductoCuentaPersonalModel;
import org.sistcoop.producto.models.ProductoCuentaPersonalProvider;
import org.sistcoop.producto.models.enums.TipoPersona;
import org.sistcoop.producto.representations.idm.ProductoCreditoRepresentation;
import org.sistcoop.producto.representations.idm.ProductoMonedaRepresentation;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class RepresentationToModel {

	public ProductoCuentaPersonalModel createProductoCuentaPersonal(ProductoCreditoRepresentation rep, ProductoCuentaPersonalProvider provider) {
		
		List<String> monedas = new ArrayList<String>();
		for (ProductoMonedaRepresentation productoMonedaRepresentation : rep.getMonedas()) {
			monedas.add(productoMonedaRepresentation.getMoneda());
		}
		
		ProductoCuentaPersonalModel model = provider.addProductoCredito(
				rep.getDenominacion(), 
				TipoPersona.valueOf(rep.getTipoPersona()), 
				monedas);
		
		return model;
	}

	public ProductoCreditoModel createProductoCredito(ProductoCreditoRepresentation rep, ProductoCreditoProvider provider) {

		List<String> monedas = new ArrayList<String>();
		for (ProductoMonedaRepresentation productoMonedaRepresentation : rep.getMonedas()) {
			monedas.add(productoMonedaRepresentation.getMoneda());
		}

		ProductoCreditoModel model = provider.addProductoCredito(
				rep.getDenominacion(), 
				TipoPersona.valueOf(rep.getTipoPersona()), 				
				rep.getMontoMinimo(),
				rep.getMontoMaximo());

		//falta crear las monedas
		return model;
	}

}
