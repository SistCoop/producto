package org.sistcoop.producto.models.utils;

import java.util.ArrayList;
import java.util.List;

import org.sistcoop.producto.models.CaracteristicaModel;
import org.sistcoop.producto.models.ComisionModel;
import org.sistcoop.producto.models.ProductoCreditoModel;
import org.sistcoop.producto.models.ProductoCuentaPersonalModel;
import org.sistcoop.producto.models.TasaModel;
import org.sistcoop.producto.representations.idm.CaracteristicaRepresentation;
import org.sistcoop.producto.representations.idm.ComisionRepresentation;
import org.sistcoop.producto.representations.idm.ProductoCreditoRepresentation;
import org.sistcoop.producto.representations.idm.ProductoCuentaPersonalRepresentation;
import org.sistcoop.producto.representations.idm.TasaRepresentation;

public class ModelToRepresentation {

    public static ProductoCuentaPersonalRepresentation toRepresentation(ProductoCuentaPersonalModel model) {
        if (model == null)
            return null;

        ProductoCuentaPersonalRepresentation rep = new ProductoCuentaPersonalRepresentation();
        rep.setId(model.getId());
        rep.setCodigo(model.getCodigo());
        rep.setDenominacion(model.getDenominacion());
        rep.setTipoPersona(model.getTipoPersona().toString());
        rep.setMoneda(model.getMoneda());
        rep.setEstado(model.getEstado());

        List<TasaModel> productoTasaModels = model.getTasas();
        List<TasaRepresentation> productoTasaRepresentations = new ArrayList<TasaRepresentation>();
        for (TasaModel productoTasaModel : productoTasaModels) {
            TasaRepresentation productoTasaRepresentation = new TasaRepresentation();
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
        rep.setId(model.getId());
        rep.setCodigo(model.getCodigo());
        rep.setDenominacion(model.getDenominacion());
        rep.setTipoPersona(model.getTipoPersona().toString());
        rep.setMontoMinimo(model.getMontoMinimo());
        rep.setMontoMaximo(model.getMontoMaximo());
        rep.setMoneda(model.getMoneda());
        rep.setEstado(model.getEstado());

        List<TasaModel> productoTasaModels = model.getTasas();
        List<TasaRepresentation> productoTasaRepresentations = new ArrayList<TasaRepresentation>();
        for (TasaModel productoTasaModel : productoTasaModels) {
            TasaRepresentation productoTasaRepresentation = new TasaRepresentation();
            productoTasaRepresentation.setId(productoTasaModel.getId());
            productoTasaRepresentation.setTasa(productoTasaModel.getTasa());
            productoTasaRepresentation.setValor(productoTasaModel.getValor());
            productoTasaRepresentations.add(productoTasaRepresentation);
        }
        rep.setTasas(productoTasaRepresentations);

        return rep;
    }

    public static CaracteristicaRepresentation toRepresentation(CaracteristicaModel model) {
        if (model == null)
            return null;

        CaracteristicaRepresentation rep = new CaracteristicaRepresentation();
        rep.setId(model.getId());
        rep.setDescripcion(model.getDescripcion());
        rep.setDescripcionDetallada(model.getDescripcionDetallada());

        return rep;
    }

    public static TasaRepresentation toRepresentation(TasaModel model) {
        if (model == null)
            return null;

        TasaRepresentation rep = new TasaRepresentation();
        rep.setId(model.getId());
        rep.setTasa(model.getTasa());
        rep.setValor(model.getValor());

        return rep;
    }

    public static ComisionRepresentation toRepresentation(ComisionModel model) {
        if (model == null)
            return null;

        ComisionRepresentation rep = new ComisionRepresentation();
        rep.setId(model.getId());
        rep.setDenominacion(model.getDenominacion());
        rep.setValor(model.getValor());
        rep.setTipoValor(model.getTipoValor().toString());
        rep.setFrecuencia(model.getFrecuencia().toString());

        return rep;
    }

}
