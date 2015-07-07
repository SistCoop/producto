package org.sistcoop.producto.models.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.sistcoop.producto.models.CaracteristicaModel;
import org.sistcoop.producto.models.ProductoComisionModel;
import org.sistcoop.producto.models.ProductoCuentaPersonalModel;
import org.sistcoop.producto.models.ProductoTasaModel;
import org.sistcoop.producto.models.enums.TipoPersona;
import org.sistcoop.producto.models.jpa.entities.ProductoCaracteristicaEntity;
import org.sistcoop.producto.models.jpa.entities.ProductoComisionEntity;
import org.sistcoop.producto.models.jpa.entities.ProductoCuentaPersonalEntity;
import org.sistcoop.producto.models.jpa.entities.ProductoTasaEntity;

public class ProductoCuentaPersonalAdapter implements ProductoCuentaPersonalModel {

	private static final long serialVersionUID = 1L;

	protected ProductoCuentaPersonalEntity productoCuentaPersonalEntity;
	protected EntityManager em;

	public ProductoCuentaPersonalAdapter(EntityManager em, ProductoCuentaPersonalEntity productoCuentaPersonalEntity) {
		this.em = em;
		this.productoCuentaPersonalEntity = productoCuentaPersonalEntity;
	}

	public ProductoCuentaPersonalEntity getProductoCuentaPersonalEntityEntity() {
		return productoCuentaPersonalEntity;
	}

	public static ProductoCuentaPersonalEntity toProductoCuentaPersonalEntity(ProductoCuentaPersonalModel model, EntityManager em) {
		if (model instanceof ProductoCuentaPersonalAdapter) {
			return ((ProductoCuentaPersonalAdapter) model).getProductoCuentaPersonalEntityEntity();
		}
		return em.getReference(ProductoCuentaPersonalEntity.class, model.getId());
	}

	@Override
	public String getId() {
		return productoCuentaPersonalEntity.getId();
	}

	@Override
	public String getCodigo() {
		return productoCuentaPersonalEntity.getCodigo();
	}
	
	@Override
	public void setCodigo(String codigo) {
		productoCuentaPersonalEntity.setCodigo(codigo);
	}

	@Override
	public String getDenominacion() {
		return productoCuentaPersonalEntity.getDenominacion();
	}

	@Override
	public void setDenominacion(String denominacion) {
		productoCuentaPersonalEntity.setDenominacion(denominacion);
	}

	@Override
	public TipoPersona getTipoPersona() {
		return productoCuentaPersonalEntity.getTipoPersona();
	}

	@Override
	public void setTipoPersona(TipoPersona tipoPersona) {
		productoCuentaPersonalEntity.setTipoPersona(tipoPersona);
	}
	
	@Override
	public String getMoneda() {
		return productoCuentaPersonalEntity.getMoneda();
	}
	
	@Override
	public void setMoneda(String moneda) {
		productoCuentaPersonalEntity.setMoneda(moneda);
	}

	@Override
	public boolean getEstado() {
		return productoCuentaPersonalEntity.isEstado();
	}

	@Override
	public void desactivar() {
		productoCuentaPersonalEntity.setEstado(false);
	}

	@Override
	public List<CaracteristicaModel> getCaracteristicas() {
		Set<ProductoCaracteristicaEntity> productoCaracteristicaEntities = productoCuentaPersonalEntity.getCaracteristicas();
		List<CaracteristicaModel> result = new ArrayList<CaracteristicaModel>();
		for (ProductoCaracteristicaEntity productoCaracteristicaEntity : productoCaracteristicaEntities) {
			result.add(new ProductoCaracteristicaAdapter(em, productoCaracteristicaEntity));
		}
		return result;
	}
	
	@Override
	public List<ProductoTasaModel> getTasas() {
		Set<ProductoTasaEntity> tasas = productoCuentaPersonalEntity.getTasas();
		List<ProductoTasaModel> result = new ArrayList<ProductoTasaModel>();
		for (ProductoTasaEntity productoTasaEntity : tasas) {
			ProductoTasaModel productoTasaModel = new ProductoTasaAdapter(em, productoTasaEntity);
			result.add(productoTasaModel);
		}
		return result;
	}

	@Override
	public List<ProductoComisionModel> getComisiones() {
		Set<ProductoComisionEntity> comisiones = productoCuentaPersonalEntity.getComisiones();
		List<ProductoComisionModel> result = new ArrayList<ProductoComisionModel>();
		for (ProductoComisionEntity productoComisionEntity : comisiones) {
			ProductoComisionModel productoComisionModel = new ProductoComisionAdapter(em, productoComisionEntity);
			result.add(productoComisionModel);
		}
		return result;
	}
	
	@Override
	public void commit() {
		em.merge(productoCuentaPersonalEntity);
	}

}
