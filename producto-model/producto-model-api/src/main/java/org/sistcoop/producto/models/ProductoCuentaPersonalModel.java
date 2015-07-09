package org.sistcoop.producto.models;

import java.math.BigDecimal;

import org.sistcoop.producto.models.enums.TipoCuentaPersonal;

public interface ProductoCuentaPersonalModel extends ProductoModel {

    TipoCuentaPersonal getTipoCuentaPersonal();

    BigDecimal getMontoMinimo();

    void setMontoMinimo(BigDecimal montoMinimo);

    BigDecimal getMontoMaximo();

    void setMontoMaximo(BigDecimal montoMaximo);

}