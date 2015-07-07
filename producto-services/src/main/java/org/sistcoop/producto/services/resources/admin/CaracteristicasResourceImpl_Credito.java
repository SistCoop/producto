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
import org.sistcoop.producto.models.ProductoCreditoModel;
import org.sistcoop.producto.models.ProductoCreditoProvider;
import org.sistcoop.producto.models.utils.ModelToRepresentation;
import org.sistcoop.producto.models.utils.RepresentationToModel;
import org.sistcoop.producto.representations.idm.CaracteristicaRepresentation;
import org.sistcoop.producto.services.resources.producers.Caracteristicas_Credito;

@Stateless
@Caracteristicas_Credito
public class CaracteristicasResourceImpl_Credito implements CaracteristicasResource {

    @PathParam("producto")
    private String producto;

    @Inject
    private ProductoCreditoProvider productoCreditoProvider;

    @Inject
    private CaracteristicaProvider caracteristicaProvider;

    @Context
    private UriInfo uriInfo;

    @Inject
    private RepresentationToModel representationToModel;

    @Inject
    @Caracteristicas_Credito
    private CaracteristicaResource caracteristicaResource;

    private ProductoCreditoModel getProductoCreditoModel() {
        return productoCreditoProvider.getProductoCreditoById(producto);
    }

    @Override
    public CaracteristicaResource caracteristica(String caracteristica) {
        return caracteristicaResource;
    }

    @Override
    public Response create(CaracteristicaRepresentation caracteristicaRepresentation) {
        CaracteristicaModel caracteristicaModel = representationToModel.createCaracteristica(
                getProductoCreditoModel(), caracteristicaRepresentation, caracteristicaProvider);

        return Response.created(uriInfo.getAbsolutePathBuilder().path(caracteristicaModel.getId()).build())
                .header("Access-Control-Expose-Headers", "Location")
                .entity(Jsend.getSuccessJSend(caracteristicaModel.getId())).build();
    }

    @Override
    public List<CaracteristicaRepresentation> search() {
        List<CaracteristicaModel> list = caracteristicaProvider.getCaracteristicas(getProductoCreditoModel());
        List<CaracteristicaRepresentation> result = new ArrayList<>();
        for (CaracteristicaModel caracteristicaModel : list) {
            result.add(ModelToRepresentation.toRepresentation(caracteristicaModel));
        }
        return result;
    }

}
