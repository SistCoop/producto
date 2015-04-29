package org.sistcoop.producto.services.resources.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.producto.client.resource.ProductoCreditoResource;
import org.sistcoop.producto.models.ProductoCaracteristicaModel;
import org.sistcoop.producto.models.ProductoCaracteristicaProvider;
import org.sistcoop.producto.models.ProductoComisionModel;
import org.sistcoop.producto.models.ProductoComisionProvider;
import org.sistcoop.producto.models.ProductoCreditoModel;
import org.sistcoop.producto.models.ProductoCreditoProvider;
import org.sistcoop.producto.models.ProductoModel;
import org.sistcoop.producto.models.ProductoProvider;
import org.sistcoop.producto.models.ProductoTasaModel;
import org.sistcoop.producto.models.ProductoTasaProvider;
import org.sistcoop.producto.models.enums.TipoPersona;
import org.sistcoop.producto.models.utils.ModelToRepresentation;
import org.sistcoop.producto.models.utils.RepresentationToModel;
import org.sistcoop.producto.representations.idm.ProductoCaracteristicaRepresentation;
import org.sistcoop.producto.representations.idm.ProductoComisionRepresentation;
import org.sistcoop.producto.representations.idm.ProductoCreditoRepresentation;
import org.sistcoop.producto.representations.idm.ProductoTasaRepresentation;

@Stateless
public class ProductoCreditoResourceImpl implements ProductoCreditoResource {

	@Inject
	private ProductoProvider productoProvider;
	
	@Inject
	private ProductoCreditoProvider productoCreditoProvider;

	@Inject
	private ProductoCaracteristicaProvider productoCaracteristicaProvider;
	
	@Inject
	private ProductoTasaProvider productoTasaProvider;
	
	@Inject
	private ProductoComisionProvider productoComisionProvider;
	
	@Inject
	private RepresentationToModel representationToModel;

	@Context
	protected UriInfo uriInfo;

	@Override
	public ProductoCreditoRepresentation findById(Integer id) {
		ProductoCreditoModel model = productoCreditoProvider.getProductoCreditoById(id);
		ProductoCreditoRepresentation rep = ModelToRepresentation.toRepresentation(model);
		return rep;
	}
	
	@Override
	public ProductoCreditoRepresentation findByCodigo(String codigo) {
		ProductoCreditoModel model = productoCreditoProvider.getProductoCreditoByCodigo(codigo);
		ProductoCreditoRepresentation rep = ModelToRepresentation.toRepresentation(model);
		return rep;
	}
	
	@Override
	public ProductoCreditoRepresentation findByDenominacion(String denominacion) {
		ProductoCreditoModel model = productoCreditoProvider.getProductoByDenominacion(denominacion);
		ProductoCreditoRepresentation rep = ModelToRepresentation.toRepresentation(model);
		return rep;
	}

	@Override
	public Response create(ProductoCreditoRepresentation productoCreditoRepresentation) {
		ProductoCreditoModel model = representationToModel.createProductoCredito(productoCreditoRepresentation, productoCreditoProvider);
		return Response.created(uriInfo.getAbsolutePathBuilder().path(model.getId().toString()).build()).header("Access-Control-Expose-Headers", "Location").entity(model.getId()).build();
	}

	@Override
	public void update(int id, ProductoCreditoRepresentation productoCreditoRepresentacion) {
		ProductoCreditoModel model = productoCreditoProvider.getProductoCreditoById(id);
		
		model.setCodigo(productoCreditoRepresentacion.getCodigo());
		model.setDenominacion(productoCreditoRepresentacion.getDenominacion());
		model.setTipoPersona(TipoPersona.valueOf(productoCreditoRepresentacion.getTipoPersona()));
		model.setMoneda(productoCreditoRepresentacion.getMoneda());
		model.setMontoMinimo(productoCreditoRepresentacion.getMontoMinimo());
		model.setMontoMinimo(productoCreditoRepresentacion.getMontoMinimo());
		model.commit();
	}
	
	@Override
	public void desactivar(int id) {
		ProductoCreditoModel model = productoCreditoProvider.getProductoCreditoById(id);
		boolean result = productoCreditoProvider.desactivarProductoCredito(model);
		if (!result)
			throw new InternalServerErrorException();
	}

	@Override
	public void delete(int id) {
		throw new InternalServerErrorException();
	}	

	@Override
	public List<ProductoCreditoRepresentation> findAll(String filterText,
			Integer firstResult, Integer maxResults, String tipoPersona,
			List<String> monedas, Boolean estado) {

		List<ProductoCreditoRepresentation> results = new ArrayList<ProductoCreditoRepresentation>();
		List<ProductoCreditoModel> productoCreditoModels = null;		
		
		if(filterText == null)
			filterText = "";
		if(firstResult == null)
			firstResult = -1;
		if(maxResults == null)
			maxResults = -1;
		if(tipoPersona == null && monedas.size() == 0) {
			productoCreditoModels = productoCreditoProvider.getProductos(filterText, firstResult, maxResults);	
		}			
		if(tipoPersona == null && monedas.size() != 0) {
			productoCreditoModels = new ArrayList<ProductoCreditoModel>();
			for (String moneda : monedas) {
				productoCreditoModels.addAll(productoCreditoProvider.getProductos(filterText, firstResult, maxResults, moneda));
			}			
		}	
		if(tipoPersona != null && monedas.size() == 0) {			
			productoCreditoModels = productoCreditoProvider.getProductos(filterText, firstResult, maxResults, TipoPersona.valueOf(tipoPersona));	
		}
		if(tipoPersona != null && monedas.size() != 0) {
			productoCreditoModels = new ArrayList<ProductoCreditoModel>();
			for (String moneda : monedas) {
				productoCreditoModels.addAll(productoCreditoProvider.getProductos(filterText, firstResult, maxResults, TipoPersona.valueOf(tipoPersona), moneda));
			}	
		}
		
		for (ProductoCreditoModel productoCreditoModel : productoCreditoModels) {
			results.add(ModelToRepresentation.toRepresentation(productoCreditoModel));
		}
		return results;
		
	}

	
	/**
	 * Producto caracteristicas*/
	
	@Override
	public Response addProductoCaracteristica(Integer id,
			ProductoCaracteristicaRepresentation productoCaracteristicaRepresentation) {
		
		ProductoModel productoModel = productoProvider.getProductoById(id);
		
		ProductoCaracteristicaModel model = representationToModel.createProductoCaracteristica(productoCaracteristicaRepresentation, productoModel, productoCaracteristicaProvider);
		return Response.created(uriInfo.getAbsolutePathBuilder().path(model.getId().toString()).build()).header("Access-Control-Expose-Headers", "Location").entity(model.getId()).build();
		
	}

	@Override
	public List<ProductoCaracteristicaRepresentation> getProductoCaracteristicas(Integer id) {
		ProductoCreditoModel model = productoCreditoProvider.getProductoCreditoById(id);
		List<ProductoCaracteristicaModel> list = model.getCaracteristicas();
		List<ProductoCaracteristicaRepresentation> result = new ArrayList<ProductoCaracteristicaRepresentation>();
		for (ProductoCaracteristicaModel productoCaracteristicaModel : list) {
			result.add(ModelToRepresentation.toRepresentation(productoCaracteristicaModel));
		}
		return result;
	}

	@Override
	public Response addProductoTasa(Integer id,
			ProductoTasaRepresentation productoTasaRepresentation) {

		ProductoModel productoModel = productoProvider.getProductoById(id);
		
		ProductoTasaModel model = representationToModel.createProductoTasa(productoTasaRepresentation, productoModel, productoTasaProvider);
		return Response.created(uriInfo.getAbsolutePathBuilder().path(model.getId().toString()).build()).header("Access-Control-Expose-Headers", "Location").entity(model.getId()).build();
		
	}

	@Override
	public List<ProductoTasaRepresentation> getProductoTasas(Integer id) {
		ProductoCreditoModel model = productoCreditoProvider.getProductoCreditoById(id);
		List<ProductoTasaModel> list = model.getTasas();
		List<ProductoTasaRepresentation> result = new ArrayList<ProductoTasaRepresentation>();
		for (ProductoTasaModel productoTasaModel : list) {
			result.add(ModelToRepresentation.toRepresentation(productoTasaModel));
		}
		return result;
	}

	@Override
	public Response addProductoComision(Integer id,
			ProductoComisionRepresentation productoComisionRepresentation) {

		ProductoModel productoModel = productoProvider.getProductoById(id);
		
		ProductoComisionModel model = representationToModel.createProductoComision(productoComisionRepresentation, productoModel, productoComisionProvider);
		return Response.created(uriInfo.getAbsolutePathBuilder().path(model.getId().toString()).build()).header("Access-Control-Expose-Headers", "Location").entity(model.getId()).build();
		
		
	}

	@Override
	public List<ProductoComisionRepresentation> getProductoComisiones(Integer id) {

		ProductoCreditoModel model = productoCreditoProvider.getProductoCreditoById(id);
		List<ProductoComisionModel> list = model.getComisiones();
		List<ProductoComisionRepresentation> result = new ArrayList<ProductoComisionRepresentation>();
		for (ProductoComisionModel productoComisionModel : list) {			
			result.add(ModelToRepresentation.toRepresentation(productoComisionModel));
		}		
		return result;
		
	}

}
