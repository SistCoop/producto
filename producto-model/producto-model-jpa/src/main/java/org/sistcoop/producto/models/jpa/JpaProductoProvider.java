package org.sistcoop.producto.models.jpa;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sistcoop.producto.models.ProductoModel;
import org.sistcoop.producto.models.ProductoProvider;
import org.sistcoop.producto.models.jpa.entities.ProductoEntity;

@Named
@Stateless
@Local(ProductoProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaProductoProvider implements ProductoProvider {

	@PersistenceContext
	protected EntityManager em;

	@Override
	public void close() {
		// TODO Auto-generated method stub
	}

	@Override
	public ProductoModel getProductoById(Integer id) {
		ProductoEntity entity = this.em.find(ProductoEntity.class, id);
		return entity != null ? new ProductoAdapter(em, entity) : null;
	}

}
