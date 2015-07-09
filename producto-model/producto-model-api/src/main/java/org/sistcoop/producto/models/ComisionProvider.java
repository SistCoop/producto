package org.sistcoop.producto.models;

import java.math.BigDecimal;

import javax.ejb.Local;

import org.sistcoop.producto.models.enums.Frecuencia;
import org.sistcoop.producto.models.enums.TipoValor;
import org.sistcoop.producto.models.search.SearchResultsModel;
import org.sistcoop.producto.provider.Provider;

@Local
public interface ComisionProvider extends Provider {

    ComisionModel findById(String id);

    ComisionModel create(ProductoModel productoModel, String denominacion, BigDecimal valor,
            TipoValor tipoValor, Frecuencia frecuencia);

    boolean remove(ComisionModel comisionModel);

    SearchResultsModel<ComisionModel> search(ProductoModel productoModel);

}
