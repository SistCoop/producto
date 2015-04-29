package org.sistcoop.producto.models;

import java.math.BigDecimal;

import org.sistcoop.producto.models.enums.Frecuencia;
import org.sistcoop.producto.models.enums.TipoValor;

public interface ProductoComisionModel extends Model {

	Integer getId();

	String getDenominacion();

	void setDenominacion(String denominacion);

	BigDecimal getValor();

	void setValor(BigDecimal valor);

	TipoValor getTipoValor();

	void setTipoValor(TipoValor tipoValor);

	Frecuencia getFrecuencia();

	void setFrecuencia(Frecuencia frecuencia);

	ProductoModel getProducto();

}