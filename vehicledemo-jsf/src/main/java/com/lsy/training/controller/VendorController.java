package com.lsy.training.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import com.lsy.training.dao.VendorDao;
import com.lsy.training.model.Vendor;

@Model
public class VendorController {

	@EJB
	VendorDao vendorDao;
	
	private Vendor newVendor;
	
	@Produces
	@Named
	public Vendor getNewVendor() {
		return newVendor;
	}
	
	public String saveNewVendor() {
		vendorDao.save(newVendor);
		return "vendors";
	}
	
	@PostConstruct
	public void afterSetup() {
		newVendor = new Vendor();
	}
	
}
