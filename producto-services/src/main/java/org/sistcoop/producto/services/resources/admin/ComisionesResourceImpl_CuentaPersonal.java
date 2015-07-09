package org.sistcoop.producto.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.producto.Jsend;
import org.sistcoop.producto.client.resource.ComisionResource;
import org.sistcoop.producto.client.resource.ComisionesResource;
import org.sistcoop.producto.models.ComisionModel;
import org.sistcoop.producto.models.ComisionProvider;
import org.sistcoop.producto.models.ProductoCuentaPersonalModel;
import org.sistcoop.producto.models.ProductoCuentaPersonalProvider;
import org.sistcoop.producto.models.ProductoModel;
import org.sistcoop.producto.models.search.SearchResultsModel;
import org.sistcoop.producto.models.utils.ModelToRepresentation;
import org.sistcoop.producto.models.utils.RepresentationToModel;
import org.sistcoop.producto.representations.idm.ComisionRepresentation;
import org.sistcoop.producto.representations.idm.search.SearchResultsRepresentation;
import org.sistcoop.producto.services.resources.producers.Comisiones_CuentaPersonal;

@Stateless
@Comisiones_CuentaPersonal
public class ComisionesResourceImpl_CuentaPersonal implements ComisionesResource {

    @PathParam("producto")
    private String producto;

    @Inject
    private ProductoCuentaPersonalProvider productoCuentaPersonalProvider;

    @Inject
    private ComisionProvider comisionProvider;

    @Context
    private UriInfo uriInfo;

    @Inject
    private RepresentationToModel representationToModel;

    @Inject
    private ComisionResource comisionResource;

    private ProductoCuentaPersonalModel getProductoCuentaPersonalModel() {
        return productoCuentaPersonalProvider.findById(producto);
    }

    @Override
    public ComisionResource comision(String comision) {
        return comisionResource;
    }

    @Override
    public Response create(ComisionRepresentation representation) {
        ComisionModel model = representationToModel.createComision(representation,
                getProductoCuentaPersonalModel(), comisionProvider);

        return Response.created(uriInfo.getAbsolutePathBuilder().path(model.getId()).build())
                .header("Access-Control-Expose-Headers", "Location")
                .entity(Jsend.getSuccessJSend(model.getId())).build();
    }

    @Override
    public SearchResultsRepresentation<ComisionRepresentation> search() {
        ProductoModel productoModel = getProductoCuentaPersonalModel();
        SearchResultsModel<ComisionModel> results = comisionProvider.search(productoModel);

        SearchResultsRepresentation<ComisionRepresentation> rep = new SearchResultsRepresentation<>();
        List<ComisionRepresentation> representations = new ArrayList<>();
        for (ComisionModel model : results.getBeans()) {
            representations.add(ModelToRepresentation.toRepresentation(model));
        }
        rep.setTotalSize(results.getTotalSize());
        rep.setItems(representations);
        return rep;
    }

}
