package com.lsy.training.test;

import java.net.URL;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import com.lsy.training.dao.VendorDao;
import com.lsy.training.model.Vendor;

@RunWith(Arquillian.class)
public class AddVendorIntegrationTest {

	@Drone
	WebDriver driver;
	
	@ArquillianResource
	URL url;
	
	@EJB
	VendorDao vendorDao;
	
	@Deployment
	public static Archive<?> createDeployment() {
		return DeploymentUtil.createDeployment();
	}
	
	@Test
	@RunAsClient
	@InSequence(1)
	public void testAddVendor() {
		AddVendorForm vendorForm = new AddVendorForm(driver, url);
		
		vendorForm.navigate();
		vendorForm.setVendorName("FOO TEC");
		vendorForm.clickSaveButton();
	}
	
	@Test
	@InSequence(2)
	public void testVendorAdded() {
		Vendor v = vendorDao.getByName("FOO TEC");
		Assert.assertNotNull(v);
	}
	
}
