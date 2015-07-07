package org.sistcoop.producto.models.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sistcoop.producto.models.CaracteristicaModel;
import org.sistcoop.producto.models.CaracteristicaProvider;
import org.sistcoop.producto.models.ProductoCreditoModel;
import org.sistcoop.producto.models.ProductoCuentaPersonalModel;
import org.sistcoop.producto.models.ProductoModel;
import org.sistcoop.producto.models.jpa.entities.ProductoCaracteristicaEntity;
import org.sistcoop.producto.models.jpa.entities.ProductoCreditoEntity;
import org.sistcoop.producto.models.jpa.entities.ProductoCuentaPersonalEntity;
import org.sistcoop.producto.models.jpa.entities.ProductoEntity;

@Named
@Stateless
@Local(CaracteristicaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaProductoCaracteristicaProvider implements CaracteristicaProvider {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public void close() {
        // TODO Auto-generated method stub
    }

    @Override
    public CaracteristicaModel getProductoCaracteristicaById(String id) {
        ProductoCaracteristicaEntity entity = this.em.find(ProductoCaracteristicaEntity.class, id);
        return entity != null ? new ProductoCaracteristicaAdapter(em, entity) : null;
    }

    @Override
    public CaracteristicaModel addProductoCaracteristica(ProductoModel productoModel, String descripcion,
            String descripcionDetallada) {

        ProductoEntity productoEntity = ProductoAdapter.toProductoEntity(productoModel, em);

        ProductoCaracteristicaEntity productoCaracteristicaEntity = new ProductoCaracteristicaEntity();
        productoCaracteristicaEntity.setProducto(productoEntity);
        productoCaracteristicaEntity.setDescripcion(descripcion);
        productoCaracteristicaEntity.setDescripcionDetallada(descripcionDetallada);

        em.persist(productoCaracteristicaEntity);
        return new ProductoCaracteristicaAdapter(em, productoCaracteristicaEntity);
    }

    @Override
    public boolean removeProductoCaracteristica(CaracteristicaModel productoCaracteristicaModel) {
        String id = productoCaracteristicaModel.getId();
        ProductoCaracteristicaEntity entity = this.em.find(ProductoCaracteristicaEntity.class, id);
        if (entity == null)
            return false;
        em.remove(entity);
        return true;
    }

    @Override
    public List<CaracteristicaModel> getProductoCaracteristicas(ProductoModel productoModel) {
        String id = productoModel.getId();
        ProductoEntity entity = this.em.find(ProductoEntity.class, id);
        Set<ProductoCaracteristicaEntity> list = entity.getCaracteristicas();
        List<CaracteristicaModel> result = new ArrayList<CaracteristicaModel>();
        for (ProductoCaracteristicaEntity productoCaracteristicaEntity : list) {
            result.add(new ProductoCaracteristicaAdapter(em, productoCaracteristicaEntity));
        }
        return result;
    }

    @Override
    public List<CaracteristicaModel> getCaracteristicas(ProductoCreditoModel productoCreditoModel) {
        String id = productoCreditoModel.getId();
        ProductoCreditoEntity entity = this.em.find(ProductoCreditoEntity.class, id);
        Set<ProductoCaracteristicaEntity> list = entity.getCaracteristicas();
        List<CaracteristicaModel> result = new ArrayList<CaracteristicaModel>();
        for (ProductoCaracteristicaEntity productoCaracteristicaEntity : list) {
            result.add(new ProductoCaracteristicaAdapter(em, productoCaracteristicaEntity));
        }
        return result;
    }

    @Override
    public List<CaracteristicaModel> getCaracteristicas(
            ProductoCuentaPersonalModel productoCuentaPersonalModel) {
        String id = productoCuentaPersonalModel.getId();
        ProductoCuentaPersonalEntity entity = this.em.find(ProductoCuentaPersonalEntity.class, id);
        Set<ProductoCaracteristicaEntity> list = entity.getCaracteristicas();
        List<CaracteristicaModel> result = new ArrayList<CaracteristicaModel>();
        for (ProductoCaracteristicaEntity productoCaracteristicaEntity : list) {
            result.add(new ProductoCaracteristicaAdapter(em, productoCaracteristicaEntity));
        }
        return result;
    }

}
