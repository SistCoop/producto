package org.sistcoop.producto.models.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCTO_CUENTAPERSONAL")
@PrimaryKeyJoinColumn
public class ProductoCuentaPersonalEntity extends ProductoEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final static String base = "org.sistcoop.producto.models.jpa.entities.ProductoCreditoEntity.";
	public final static String findAll = base + "findAll";

}
