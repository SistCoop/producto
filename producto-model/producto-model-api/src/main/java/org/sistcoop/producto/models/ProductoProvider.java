package org.sistcoop.producto.models;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import org.sistcoop.producto.models.enums.TipoPersona;
import org.sistcoop.producto.provider.Provider;

@Local
public interface ProductoProvider extends Provider {

	ProductoCreditoModel addProductoCuentaPersonal(
			String denominacion, 
			TipoPersona tipoPersona, 
			List<String> monedas);

	ProductoCreditoModel addProductoCredito(
			String denominacion, 
			TipoPersona tipoPersona, 
			List<String> monedas,
			BigDecimal montoMinimo,
			BigDecimal montoMaximo);
	
	boolean desactivarProducto(ProductoModel productoModel);

	ProductoCreditoModel getProductoById(Integer id);

	List<ProductoModel> getProductos();
	
	List<ProductoCuentaPersonalModel> getProductosCuentaPersonal();

	List<ProductoCuentaPersonalModel> getProductosCuentaPersonal(TipoPersona tipoPersona);

	List<ProductoCuentaPersonalModel> getProductosCuentaPersonal(boolean estado);

	List<ProductoCuentaPersonalModel> getProductosCuentaPersonal(TipoPersona tipoPersona, boolean estado);

	List<ProductoCreditoModel> getProductosCredito();

	List<ProductoCreditoModel> getProductosCreditoCredito(TipoPersona tipoPersona);

	List<ProductoCreditoModel> getProductosCredito(boolean estado);

	List<ProductoCreditoModel> getProductosCredito(TipoPersona tipoPersona, boolean estado);
	
}
