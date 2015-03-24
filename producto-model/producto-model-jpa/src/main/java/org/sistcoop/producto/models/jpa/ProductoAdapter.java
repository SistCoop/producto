package org.sistcoop.producto.models.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.sistcoop.producto.models.ProductoModel;
import org.sistcoop.producto.models.ProductoMonedaModel;
import org.sistcoop.producto.models.ProductoTasaModel;
import org.sistcoop.producto.models.enums.TipoPersona;
import org.sistcoop.producto.models.jpa.entities.ProductoEntity;
import org.sistcoop.producto.models.jpa.entities.ProductoMonedaEntity;
import org.sistcoop.producto.models.jpa.entities.ProductoTasaEntity;

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

	public static ProductoEntity toProductoEntity(ProductoModel model, EntityManager em) {
		if (model instanceof ProductoAdapter) {
			return ((ProductoAdapter) model).getProductoEntity();
		}
		return em.getReference(ProductoEntity.class, model.getId());
	}

	@Override
	public void commit() {
		em.merge(productoEntity);
	}

	@Override
	public Integer getId() {
		return productoEntity.getId();
	}

	@Override
	public String getCodigo() {
		return productoEntity.getCodigo();
	}

	@Override
	public String getDenominacion() {
		return productoEntity.getDenominacion();
	}

	@Override
	public void setDenominacion(String denominacion) {
		productoEntity.setDenominacion(denominacion);
	}

	@Override
	public TipoPersona getTipoPersona() {
		return productoEntity.getTipoPersona();
	}

	@Override
	public void setTipoPersona(TipoPersona tipoPersona) {
		productoEntity.setTipoPersona(tipoPersona);
	}

	@Override
	public boolean getEstado() {
		return productoEntity.isEstado();
	}

	@Override
	public void desactivar() {
		productoEntity.setEstado(false);
	}

	@Override
	public List<ProductoMonedaModel> getMonedas() {
		Set<ProductoMonedaEntity> monedas = productoEntity.getMonedas();
		List<ProductoMonedaModel> result = new ArrayList<ProductoMonedaModel>();
		for (ProductoMonedaEntity productoMonedaEntity : monedas) {
			ProductoMonedaModel productoMonedaModel = new ProductoMonedaAdapter(em, productoMonedaEntity);
			result.add(productoMonedaModel);
		}
		return result;
	}

	@Override
	public List<ProductoTasaModel> getTasas() {
		Set<ProductoTasaEntity> tasas = productoEntity.getTasas();
		List<ProductoTasaModel> result = new ArrayList<ProductoTasaModel>();
		for (ProductoTasaEntity productoTasaEntity : tasas) {
			ProductoTasaModel productoTasaModel = new ProductoTasaAdapter(em, productoTasaEntity);
			result.add(productoTasaModel);
		}
		return result;
	}

}
