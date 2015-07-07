package org.sistcoop.producto.models.utils;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoop.producto.models.CaracteristicaModel;
import org.sistcoop.producto.models.CaracteristicaProvider;
import org.sistcoop.producto.models.ProductoComisionModel;
import org.sistcoop.producto.models.ProductoComisionProvider;
import org.sistcoop.producto.models.ProductoCreditoModel;
import org.sistcoop.producto.models.ProductoCreditoProvider;
import org.sistcoop.producto.models.ProductoCuentaPersonalModel;
import org.sistcoop.producto.models.ProductoCuentaPersonalProvider;
import org.sistcoop.producto.models.ProductoModel;
import org.sistcoop.producto.models.ProductoTasaModel;
import org.sistcoop.producto.models.ProductoTasaProvider;
import org.sistcoop.producto.models.enums.Frecuencia;
import org.sistcoop.producto.models.enums.TipoPersona;
import org.sistcoop.producto.models.enums.TipoValor;
import org.sistcoop.producto.representations.idm.CaracteristicaRepresentation;
import org.sistcoop.producto.representations.idm.ComisionRepresentation;
import org.sistcoop.producto.representations.idm.ProductoCreditoRepresentation;
import org.sistcoop.producto.representations.idm.ProductoCuentaPersonalRepresentation;
import org.sistcoop.producto.representations.idm.TasaRepresentation;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class RepresentationToModel {

    public ProductoCuentaPersonalModel createProductoCuentaPersonal(ProductoCuentaPersonalRepresentation rep,
            ProductoCuentaPersonalProvider provider) {
        // TODO here
        return null;
    }

    public ProductoCreditoModel createProductoCredito(ProductoCreditoRepresentation rep,
            ProductoCreditoProvider provider) {

        ProductoCreditoModel model = provider.addProductoCredito(rep.getCodigo(), rep.getDenominacion(),
                TipoPersona.valueOf(rep.getTipoPersona()), rep.getMoneda(), rep.getMontoMinimo(),
                rep.getMontoMaximo());

        return model;
    }

    public CaracteristicaModel createProductoCaracteristica(
            CaracteristicaRepresentation productoCaracteristicaRepresentation, ProductoModel productoModel,
            CaracteristicaProvider productoCaracteristicaProvider) {

        CaracteristicaModel model = productoCaracteristicaProvider.addProductoCaracteristica(productoModel,
                productoCaracteristicaRepresentation.getDescripcion(),
                productoCaracteristicaRepresentation.getDescripcionDetallada());

        return model;
    }

    public ProductoTasaModel createProductoTasa(TasaRepresentation productoTasaRepresentation,
            ProductoModel productoModel, ProductoTasaProvider productoTasaProvider) {

        ProductoTasaModel model = productoTasaProvider.addProductoTasa(productoModel,
                productoTasaRepresentation.getTasa(), productoTasaRepresentation.getValor());

        return model;

    }

    public ProductoComisionModel createProductoComision(
            ComisionRepresentation productoComisionRepresentation, ProductoModel productoModel,
            ProductoComisionProvider productoComisionProvider) {

        ProductoComisionModel model = productoComisionProvider.addProductoComision(productoModel,
                productoComisionRepresentation.getDenominacion(), productoComisionRepresentation.getValor(),
                TipoValor.valueOf(productoComisionRepresentation.getTipoValor()),
                Frecuencia.valueOf(productoComisionRepresentation.getFrecuencia()));

        return model;

    }

    public CaracteristicaModel createCaracteristica(ProductoCreditoModel productoCreditoModel,
            CaracteristicaRepresentation caracteristicaRepresentation,
            CaracteristicaProvider caracteristicaProvider) {
        // TODO Auto-generated method stub
        return null;
    }

    public CaracteristicaModel createCaracteristica(ProductoCuentaPersonalModel productoCuentaPersonalModel,
            CaracteristicaRepresentation caracteristicaRepresentation,
            CaracteristicaProvider caracteristicaProvider) {
        // TODO Auto-generated method stub
        return null;
    }

}
