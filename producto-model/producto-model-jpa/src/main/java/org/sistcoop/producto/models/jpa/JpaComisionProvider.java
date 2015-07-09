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

import org.sistcoop.producto.models.ComisionModel;
import org.sistcoop.producto.models.ComisionProvider;
import org.sistcoop.producto.models.ProductoModel;
import org.sistcoop.producto.models.enums.Frecuencia;
import org.sistcoop.producto.models.enums.TipoValor;
import org.sistcoop.producto.models.jpa.entities.ComisionEntity;
import org.sistcoop.producto.models.jpa.entities.ProductoEntity;
import org.sistcoop.producto.models.search.SearchResultsModel;

@Named
@Stateless
@Local(ComisionProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaComisionProvider implements ComisionProvider {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public void close() {
        // TODO Auto-generated method stub
    }

    @Override
    public ComisionModel findById(String id) {
        ComisionEntity entity = this.em.find(ComisionEntity.class, id);
        return entity != null ? new ComisionAdapter(em, entity) : null;
    }

    @Override
    public ComisionModel create(ProductoModel productoModel, String denominacion, BigDecimal valor,
            TipoValor tipoValor, Frecuencia frecuencia) {

        ProductoEntity productoEntity = this.em.find(ProductoEntity.class, productoModel.getId());

        ComisionEntity productoComisionEntity = new ComisionEntity();
        productoComisionEntity.setProducto(productoEntity);
        productoComisionEntity.setDenominacion(denominacion);
        productoComisionEntity.setValor(valor);
        productoComisionEntity.setTipoValor(tipoValor);
        productoComisionEntity.setFrecuencia(frecuencia);

        em.persist(productoComisionEntity);
        return new ComisionAdapter(em, productoComisionEntity);
    }

    @Override
    public boolean remove(ComisionModel productoComisionModel) {
        String id = productoComisionModel.getId();
        ComisionEntity entity = this.em.find(ComisionEntity.class, id);
        if (entity == null) {
            return false;
        }
        em.remove(entity);
        return true;
    }

    @Override
    public SearchResultsModel<ComisionModel> search(ProductoModel productoModel) {
        String id = productoModel.getId();
        ProductoEntity entity = this.em.find(ProductoEntity.class, id);
        Set<ComisionEntity> entities = entity.getComisiones();
        List<ComisionModel> models = new ArrayList<ComisionModel>();
        for (ComisionEntity comisionEntity : entities) {
            models.add(new ComisionAdapter(em, comisionEntity));
        }

        SearchResultsModel<ComisionModel> result = new SearchResultsModel<>();
        result.setBeans(models);
        result.setTotalSize(models.size());
        return result;
    }

}
