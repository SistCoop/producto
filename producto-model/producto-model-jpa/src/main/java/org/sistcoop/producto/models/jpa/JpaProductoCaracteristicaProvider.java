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

import org.sistcoop.producto.models.ProductoCaracteristicaModel;
import org.sistcoop.producto.models.ProductoCaracteristicaProvider;
import org.sistcoop.producto.models.ProductoModel;
import org.sistcoop.producto.models.jpa.entities.ProductoCaracteristicaEntity;
import org.sistcoop.producto.models.jpa.entities.ProductoEntity;

@Named
@Stateless
@Local(ProductoCaracteristicaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaProductoCaracteristicaProvider implements ProductoCaracteristicaProvider {

	@PersistenceContext
	protected EntityManager em;

	@Override
	public void close() {
		// TODO Auto-generated method stub
	}

	@Override
	public ProductoCaracteristicaModel getProductoCaracteristicaById(Integer id) {
		ProductoCaracteristicaEntity entity = this.em.find(ProductoCaracteristicaEntity.class, id);
		return entity != null ? new ProductoCaracteristicaAdapter(em, entity) : null;
	}

	@Override
	public ProductoCaracteristicaModel addProductoCaracteristica(
			ProductoModel productoModel, String descripcion,
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
	public boolean eliminarProductoCaracteristica(ProductoCaracteristicaModel productoCaracteristicaModel) {
		Integer id = productoCaracteristicaModel.getId();
		ProductoCaracteristicaEntity entity = this.em.find(ProductoCaracteristicaEntity.class, id);
		if (entity == null) return false;
		em.remove(entity);
		return true;  		
	}

	@Override
	public List<ProductoCaracteristicaModel> getProductoCaracteristicas(ProductoModel productoModel) {
		Integer id = productoModel.getId();
		ProductoEntity entity = this.em.find(ProductoEntity.class, id);
		Set<ProductoCaracteristicaEntity> list = entity.getCaracteristicas();
		List<ProductoCaracteristicaModel> result = new ArrayList<ProductoCaracteristicaModel>();
		for (ProductoCaracteristicaEntity productoCaracteristicaEntity : list) {
			result.add(new ProductoCaracteristicaAdapter(em, productoCaracteristicaEntity));
		}
		return result;
	}
	
	

	

}
