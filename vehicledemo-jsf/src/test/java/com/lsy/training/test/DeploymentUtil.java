package com.lsy.training.test;

import java.io.File;

import org.jboss.arquillian.protocol.servlet.arq514hack.descriptors.api.application.ApplicationDescriptor;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.descriptor.api.Descriptors;

import com.lsy.training.controller.VendorController;
import com.lsy.training.dao.VendorDao;
import com.lsy.training.dto.VendorDto;
import com.lsy.training.ws.VehicleWebService;

public class DeploymentUtil {

	public static final String applicationContext = "vehicledemo-jsf";

	public static Archive<?> createDeployment() {

		JavaArchive api = ShrinkWrap
				.create(JavaArchive.class, "vehicledemo-api.jar")
				.addPackage(VendorDto.class.getPackage())
				.addPackage(VehicleWebService.class.getPackage());

		JavaArchive ejb = ShrinkWrap
				.createFromZipFile(
						JavaArchive.class,
						new File(
								"F:/data/training-august/vehicledemo-3/vehicledemo-ejb/target/vehicledemo-ejb-1.0-SNAPSHOT.jar"));

		JavaArchive test =ShrinkWrap.create(JavaArchive.class)
				.addClass(AddVendorIntegrationTest.class);
		
		WebArchive jsf = ShrinkWrap.create(WebArchive.class, "vehicledemo-jsf.war")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "faces-config.xml")
				.addAsWebResource("addVendor.xhtml")
				.addAsDirectory("WEB-INF/templates")
				.addAsWebInfResource("test-ds.xml")
				.addClass(VendorController.class)
				.addAsWebInfResource("WEB-INF/templates/default.xhtml","templates/default.xhtml");
				
		
		return ShrinkWrap
				.create(EnterpriseArchive.class, "vehicledemo.ear")
				.addAsModule(ejb)
				.addAsModule(jsf)
				.addAsLibrary(api)
				.addAsLibrary(test)
				.addAsManifestResource(createApplicationXml(),
						"application.xml");
	}

	private static StringAsset createApplicationXml() {
		return new StringAsset(Descriptors.create(ApplicationDescriptor.class)
				.version("6").displayName("vehicledemo-ear")
				.ejbModule("vehicledemo-ejb-1.0-SNAPSHOT.jar") 
				.webModule("vehicledemo-jsf.war", applicationContext)
				.libraryDirectory("lib").exportAsString());
	}

}