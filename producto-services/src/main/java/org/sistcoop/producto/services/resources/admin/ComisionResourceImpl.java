package org.sistcoop.producto.services.resources.admin;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.PathParam;

import org.sistcoop.producto.client.resource.ComisionResource;
import org.sistcoop.producto.models.ComisionModel;
import org.sistcoop.producto.models.ComisionProvider;
import org.sistcoop.producto.models.utils.ModelToRepresentation;
import org.sistcoop.producto.representations.idm.ComisionRepresentation;
import org.sistcoop.producto.services.managers.ComisionManager;

@Stateless
public class ComisionResourceImpl implements ComisionResource {

    @PathParam("comision")
    private String comision;

    @Inject
    private ComisionProvider comisionProvider;

    @Inject
    private ComisionManager comisionManager;

    private ComisionModel getComisionModel() {
        return comisionProvider.findById(comision);
    }

    @Override
    public ComisionRepresentation comision() {
        return ModelToRepresentation.toRepresentation(getComisionModel());
    }

    @Override
    public void update(ComisionRepresentation representation) {
        comisionManager.updateComision(getComisionModel(), representation);
    }

    @Override
    public void remove() {
        comisionProvider.remove(getComisionModel());
    }

}
