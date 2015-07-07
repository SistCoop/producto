package org.sistcoop.producto.services.managers;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoop.producto.models.CaracteristicaModel;
import org.sistcoop.producto.representations.idm.CaracteristicaRepresentation;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class CaracteristicaManager {

    public void updateCaracteristica(CaracteristicaModel model, CaracteristicaRepresentation rep) {

    }

}