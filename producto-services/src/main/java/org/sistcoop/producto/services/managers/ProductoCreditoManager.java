package org.sistcoop.producto.services.managers;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoop.producto.models.ProductoCreditoModel;
import org.sistcoop.producto.representations.idm.ProductoCreditoRepresentation;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ProductoCreditoManager {

    public void updateProducto(ProductoCreditoModel model, ProductoCreditoRepresentation rep) {

    }

}