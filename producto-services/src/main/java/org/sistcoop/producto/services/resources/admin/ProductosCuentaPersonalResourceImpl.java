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
import org.sistcoop.producto.models.ProductoCreditoModel;
import org.sistcoop.producto.models.ProductoCuentaPersonalModel;
import org.sistcoop.producto.models.ProductoCuentaPersonalProvider;
import org.sistcoop.producto.models.enums.TipoCuentaPersonal;
import org.sistcoop.producto.models.enums.TipoPersona;
import org.sistcoop.producto.models.search.SearchResultsModel;
import org.sistcoop.producto.models.utils.ModelToRepresentation;
import org.sistcoop.producto.models.utils.RepresentationToModel;
import org.sistcoop.producto.representations.idm.ProductoCreditoRepresentation;
import org.sistcoop.producto.representations.idm.ProductoCuentaPersonalRepresentation;
import org.sistcoop.producto.representations.idm.search.SearchResultsRepresentation;

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
    public SearchResultsRepresentation<ProductoCuentaPersonalRepresentation> search(String tipoCuenta,
            String tipoPersona, String moneda, boolean estado, String filterText, int firstResult,
            int maxResults) {

        SearchResultsModel<ProductoCuentaPersonalModel> results = null;

        if (tipoCuenta != null && tipoPersona != null && moneda != null) {
            results = productoCuentaPersonalProvider.getProductos(
                    TipoCuentaPersonal.valueOf(tipoCuenta.toUpperCase()),
                    TipoPersona.valueOf(tipoPersona.toUpperCase()), moneda, estado, filterText, firstResult,
                    maxResults);
        } else {
            if (tipoCuenta == null && tipoPersona == null && moneda == null) {
                results = productoCuentaPersonalProvider.getProductos(estado, filterText, firstResult,
                        maxResults);
            } else {
                if (tipoCuenta != null && tipoPersona != null) {
                    results = productoCuentaPersonalProvider.getProductos(
                            TipoCuentaPersonal.valueOf(tipoCuenta.toUpperCase()),
                            TipoPersona.valueOf(tipoPersona.toUpperCase()), estado, filterText, firstResult,
                            maxResults);
                } else if (tipoPersona != null && moneda != null) {
                    results = productoCuentaPersonalProvider.getProductos(
                            TipoPersona.valueOf(tipoPersona.toUpperCase()), moneda, estado, filterText,
                            firstResult, maxResults);
                } else if (tipoCuenta != null && moneda != null) {
                    results = productoCuentaPersonalProvider.getProductos(
                            TipoCuentaPersonal.valueOf(tipoCuenta.toUpperCase()), moneda, estado, filterText,
                            firstResult, maxResults);
                } else {
                    if (tipoCuenta != null) {
                        results = productoCuentaPersonalProvider.getProductos(
                                TipoCuentaPersonal.valueOf(tipoCuenta.toUpperCase()), estado, filterText,
                                firstResult, maxResults);
                    } else if (tipoPersona != null) {
                        results = productoCuentaPersonalProvider.getProductos(
                                TipoPersona.valueOf(tipoPersona.toUpperCase()), estado, filterText,
                                firstResult, maxResults);
                    } else if (moneda != null) {
                        results = productoCuentaPersonalProvider.getProductos(moneda, estado, filterText,
                                firstResult, maxResults);
                    }
                }
            }
        }

        SearchResultsRepresentation<ProductoCuentaPersonalRepresentation> rep = new SearchResultsRepresentation<>();
        List<ProductoCuentaPersonalRepresentation> representations = new ArrayList<>();
        for (ProductoCuentaPersonalModel model : results.getBeans()) {
            representations.add(ModelToRepresentation.toRepresentation(model));
        }
        rep.setTotalSize(results.getTotalSize());
        rep.setItems(representations);
        return rep;

    }
}
