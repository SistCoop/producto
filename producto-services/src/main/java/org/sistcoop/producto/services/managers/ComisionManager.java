package org.sistcoop.producto.services.managers;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoop.producto.models.ComisionModel;
import org.sistcoop.producto.models.enums.Frecuencia;
import org.sistcoop.producto.models.enums.TipoValor;
import org.sistcoop.producto.representations.idm.ComisionRepresentation;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ComisionManager {

    public void updateComision(ComisionModel model, ComisionRepresentation representation) {
        model.setDenominacion(representation.getDenominacion());
        model.setFrecuencia(Frecuencia.valueOf(representation.getFrecuencia()));
        model.setTipoValor(TipoValor.valueOf(representation.getTipoValor()));
        model.setValor(representation.getValor());
        model.commit();
    }

}