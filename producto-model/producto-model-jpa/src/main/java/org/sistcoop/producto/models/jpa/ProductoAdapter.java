package org.sistcoop.producto.models.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import org.sistcoop.producto.models.ProductoModel;
import org.sistcoop.producto.models.ProductoMonedaModel;
import org.sistcoop.producto.models.ProductoTasaModel;
import org.sistcoop.producto.models.enums.TipoPersona;
import org.sistcoop.producto.models.jpa.entities.ProductoEntity;

public class ProductoAdapter implements ProductoModel {

	private static final long serialVersionUID = 1L;

	protected ProductoEntity productoEntity;
	protected EntityManager em;

	public ProductoAdapter(EntityManager em, ProductoEntity productoEntity) {
		this.em = em;
		this.productoEntity = productoEntity;
	}

	public ProductoEntity getProductoEntity() {
		return productoEntity;
	}

	@Override
	public void commit() {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCodigo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDenominacion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDenominacion(String denominacion) {
		// TODO Auto-generated method stub

	}

	@Override
	public TipoPersona getTipoPersona() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTipoPersona(TipoPersona tipoPersona) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean getEstado() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void desactivar() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ProductoMonedaModel> getMonedas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMonedas(List<ProductoMonedaModel> monedaModels) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ProductoTasaModel> getTasas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTasas(List<ProductoTasaModel> productoTasaModels) {
		// TODO Auto-generated method stub

	}

}
