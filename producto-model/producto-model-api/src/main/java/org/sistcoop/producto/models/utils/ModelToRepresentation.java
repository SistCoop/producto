package org.sistcoop.producto.models.utils;

import org.sistcoop.producto.models.ProductoCreditoModel;
import org.sistcoop.producto.representations.idm.ProductoCreditoRepresentation;

public class ModelToRepresentation {

	public static ProductoCreditoRepresentation toRepresentation(ProductoCreditoModel model) {
		if (model == null)
			return null;
		ProductoCreditoRepresentation rep = new ProductoCreditoRepresentation();

		return rep;
	}

}
