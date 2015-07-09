package org.sistcoop.producto.models;

import java.math.BigDecimal;

import javax.ejb.Local;

import org.sistcoop.producto.models.search.SearchResultsModel;
import org.sistcoop.producto.provider.Provider;

@Local
public interface TasaProvider extends Provider {

    TasaModel findById(String id);

    TasaModel create(ProductoModel productoModel, String tasa, BigDecimal valor);

    boolean remove(TasaModel productoTasaModel);

    SearchResultsModel<TasaModel> search(ProductoModel productoModel);

}
