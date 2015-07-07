package org.sistcoop.producto.models;

public interface CaracteristicaModel extends Model {

	String getId();

	String getDescripcion();

	void setDescripcion(String descripcion);

	String getDescripcionDetallada();

	void setDescripcionDetallada(String descripcionDetallada);

	ProductoModel getProducto();

}