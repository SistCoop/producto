package org.sistcoop.producto.models.utils;

import java.util.ArrayList;
import java.util.List;

import org.sistcoop.producto.models.ProductoCreditoModel;
import org.sistcoop.producto.models.ProductoCuentaPersonalModel;
import org.sistcoop.producto.models.ProductoTasaModel;
import org.sistcoop.producto.representations.idm.ProductoCreditoRepresentation;
import org.sistcoop.producto.representations.idm.ProductoCuentaPersonalRepresentation;
import org.sistcoop.producto.representations.idm.ProductoTasaRepresentation;

public class ModelToRepresentation {

	public static ProductoCuentaPersonalRepresentation toRepresentation(ProductoCuentaPersonalModel model) {
		if (model == null)
			return null;

		ProductoCuentaPersonalRepresentation rep = new ProductoCuentaPersonalRepresentation();
		rep.setDenominacion(model.getDenominacion());
		rep.setTipoPersona(model.getTipoPersona().toString());

		List<ProductoTasaModel> productoTasaModels = model.getTasas();
		List<ProductoTasaRepresentation> productoTasaRepresentations = new ArrayList<ProductoTasaRepresentation>();
		for (ProductoTasaModel productoTasaModel : productoTasaModels) {
			ProductoTasaRepresentation productoTasaRepresentation = new ProductoTasaRepresentation();
			productoTasaRepresentation.setId(productoTasaModel.getId());
			productoTasaRepresentation.setTasa(productoTasaModel.getTasa());
			productoTasaRepresentation.setValor(productoTasaModel.getValor());
			productoTasaRepresentations.add(productoTasaRepresentation);
		}
		rep.setTasas(productoTasaRepresentations);

		return rep;
	}

	public static ProductoCreditoRepresentation toRepresentation(ProductoCreditoModel model) {
		if (model == null)
			return null;

		ProductoCreditoRepresentation rep = new ProductoCreditoRepresentation();
		rep.setDenominacion(model.getDenominacion());
		rep.setTipoPersona(model.getTipoPersona().toString());
		rep.setMontoMinimo(model.getMontoMinimo());
		rep.setMontoMaximo(model.getMontoMaximo());

		List<ProductoTasaModel> productoTasaModels = model.getTasas();
		List<ProductoTasaRepresentation> productoTasaRepresentations = new ArrayList<ProductoTasaRepresentation>();
		for (ProductoTasaModel productoTasaModel : productoTasaModels) {
			ProductoTasaRepresentation productoTasaRepresentation = new ProductoTasaRepresentation();
			productoTasaRepresentation.setId(productoTasaModel.getId());
			productoTasaRepresentation.setTasa(productoTasaModel.getTasa());
			productoTasaRepresentation.setValor(productoTasaModel.getValor());
			productoTasaRepresentations.add(productoTasaRepresentation);
		}
		rep.setTasas(productoTasaRepresentations);

		return rep;
	}

}
