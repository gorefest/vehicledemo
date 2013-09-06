package com.lsy.training.controller;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import com.lsy.training.dao.EngineDao;
import com.lsy.training.dao.VehicleDao;
import com.lsy.training.dao.VendorDao;
import com.lsy.training.model.Vehicle;

@Model
public class VehicleController {

	@EJB
	VehicleDao vehicleDao;
	
	@EJB
	EngineDao engineDao;
	
	@EJB
	VendorDao vendorDao;
	
	
	private Vehicle newVehicle;
	
	private String vendorName;
	private String engineId;
	
	@Produces
	@Named
	public Vehicle getNewVehicle() {
		return newVehicle;
	}
	
	public String saveNewVehicle() {
		newVehicle.setVendor(vendorDao.getByName(vendorName));
		newVehicle.setEngine(engineDao.getByEngineId(engineId));
		vehicleDao.save(newVehicle);
		return "vehicles";
	}
	
	@PostConstruct
	public void afterSetup() {
		newVehicle = new Vehicle();
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getEngineId() {
		return engineId;
	}

	public void setEngineId(String engineId) {
		this.engineId = engineId;
	}

	
}
