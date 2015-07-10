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
import org.sistcoop.producto.models.enums.TipoPersona;
import org.sistcoop.producto.models.search.PagingModel;
import org.sistcoop.producto.models.search.SearchCriteriaModel;
import org.sistcoop.producto.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.producto.models.search.SearchResultsModel;
import org.sistcoop.producto.models.search.util.ProductoModelAtribute;
import org.sistcoop.producto.models.utils.ModelToRepresentation;
import org.sistcoop.producto.models.utils.RepresentationToModel;
import org.sistcoop.producto.representations.idm.ProductoCreditoRepresentation;
import org.sistcoop.producto.representations.idm.search.SearchResultsRepresentation;

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
    public Response create(ProductoCreditoRepresentation representation) {
        ProductoCreditoModel model = representationToModel.createProductoCredito(representation,
                productoCreditoProvider);

        return Response.created(uriInfo.getAbsolutePathBuilder().path(model.getId()).build())
                .header("Access-Control-Expose-Headers", "Location")
                .entity(Jsend.getSuccessJSend(model.getId())).build();
    }

    @Override
    public SearchResultsRepresentation<ProductoCreditoRepresentation> search(String tipoPersona,
            String moneda, boolean estado, String filterText, int page, int pageSize) {

        // set paging
        PagingModel paging = new PagingModel();
        paging.setPage(page);
        paging.setPageSize(pageSize);

        SearchCriteriaModel searchCriteriaBean = new SearchCriteriaModel();
        searchCriteriaBean.setPaging(paging);
        searchCriteriaBean.setOrder(ProductoModelAtribute.denominacion, false);

        // add filters
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
        SearchResultsModel<ProductoCreditoModel> results = productoCreditoProvider.search(searchCriteriaBean);
        SearchResultsRepresentation<ProductoCreditoRepresentation> rep = new SearchResultsRepresentation<>();
        List<ProductoCreditoRepresentation> representations = new ArrayList<>();
        for (ProductoCreditoModel model : results.getModels()) {
            representations.add(ModelToRepresentation.toRepresentation(model));
        }
        rep.setTotalSize(results.getTotalSize());
        rep.setItems(representations);
        return rep;
    }

}
