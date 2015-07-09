package org.sistcoop.producto.services.managers;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoop.producto.models.ProductoCreditoModel;
import org.sistcoop.producto.models.enums.TipoPersona;
import org.sistcoop.producto.representations.idm.ProductoCreditoRepresentation;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ProductoCreditoManager {

    public void updateProducto(ProductoCreditoModel model, ProductoCreditoRepresentation rep) {
        model.setDenominacion(rep.getDenominacion());
        model.setMontoMinimo(rep.getMontoMinimo());
        model.setMontoMaximo(rep.getMontoMaximo());
        model.setTipoPersona(TipoPersona.valueOf(rep.getTipoPersona()));
        model.commit();
    }

    public void disableProducto(ProductoCreditoModel model) {
        model.desactivar();
        model.commit();
    }

}