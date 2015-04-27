package org.sistcoop.producto.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoop.producto.provider.Provider;

@Local
public interface ProductoCaracteristicaProvider extends Provider {	

	ProductoCaracteristicaModel getProductoCaracteristicaById(Integer id);		
	
	ProductoCaracteristicaModel addProductoCaracteristica(
			ProductoModel productoModel,
			String descripcion,
			String descripcionDetallada);
	
	boolean eliminarProductoCaracteristica(ProductoCaracteristicaModel productoCaracteristicaModel);

	List<ProductoCaracteristicaModel> getProductoCaracteristicas(ProductoModel productoModel);	

}
