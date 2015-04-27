package org.sistcoop.producto.models.jpa;

import javax.persistence.EntityManager;

import org.sistcoop.producto.models.ProductoCaracteristicaModel;
import org.sistcoop.producto.models.ProductoModel;
import org.sistcoop.producto.models.jpa.entities.ProductoCaracteristicaEntity;
import org.sistcoop.producto.models.jpa.entities.ProductoEntity;

public class ProductoCaracteristicaAdapter implements
		ProductoCaracteristicaModel {

	private static final long serialVersionUID = 1L;

	protected ProductoCaracteristicaEntity productoCaracteristicaEntity;
	protected EntityManager em;

	public ProductoCaracteristicaAdapter(EntityManager em,ProductoCaracteristicaEntity productoCaracteristicaEntity) {
		this.em = em;
		this.productoCaracteristicaEntity = productoCaracteristicaEntity;
	}

	public ProductoCaracteristicaEntity getProductoCaracteristicaEntity() {
		return productoCaracteristicaEntity;
	}

	public static ProductoCaracteristicaEntity toProductoCaracteristicaEntity(ProductoCaracteristicaModel model, EntityManager em) {
		if (model instanceof ProductoCaracteristicaAdapter) {
			return ((ProductoCaracteristicaAdapter) model).getProductoCaracteristicaEntity();
		}
		return em.getReference(ProductoCaracteristicaEntity.class, model.getId());
	}

	@Override
	public void commit() {
		em.merge(productoCaracteristicaEntity);
	}

	@Override
	public Integer getId() {
		return productoCaracteristicaEntity.getId();
	}

	@Override
	public String getDescripcion() {
		return productoCaracteristicaEntity.getDescripcion();
	}

	@Override
	public void setDescripcion(String descripcion) {
		productoCaracteristicaEntity.setDescripcion(descripcion);
	}

	@Override
	public String getDescripcionDetallada() {
		return productoCaracteristicaEntity.getDescripcionDetallada();
	}

	@Override
	public void setDescripcionDetallada(String descripcionDetallada) {
		productoCaracteristicaEntity
				.setDescripcionDetallada(descripcionDetallada);
	}

	@Override
	public ProductoModel getProducto() {
		ProductoEntity productoEntity = productoCaracteristicaEntity
				.getProducto();
		return new ProductoAdapter(em, productoEntity);
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
		if (!(obj instanceof ProductoCaracteristicaModel))
			return false;
		ProductoCaracteristicaModel other = (ProductoCaracteristicaModel) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}

}
