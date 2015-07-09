package org.sistcoop.producto.models;

import javax.ejb.Local;

import org.sistcoop.producto.models.enums.TipoCuentaPersonal;
import org.sistcoop.producto.models.enums.TipoPersona;
import org.sistcoop.producto.models.search.SearchCriteriaBean;
import org.sistcoop.producto.models.search.SearchResultsModel;
import org.sistcoop.producto.provider.Provider;

@Local
public interface ProductoCuentaPersonalProvider extends Provider {

    ProductoCuentaPersonalModel findById(String id);

    ProductoCuentaPersonalModel findByCodigo(String codigo);

    ProductoCuentaPersonalModel create(TipoCuentaPersonal tipoCuenta, TipoPersona tipoPersona, String moneda,
            String denominacion);

    boolean remove(ProductoCuentaPersonalModel productoModel);

    SearchResultsModel<ProductoCuentaPersonalModel> search();

    SearchResultsModel<ProductoCuentaPersonalModel> search(SearchCriteriaBean searchCriteriaBean);

}
