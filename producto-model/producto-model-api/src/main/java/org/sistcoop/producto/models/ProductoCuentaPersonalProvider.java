package org.sistcoop.producto.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoop.producto.models.enums.TipoPersona;
import org.sistcoop.producto.provider.Provider;

@Local
public interface ProductoCuentaPersonalProvider extends Provider {

	ProductoCuentaPersonalModel addProductoCredito(
			String denominacion, 
			TipoPersona tipoPersona, 
			List<String> monedas);
	
	boolean desactivarProducto(ProductoCuentaPersonalModel productoModel);

	ProductoCuentaPersonalModel getProductoById(Integer id);

	List<ProductoCuentaPersonalModel> getProductos();	

	List<ProductoCuentaPersonalModel> getProductos(TipoPersona tipoPersona);

	List<ProductoCuentaPersonalModel> getProductos(boolean estado);

	List<ProductoCuentaPersonalModel> getProductos(TipoPersona tipoPersona, boolean estado);
	
}
