package org.sistcoop.producto.models;

import java.math.BigDecimal;

import javax.ejb.Local;

import org.sistcoop.producto.models.enums.TipoPersona;
import org.sistcoop.producto.models.search.SearchCriteriaBean;
import org.sistcoop.producto.models.search.SearchResultsModel;
import org.sistcoop.producto.provider.Provider;

@Local
public interface ProductoCreditoProvider extends Provider {

    ProductoCreditoModel findById(String id);

    ProductoCreditoModel findByCodigo(String codigo);

    ProductoCreditoModel create(TipoPersona tipoPersona, String moneda, String denominacion,
            BigDecimal montoMinimo, BigDecimal montoMaximo);

    boolean remove(ProductoCreditoModel productoModel);

    SearchResultsModel<ProductoCreditoModel> search();

    SearchResultsModel<ProductoCreditoModel> search(SearchCriteriaBean searchCriteriaBean);

}
