package org.sistcoop.producto.models.search;

import javax.ejb.Local;

import org.sistcoop.producto.provider.Provider;

@Local
public interface SearchCriteriaFilter_ProductoCuentaPersonalProvider extends Provider {

    SearchCriteriaFilterModel id();

    SearchCriteriaFilterModel tipoPersona();

}
