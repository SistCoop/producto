package org.sistcoop.producto.models.jpa;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import org.sistcoop.producto.models.ProductoModel;
import org.sistcoop.producto.models.ProductoTasaModel;
import org.sistcoop.producto.models.jpa.entities.ProductoEntity;
import org.sistcoop.producto.models.jpa.entities.ProductoTasaEntity;

public class ProductoTasaAdapter implements ProductoTasaModel {

	private static final long serialVersionUID = 1L;

	protected ProductoTasaEntity productoTasaEntity;
	protected EntityManager em;

	public ProductoTasaAdapter(EntityManager em, ProductoTasaEntity productoTasaEntity) {
		this.em = em;
		this.productoTasaEntity = productoTasaEntity;
	}

	public ProductoTasaEntity getProductoTasaEntity() {
		return productoTasaEntity;
	}

	public static ProductoTasaEntity toProductoTasaEntity(ProductoTasaModel model, EntityManager em) {
		if (model instanceof ProductoTasaAdapter) {
			return ((ProductoTasaAdapter) model).getProductoTasaEntity();
		}
		return em.getReference(ProductoTasaEntity.class, model.getId());
	}

	@Override
	public void commit() {
		em.merge(productoTasaEntity);
	}

	@Override
	public Integer getId() {
		return productoTasaEntity.getId();
	}

	@Override
	public ProductoModel getProducto() {
		ProductoEntity productoEntity = productoTasaEntity.getProducto();
		return new ProductoAdapter(em, productoEntity);
	}

	@Override
	public BigDecimal getValor() {
		return productoTasaEntity.getValor();
	}

	@Override
	public void setValor(BigDecimal valor) {
		productoTasaEntity.setValor(valor);
	}

	@Override
	public String getTasa() {
		return productoTasaEntity.getTasa();
	}

}
