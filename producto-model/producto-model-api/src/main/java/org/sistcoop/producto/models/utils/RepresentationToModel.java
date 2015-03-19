package org.sistcoop.producto.models.utils;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoop.producto.models.ProductoCreditoModel;
import org.sistcoop.producto.models.ProductoProvider;
import org.sistcoop.producto.representations.idm.ProductoCreditoRepresentation;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class RepresentationToModel {

	public ProductoCreditoModel createTipoDocumento(ProductoCreditoRepresentation rep, ProductoProvider provider) {
		// ProductoCreditoModel model =
		// provider.addTipoDocumento(rep.getAbreviatura(),
		// rep.getDenominacion(), rep.getCantidadCaracteres(),
		// TipoPersona.valueOf(rep.getTipoPersona()));
		// return model;
		return null;
	}

}
