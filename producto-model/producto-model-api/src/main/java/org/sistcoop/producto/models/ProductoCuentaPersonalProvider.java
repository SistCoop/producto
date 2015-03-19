package org.sistcoop.producto.models;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import org.sistcoop.producto.models.enums.TipoPersona;
import org.sistcoop.producto.provider.Provider;

@Local
public interface ProductoCuentaPersonalProvider extends Provider {

	ProductoCuentaPersonalModel addProductoCredito(
			String denominacion, 
			TipoPersona tipoPersona, 
			List<String> monedas,
			BigDecimal montoMinimo,
			BigDecimal montoMaximo);
	
	boolean desactivarProducto(ProductoCuentaPersonalModel productoModel);

	ProductoCuentaPersonalModel getProductoById(Integer id);

	List<ProductoCuentaPersonalModel> getProductos();	

	List<ProductoCreditoModel> getProductos(TipoPersona tipoPersona);

	List<ProductoCreditoModel> getProductos(boolean estado);

	List<ProductoCreditoModel> getProductos(TipoPersona tipoPersona, boolean estado);
	
}
