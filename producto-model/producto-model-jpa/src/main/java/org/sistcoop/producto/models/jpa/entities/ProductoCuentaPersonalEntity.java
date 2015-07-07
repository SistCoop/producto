package org.sistcoop.producto.models.jpa.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.sistcoop.producto.models.enums.TipoCuentaPersonal;

@Entity
@Table(name = "PRODUCTO_CUENTAPERSONAL")
@PrimaryKeyJoinColumn
@NamedQueries({
        @NamedQuery(name = ProductoCuentaPersonalEntity.findAll, query = "SELECT p FROM ProductoCuentaPersonalEntity p"),
        @NamedQuery(name = ProductoCuentaPersonalEntity.findByTipoPersona, query = "SELECT p FROM ProductoCuentaPersonalEntity p WHERE p.tipoPersona = :tipoPersona") })
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

    @NotNull
    @Enumerated(EnumType.STRING)
    public TipoCuentaPersonal getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TipoCuentaPersonal tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

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
