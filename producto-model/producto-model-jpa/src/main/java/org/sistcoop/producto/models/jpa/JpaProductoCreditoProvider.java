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

@Named
@Stateless
@Local(ProductoCreditoProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaProductoCreditoProvider implements ProductoCreditoProvider {

	@PersistenceContext
	protected EntityManager em;

	@Override
	public void close() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public ProductoCreditoModel getProductoCreditoById(Integer id) {
		ProductoCreditoEntity productoCreditoEntity = this.em.find(ProductoCreditoEntity.class, id);
		return productoCreditoEntity != null ? new ProductoCreditoAdapter(em, productoCreditoEntity) : null;
	}
	
	@Override
	public ProductoCreditoModel getProductoCreditoByCodigo(String codigo) {
		TypedQuery<ProductoCreditoEntity> query = em.createNamedQuery(ProductoCreditoEntity.findByCodigo, ProductoCreditoEntity.class);
		query.setParameter("codigo", codigo);
		List<ProductoCreditoEntity> results = query.getResultList();
		if(results.size() == 0)
			return null;
		return new ProductoCreditoAdapter(em, results.get(0));
	}
	
	@Override
	public ProductoCreditoModel getProductoByDenominacion(String denominacion) {
		TypedQuery<ProductoCreditoEntity> query = em.createNamedQuery(ProductoCreditoEntity.findByDenominacion, ProductoCreditoEntity.class);
		query.setParameter("denominacion", denominacion);
		List<ProductoCreditoEntity> results = query.getResultList();
		if(results.size() == 0)
			return null;
		return new ProductoCreditoAdapter(em, results.get(0));
	}

	@Override
	public ProductoCreditoModel addProductoCredito(String codigo, String denominacion, TipoPersona tipoPersona, String moneda, BigDecimal montoMinimo, BigDecimal montoMaximo) {
		ProductoCreditoEntity entity = new ProductoCreditoEntity();
		entity.setCodigo(codigo);
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
	public boolean desactivarProductoCredito(ProductoCreditoModel productoModel) {
		ProductoCreditoEntity entity = ProductoCreditoAdapter.toProductoCreditoEntity(productoModel, em);
		entity.setEstado(false);
		em.merge(entity);
		return true;
	}

	@Override
	public List<ProductoCreditoModel> getProductos() {
		return getProductos(true);
	}

	@Override
	public List<ProductoCreditoModel> getProductos(TipoPersona tipoPersona) {
		return getProductos(tipoPersona, true);
	}

	@Override
	public List<ProductoCreditoModel> getProductos(boolean estado) {
		TypedQuery<ProductoCreditoEntity> query = em.createNamedQuery(ProductoCreditoEntity.findAll, ProductoCreditoEntity.class);
		List<ProductoCreditoEntity> results = query.getResultList();
		List<ProductoCreditoModel> productos = new ArrayList<ProductoCreditoModel>();
		for (ProductoCreditoEntity entity : results) {
			if (entity.isEstado() == estado)
				productos.add(new ProductoCreditoAdapter(em, entity));
		}
		return productos;
	}

	@Override
	public List<ProductoCreditoModel> getProductos(TipoPersona tipoPersona, boolean estado) {
		TypedQuery<ProductoCreditoEntity> query = em.createNamedQuery(ProductoCreditoEntity.findByTipoPersona, ProductoCreditoEntity.class);
		List<ProductoCreditoEntity> results = query.getResultList();
		List<ProductoCreditoModel> productos = new ArrayList<ProductoCreditoModel>();
		for (ProductoCreditoEntity entity : results) {
			if (entity.isEstado() == estado)
				productos.add(new ProductoCreditoAdapter(em, entity));
		}
		return productos;
	}
	
	@Override
	public List<ProductoCreditoModel> getProductos(String filterText, int firstResult, int maxResults) {

		if (filterText == null)
			filterText = "";

		TypedQuery<ProductoCreditoEntity> query = em.createNamedQuery(ProductoCreditoEntity.findByFilterText, ProductoCreditoEntity.class);
		query.setParameter("filterText", "%" + filterText.toUpperCase() + "%");		
		
		if (firstResult != -1) {
			query.setFirstResult(firstResult);
		}
		if (maxResults != -1) {
			query.setMaxResults(maxResults);
		}
		
		List<ProductoCreditoEntity> results = query.getResultList();
		List<ProductoCreditoModel> productoCreditoModels = new ArrayList<ProductoCreditoModel>();
		for (ProductoCreditoEntity entity : results) {
			if(entity.isEstado()) {
				productoCreditoModels.add(new ProductoCreditoAdapter(em, entity));	
			}	
		}	
		
		return productoCreditoModels;
	}

	@Override
	public List<ProductoCreditoModel> getProductos(String filterText, int firstResult, int maxResults, TipoPersona tipoPersona) {

		if (filterText == null)
			filterText = "";
		
		TypedQuery<ProductoCreditoEntity> query = em.createNamedQuery(ProductoCreditoEntity.findByFilterTextTipoPersona, ProductoCreditoEntity.class);
		query.setParameter("filterText", "%" + filterText.toUpperCase() + "%");	
		query.setParameter("tipoPersona", tipoPersona);	
		
		if (firstResult != -1) {
			query.setFirstResult(firstResult);
		}
		if (maxResults != -1) {
			query.setMaxResults(maxResults);
		}
		
		List<ProductoCreditoEntity> results = query.getResultList();
		List<ProductoCreditoModel> productoCreditoModels = new ArrayList<ProductoCreditoModel>();
		for (ProductoCreditoEntity entity : results) {
			if(entity.isEstado()) {
				productoCreditoModels.add(new ProductoCreditoAdapter(em, entity));	
			}	
		}	
		
		return productoCreditoModels;
		
	}

	@Override
	public List<ProductoCreditoModel> getProductos(String filterText, int firstResult, int maxResults, String moneda) {

		if (filterText == null)
			filterText = "";

		TypedQuery<ProductoCreditoEntity> query = em.createNamedQuery(ProductoCreditoEntity.findByFilterTextMoneda, ProductoCreditoEntity.class);
		query.setParameter("filterText", "%" + filterText.toUpperCase() + "%");	
		query.setParameter("moneda", moneda);	
		
		if (firstResult != -1) {
			query.setFirstResult(firstResult);
		}
		if (maxResults != -1) {
			query.setMaxResults(maxResults);
		}
		
		List<ProductoCreditoEntity> results = query.getResultList();
		List<ProductoCreditoModel> productoCreditoModels = new ArrayList<ProductoCreditoModel>();
		for (ProductoCreditoEntity entity : results) {
			if(entity.isEstado()) {
				productoCreditoModels.add(new ProductoCreditoAdapter(em, entity));	
			}	
		}	
		
		return productoCreditoModels;
		
	}
	
	@Override
	public List<ProductoCreditoModel> getProductos(String filterText, int firstResult, int maxResults, TipoPersona tipoPersona, String moneda) {

		if (filterText == null)
			filterText = "";

		TypedQuery<ProductoCreditoEntity> query = em.createNamedQuery(ProductoCreditoEntity.findByFilterTextTipoPersonaMoneda, ProductoCreditoEntity.class);
		query.setParameter("filterText", "%" + filterText.toUpperCase() + "%");
		query.setParameter("tipoPersona", tipoPersona);
		query.setParameter("moneda", moneda);
		
		if (firstResult != -1) {
			query.setFirstResult(firstResult);
		}
		if (maxResults != -1) {
			query.setMaxResults(maxResults);
		}
		
		List<ProductoCreditoEntity> results = query.getResultList();
		List<ProductoCreditoModel> productoCreditoModels = new ArrayList<ProductoCreditoModel>();
		for (ProductoCreditoEntity entity : results) {
			if(entity.isEstado()) {
				productoCreditoModels.add(new ProductoCreditoAdapter(em, entity));	
			}	
		}	
		
		return productoCreditoModels;
	}

	

}
