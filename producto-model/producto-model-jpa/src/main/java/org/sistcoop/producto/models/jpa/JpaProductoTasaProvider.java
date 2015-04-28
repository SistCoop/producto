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
import org.sistcoop.producto.models.ProductoTasaModel;
import org.sistcoop.producto.models.ProductoTasaProvider;
import org.sistcoop.producto.models.jpa.entities.ProductoEntity;
import org.sistcoop.producto.models.jpa.entities.ProductoTasaEntity;

@Named
@Stateless
@Local(ProductoTasaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaProductoTasaProvider implements ProductoTasaProvider {

	@PersistenceContext
	protected EntityManager em;

	@Override
	public void close() {
		// TODO Auto-generated method stub
	}

	@Override
	public ProductoTasaModel getProductoTasaById(Integer id) {
		ProductoTasaEntity entity = this.em.find(ProductoTasaEntity.class, id);
		return entity != null ? new ProductoTasaAdapter(em, entity) : null;
	}

	@Override
	public ProductoTasaModel addProductoTasa(ProductoModel productoModel,
			String tasa, BigDecimal valor) {

		ProductoEntity productoEntity = ProductoAdapter.toProductoEntity(productoModel, em);
		
		ProductoTasaEntity productoTasaEntity = new ProductoTasaEntity();
		productoTasaEntity.setProducto(productoEntity);
		productoTasaEntity.setTasa(tasa);
		productoTasaEntity.setValor(valor);
		
		em.persist(productoTasaEntity);
		return new ProductoTasaAdapter(em, productoTasaEntity);
		
	}

	@Override
	public boolean eliminarProductoTasa(ProductoTasaModel productoTasaModel) {
		Integer id = productoTasaModel.getId();
		ProductoTasaEntity entity = this.em.find(ProductoTasaEntity.class, id);
		if (entity == null) return false;
		em.remove(entity);
		return true;
	}

	@Override
	public List<ProductoTasaModel> getProductoTasas(ProductoModel productoModel) {
		Integer id = productoModel.getId();
		ProductoEntity entity = this.em.find(ProductoEntity.class, id);
		Set<ProductoTasaEntity> list = entity.getTasas();
		List<ProductoTasaModel> result = new ArrayList<ProductoTasaModel>();
		for (ProductoTasaEntity productoTasaEntity : list) {
			result.add(new ProductoTasaAdapter(em, productoTasaEntity));
		}
		return result;
	}

}
