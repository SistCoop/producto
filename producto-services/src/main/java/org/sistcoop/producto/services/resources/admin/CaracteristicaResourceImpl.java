package org.sistcoop.producto.services.resources.admin;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.PathParam;

import org.sistcoop.producto.client.resource.CaracteristicaResource;
import org.sistcoop.producto.models.CaracteristicaModel;
import org.sistcoop.producto.models.CaracteristicaProvider;
import org.sistcoop.producto.models.utils.ModelToRepresentation;
import org.sistcoop.producto.representations.idm.CaracteristicaRepresentation;
import org.sistcoop.producto.services.managers.CaracteristicaManager;

@Stateless
public class CaracteristicaResourceImpl implements CaracteristicaResource {

    @PathParam("caracteristica")
    private String caracteristica;

    @Inject
    private CaracteristicaProvider caracteristicaProvider;

    @Inject
    private CaracteristicaManager caracteristicaManager;

    private CaracteristicaModel getCaracteristicaModel() {
        return caracteristicaProvider.findById(caracteristica);
    }

    @Override
    public CaracteristicaRepresentation caracteristica() {
        return ModelToRepresentation.toRepresentation(getCaracteristicaModel());
    }

    @Override
    public void update(CaracteristicaRepresentation representation) {
        caracteristicaManager.updateCaracteristica(getCaracteristicaModel(), representation);
    }

    @Override
    public void remove() {
        caracteristicaProvider.remove(getCaracteristicaModel());
    }

}
