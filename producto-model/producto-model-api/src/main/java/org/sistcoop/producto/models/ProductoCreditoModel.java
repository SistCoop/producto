package org.sistcoop.producto.models;

import java.math.BigDecimal;

public interface ProductoCreditoModel extends ProductoModel {

	BigDecimal getMontoMinimo();

	void setMontoMinimo(BigDecimal montoMinimo);

	BigDecimal getMontoMaximo();

	void setMontoMaximo(BigDecimal montoMaximo);

}