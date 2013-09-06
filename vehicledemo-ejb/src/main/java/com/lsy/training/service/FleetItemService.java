package com.lsy.training.service;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lsy.training.dao.BusinessCustomerDao;
import com.lsy.training.model.BusinessCustomer;
import com.lsy.training.model.FleetItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lsy.training.dao.CustomerUnitDao;
import com.lsy.training.dao.FleetItemDao;
import com.lsy.training.dao.VehicleDao;
import com.lsy.training.model.MaintenanceActivity;
import com.lsy.training.model.Vehicle;
import com.lsy.training.model.MaintenanceActivity.MaintenanceType;

@Stateless
public class FleetItemService {

	@EJB
	FleetItemDao fleetItemDao;

	@EJB
	CustomerUnitDao customerUnitDao;

	@EJB
    BusinessCustomerDao businessCustomerDao;
	
	@EJB
	VehicleDao vehicleDao;
	
	private final Logger log = LoggerFactory.getLogger(FleetItemService.class);
	
	public void initFleetData() {
		log.info("BEGIN INIT DATA");
		
		BusinessCustomer bc = businessCustomerDao.getByName("Foobatz Inc.");
		Vehicle v = vehicleDao.getByFgnr("WWAUZ12345678");
		
		FleetItem fi = new FleetItem(bc,v);
		fleetItemDao.save(fi);
		
		MaintenanceActivity activity = new MaintenanceActivity(MaintenanceType.DEFECT, new Date(), "Gabba Gabba Hey!");
		fi.addMaintenanceActivity(activity);
		
		log.info("END INIT DATA");
	}
	
}
