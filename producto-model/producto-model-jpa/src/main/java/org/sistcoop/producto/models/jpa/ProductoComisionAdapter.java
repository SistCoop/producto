package org.sistcoop.producto.models.jpa;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import org.sistcoop.producto.models.ProductoComisionModel;
import org.sistcoop.producto.models.ProductoModel;
import org.sistcoop.producto.models.enums.Frecuencia;
import org.sistcoop.producto.models.enums.TipoValor;
import org.sistcoop.producto.models.jpa.entities.ProductoComisionEntity;

public class ProductoComisionAdapter implements ProductoComisionModel {

    private static final long serialVersionUID = 1L;

    protected ProductoComisionEntity productoComisionEntity;
    protected EntityManager em;

    public ProductoComisionAdapter(EntityManager em, ProductoComisionEntity productoComisionEntity) {
        this.em = em;
        this.productoComisionEntity = productoComisionEntity;
    }

    public ProductoComisionEntity getProductoComisionEntity() {
        return productoComisionEntity;
    }

    public static ProductoComisionEntity toProductoComisionEntity(ProductoComisionModel model,
            EntityManager em) {
        if (model instanceof ProductoComisionAdapter) {
            return ((ProductoComisionAdapter) model).getProductoComisionEntity();
        }
        return em.getReference(ProductoComisionEntity.class, model.getId());
    }

    @Override
    public void commit() {
        em.merge(productoComisionEntity);
    }

    @Override
    public String getId() {
        return productoComisionEntity.getId();
    }

    @Override
    public String getDenominacion() {
        return productoComisionEntity.getDenominacion();
    }

    @Override
    public void setDenominacion(String denominacion) {
        productoComisionEntity.setDenominacion(denominacion);
    }

    @Override
    public BigDecimal getValor() {
        return productoComisionEntity.getValor();
    }

    @Override
    public void setValor(BigDecimal valor) {
        productoComisionEntity.setValor(valor);
    }

    @Override
    public TipoValor getTipoValor() {
        return productoComisionEntity.getTipoValor();
    }

    @Override
    public void setTipoValor(TipoValor tipoValor) {
        productoComisionEntity.setTipoValor(tipoValor);
    }

    @Override
    public Frecuencia getFrecuencia() {
        return productoComisionEntity.getFrecuencia();
    }

    @Override
    public void setFrecuencia(Frecuencia frecuencia) {
        productoComisionEntity.setFrecuencia(frecuencia);
    }

    @Override
    public ProductoModel getProducto() {
        return new ProductoAdapter(em, productoComisionEntity.getProducto());
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
        if (!(obj instanceof ProductoComisionModel))
            return false;
        ProductoComisionModel other = (ProductoComisionModel) obj;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        return true;
    }

}
