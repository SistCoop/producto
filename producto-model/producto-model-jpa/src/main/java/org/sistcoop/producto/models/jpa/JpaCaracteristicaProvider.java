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
import org.sistcoop.producto.models.ProductoModel;
import org.sistcoop.producto.models.jpa.entities.CaracteristicaEntity;
import org.sistcoop.producto.models.jpa.entities.ProductoEntity;
import org.sistcoop.producto.models.search.SearchResultsModel;

@Named
@Stateless
@Local(CaracteristicaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaCaracteristicaProvider implements CaracteristicaProvider {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public void close() {
        // TODO Auto-generated method stub
    }

    @Override
    public CaracteristicaModel findById(String id) {
        CaracteristicaEntity entity = this.em.find(CaracteristicaEntity.class, id);
        return entity != null ? new CaracteristicaAdapter(em, entity) : null;
    }

    @Override
    public CaracteristicaModel create(ProductoModel productoModel, String descripcion,
            String descripcionDetallada) {

        ProductoEntity productoEntity = this.em.find(ProductoEntity.class, productoModel.getId());

        CaracteristicaEntity productoCaracteristicaEntity = new CaracteristicaEntity();
        productoCaracteristicaEntity.setProducto(productoEntity);
        productoCaracteristicaEntity.setDescripcion(descripcion);
        productoCaracteristicaEntity.setDescripcionDetallada(descripcionDetallada);

        em.persist(productoCaracteristicaEntity);
        return new CaracteristicaAdapter(em, productoCaracteristicaEntity);
    }

    @Override
    public boolean remove(CaracteristicaModel caracteristicaModel) {
        String id = caracteristicaModel.getId();
        CaracteristicaEntity entity = this.em.find(CaracteristicaEntity.class, id);
        if (entity == null) {
            return false;
        }
        em.remove(entity);
        return true;
    }

    @Override
    public SearchResultsModel<CaracteristicaModel> search(ProductoModel productoModel) {
        String id = productoModel.getId();
        ProductoEntity entity = this.em.find(ProductoEntity.class, id);
        Set<CaracteristicaEntity> entities = entity.getCaracteristicas();
        List<CaracteristicaModel> models = new ArrayList<CaracteristicaModel>();
        for (CaracteristicaEntity caracteristicaEntity : entities) {
            models.add(new CaracteristicaAdapter(em, caracteristicaEntity));
        }

        SearchResultsModel<CaracteristicaModel> result = new SearchResultsModel<>();
        result.setModels(models);
        result.setTotalSize(models.size());
        return result;
    }

}
