package org.sistcoop.producto.models.jpa.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.sistcoop.producto.models.enums.TipoCuentaPersonal;

@Entity
@Table(name = "PRODUCTO_CUENTAPERSONAL")
@PrimaryKeyJoinColumn
@NamedQueries({ 
	@NamedQuery(name = ProductoCuentaPersonalEntity.findAll, query = "SELECT p FROM ProductoCuentaPersonalEntity p"), 
	@NamedQuery(name = ProductoCuentaPersonalEntity.findByTipoPersona, query = "SELECT p FROM ProductoCuentaPersonalEntity p WHERE p.tipoPersona = :tipoPersona")})
public class ProductoCuentaPersonalEntity extends ProductoEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final static String base = "org.sistcoop.producto.models.jpa.entities.ProductoCuentaPersonalEntity.";
	public final static String findAll = base + "findAll";
	public final static String findByTipoPersona = base + "findByTipoPersona";

	
	private TipoCuentaPersonal tipoCuenta;
	
	private BigDecimal montoMinimo;
    private BigDecimal montoMaximo;
    
    private int plazoMinimo;
    private int plazoMaximo;
}
