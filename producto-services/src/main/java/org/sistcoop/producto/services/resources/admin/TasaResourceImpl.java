package org.sistcoop.producto.services.resources.admin;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.PathParam;

import org.sistcoop.producto.client.resource.TasaResource;
import org.sistcoop.producto.models.TasaModel;
import org.sistcoop.producto.models.TasaProvider;
import org.sistcoop.producto.models.utils.ModelToRepresentation;
import org.sistcoop.producto.representations.idm.TasaRepresentation;
import org.sistcoop.producto.services.managers.TasaManager;

@Stateless
public class TasaResourceImpl implements TasaResource {

    @PathParam("tasa")
    private String tasa;

    @Inject
    private TasaProvider tasaProvider;

    @Inject
    private TasaManager tasaManager;

    private TasaModel getTasaModel() {
        return tasaProvider.findById(tasa);
    }

    @Override
    public TasaRepresentation tasa() {
        return ModelToRepresentation.toRepresentation(getTasaModel());
    }

    @Override
    public void update(TasaRepresentation representation) {
        tasaManager.updateTasa(getTasaModel(), representation);
    }

    @Override
    public void remove() {
        tasaProvider.remove(getTasaModel());
    }

}
