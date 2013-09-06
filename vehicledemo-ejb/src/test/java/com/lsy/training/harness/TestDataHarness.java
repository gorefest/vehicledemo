package com.lsy.training.harness;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.lsy.training.service.EngineService;
import com.lsy.training.service.VehicleService;
import com.lsy.training.service.VendorService;
import com.lsy.training.util.VehicleUtil;

@Singleton
@Startup
public class TestDataHarness {

	@EJB
	VendorService vendorService;
	
	@EJB
	VehicleService vehicleService;
	
	@EJB
	EngineService engineService;
	
	
	@PostConstruct
	public void postConstruct() {
		vendorService.initData();
		engineService.initData();
		vehicleService.initData();
	}
	
}
