package org.sistcoop.producto.models.jpa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.sistcoop.producto.models.ProductoCaracteristicaModel;
import org.sistcoop.producto.models.ProductoCreditoModel;
import org.sistcoop.producto.models.ProductoModel;
import org.sistcoop.producto.models.ProductoTasaModel;
import org.sistcoop.producto.models.enums.TipoPersona;
import org.sistcoop.producto.models.jpa.entities.ProductoCaracteristicaEntity;
import org.sistcoop.producto.models.jpa.entities.ProductoCreditoEntity;
import org.sistcoop.producto.models.jpa.entities.ProductoTasaEntity;

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

	public static ProductoCreditoEntity toProductoCreditoEntity(ProductoCreditoModel model, EntityManager em) {
		if (model instanceof ProductoCreditoAdapter) {
			return ((ProductoCreditoAdapter) model).getProductoCreditoEntity();
		}
		return em.getReference(ProductoCreditoEntity.class, model.getId());
	}

	@Override
	public Integer getId() {
		return productoCreditoEntity.getId();
	}

	@Override
	public String getCodigo() {
		return productoCreditoEntity.getCodigo();
	}
	
	@Override
	public void setCodigo(String codigo) {
		productoCreditoEntity.setCodigo(codigo);
	}

	@Override
	public String getDenominacion() {
		return productoCreditoEntity.getDenominacion();
	}

	@Override
	public void setDenominacion(String denominacion) {
		productoCreditoEntity.setDenominacion(denominacion);
	}

	@Override
	public TipoPersona getTipoPersona() {
		return productoCreditoEntity.getTipoPersona();
	}

	@Override
	public void setTipoPersona(TipoPersona tipoPersona) {
		productoCreditoEntity.setTipoPersona(tipoPersona);
	}
		
	@Override
	public String getMoneda() {
		return productoCreditoEntity.getMoneda();
	}
	
	@Override
	public void setMoneda(String moneda) {
		productoCreditoEntity.setMoneda(moneda);
	}

	@Override
	public boolean getEstado() {
		return productoCreditoEntity.isEstado();
	}

	@Override
	public void desactivar() {
		productoCreditoEntity.setEstado(false);
	}

	@Override
	public List<ProductoTasaModel> getTasas() {
		Set<ProductoTasaEntity> tasas = productoCreditoEntity.getTasas();
		List<ProductoTasaModel> result = new ArrayList<ProductoTasaModel>();
		for (ProductoTasaEntity productoTasaEntity : tasas) {
			ProductoTasaModel productoTasaModel = new ProductoTasaAdapter(em, productoTasaEntity);
			result.add(productoTasaModel);
		}
		return result;
	}

	@Override
	public BigDecimal getMontoMinimo() {
		return productoCreditoEntity.getMontoMinimo();
	}

	@Override
	public void setMontoMinimo(BigDecimal montoMinimo) {
		productoCreditoEntity.setMontoMinimo(montoMinimo);
	}

	@Override
	public BigDecimal getMontoMaximo() {
		return productoCreditoEntity.getMontoMaximo();
	}

	@Override
	public void setMontoMaximo(BigDecimal montoMaximo) {
		productoCreditoEntity.setMontoMaximo(montoMaximo);
	}

	@Override
	public void commit() {
		em.merge(productoCreditoEntity);
	}
	
	@Override
	public List<ProductoCaracteristicaModel> getCaracteristicas() {
		Set<ProductoCaracteristicaEntity> productoCaracteristicaEntities = productoCreditoEntity.getCaracteristicas();
		List<ProductoCaracteristicaModel> result = new ArrayList<ProductoCaracteristicaModel>();
		for (ProductoCaracteristicaEntity productoCaracteristicaEntity : productoCaracteristicaEntities) {
			result.add(new ProductoCaracteristicaAdapter(em, productoCaracteristicaEntity));
		}
		return result;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ProductoModel))
			return false;
		ProductoModel other = (ProductoModel) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}

}
