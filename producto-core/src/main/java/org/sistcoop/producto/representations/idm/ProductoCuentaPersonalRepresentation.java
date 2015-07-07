package org.sistcoop.producto.representations.idm;

import java.io.Serializable;
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

    protected String moneda;
    private List<TasaRepresentation> tasas;

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

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public List<TasaRepresentation> getTasas() {
        return tasas;
    }

    public void setTasas(List<TasaRepresentation> tasas) {
        this.tasas = tasas;
    }

}
