package org.sistcoop.producto.models;

import java.math.BigDecimal;

public interface TasaModel extends Model {

	String getId();

	BigDecimal getValor();

	void setValor(BigDecimal valor);

	String getTasa();	

	ProductoModel getProducto();

}