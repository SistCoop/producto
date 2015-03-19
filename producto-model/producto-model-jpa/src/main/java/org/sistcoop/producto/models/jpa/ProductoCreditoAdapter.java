package org.sistcoop.producto.models.jpa;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import org.sistcoop.producto.models.ProductoCreditoModel;
import org.sistcoop.producto.models.ProductoMonedaModel;
import org.sistcoop.producto.models.ProductoTasaModel;
import org.sistcoop.producto.models.enums.TipoPersona;
import org.sistcoop.producto.models.jpa.entities.ProductoCreditoEntity;

public class ProductoCreditoAdapter implements ProductoCreditoModel {

	private static final long serialVersionUID = 1L;

	protected ProductoCreditoEntity productoCreditoEntity;
	protected EntityManager em;

	public ProductoCreditoAdapter(EntityManager em, ProductoCreditoEntity productoCreditoEntity) {
		this.em = em;
		this.productoCreditoEntity = productoCreditoEntity;
	}

	public ProductoCreditoEntity getProductoCreditoEntity() {
		return productoCreditoEntity;
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

	@Override
	public void commit() {
		// TODO Auto-generated method stub

	}

	@Override
	public BigDecimal getMontoMinimo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMontoMinimo(BigDecimal montoMinimo) {
		// TODO Auto-generated method stub

	}

	@Override
	public BigDecimal getMontoMaximo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMontoMaximo(BigDecimal montoMaximo) {
		// TODO Auto-generated method stub

	}

}
