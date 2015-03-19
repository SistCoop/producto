package org.sistcoop.producto.models.jpa.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PRODUCTO_CREDITO")
@PrimaryKeyJoinColumn
public class ProductoCreditoEntity extends ProductoEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final static String base = "org.sistcoop.producto.models.jpa.entities.ProductoCreditoEntity.";
	public final static String findAll = base + "findAll";

	private BigDecimal montoMinimo;
	private BigDecimal montoMaximo;

	@NotNull
	@Min(value = 1)
	@Max(value = 1000000)
	public BigDecimal getMontoMinimo() {
		return montoMinimo;
	}

	public void setMontoMinimo(BigDecimal montoMinimo) {
		this.montoMinimo = montoMinimo;
	}

	@NotNull
	@Min(value = 1)
	@Max(value = 1000000)
	public BigDecimal getMontoMaximo() {
		return montoMaximo;
	}

	public void setMontoMaximo(BigDecimal montoMaximo) {
		this.montoMaximo = montoMaximo;
	}

}
