package org.sistcoop.producto.models.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.sistcoop.producto.models.ProductoCuentaPersonalModel;
import org.sistcoop.producto.models.ProductoCuentaPersonalProvider;
import org.sistcoop.producto.models.enums.TipoCuentaPersonal;
import org.sistcoop.producto.models.enums.TipoPersona;
import org.sistcoop.producto.models.jpa.entities.ProductoCuentaPersonalEntity;
import org.sistcoop.producto.models.search.SearchCriteriaModel;
import org.sistcoop.producto.models.search.SearchResultsModel;

@Named
@Stateless
@Local(ProductoCuentaPersonalProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaProductoCuentaPersonalProvider extends AbstractJpaStorage implements
        ProductoCuentaPersonalProvider {

    @PersistenceContext
    protected EntityManager em;

    protected EntityManager getEntityManager() {
        return this.em;
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
    }

    @Override
    public ProductoCuentaPersonalModel findById(String id) {
        ProductoCuentaPersonalEntity entity = this.em.find(ProductoCuentaPersonalEntity.class, id);
        return entity != null ? new ProductoCuentaPersonalAdapter(em, entity) : null;
    }

    @Override
    public ProductoCuentaPersonalModel findByCodigo(String codigo) {
        TypedQuery<ProductoCuentaPersonalEntity> query = em.createNamedQuery(
                "ProductoCuentaPersonalEntity.findByCodigo", ProductoCuentaPersonalEntity.class);

        query.setParameter("codigo", codigo);
        List<ProductoCuentaPersonalEntity> results = query.getResultList();
        if (results.size() == 0) {
            return null;
        }
        return new ProductoCuentaPersonalAdapter(em, results.get(0));
    }

    @Override
    public ProductoCuentaPersonalModel create(TipoCuentaPersonal tipoCuenta, TipoPersona tipoPersona,
            String moneda, String denominacion) {

        ProductoCuentaPersonalEntity entity = new ProductoCuentaPersonalEntity();
        entity.setTipoCuenta(tipoCuenta);
        entity.setTipoPersona(tipoPersona);
        entity.setMoneda(moneda);
        entity.setDenominacion(denominacion);
        entity.setCodigo(null);
        entity.setEstado(true);

        em.persist(entity);
        return new ProductoCuentaPersonalAdapter(em, entity);
    }

    @Override
    public boolean remove(ProductoCuentaPersonalModel productoModel) {
        String id = productoModel.getId();
        ProductoCuentaPersonalEntity entity = this.em.find(ProductoCuentaPersonalEntity.class, id);
        if (entity == null) {
            return false;
        }
        em.remove(entity);
        return true;
    }

    @Override
    public SearchResultsModel<ProductoCuentaPersonalModel> search() {
        TypedQuery<ProductoCuentaPersonalEntity> query = em.createNamedQuery(
                "ProductoCuentaPersonalEntity.findAll", ProductoCuentaPersonalEntity.class);

        List<ProductoCuentaPersonalEntity> entities = query.getResultList();
        List<ProductoCuentaPersonalModel> models = new ArrayList<ProductoCuentaPersonalModel>();
        for (ProductoCuentaPersonalEntity productoCuentaPersonalEntity : entities) {
            models.add(new ProductoCuentaPersonalAdapter(em, productoCuentaPersonalEntity));
        }

        SearchResultsModel<ProductoCuentaPersonalModel> result = new SearchResultsModel<>();
        result.setModels(models);
        result.setTotalSize(models.size());
        return result;
    }

    @Override
    public SearchResultsModel<ProductoCuentaPersonalModel> search(SearchCriteriaModel criteria) {
        SearchResultsModel<ProductoCuentaPersonalEntity> entityResult = find(criteria,
                ProductoCuentaPersonalEntity.class);

        SearchResultsModel<ProductoCuentaPersonalModel> modelResult = new SearchResultsModel<>();
        List<ProductoCuentaPersonalModel> list = new ArrayList<>();
        for (ProductoCuentaPersonalEntity entity : entityResult.getModels()) {
            list.add(new ProductoCuentaPersonalAdapter(em, entity));
        }
        modelResult.setTotalSize(entityResult.getTotalSize());
        modelResult.setModels(list);
        return modelResult;
    }

}
