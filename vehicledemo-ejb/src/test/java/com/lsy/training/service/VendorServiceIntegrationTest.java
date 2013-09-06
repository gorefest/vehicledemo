package com.lsy.training.service;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

import com.lsy.training.dao.VendorDao;
import com.lsy.training.harness.TestDataHarness;
import com.lsy.training.model.Vendor;
import com.lsy.training.util.VehicleUtil;

/**
 * Demonstriert einen Integrationstest mit Arquillian. Benötigt einen Container (hier Profile jboss-as-managed)
 * und die notwendigen Maven-Dependencies
 */
@RunWith(Arquillian.class)  // Hier teilen wir Junit mit, dass Arquillian den Test durchführt
public class VendorServiceIntegrationTest {

	
	@Deployment // Zum Testen benötigen wir ein Archive. Die Bereitstellung
                // des Basis-Archivs kann man  - je nach Testart - auch externalisierung
                // und wiederverwenden.
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE,"beans.xml")
				.addAsWebInfResource("test-ds.xml","test-ds.xml")
				.addAsResource("META-INF/test-persistence.xml","META-INF/persistence.xml")
				.addClasses(VendorService.class, EngineService.class, VehicleService.class
						   , ApplicationLogService.class, TestDataHarness.class)
				.addPackages(false, Vendor.class.getPackage()
						          , VendorDao.class.getPackage()
						          , VehicleUtil.class.getPackage());
	}
	
	@Inject     // Da dieser Test im Container ausgeführt wird, können wir uns EJBs injizieren
	VendorService vendorService;
	
	@Inject
	VendorDao vendorDao;
	
	@Test
	@UsingDataSet("vendor.yml")   // DBUnit soll vorher etwas in die Datenbank schreiben
	public void testVendorService() {
		assertNotNull(vendorService);
		Vendor v = vendorService.getBigBlockVendor();
		assertNotNull(v);
		assertEquals("Badass Motor Company", v.getName());		
	}
	
	@Test
	@UsingDataSet("vendor.yml")
	public void testVendor(){
		Vendor v = vendorDao.getById(666L);
		assertNotNull(v);
		assertEquals("Badass Motor Company", v.getName());		
	}
	
	
}
