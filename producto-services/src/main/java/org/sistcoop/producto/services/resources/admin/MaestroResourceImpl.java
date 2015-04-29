package org.sistcoop.producto.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.sistcoop.producto.client.resource.MaestroResource;
import org.sistcoop.producto.models.enums.Frecuencia;
import org.sistcoop.producto.models.enums.TipoValor;
import org.sistcoop.producto.representations.idm.FrecuenciaRepresentation;
import org.sistcoop.producto.representations.idm.TipoValorRepresentation;

@Stateless
public class MaestroResourceImpl implements MaestroResource {

	@Override
	public List<TipoValorRepresentation> getTipoValores() {
		TipoValor[] s = TipoValor.values();
		List<TipoValorRepresentation> list = new ArrayList<TipoValorRepresentation>();
		for (int i = 0; i < s.length; i++) {
			TipoValorRepresentation rep = new TipoValorRepresentation();
			rep.setDenominacion(s[i].toString());
			list.add(rep);
		}
		return list;
	}

	@Override
	public List<FrecuenciaRepresentation> getFrecuencias() {
		Frecuencia[] s = Frecuencia.values();
		List<FrecuenciaRepresentation> list = new ArrayList<FrecuenciaRepresentation>();
		for (int i = 0; i < s.length; i++) {
			FrecuenciaRepresentation rep = new FrecuenciaRepresentation();
			rep.setDenominacion(s[i].toString());
			list.add(rep);
		}
		return list;
	}

}
