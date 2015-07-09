package org.sistcoop.producto.models.search;

import javax.ejb.Local;

import org.sistcoop.producto.provider.Provider;

@Local
public interface SearchCriteriaFilter_ProductoCreditoProvider extends Provider {

    String id();

    String tipoPersona();

    String moneda();

    String estado();

}
