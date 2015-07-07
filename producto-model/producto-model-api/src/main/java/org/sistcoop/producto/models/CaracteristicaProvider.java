package org.sistcoop.producto.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoop.producto.provider.Provider;

@Local
public interface CaracteristicaProvider extends Provider {	

	CaracteristicaModel getProductoCaracteristicaById(String id);		
	
	CaracteristicaModel addProductoCaracteristica(
			ProductoModel productoModel,
			String descripcion,
			String descripcionDetallada);
	
	boolean removeProductoCaracteristica(CaracteristicaModel caracteristicaModel);

	List<CaracteristicaModel> getProductoCaracteristicas(ProductoModel productoModel);

    List<CaracteristicaModel> getCaracteristicas(ProductoCreditoModel productoCreditoModel);

    List<CaracteristicaModel> getCaracteristicas(ProductoCuentaPersonalModel productoCuentaPersonalModel);	

}
