package org.sistcoop.producto.models.jpa;

import javax.persistence.EntityManager;

import org.sistcoop.producto.models.CaracteristicaModel;
import org.sistcoop.producto.models.ProductoModel;
import org.sistcoop.producto.models.jpa.entities.CaracteristicaEntity;
import org.sistcoop.producto.models.jpa.entities.ProductoEntity;

public class CaracteristicaAdapter implements CaracteristicaModel {

    private static final long serialVersionUID = 1L;

    protected CaracteristicaEntity productoCaracteristicaEntity;
    protected EntityManager em;

    public CaracteristicaAdapter(EntityManager em, CaracteristicaEntity productoCaracteristicaEntity) {
        this.em = em;
        this.productoCaracteristicaEntity = productoCaracteristicaEntity;
    }

    public CaracteristicaEntity getProductoCaracteristicaEntity() {
        return productoCaracteristicaEntity;
    }

    public static CaracteristicaEntity toProductoCaracteristicaEntity(CaracteristicaModel model,
            EntityManager em) {
        if (model instanceof CaracteristicaAdapter) {
            return ((CaracteristicaAdapter) model).getProductoCaracteristicaEntity();
        }
        return em.getReference(CaracteristicaEntity.class, model.getId());
    }

    @Override
    public void commit() {
        em.merge(productoCaracteristicaEntity);
    }

    @Override
    public String getId() {
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
        productoCaracteristicaEntity.setDescripcionDetallada(descripcionDetallada);
    }

    @Override
    public ProductoModel getProducto() {
        ProductoEntity productoEntity = productoCaracteristicaEntity.getProducto();
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
        if (!(obj instanceof CaracteristicaModel))
            return false;
        CaracteristicaModel other = (CaracteristicaModel) obj;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        return true;
    }

}
