package org.sistcoop.producto.representations.idm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "tipoValor")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class TipoValorRepresentation {

	private String denominacion;

	@XmlAttribute
	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

}
