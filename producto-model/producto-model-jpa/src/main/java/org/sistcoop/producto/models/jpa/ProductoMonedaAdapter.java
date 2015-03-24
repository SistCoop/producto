package org.sistcoop.producto.models.jpa;

import javax.persistence.EntityManager;

import org.sistcoop.producto.models.ProductoModel;
import org.sistcoop.producto.models.ProductoMonedaModel;
import org.sistcoop.producto.models.jpa.entities.ProductoEntity;
import org.sistcoop.producto.models.jpa.entities.ProductoMonedaEntity;

public class ProductoMonedaAdapter implements ProductoMonedaModel {

	private static final long serialVersionUID = 1L;

	protected ProductoMonedaEntity productoMonedaEntity;
	protected EntityManager em;

	public ProductoMonedaAdapter(EntityManager em, ProductoMonedaEntity productoMonedaEntity) {
		this.em = em;
		this.productoMonedaEntity = productoMonedaEntity;
	}

	public ProductoMonedaEntity getProductoMonedaEntity() {
		return productoMonedaEntity;
	}

	public static ProductoMonedaEntity toProductoMonedaEntity(ProductoMonedaModel model, EntityManager em) {
		if (model instanceof ProductoMonedaAdapter) {
			return ((ProductoMonedaAdapter) model).getProductoMonedaEntity();
		}
		return em.getReference(ProductoMonedaEntity.class, model.getId());
	}

	@Override
	public void commit() {
		em.merge(productoMonedaEntity);
	}

	@Override
	public Integer getId() {
		return productoMonedaEntity.getId();
	}

	@Override
	public String getMoneda() {
		return productoMonedaEntity.getMoneda();
	}

	@Override
	public ProductoModel getProducto() {
		ProductoEntity productoEntity = productoMonedaEntity.getProducto();
		return new ProductoAdapter(em, productoEntity);
	}

}
