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
import org.sistcoop.producto.models.utils.ModelToRepresentation;
import org.sistcoop.producto.models.utils.RepresentationToModel;
import org.sistcoop.producto.representations.idm.CaracteristicaRepresentation;
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
    @Caracteristicas_CuentaPersonal
    private CaracteristicaResource caracteristicaResource;

    @Override
    public CaracteristicaResource caracteristica(String caracteristica) {
        return caracteristicaResource;
    }

    private ProductoCuentaPersonalModel getProductoCuentaPersonalModel() {
        return productoCuentaPersonalProvider.getProductoById(producto);
    }

    @Override
    public Response create(CaracteristicaRepresentation caracteristicaRepresentation) {
        CaracteristicaModel caracteristicaModel = representationToModel.createCaracteristica(
                getProductoCuentaPersonalModel(), caracteristicaRepresentation, caracteristicaProvider);

        return Response.created(uriInfo.getAbsolutePathBuilder().path(caracteristicaModel.getId()).build())
                .header("Access-Control-Expose-Headers", "Location")
                .entity(Jsend.getSuccessJSend(caracteristicaModel.getId())).build();
    }

    @Override
    public List<CaracteristicaRepresentation> search() {
        List<CaracteristicaModel> list = caracteristicaProvider
                .getCaracteristicas(getProductoCuentaPersonalModel());
        List<CaracteristicaRepresentation> result = new ArrayList<>();
        for (CaracteristicaModel caracteristicaModel : list) {
            result.add(ModelToRepresentation.toRepresentation(caracteristicaModel));
        }
        return result;
    }

}
