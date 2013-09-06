package com.lsy.training.data;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

import com.lsy.training.dao.VehicleDao;
import com.lsy.training.dao.VendorDao;
import com.lsy.training.model.Vehicle;
import com.lsy.training.model.Vendor;

@RequestScoped
@ManagedBean(name="currentVehicleProducer")
public class CurrentVehicleProducer {

	@EJB
	VehicleDao vehicleDao;
	
	private String fgnr;
	
	private Vehicle currentVehicle;

	public Vehicle getCurrentVehicle() {
		return currentVehicle;
	}

	public String loadCurrentVehicle() {
		currentVehicle = vehicleDao.getByFgnr(fgnr);
		return "vehicleDetails";
	}
	
	public String loadCurrentVehicleByFgnr(String xxx) {
		currentVehicle = vehicleDao.getByFgnr(xxx);
		return "vehicleDetails";
	}

	
	public String rememberCurrentVehicle(Vehicle v) {
		currentVehicle = v;
		return "vehicleDetails";
	}


	public String getFgnr() {
		return fgnr;
	}

	public void setFgnr(String fgnr) {
		this.fgnr = fgnr;
	}
	
	
	
}
