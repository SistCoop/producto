package org.sistcoop.producto.models;

import java.math.BigDecimal;

public interface ProductoTasaModel extends Model {

	Integer getId();

	BigDecimal getValor();

	void setValor(BigDecimal valor);

	String getTasa();	

	ProductoModel getProducto();

}