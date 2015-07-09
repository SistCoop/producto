package org.sistcoop.producto.services.resources.admin;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.sistcoop.producto.client.resource.CaracteristicasResource;
import org.sistcoop.producto.client.resource.ComisionesResource;
import org.sistcoop.producto.client.resource.ProductoCuentaPersonalResource;
import org.sistcoop.producto.client.resource.TasasResource;
import org.sistcoop.producto.models.ProductoCuentaPersonalModel;
import org.sistcoop.producto.models.ProductoCuentaPersonalProvider;
import org.sistcoop.producto.models.utils.ModelToRepresentation;
import org.sistcoop.producto.representations.idm.ProductoCuentaPersonalRepresentation;
import org.sistcoop.producto.services.managers.ProductoCuentaPersonalManager;
import org.sistcoop.producto.services.resources.producers.Caracteristicas_CuentaPersonal;
import org.sistcoop.producto.services.resources.producers.Comisiones_CuentaPersonal;
import org.sistcoop.producto.services.resources.producers.Tasas_CuentaPersonal;

@Stateless
public class ProductoCuentaPersonalResourceImpl implements ProductoCuentaPersonalResource {

    @PathParam("producto")
    private String producto;

    @Inject
    private ProductoCuentaPersonalProvider productoCuentaPersonalProvider;

    @Inject
    private ProductoCuentaPersonalManager productoCuentaPersonalManager;

    @Context
    private UriInfo uriInfo;

    @Inject
    @Caracteristicas_CuentaPersonal
    private CaracteristicasResource caracteristicasResource;

    @Inject
    @Tasas_CuentaPersonal
    private TasasResource tasasResource;

    @Inject
    @Comisiones_CuentaPersonal
    private ComisionesResource comisionesResource;

    private ProductoCuentaPersonalModel getProductoCuentaPersonalModel() {
        return productoCuentaPersonalProvider.findById(producto);
    }

    @Override
    public ProductoCuentaPersonalRepresentation producto() {
        return ModelToRepresentation.toRepresentation(getProductoCuentaPersonalModel());
    }

    @Override
    public void update(ProductoCuentaPersonalRepresentation representation) {
        productoCuentaPersonalManager.updateProducto(getProductoCuentaPersonalModel(), representation);
    }

    @Override
    public void remove() {
        throw new BadRequestException();
    }

    @Override
    public void disable() {
        productoCuentaPersonalManager.disableProducto(getProductoCuentaPersonalModel());
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
