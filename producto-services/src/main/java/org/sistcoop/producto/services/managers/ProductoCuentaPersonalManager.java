package org.sistcoop.producto.services.managers;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoop.producto.models.ProductoCuentaPersonalModel;
import org.sistcoop.producto.representations.idm.ProductoCuentaPersonalRepresentation;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ProductoCuentaPersonalManager {

    public void updateProducto(ProductoCuentaPersonalModel model, ProductoCuentaPersonalRepresentation rep) {

    }

}