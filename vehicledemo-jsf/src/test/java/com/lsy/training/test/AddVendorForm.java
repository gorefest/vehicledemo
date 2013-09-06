package com.lsy.training.test;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddVendorForm {

	private final WebDriver driver;
	private final URL url;
	
	public AddVendorForm(WebDriver driver, URL url) {
		super();
		this.driver = driver;
		this.url = url;
	}
	
	public void setValue(String id, String value) {
		WebElement elem = driver.findElement(By.id(id));
		elem.clear();
		elem.sendKeys(value);
	}
	
	public void navigate() {
		String baseUrl = String.format("http://%s:%s/"+DeploymentUtil.applicationContext
										, url.getHost(), url.getPort());
		driver.get(baseUrl+"/addVendor.jsf");
	}
	
	public void setVendorName(String name) {
		setValue("addVendor:txtVendorName", name);
	}
	
	public void clickSaveButton() {
		driver.findElement(By.id("addVendor:btnSave")).click();
	}
	
}
