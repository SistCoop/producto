package org.sistcoop.producto.representations.idm;

import java.io.Serializable;

public class ProductoMonedaRepresentation implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private String moneda;

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

}
