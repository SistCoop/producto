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

import org.sistcoop.producto.models.ProductoComisionModel;
import org.sistcoop.producto.models.ProductoComisionProvider;
import org.sistcoop.producto.models.ProductoModel;
import org.sistcoop.producto.models.enums.Frecuencia;
import org.sistcoop.producto.models.enums.TipoValor;
import org.sistcoop.producto.models.jpa.entities.ProductoComisionEntity;
import org.sistcoop.producto.models.jpa.entities.ProductoEntity;

@Named
@Stateless
@Local(ProductoComisionProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaProductoComisionProvider implements ProductoComisionProvider {

	@PersistenceContext
	protected EntityManager em;

	@Override
	public void close() {
		// TODO Auto-generated method stub
	}

	@Override
	public ProductoComisionModel getProductoComisionById(Integer id) {
		ProductoComisionEntity entity = this.em.find(ProductoComisionEntity.class, id);
		return entity != null ? new ProductoComisionAdapter(em, entity) : null;
	}

	@Override
	public ProductoComisionModel addProductoComision(
			ProductoModel productoModel, String denominacion, BigDecimal valor,
			TipoValor tipoValor, Frecuencia frecuencia) {

		ProductoEntity productoEntity = ProductoAdapter.toProductoEntity(productoModel, em);
		
		ProductoComisionEntity productoComisionEntity = new ProductoComisionEntity();
		productoComisionEntity.setProducto(productoEntity);
		productoComisionEntity.setDenominacion(denominacion);
		productoComisionEntity.setValor(valor);
		productoComisionEntity.setTipoValor(tipoValor);
		productoComisionEntity.setFrecuencia(frecuencia);		
		
		em.persist(productoComisionEntity);
		return new ProductoComisionAdapter(em, productoComisionEntity);
		
	}

	@Override
	public boolean eliminarProductoComision(
			ProductoComisionModel productoComisionModel) {

		Integer id = productoComisionModel.getId();
		ProductoComisionEntity entity = this.em.find(ProductoComisionEntity.class, id);
		if (entity == null) return false;
		em.remove(entity);
		return true; 
	}

	@Override
	public List<ProductoComisionModel> getProductoComisiones(
			ProductoModel productoModel) {

		Integer id = productoModel.getId();
		ProductoEntity entity = this.em.find(ProductoEntity.class, id);
		Set<ProductoComisionEntity> list = entity.getComisiones();
		List<ProductoComisionModel> result = new ArrayList<ProductoComisionModel>();
		for (ProductoComisionEntity productoComisionEntity : list) {
			result.add(new ProductoComisionAdapter(em, productoComisionEntity));
		}
		return result;
		
	}

}
