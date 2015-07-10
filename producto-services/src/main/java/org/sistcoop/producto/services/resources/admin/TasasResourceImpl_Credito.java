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
import org.sistcoop.producto.client.resource.TasaResource;
import org.sistcoop.producto.client.resource.TasasResource;
import org.sistcoop.producto.models.ProductoCreditoModel;
import org.sistcoop.producto.models.ProductoCreditoProvider;
import org.sistcoop.producto.models.ProductoModel;
import org.sistcoop.producto.models.TasaModel;
import org.sistcoop.producto.models.TasaProvider;
import org.sistcoop.producto.models.search.SearchResultsModel;
import org.sistcoop.producto.models.utils.ModelToRepresentation;
import org.sistcoop.producto.models.utils.RepresentationToModel;
import org.sistcoop.producto.representations.idm.TasaRepresentation;
import org.sistcoop.producto.representations.idm.search.SearchResultsRepresentation;
import org.sistcoop.producto.services.resources.producers.Tasas_Credito;

@Stateless
@Tasas_Credito
public class TasasResourceImpl_Credito implements TasasResource {

    @PathParam("producto")
    private String producto;

    @Inject
    private ProductoCreditoProvider productoCreditoProvider;

    @Inject
    private TasaProvider tasaProvider;

    @Context
    private UriInfo uriInfo;

    @Inject
    private RepresentationToModel representationToModel;

    @Inject
    @Tasas_Credito
    private TasaResource tasaResource;

    private ProductoCreditoModel getProductoCreditoModel() {
        return productoCreditoProvider.findById(producto);
    }

    @Override
    public TasaResource tasa(String tasa) {
        return tasaResource;
    }

    @Override
    public Response create(TasaRepresentation representation) {
        TasaModel model = representationToModel.createTasa(representation, getProductoCreditoModel(),
                tasaProvider);

        return Response.created(uriInfo.getAbsolutePathBuilder().path(model.getId()).build())
                .header("Access-Control-Expose-Headers", "Location")
                .entity(Jsend.getSuccessJSend(model.getId())).build();
    }

    @Override
    public SearchResultsRepresentation<TasaRepresentation> search() {
        ProductoModel productoModel = getProductoCreditoModel();
        SearchResultsModel<TasaModel> results = tasaProvider.search(productoModel);

        SearchResultsRepresentation<TasaRepresentation> rep = new SearchResultsRepresentation<>();
        List<TasaRepresentation> representations = new ArrayList<>();
        for (TasaModel model : results.getModels()) {
            representations.add(ModelToRepresentation.toRepresentation(model));
        }
        rep.setTotalSize(results.getTotalSize());
        rep.setItems(representations);
        return rep;
    }

}
