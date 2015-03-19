package org.sistcoop.producto.models;

public interface ProductoMonedaModel extends Model {

	Integer getId();

	String getMoneda();

	ProductoModel getProducto();

}