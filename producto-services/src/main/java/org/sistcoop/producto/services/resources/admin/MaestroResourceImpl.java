package org.sistcoop.producto.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.sistcoop.producto.client.resource.MaestroResource;
import org.sistcoop.producto.models.enums.Frecuencia;
import org.sistcoop.producto.models.enums.TipoValor;
import org.sistcoop.producto.representations.idm.search.SearchResultsRepresentation;

@Stateless
public class MaestroResourceImpl implements MaestroResource {

    @Override
    public SearchResultsRepresentation<String> getTipoValores() {
        TipoValor[] enums = TipoValor.values();

        SearchResultsRepresentation<String> rep = new SearchResultsRepresentation<>();
        List<String> representations = new ArrayList<>();
        for (int i = 0; i < enums.length; i++) {
            representations.add(enums[i].toString());
        }
        rep.setTotalSize(representations.size());
        rep.setItems(representations);
        return rep;
    }

    @Override
    public SearchResultsRepresentation<String> getFrecuencias() {
        Frecuencia[] enums = Frecuencia.values();

        SearchResultsRepresentation<String> rep = new SearchResultsRepresentation<>();
        List<String> representations = new ArrayList<>();
        for (int i = 0; i < enums.length; i++) {
            representations.add(enums[i].toString());
        }
        rep.setTotalSize(representations.size());
        rep.setItems(representations);
        return rep;
    }

}
