package com.lsy.training.service;

import java.util.Collection;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lsy.training.dao.VendorDao;
import com.lsy.training.model.Vendor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lsy.training.dao.VehicleDao;
import com.lsy.training.model.ApplicationLog;
import com.lsy.training.model.Vehicle;
import com.lsy.training.util.VehicleUtil;

/**
 * Servicebean für die Verwaltung von Vendor
 */
@Stateless
public class VendorService {

	private final Logger log = LoggerFactory.getLogger(VendorService.class);

	@EJB
    VendorDao vendorDao;

	@EJB
	VehicleDao vehicleDao;
	
	@EJB
	ApplicationLogService applicationLogService; 

	public void initData() {
		log.info("BEGIN INIT DATA");
		
		Vendor m1 = new Vendor("VOLKSWAGEN");
		Vendor m2 = new Vendor("BMW");
		Vendor m3 = new Vendor("AUDI");
		Vendor m4 = new Vendor("FORD");
		Vendor m5 = new Vendor("MERCEDES");
		
		vendorDao.save(m1);
		vendorDao.save(m2);
		vendorDao.save(m3);
		vendorDao.save(m4);
		vendorDao.save(m5);
		
		log.info("END INIT DATA");

	}
	
	public Vendor getBigBlockVendor() {
		ApplicationLog log = new ApplicationLog();
		log.setBegin(new Date());
		Collection<Vehicle> vehicles = vehicleDao.loadAllCompletley();
		
		Vendor m = VehicleUtil.getBigBlockVendor(vehicles);
		
		if (m != null) {
			log.setEnd(new Date());
			log.setComment("Big Cahuna found!");
			log.setClassName(this.getClass().getSimpleName());
			log.setFunctionName("getBigBlockVendor");
			applicationLogService.saveNewLogEntry(log);
		}
		
		return m;
	}
}
