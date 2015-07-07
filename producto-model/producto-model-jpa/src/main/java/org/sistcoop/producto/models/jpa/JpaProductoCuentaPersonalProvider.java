package org.sistcoop.producto.models.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
import org.sistcoop.producto.models.enums.TipoPersona;
import org.sistcoop.producto.models.jpa.entities.ProductoCuentaPersonalEntity;

@Named
@Stateless
@Local(ProductoCuentaPersonalProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaProductoCuentaPersonalProvider implements ProductoCuentaPersonalProvider {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public void close() {
        // TODO Auto-generated method stub
    }

    @Override
    public ProductoCuentaPersonalModel addProducto(String denominacion, TipoPersona tipoPersona,
            List<String> monedas) {
        ProductoCuentaPersonalEntity entity = new ProductoCuentaPersonalEntity();
        entity.setDenominacion(denominacion);
        entity.setTipoPersona(tipoPersona);

        String codigo = UUID.randomUUID().toString();
        entity.setCodigo(codigo);

        entity.setEstado(true);

        em.persist(entity);
        return new ProductoCuentaPersonalAdapter(em, entity);
    }

    @Override
    public boolean desactivarProducto(ProductoCuentaPersonalModel productoModel) {
        ProductoCuentaPersonalEntity entity = ProductoCuentaPersonalAdapter.toProductoCuentaPersonalEntity(
                productoModel, em);
        entity.setEstado(false);
        em.merge(entity);
        return true;
    }

    @Override
    public ProductoCuentaPersonalModel getProductoById(String id) {
        ProductoCuentaPersonalEntity productoCuentaPersonalEntity = this.em.find(
                ProductoCuentaPersonalEntity.class, id);
        return productoCuentaPersonalEntity != null ? new ProductoCuentaPersonalAdapter(em,
                productoCuentaPersonalEntity) : null;
    }

    @Override
    public List<ProductoCuentaPersonalModel> getProductos() {
        return getProductos(true);
    }

    @Override
    public List<ProductoCuentaPersonalModel> getProductos(TipoPersona tipoPersona) {
        return getProductos(tipoPersona, true);
    }

    @Override
    public List<ProductoCuentaPersonalModel> getProductos(boolean estado) {
        TypedQuery<ProductoCuentaPersonalEntity> query = em.createNamedQuery(
                ProductoCuentaPersonalEntity.findAll, ProductoCuentaPersonalEntity.class);
        List<ProductoCuentaPersonalEntity> results = query.getResultList();
        List<ProductoCuentaPersonalModel> productos = new ArrayList<ProductoCuentaPersonalModel>();
        for (ProductoCuentaPersonalEntity entity : results) {
            if (entity.isEstado() == estado)
                productos.add(new ProductoCuentaPersonalAdapter(em, entity));
        }
        return productos;
    }

    @Override
    public List<ProductoCuentaPersonalModel> getProductos(TipoPersona tipoPersona, boolean estado) {
        TypedQuery<ProductoCuentaPersonalEntity> query = em.createNamedQuery(
                ProductoCuentaPersonalEntity.findByTipoPersona, ProductoCuentaPersonalEntity.class);
        List<ProductoCuentaPersonalEntity> results = query.getResultList();
        List<ProductoCuentaPersonalModel> productos = new ArrayList<ProductoCuentaPersonalModel>();
        for (ProductoCuentaPersonalEntity entity : results) {
            if (entity.isEstado() == estado)
                productos.add(new ProductoCuentaPersonalAdapter(em, entity));
        }
        return productos;
    }

}
