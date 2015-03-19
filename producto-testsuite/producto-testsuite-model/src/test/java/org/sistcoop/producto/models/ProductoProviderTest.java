package org.sistcoop.producto.models;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.List;

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
import org.sistcoop.producto.models.jpa.JpaProductoProvider;
import org.sistcoop.producto.models.jpa.ProductoAdapter;
import org.sistcoop.producto.models.jpa.entities.ProductoEntity;
import org.sistcoop.producto.provider.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(Arquillian.class)
@UsingDataSet("empty.xml")
public class ProductoProviderTest {

	Logger log = LoggerFactory.getLogger(ProductoProviderTest.class);

	//@Inject
	//private TipoDocumentoProvider tipoDocumentoProvider;

	@Deployment
	public static WebArchive createDeployment() {
		File[] dependencies = Maven.resolver()
				.resolve("org.slf4j:slf4j-simple:1.7.10")
				.withoutTransitivity()
				.asFile();

		WebArchive war = ShrinkWrap.create(WebArchive.class, "test.war")
		/** persona-model-api **/
		.addClass(Provider.class)
		.addClass(ProductoProvider.class)

		.addPackage(ProductoModel.class.getPackage())
		.addPackage(TipoPersona.class.getPackage())

		/** persona-model-jpa **/
		.addClass(JpaProductoProvider.class)
		.addPackage(ProductoAdapter.class.getPackage())

		.addPackage(ProductoEntity.class.getPackage())

		.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
		.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
		.addAsWebInfResource("test-ds.xml");

		war.addAsLibraries(dependencies);

		return war;
	}

	@Test
	public void addTipoDocumento() {
		
	}
	
}
