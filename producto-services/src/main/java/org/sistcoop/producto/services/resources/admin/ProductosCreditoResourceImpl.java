package org.sistcoop.producto.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.producto.Jsend;
import org.sistcoop.producto.client.resource.ProductoCreditoResource;
import org.sistcoop.producto.client.resource.ProductosCreditoResource;
import org.sistcoop.producto.models.ProductoCreditoModel;
import org.sistcoop.producto.models.ProductoCreditoProvider;
import org.sistcoop.producto.models.utils.ModelToRepresentation;
import org.sistcoop.producto.models.utils.RepresentationToModel;
import org.sistcoop.producto.representations.idm.ProductoCreditoRepresentation;

@Stateless
public class ProductosCreditoResourceImpl implements ProductosCreditoResource {

    @Inject
    private ProductoCreditoProvider productoCreditoProvider;

    @Inject
    private RepresentationToModel representationToModel;

    @Context
    private UriInfo uriInfo;

    @Inject
    private ProductoCreditoResource productoCreditoResource;

    @Override
    public ProductoCreditoResource producto(String producto) {
        return productoCreditoResource;
    }

    @Override
    public Response create(ProductoCreditoRepresentation productoCreditoRepresentation) {
        ProductoCreditoModel productoCreditoModel = representationToModel.createProductoCredito(
                productoCreditoRepresentation, productoCreditoProvider);

        return Response.created(uriInfo.getAbsolutePathBuilder().path(productoCreditoModel.getId()).build())
                .header("Access-Control-Expose-Headers", "Location")
                .entity(Jsend.getSuccessJSend(productoCreditoModel.getId())).build();
    }

    @Override
    public List<ProductoCreditoRepresentation> search(boolean estado, String tipoPersona, String[] moneda,
            String filterText, Integer firstResult, Integer maxResults) {

        List<ProductoCreditoModel> list = productoCreditoProvider.getProductos(estado);
        List<ProductoCreditoRepresentation> result = new ArrayList<>();
        for (ProductoCreditoModel productoCreditoModel : list) {
            result.add(ModelToRepresentation.toRepresentation(productoCreditoModel));
        }
        return result;
    }

}
