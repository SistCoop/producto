package org.sistcoop.producto.representations.idm;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ProductoCuentaPersonalRepresentation implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private String id;
    private String codigo;
    private String denominacion;
    private String tipoPersona;
    private boolean estado;

    private String tipoCuenta;

    protected String moneda;
    private BigDecimal montoMinimo;
    private BigDecimal montoMaximo;

    private List<TasaRepresentation> tasas;
    private List<ComisionRepresentation> comisiones;
    private List<CaracteristicaRepresentation> caracteristicas;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public BigDecimal getMontoMinimo() {
        return montoMinimo;
    }

    public void setMontoMinimo(BigDecimal montoMinimo) {
        this.montoMinimo = montoMinimo;
    }

    public BigDecimal getMontoMaximo() {
        return montoMaximo;
    }

    public void setMontoMaximo(BigDecimal montoMaximo) {
        this.montoMaximo = montoMaximo;
    }

    public List<TasaRepresentation> getTasas() {
        return tasas;
    }

    public void setTasas(List<TasaRepresentation> tasas) {
        this.tasas = tasas;
    }

    public List<ComisionRepresentation> getComisiones() {
        return comisiones;
    }

    public void setComisiones(List<ComisionRepresentation> comisiones) {
        this.comisiones = comisiones;
    }

    public List<CaracteristicaRepresentation> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<CaracteristicaRepresentation> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

}
