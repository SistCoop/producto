package org.sistcoop.producto.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.producto.Jsend;
import org.sistcoop.producto.client.resource.ProductoCuentaPersonalResource;
import org.sistcoop.producto.client.resource.ProductosCuentaPersonalResource;
import org.sistcoop.producto.models.ProductoCuentaPersonalModel;
import org.sistcoop.producto.models.ProductoCuentaPersonalProvider;
import org.sistcoop.producto.models.utils.ModelToRepresentation;
import org.sistcoop.producto.models.utils.RepresentationToModel;
import org.sistcoop.producto.representations.idm.ProductoCuentaPersonalRepresentation;

@Stateless
public class ProductosCuentaPersonalResourceImpl implements ProductosCuentaPersonalResource {

    @Inject
    private ProductoCuentaPersonalProvider productoCuentaPersonalProvider;

    @Inject
    private RepresentationToModel representationToModel;

    @Context
    private UriInfo uriInfo;

    @Inject
    private ProductoCuentaPersonalResource productoCuentaPersonalResource;

    @Override
    public ProductoCuentaPersonalResource producto(String producto) {
        return productoCuentaPersonalResource;
    }

    @Override
    public Response create(ProductoCuentaPersonalRepresentation productoCuentaPersonalRepresentation) {
        ProductoCuentaPersonalModel productoCuentaPersonalModel = representationToModel
                .createProductoCuentaPersonal(productoCuentaPersonalRepresentation,
                        productoCuentaPersonalProvider);

        return Response
                .created(uriInfo.getAbsolutePathBuilder().path(productoCuentaPersonalModel.getId()).build())
                .header("Access-Control-Expose-Headers", "Location")
                .entity(Jsend.getSuccessJSend(productoCuentaPersonalModel.getId())).build();
    }

    @Override
    public List<ProductoCuentaPersonalRepresentation> search(String tipoPersona, String tipoCuenta,
            String[] moneda, boolean estado, String filterText, Integer firstResult, Integer maxResults) {

        List<ProductoCuentaPersonalModel> list = productoCuentaPersonalProvider.getProductos(estado);
        List<ProductoCuentaPersonalRepresentation> result = new ArrayList<>();
        for (ProductoCuentaPersonalModel productoCuentaPersonalModel : list) {
            result.add(ModelToRepresentation.toRepresentation(productoCuentaPersonalModel));
        }
        return result;
    }

}
