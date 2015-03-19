package org.sistcoop.producto.representations.idm;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "productoCuentaPersonal")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ProductoCuentaPersonalRepresentation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String codigo;
	private String denominacion;
	private String tipoPersona;
	private boolean estado;

	protected List<ProductoMonedaRepresentation> monedas;
	private List<ProductoTasaRepresentation> tasas;

	@XmlAttribute
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@XmlAttribute
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@XmlAttribute
	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	@XmlAttribute
	public String getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	@XmlAttribute
	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@XmlAttribute
	public List<ProductoMonedaRepresentation> getMonedas() {
		return monedas;
	}

	public void setMonedas(List<ProductoMonedaRepresentation> monedas) {
		this.monedas = monedas;
	}

	@XmlAttribute
	public List<ProductoTasaRepresentation> getTasas() {
		return tasas;
	}

	public void setTasas(List<ProductoTasaRepresentation> tasas) {
		this.tasas = tasas;
	}

}
