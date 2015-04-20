package org.sistcoop.producto.models;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import org.sistcoop.producto.models.enums.TipoPersona;
import org.sistcoop.producto.provider.Provider;

@Local
public interface ProductoCreditoProvider extends Provider {	

	ProductoCreditoModel getProductoCreditoById(Integer id);
	
	ProductoCreditoModel getProductoCreditoByCodigo(String codigo);
	
	ProductoCreditoModel getProductoByDenominacion(String denominacion);
	
	ProductoCreditoModel addProductoCredito(
			String codigo,
			String denominacion, 
			TipoPersona tipoPersona,
			String moneda,
			BigDecimal montoMinimo,
			BigDecimal montoMaximo);
	
	boolean desactivarProductoCredito(ProductoCreditoModel productoModel);

	List<ProductoCreditoModel> getProductos();
		
	List<ProductoCreditoModel> getProductos(TipoPersona tipoPersona);

	List<ProductoCreditoModel> getProductos(boolean estado);

	List<ProductoCreditoModel> getProductos(TipoPersona tipoPersona, boolean estado);	
}
