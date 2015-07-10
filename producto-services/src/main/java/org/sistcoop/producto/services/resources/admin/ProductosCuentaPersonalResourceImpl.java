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
import org.sistcoop.producto.models.enums.TipoCuentaPersonal;
import org.sistcoop.producto.models.enums.TipoPersona;
import org.sistcoop.producto.models.search.PagingModel;
import org.sistcoop.producto.models.search.SearchCriteriaModel;
import org.sistcoop.producto.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.producto.models.search.SearchResultsModel;
import org.sistcoop.producto.models.search.util.ProductoModelAtribute;
import org.sistcoop.producto.models.utils.ModelToRepresentation;
import org.sistcoop.producto.models.utils.RepresentationToModel;
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
            String tipoPersona, String moneda, boolean estado, String filterText, int page, int pageSize) {

        // set paging
        PagingModel paging = new PagingModel();
        paging.setPage(page);
        paging.setPageSize(pageSize);

        SearchCriteriaModel searchCriteriaBean = new SearchCriteriaModel();
        searchCriteriaBean.setPaging(paging);
        searchCriteriaBean.setOrder(ProductoModelAtribute.denominacion, false);

        // add filters
        if (tipoCuenta != null)
            searchCriteriaBean.addFilter(ProductoModelAtribute.tipoCuenta,
                    TipoCuentaPersonal.valueOf(tipoCuenta).toString(), SearchCriteriaFilterOperator.eq);
        if (tipoPersona != null)
            searchCriteriaBean.addFilter(ProductoModelAtribute.tipoPersona, TipoPersona.valueOf(tipoPersona)
                    .toString(), SearchCriteriaFilterOperator.eq);
        if (moneda != null)
            searchCriteriaBean.addFilter(ProductoModelAtribute.moneda, moneda,
                    SearchCriteriaFilterOperator.eq);
        searchCriteriaBean.addFilter(ProductoModelAtribute.estado, estado ? "true" : "false",
                SearchCriteriaFilterOperator.bool_eq);
        searchCriteriaBean.addFilter(ProductoModelAtribute.codigo, filterText,
                SearchCriteriaFilterOperator.like);
        searchCriteriaBean.addFilter(ProductoModelAtribute.denominacion, filterText,
                SearchCriteriaFilterOperator.like);

        // search
        SearchResultsModel<ProductoCuentaPersonalModel> results = productoCuentaPersonalProvider
                .search(searchCriteriaBean);
        SearchResultsRepresentation<ProductoCuentaPersonalRepresentation> rep = new SearchResultsRepresentation<>();
        List<ProductoCuentaPersonalRepresentation> representations = new ArrayList<>();
        for (ProductoCuentaPersonalModel model : results.getModels()) {
            representations.add(ModelToRepresentation.toRepresentation(model));
        }
        rep.setTotalSize(results.getTotalSize());
        rep.setItems(representations);
        return rep;

    }
}
