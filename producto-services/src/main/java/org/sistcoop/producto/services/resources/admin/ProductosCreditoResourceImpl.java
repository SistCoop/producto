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
import org.sistcoop.producto.models.search.SearchCriteriaBean;
import org.sistcoop.producto.models.search.SearchCriteriaFilterOperator;
import org.sistcoop.producto.models.search.SearchCriteriaFilter_ProductoCreditoProvider;
import org.sistcoop.producto.models.search.SearchResultsModel;
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

    @Inject
    private SearchCriteriaFilter_ProductoCreditoProvider searchCriteriaFilterProvider;

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
            String moneda, boolean estado, String filterText, int page, int size) {

        PagingModel paging = new PagingModel();
        paging.setPage(page);
        paging.setPageSize(size);

        SearchCriteriaBean searchCriteriaBean = new SearchCriteriaBean();
        searchCriteriaBean.setPaging(paging);

        if (tipoPersona != null) {
            searchCriteriaBean.addFilter(searchCriteriaFilterProvider.tipoPersona(),
                    TipoPersona.valueOf(tipoPersona).toString(), SearchCriteriaFilterOperator.eq);
        }
        if (moneda != null) {
            searchCriteriaBean.addFilter(searchCriteriaFilterProvider.moneda(), moneda,
                    SearchCriteriaFilterOperator.eq);
        }
        searchCriteriaBean.addFilter(searchCriteriaFilterProvider.estado(), estado ? "true" : "false",
                SearchCriteriaFilterOperator.bool_eq);

        SearchResultsModel<ProductoCreditoModel> results = productoCreditoProvider.search(searchCriteriaBean);
        SearchResultsRepresentation<ProductoCreditoRepresentation> rep = new SearchResultsRepresentation<>();
        List<ProductoCreditoRepresentation> representations = new ArrayList<>();
        for (ProductoCreditoModel model : results.getBeans()) {
            representations.add(ModelToRepresentation.toRepresentation(model));
        }
        rep.setTotalSize(results.getTotalSize());
        rep.setItems(representations);
        return rep;
    }
}
