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
import org.sistcoop.producto.client.resource.CaracteristicaResource;
import org.sistcoop.producto.client.resource.CaracteristicasResource;
import org.sistcoop.producto.models.CaracteristicaModel;
import org.sistcoop.producto.models.CaracteristicaProvider;
import org.sistcoop.producto.models.ProductoCuentaPersonalModel;
import org.sistcoop.producto.models.ProductoCuentaPersonalProvider;
import org.sistcoop.producto.models.ProductoModel;
import org.sistcoop.producto.models.search.SearchResultsModel;
import org.sistcoop.producto.models.utils.ModelToRepresentation;
import org.sistcoop.producto.models.utils.RepresentationToModel;
import org.sistcoop.producto.representations.idm.CaracteristicaRepresentation;
import org.sistcoop.producto.representations.idm.search.SearchResultsRepresentation;
import org.sistcoop.producto.services.resources.producers.Caracteristicas_CuentaPersonal;

@Stateless
@Caracteristicas_CuentaPersonal
public class CaracteristicasResourceImpl_CuentaPersonal implements CaracteristicasResource {

    @PathParam("producto")
    private String producto;

    @Inject
    private ProductoCuentaPersonalProvider productoCuentaPersonalProvider;

    @Inject
    private CaracteristicaProvider caracteristicaProvider;

    @Context
    private UriInfo uriInfo;

    @Inject
    private RepresentationToModel representationToModel;

    @Inject
    private CaracteristicaResource caracteristicaResource;

    @Override
    public CaracteristicaResource caracteristica(String caracteristica) {
        return caracteristicaResource;
    }

    private ProductoCuentaPersonalModel getProductoCuentaPersonalModel() {
        return productoCuentaPersonalProvider.findById(producto);
    }

    @Override
    public Response create(CaracteristicaRepresentation representation) {
        CaracteristicaModel model = representationToModel.createCaracteristica(representation,
                getProductoCuentaPersonalModel(), caracteristicaProvider);

        return Response.created(uriInfo.getAbsolutePathBuilder().path(model.getId()).build())
                .header("Access-Control-Expose-Headers", "Location")
                .entity(Jsend.getSuccessJSend(model.getId())).build();
    }

    @Override
    public SearchResultsRepresentation<CaracteristicaRepresentation> search() {
        ProductoModel productoModel = getProductoCuentaPersonalModel();
        SearchResultsModel<CaracteristicaModel> results = caracteristicaProvider.search(productoModel);

        SearchResultsRepresentation<CaracteristicaRepresentation> rep = new SearchResultsRepresentation<>();
        List<CaracteristicaRepresentation> representations = new ArrayList<>();
        for (CaracteristicaModel model : results.getModels()) {
            representations.add(ModelToRepresentation.toRepresentation(model));
        }
        rep.setTotalSize(results.getTotalSize());
        rep.setItems(representations);
        return rep;
    }

}
