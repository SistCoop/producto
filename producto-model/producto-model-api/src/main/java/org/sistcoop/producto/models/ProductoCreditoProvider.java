package org.sistcoop.producto.models;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import org.sistcoop.producto.models.enums.TipoPersona;
import org.sistcoop.producto.provider.Provider;

@Local
public interface ProductoCreditoProvider extends Provider {	

	ProductoCreditoModel addProductoCredito(
			String denominacion, 
			TipoPersona tipoPersona, 
			List<String> monedas,
			BigDecimal montoMinimo,
			BigDecimal montoMaximo);
	
	boolean desactivarProducto(ProductoCreditoModel productoModel);

	ProductoCreditoModel getProductoById(Integer id);

	List<ProductoCreditoModel> getProductos();
		
	List<ProductoCreditoModel> getProductos(TipoPersona tipoPersona);

	List<ProductoCreditoModel> getProductos(boolean estado);

	List<ProductoCreditoModel> getProductos(TipoPersona tipoPersona, boolean estado);	
}
