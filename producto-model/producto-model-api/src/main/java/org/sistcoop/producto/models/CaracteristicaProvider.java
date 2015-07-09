package org.sistcoop.producto.models;

import javax.ejb.Local;

import org.sistcoop.producto.models.search.SearchResultsModel;
import org.sistcoop.producto.provider.Provider;

@Local
public interface CaracteristicaProvider extends Provider {

    CaracteristicaModel findById(String id);

    CaracteristicaModel create(ProductoModel productoModel, String descripcion, String descripcionDetallada);

    boolean remove(CaracteristicaModel caracteristicaModel);

    SearchResultsModel<CaracteristicaModel> search(ProductoModel productoModel);

}
