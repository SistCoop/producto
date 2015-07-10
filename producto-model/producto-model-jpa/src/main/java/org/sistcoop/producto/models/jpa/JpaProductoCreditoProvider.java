package org.sistcoop.producto.models.jpa;

import java.math.BigDecimal;
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

import org.sistcoop.producto.models.ProductoCreditoModel;
import org.sistcoop.producto.models.ProductoCreditoProvider;
import org.sistcoop.producto.models.enums.TipoPersona;
import org.sistcoop.producto.models.jpa.entities.ProductoCreditoEntity;
import org.sistcoop.producto.models.search.SearchCriteriaModel;
import org.sistcoop.producto.models.search.SearchResultsModel;

@Named
@Stateless
@Local(ProductoCreditoProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaProductoCreditoProvider extends AbstractJpaStorage implements ProductoCreditoProvider {

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
    public ProductoCreditoModel findById(String id) {
        ProductoCreditoEntity productoCreditoEntity = this.em.find(ProductoCreditoEntity.class, id);
        return productoCreditoEntity != null ? new ProductoCreditoAdapter(em, productoCreditoEntity) : null;
    }

    @Override
    public ProductoCreditoModel findByCodigo(String codigo) {
        TypedQuery<ProductoCreditoEntity> query = em.createNamedQuery("ProductoCreditoEntity.findByCodigo",
                ProductoCreditoEntity.class);
        query.setParameter("codigo", codigo);
        List<ProductoCreditoEntity> results = query.getResultList();
        if (results.size() == 0) {
            return null;
        }
        return new ProductoCreditoAdapter(em, results.get(0));
    }

    @Override
    public ProductoCreditoModel create(TipoPersona tipoPersona, String moneda, String denominacion,
            BigDecimal montoMinimo, BigDecimal montoMaximo) {

        ProductoCreditoEntity entity = new ProductoCreditoEntity();
        entity.setCodigo(null);
        entity.setDenominacion(denominacion);
        entity.setTipoPersona(tipoPersona);
        entity.setMoneda(moneda);
        entity.setMontoMinimo(montoMinimo);
        entity.setMontoMaximo(montoMaximo);
        entity.setEstado(true);

        em.persist(entity);
        return new ProductoCreditoAdapter(em, entity);
    }

    @Override
    public boolean remove(ProductoCreditoModel productoModel) {
        String id = productoModel.getId();
        ProductoCreditoEntity entity = this.em.find(ProductoCreditoEntity.class, id);
        if (entity == null) {
            return false;
        }
        em.remove(entity);
        return true;
    }

    @Override
    public SearchResultsModel<ProductoCreditoModel> search() {
        TypedQuery<ProductoCreditoEntity> query = em.createNamedQuery("ProductoCreditoEntity.findAll",
                ProductoCreditoEntity.class);

        List<ProductoCreditoEntity> entities = query.getResultList();
        List<ProductoCreditoModel> models = new ArrayList<ProductoCreditoModel>();
        for (ProductoCreditoEntity productoCreditoEntity : entities) {
            models.add(new ProductoCreditoAdapter(em, productoCreditoEntity));
        }

        SearchResultsModel<ProductoCreditoModel> result = new SearchResultsModel<>();
        result.setModels(models);
        result.setTotalSize(models.size());
        return result;
    }

    @Override
    public SearchResultsModel<ProductoCreditoModel> search(SearchCriteriaModel criteria) {
        SearchResultsModel<ProductoCreditoEntity> entityResult = find(criteria, ProductoCreditoEntity.class);

        SearchResultsModel<ProductoCreditoModel> modelResult = new SearchResultsModel<>();
        List<ProductoCreditoModel> list = new ArrayList<>();
        for (ProductoCreditoEntity entity : entityResult.getModels()) {
            list.add(new ProductoCreditoAdapter(em, entity));
        }
        modelResult.setTotalSize(entityResult.getTotalSize());
        modelResult.setModels(list);
        return modelResult;
    }

}
