package org.sistcoop.producto.representations.idm;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotBlank;

@XmlRootElement(name = "productoCaracteristica")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ProductoCaracteristicaRepresentation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String descripcion;
	private String descripcionDetallada;

	@XmlAttribute
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@XmlAttribute
	@NotNull
	@NotBlank
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@XmlAttribute
	@NotNull
	@NotBlank
	public String getDescripcionDetallada() {
		return descripcionDetallada;
	}

	public void setDescripcionDetallada(String descripcionDetallada) {
		this.descripcionDetallada = descripcionDetallada;
	}

}
