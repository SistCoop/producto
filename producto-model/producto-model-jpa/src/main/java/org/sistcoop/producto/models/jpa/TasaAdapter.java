package org.sistcoop.producto.models.jpa;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import org.sistcoop.producto.models.ProductoModel;
import org.sistcoop.producto.models.TasaModel;
import org.sistcoop.producto.models.jpa.entities.ProductoEntity;
import org.sistcoop.producto.models.jpa.entities.TasaEntity;

public class TasaAdapter implements TasaModel {

    private static final long serialVersionUID = 1L;

    protected TasaEntity productoTasaEntity;
    protected EntityManager em;

    public TasaAdapter(EntityManager em, TasaEntity productoTasaEntity) {
        this.em = em;
        this.productoTasaEntity = productoTasaEntity;
    }

    public TasaEntity getProductoTasaEntity() {
        return productoTasaEntity;
    }

    public static TasaEntity toProductoTasaEntity(TasaModel model, EntityManager em) {
        if (model instanceof TasaAdapter) {
            return ((TasaAdapter) model).getProductoTasaEntity();
        }
        return em.getReference(TasaEntity.class, model.getId());
    }

    @Override
    public void commit() {
        em.merge(productoTasaEntity);
    }

    @Override
    public String getId() {
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
