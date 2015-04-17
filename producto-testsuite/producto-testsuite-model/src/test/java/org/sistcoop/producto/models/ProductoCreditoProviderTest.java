package org.sistcoop.producto.models;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.math.BigDecimal;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sistcoop.producto.models.enums.TipoPersona;
import org.sistcoop.producto.models.jpa.JpaProductoCuentaPersonalProvider;
import org.sistcoop.producto.models.jpa.ProductoAdapter;
import org.sistcoop.producto.models.jpa.entities.ProductoEntity;
import org.sistcoop.producto.provider.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(Arquillian.class)
@UsingDataSet("empty.xml")
public class ProductoCreditoProviderTest {

	Logger log = LoggerFactory.getLogger(ProductoCreditoProviderTest.class);

	@Inject
	private ProductoCreditoProvider productoCreditoProvider;

	@Deployment
	public static WebArchive createDeployment() {
		File[] dependencies = Maven.resolver()
				.resolve("org.slf4j:slf4j-simple:1.7.10")
				.withoutTransitivity()
				.asFile();

		WebArchive war = ShrinkWrap.create(WebArchive.class, "test.war")
		/** persona-model-api **/
		.addClass(Provider.class)
		.addClass(ProductoCreditoProvider.class)

		.addPackage(ProductoModel.class.getPackage())
		.addPackage(TipoPersona.class.getPackage())

		/** persona-model-jpa **/
		.addClass(JpaProductoCuentaPersonalProvider.class)
		.addPackage(ProductoAdapter.class.getPackage())

		.addPackage(ProductoEntity.class.getPackage())

		.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
		.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
		.addAsWebInfResource("test-ds.xml");

		war.addAsLibraries(dependencies);

		return war;
	}

	@Test
	public void addProductoCredito() {
		ProductoCreditoModel a = productoCreditoProvider.addProductoCredito("CRE001", "Rapidiario", TipoPersona.NATURAL, "S/.", new BigDecimal("1"), new BigDecimal("1000"));
		
		assertThat("model no debe ser null", a, is(notNullValue()));
		assertThat("id no debe ser null", a.getId(), is(notNullValue()));
	}
	
	@Test
	public void geProductoCreditoById()  {		
		ProductoCreditoModel model1 = productoCreditoProvider.addProductoCredito("CRE001", "Rapidiario", TipoPersona.NATURAL, "S/.", new BigDecimal("1"), new BigDecimal("1000"));
				
		Integer id = model1.getId();
		ProductoCreditoModel model2 = productoCreditoProvider.getProductoCreditoById(id);

		assertThat(model1, is(equalTo(model2)));
	}
	
	@Test
	public void getProductoCreditoByCodigo()  {		
		ProductoCreditoModel model1 = productoCreditoProvider.addProductoCredito("CRE001", "Rapidiario", TipoPersona.NATURAL, "S/.", new BigDecimal("1"), new BigDecimal("1000"));
				
		String codigo = model1.getCodigo();
		ProductoCreditoModel model2 = productoCreditoProvider.getProductoCreditoByCodigo(codigo);

		assertThat(model1, is(equalTo(model2)));
	}
	
	@Test
	public void getProductoCreditoByDenominacion()  {		
		ProductoCreditoModel model1 = productoCreditoProvider.addProductoCredito("CRE001", "Rapidiario", TipoPersona.NATURAL, "S/.", new BigDecimal("1"), new BigDecimal("1000"));
				
		String denominacion = model1.getDenominacion();
		ProductoCreditoModel model2 = productoCreditoProvider.getProductoByDenominacion(denominacion);

		assertThat(model1, is(equalTo(model2)));
	}
}
