package org.sistcoop.producto.models.jpa;

import java.math.BigDecimal;
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

import org.sistcoop.producto.models.ProductoModel;
import org.sistcoop.producto.models.TasaModel;
import org.sistcoop.producto.models.TasaProvider;
import org.sistcoop.producto.models.jpa.entities.ProductoEntity;
import org.sistcoop.producto.models.jpa.entities.TasaEntity;
import org.sistcoop.producto.models.search.SearchResultsModel;

@Named
@Stateless
@Local(TasaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaTasaProvider implements TasaProvider {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public void close() {
        // TODO Auto-generated method stub
    }

    @Override
    public TasaModel findById(String id) {
        TasaEntity entity = this.em.find(TasaEntity.class, id);
        return entity != null ? new TasaAdapter(em, entity) : null;
    }

    @Override
    public TasaModel create(ProductoModel productoModel, String tasa, BigDecimal valor) {
        ProductoEntity productoEntity = this.em.find(ProductoEntity.class, productoModel.getId());

        TasaEntity productoTasaEntity = new TasaEntity();
        productoTasaEntity.setProducto(productoEntity);
        productoTasaEntity.setTasa(tasa);
        productoTasaEntity.setValor(valor);

        em.persist(productoTasaEntity);
        return new TasaAdapter(em, productoTasaEntity);

    }

    @Override
    public boolean remove(TasaModel productoTasaModel) {
        String id = productoTasaModel.getId();
        TasaEntity entity = this.em.find(TasaEntity.class, id);
        if (entity == null) {
            return false;
        }
        em.remove(entity);
        return true;
    }

    @Override
    public SearchResultsModel<TasaModel> search(ProductoModel productoModel) {
        String id = productoModel.getId();
        ProductoEntity entity = this.em.find(ProductoEntity.class, id);
        Set<TasaEntity> entities = entity.getTasas();
        List<TasaModel> models = new ArrayList<TasaModel>();
        for (TasaEntity tasaEntity : entities) {
            models.add(new TasaAdapter(em, tasaEntity));
        }

        SearchResultsModel<TasaModel> result = new SearchResultsModel<>();
        result.setBeans(models);
        result.setTotalSize(models.size());
        return result;
    }

}
