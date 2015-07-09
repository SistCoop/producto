package org.sistcoop.producto.models.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.sistcoop.producto.models.CaracteristicaModel;
import org.sistcoop.producto.models.ComisionModel;
import org.sistcoop.producto.models.ProductoModel;
import org.sistcoop.producto.models.TasaModel;
import org.sistcoop.producto.models.enums.TipoPersona;
import org.sistcoop.producto.models.jpa.entities.CaracteristicaEntity;
import org.sistcoop.producto.models.jpa.entities.ComisionEntity;
import org.sistcoop.producto.models.jpa.entities.ProductoEntity;
import org.sistcoop.producto.models.jpa.entities.TasaEntity;

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
    public String getId() {
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
    public String getMoneda() {
        return productoEntity.getMoneda();
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
    public List<CaracteristicaModel> getCaracteristicas() {
        Set<CaracteristicaEntity> productoCaracteristicaEntities = productoEntity
                .getCaracteristicas();
        List<CaracteristicaModel> result = new ArrayList<CaracteristicaModel>();
        for (CaracteristicaEntity productoCaracteristicaEntity : productoCaracteristicaEntities) {
            result.add(new CaracteristicaAdapter(em, productoCaracteristicaEntity));
        }
        return result;
    }

    @Override
    public List<TasaModel> getTasas() {
        Set<TasaEntity> tasas = productoEntity.getTasas();
        List<TasaModel> result = new ArrayList<TasaModel>();
        for (TasaEntity productoTasaEntity : tasas) {
            TasaModel productoTasaModel = new TasaAdapter(em, productoTasaEntity);
            result.add(productoTasaModel);
        }
        return result;
    }

    @Override
    public List<ComisionModel> getComisiones() {
        Set<ComisionEntity> comisiones = productoEntity.getComisiones();
        List<ComisionModel> result = new ArrayList<ComisionModel>();
        for (ComisionEntity productoComisionEntity : comisiones) {
            ComisionModel productoComisionModel = new ComisionAdapter(em, productoComisionEntity);
            result.add(productoComisionModel);
        }
        return result;
    }

}
