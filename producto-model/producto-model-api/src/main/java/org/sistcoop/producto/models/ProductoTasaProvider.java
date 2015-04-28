package org.sistcoop.producto.models;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import org.sistcoop.producto.provider.Provider;

@Local
public interface ProductoTasaProvider extends Provider {

	ProductoTasaModel getProductoTasaById(Integer id);

	ProductoTasaModel addProductoTasa(
			ProductoModel productoModel, 
			String tasa,
			BigDecimal valor);

	boolean eliminarProductoTasa(ProductoTasaModel productoTasaModel);

	List<ProductoTasaModel> getProductoTasas(ProductoModel productoModel);

}
