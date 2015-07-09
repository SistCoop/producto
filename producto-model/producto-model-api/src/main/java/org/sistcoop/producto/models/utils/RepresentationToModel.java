package org.sistcoop.producto.models.utils;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.sistcoop.producto.models.CaracteristicaModel;
import org.sistcoop.producto.models.CaracteristicaProvider;
import org.sistcoop.producto.models.ComisionModel;
import org.sistcoop.producto.models.ComisionProvider;
import org.sistcoop.producto.models.ProductoCreditoModel;
import org.sistcoop.producto.models.ProductoCreditoProvider;
import org.sistcoop.producto.models.ProductoCuentaPersonalModel;
import org.sistcoop.producto.models.ProductoCuentaPersonalProvider;
import org.sistcoop.producto.models.ProductoModel;
import org.sistcoop.producto.models.TasaModel;
import org.sistcoop.producto.models.TasaProvider;
import org.sistcoop.producto.models.enums.Frecuencia;
import org.sistcoop.producto.models.enums.TipoCuentaPersonal;
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

        ProductoCuentaPersonalModel model = provider.create(
                TipoCuentaPersonal.valueOf(rep.getTipoCuenta()), TipoPersona.valueOf(rep.getTipoPersona()),
                rep.getMoneda(), rep.getDenominacion());

        model.setMontoMinimo(rep.getMontoMinimo());
        model.setMontoMaximo(rep.getMontoMaximo());
        model.commit();

        return model;
    }

    public ProductoCreditoModel createProductoCredito(ProductoCreditoRepresentation rep,
            ProductoCreditoProvider provider) {

        ProductoCreditoModel model = provider.create(TipoPersona.valueOf(rep.getTipoPersona()),
                rep.getMoneda(), rep.getDenominacion(), rep.getMontoMinimo(), rep.getMontoMaximo());

        return model;
    }

    public CaracteristicaModel createProductoCaracteristica(
            CaracteristicaRepresentation productoCaracteristicaRepresentation, ProductoModel productoModel,
            CaracteristicaProvider productoCaracteristicaProvider) {

        CaracteristicaModel model = productoCaracteristicaProvider.create(productoModel,
                productoCaracteristicaRepresentation.getDescripcion(),
                productoCaracteristicaRepresentation.getDescripcionDetallada());

        return model;
    }

    public TasaModel createProductoTasa(TasaRepresentation productoTasaRepresentation,
            ProductoModel productoModel, TasaProvider productoTasaProvider) {

        TasaModel model = productoTasaProvider.create(productoModel,
                productoTasaRepresentation.getTasa(), productoTasaRepresentation.getValor());

        return model;

    }

    public ComisionModel createProductoComision(ComisionRepresentation productoComisionRepresentation,
            ProductoModel productoModel, ComisionProvider productoComisionProvider) {

        ComisionModel model = productoComisionProvider.create(productoModel,
                productoComisionRepresentation.getDenominacion(), productoComisionRepresentation.getValor(),
                TipoValor.valueOf(productoComisionRepresentation.getTipoValor()),
                Frecuencia.valueOf(productoComisionRepresentation.getFrecuencia()));

        return model;

    }

    public CaracteristicaModel createCaracteristica(
            CaracteristicaRepresentation caracteristicaRepresentation,
            ProductoCreditoModel productoCreditoModel, CaracteristicaProvider caracteristicaProvider) {
        // TODO Auto-generated method stub
        return null;
    }

    public CaracteristicaModel createCaracteristica(
            CaracteristicaRepresentation caracteristicaRepresentation,
            ProductoCuentaPersonalModel productoCuentaPersonalModel,
            CaracteristicaProvider caracteristicaProvider) {
        // TODO Auto-generated method stub
        return null;
    }

    public TasaModel createTasa(ProductoCuentaPersonalModel productoCuentaPersonalModel,
            TasaRepresentation tasaRepresentation, CaracteristicaProvider caracteristicaProvider) {
        // TODO Auto-generated method stub
        return null;
    }

    public TasaModel createTasa(ProductoCreditoModel productoCreditoModel,
            TasaRepresentation tasaRepresentation, CaracteristicaProvider caracteristicaProvider) {
        // TODO Auto-generated method stub
        return null;
    }

    public TasaModel createTasa(ProductoCuentaPersonalModel productoCuentaPersonalModel,
            TasaRepresentation tasaRepresentation, TasaProvider tasaProvider) {
        // TODO Auto-generated method stub
        return null;
    }

    public TasaModel createTasa(TasaRepresentation tasaRepresentation,
            ProductoCreditoModel productoCreditoModel, TasaProvider tasaProvider) {
        // TODO Auto-generated method stub
        return null;
    }

    public ComisionModel createComision(ComisionRepresentation comisionRepresentation,
            ProductoCreditoModel productoCreditoModel, ComisionProvider comisionProvider) {
        // TODO Auto-generated method stub
        return null;
    }

    public ComisionModel createComision(ComisionRepresentation comisionRepresentation,
            ProductoCuentaPersonalModel productoCuentaPersonalModel, ComisionProvider comisionProvider) {
        // TODO Auto-generated method stub
        return null;
    }

}
