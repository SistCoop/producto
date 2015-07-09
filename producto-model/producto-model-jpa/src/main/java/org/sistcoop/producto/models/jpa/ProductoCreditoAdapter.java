package org.sistcoop.producto.models.jpa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.sistcoop.producto.models.CaracteristicaModel;
import org.sistcoop.producto.models.ComisionModel;
import org.sistcoop.producto.models.ProductoCreditoModel;
import org.sistcoop.producto.models.ProductoModel;
import org.sistcoop.producto.models.TasaModel;
import org.sistcoop.producto.models.enums.TipoPersona;
import org.sistcoop.producto.models.jpa.entities.CaracteristicaEntity;
import org.sistcoop.producto.models.jpa.entities.ComisionEntity;
import org.sistcoop.producto.models.jpa.entities.ProductoCreditoEntity;
import org.sistcoop.producto.models.jpa.entities.TasaEntity;

public class ProductoCreditoAdapter implements ProductoCreditoModel {

    private static final long serialVersionUID = 1L;

    protected ProductoCreditoEntity productoCreditoEntity;
    protected EntityManager em;

    public ProductoCreditoAdapter(EntityManager em, ProductoCreditoEntity productoCreditoEntity) {
        this.em = em;
        this.productoCreditoEntity = productoCreditoEntity;
    }

    public ProductoCreditoEntity getProductoCreditoEntity() {
        return productoCreditoEntity;
    }

    public static ProductoCreditoEntity toProductoCreditoEntity(ProductoCreditoModel model, EntityManager em) {
        if (model instanceof ProductoCreditoAdapter) {
            return ((ProductoCreditoAdapter) model).getProductoCreditoEntity();
        }
        return em.getReference(ProductoCreditoEntity.class, model.getId());
    }

    @Override
    public String getId() {
        return productoCreditoEntity.getId();
    }

    @Override
    public String getCodigo() {
        return productoCreditoEntity.getCodigo();
    }

    @Override
    public String getDenominacion() {
        return productoCreditoEntity.getDenominacion();
    }

    @Override
    public void setDenominacion(String denominacion) {
        productoCreditoEntity.setDenominacion(denominacion);
    }

    @Override
    public TipoPersona getTipoPersona() {
        return productoCreditoEntity.getTipoPersona();
    }

    @Override
    public void setTipoPersona(TipoPersona tipoPersona) {
        productoCreditoEntity.setTipoPersona(tipoPersona);
    }

    @Override
    public String getMoneda() {
        return productoCreditoEntity.getMoneda();
    }

    @Override
    public boolean getEstado() {
        return productoCreditoEntity.isEstado();
    }

    @Override
    public void desactivar() {
        productoCreditoEntity.setEstado(false);
    }

    @Override
    public List<TasaModel> getTasas() {
        Set<TasaEntity> tasas = productoCreditoEntity.getTasas();
        List<TasaModel> result = new ArrayList<TasaModel>();
        for (TasaEntity productoTasaEntity : tasas) {
            TasaModel productoTasaModel = new TasaAdapter(em, productoTasaEntity);
            result.add(productoTasaModel);
        }
        return result;
    }

    @Override
    public BigDecimal getMontoMinimo() {
        return productoCreditoEntity.getMontoMinimo();
    }

    @Override
    public void setMontoMinimo(BigDecimal montoMinimo) {
        productoCreditoEntity.setMontoMinimo(montoMinimo);
    }

    @Override
    public BigDecimal getMontoMaximo() {
        return productoCreditoEntity.getMontoMaximo();
    }

    @Override
    public void setMontoMaximo(BigDecimal montoMaximo) {
        productoCreditoEntity.setMontoMaximo(montoMaximo);
    }

    @Override
    public void commit() {
        em.merge(productoCreditoEntity);
    }

    @Override
    public List<CaracteristicaModel> getCaracteristicas() {
        Set<CaracteristicaEntity> productoCaracteristicaEntities = productoCreditoEntity
                .getCaracteristicas();
        List<CaracteristicaModel> result = new ArrayList<CaracteristicaModel>();
        for (CaracteristicaEntity productoCaracteristicaEntity : productoCaracteristicaEntities) {
            result.add(new CaracteristicaAdapter(em, productoCaracteristicaEntity));
        }
        return result;
    }

    @Override
    public List<ComisionModel> getComisiones() {
        Set<ComisionEntity> comisiones = productoCreditoEntity.getComisiones();
        List<ComisionModel> result = new ArrayList<ComisionModel>();
        for (ComisionEntity productoComisionEntity : comisiones) {
            ComisionModel productoComisionModel = new ComisionAdapter(em, productoComisionEntity);
            result.add(productoComisionModel);
        }
        return result;
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
        if (!(obj instanceof ProductoModel))
            return false;
        ProductoModel other = (ProductoModel) obj;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        return true;
    }

}
