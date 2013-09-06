package com.lsy.training.data;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import com.lsy.training.dao.VehicleDao;
import com.lsy.training.dao.VendorDao;
import com.lsy.training.model.Vehicle;
import com.lsy.training.model.Vendor;

@RequestScoped
public class VehicleListProducer {

	@EJB
	VehicleDao vehicleDao;
	
	private Collection<Vehicle> vehicles;

	// @Named provides access the return value via the EL variable name
	// "members" in the UI (e.g.,
	// Facelets or JSP view)
	@Produces
	@Named
	public Collection<Vehicle> getVehicles() {
		return vehicles;
	}

	@PostConstruct
   public void retrieveAllMembersOrderedByName() {
	   vehicles = vehicleDao.loadAll(); 
   }
}
