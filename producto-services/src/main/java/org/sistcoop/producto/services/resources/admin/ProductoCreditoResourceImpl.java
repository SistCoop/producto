package org.sistcoop.producto.services.resources.admin;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.producto.client.resource.CaracteristicasResource;
import org.sistcoop.producto.client.resource.ComisionesResource;
import org.sistcoop.producto.client.resource.ProductoCreditoResource;
import org.sistcoop.producto.client.resource.TasasResource;
import org.sistcoop.producto.models.ProductoCreditoModel;
import org.sistcoop.producto.models.ProductoCreditoProvider;
import org.sistcoop.producto.models.utils.ModelToRepresentation;
import org.sistcoop.producto.representations.idm.ProductoCreditoRepresentation;
import org.sistcoop.producto.services.managers.ProductoCreditoManager;
import org.sistcoop.producto.services.resources.producers.Caracteristicas_Credito;
import org.sistcoop.producto.services.resources.producers.Comisiones_Credito;
import org.sistcoop.producto.services.resources.producers.Tasas_Credito;

@Stateless
public class ProductoCreditoResourceImpl implements ProductoCreditoResource {

    @PathParam("producto")
    private String producto;

    @Inject
    private ProductoCreditoProvider productoCreditoProvider;

    @Inject
    private ProductoCreditoManager productoCreditoManager;

    @Context
    private UriInfo uriInfo;

    @Inject
    @Caracteristicas_Credito
    private CaracteristicasResource caracteristicasResource;

    @Inject
    @Tasas_Credito
    private TasasResource tasasResource;

    @Inject
    @Comisiones_Credito
    private ComisionesResource comisionesResource;

    private ProductoCreditoModel getProductoCreditoModel() {
        return productoCreditoProvider.findById(producto);
    }

    @Override
    public ProductoCreditoRepresentation producto() {
        return ModelToRepresentation.toRepresentation(getProductoCreditoModel());
    }

    @Override
    public void update(ProductoCreditoRepresentation representation) {
        productoCreditoManager.updateProducto(getProductoCreditoModel(), representation);
    }

    @Override
    public void remove() {
        throw new BadRequestException();
    }

    @Override
    public void disable() {
        productoCreditoManager.disableProducto(getProductoCreditoModel());
    }

    @Override
    public CaracteristicasResource caracteristicas() {
        return caracteristicasResource;
    }

    @Override
    public TasasResource tasas() {
        return tasasResource;
    }

    @Override
    public ComisionesResource comisiones() {
        return comisionesResource;
    }

}
