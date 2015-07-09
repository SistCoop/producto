package org.sistcoop.producto.services.managers;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoop.producto.models.TasaModel;
import org.sistcoop.producto.representations.idm.TasaRepresentation;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class TasaManager {

    public void updateTasa(TasaModel model, TasaRepresentation representation) {
        model.setValor(representation.getValor());
        model.commit();
    }

}