package org.sistcoop.producto.models;

import javax.ejb.Local;

import org.sistcoop.producto.provider.Provider;

@Local
public interface ProductoProvider extends Provider {	

	ProductoModel getProductoById(Integer id);
	
}
