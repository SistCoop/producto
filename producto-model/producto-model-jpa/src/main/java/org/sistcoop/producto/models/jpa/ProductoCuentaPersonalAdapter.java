package org.sistcoop.producto.models.jpa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.sistcoop.producto.models.CaracteristicaModel;
import org.sistcoop.producto.models.ComisionModel;
import org.sistcoop.producto.models.ProductoCuentaPersonalModel;
import org.sistcoop.producto.models.TasaModel;
import org.sistcoop.producto.models.enums.TipoCuentaPersonal;
import org.sistcoop.producto.models.enums.TipoPersona;
import org.sistcoop.producto.models.jpa.entities.CaracteristicaEntity;
import org.sistcoop.producto.models.jpa.entities.ComisionEntity;
import org.sistcoop.producto.models.jpa.entities.ProductoCuentaPersonalEntity;
import org.sistcoop.producto.models.jpa.entities.TasaEntity;

public class ProductoCuentaPersonalAdapter implements ProductoCuentaPersonalModel {

    private static final long serialVersionUID = 1L;

    protected ProductoCuentaPersonalEntity productoCuentaPersonalEntity;
    protected EntityManager em;

    public ProductoCuentaPersonalAdapter(EntityManager em,
            ProductoCuentaPersonalEntity productoCuentaPersonalEntity) {
        this.em = em;
        this.productoCuentaPersonalEntity = productoCuentaPersonalEntity;
    }

    public ProductoCuentaPersonalEntity getProductoCuentaPersonalEntityEntity() {
        return productoCuentaPersonalEntity;
    }

    public static ProductoCuentaPersonalEntity toProductoCuentaPersonalEntity(
            ProductoCuentaPersonalModel model, EntityManager em) {
        if (model instanceof ProductoCuentaPersonalAdapter) {
            return ((ProductoCuentaPersonalAdapter) model).getProductoCuentaPersonalEntityEntity();
        }
        return em.getReference(ProductoCuentaPersonalEntity.class, model.getId());
    }

    @Override
    public String getId() {
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
    public String getMoneda() {
        return productoCuentaPersonalEntity.getMoneda();
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
    public TipoCuentaPersonal getTipoCuentaPersonal() {
        return productoCuentaPersonalEntity.getTipoCuenta();
    }

    @Override
    public BigDecimal getMontoMinimo() {
        return productoCuentaPersonalEntity.getMontoMinimo();
    }

    @Override
    public void setMontoMinimo(BigDecimal montoMinimo) {
        productoCuentaPersonalEntity.setMontoMinimo(montoMinimo);
    }

    @Override
    public BigDecimal getMontoMaximo() {
        return productoCuentaPersonalEntity.getMontoMaximo();
    }

    @Override
    public void setMontoMaximo(BigDecimal montoMaximo) {
        productoCuentaPersonalEntity.setMontoMaximo(montoMaximo);
    }

    @Override
    public void commit() {
        em.merge(productoCuentaPersonalEntity);
    }

    @Override
    public List<CaracteristicaModel> getCaracteristicas() {
        Set<CaracteristicaEntity> productoCaracteristicaEntities = productoCuentaPersonalEntity
                .getCaracteristicas();
        List<CaracteristicaModel> result = new ArrayList<CaracteristicaModel>();
        for (CaracteristicaEntity productoCaracteristicaEntity : productoCaracteristicaEntities) {
            result.add(new CaracteristicaAdapter(em, productoCaracteristicaEntity));
        }
        return result;
    }

    @Override
    public List<TasaModel> getTasas() {
        Set<TasaEntity> tasas = productoCuentaPersonalEntity.getTasas();
        List<TasaModel> result = new ArrayList<TasaModel>();
        for (TasaEntity productoTasaEntity : tasas) {
            TasaModel productoTasaModel = new TasaAdapter(em, productoTasaEntity);
            result.add(productoTasaModel);
        }
        return result;
    }

    @Override
    public List<ComisionModel> getComisiones() {
        Set<ComisionEntity> comisiones = productoCuentaPersonalEntity.getComisiones();
        List<ComisionModel> result = new ArrayList<ComisionModel>();
        for (ComisionEntity productoComisionEntity : comisiones) {
            ComisionModel productoComisionModel = new ComisionAdapter(em, productoComisionEntity);
            result.add(productoComisionModel);
        }
        return result;
    }

}
