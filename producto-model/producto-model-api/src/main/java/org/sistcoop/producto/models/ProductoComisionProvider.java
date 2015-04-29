package org.sistcoop.producto.models;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import org.sistcoop.producto.models.enums.Frecuencia;
import org.sistcoop.producto.models.enums.TipoValor;
import org.sistcoop.producto.provider.Provider;

@Local
public interface ProductoComisionProvider extends Provider {	

	ProductoComisionModel getProductoComisionById(Integer id);		
	
	ProductoComisionModel addProductoComision(
			ProductoModel productoModel,
			String denominacion,
			BigDecimal valor,
			TipoValor tipoValor,
			Frecuencia frecuencia);
	
	boolean eliminarProductoComision(ProductoComisionModel productoComisionModel);

	List<ProductoComisionModel> getProductoComisiones(ProductoModel productoModel);	

}
