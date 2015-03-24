package org.sistcoop.producto.models.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.sistcoop.producto.models.ProductoCuentaPersonalModel;
import org.sistcoop.producto.models.ProductoMonedaModel;
import org.sistcoop.producto.models.ProductoTasaModel;
import org.sistcoop.producto.models.enums.TipoPersona;
import org.sistcoop.producto.models.jpa.entities.ProductoCuentaPersonalEntity;
import org.sistcoop.producto.models.jpa.entities.ProductoMonedaEntity;
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
	public Integer getId() {
		return productoCuentaPersonalEntity.getId();
	}

	@Override
	public String getCodigo() {
		return productoCuentaPersonalEntity.getCodigo();
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
	public boolean getEstado() {
		return productoCuentaPersonalEntity.isEstado();
	}

	@Override
	public void desactivar() {
		productoCuentaPersonalEntity.setEstado(false);
	}

	@Override
	public List<ProductoMonedaModel> getMonedas() {
		Set<ProductoMonedaEntity> monedas = productoCuentaPersonalEntity.getMonedas();
		List<ProductoMonedaModel> result = new ArrayList<ProductoMonedaModel>();
		for (ProductoMonedaEntity productoMonedaEntity : monedas) {
			ProductoMonedaModel productoMonedaModel = new ProductoMonedaAdapter(em, productoMonedaEntity);
			result.add(productoMonedaModel);
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
	public void commit() {
		em.merge(productoCuentaPersonalEntity);
	}

}
