package org.sistcoop.producto.models;

public interface ProductoCaracteristicaModel extends Model {

	Integer getId();

	String getDescripcion();

	void setDescripcion(String descripcion);

	String getDescripcionDetallada();

	void setDescripcionDetallada(String descripcionDetallada);

	ProductoModel getProducto();

}