package com.lsy.training.service;

import java.math.BigDecimal;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lsy.training.dao.VendorDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lsy.training.dao.EngineDao;
import com.lsy.training.dao.VehicleDao;
import com.lsy.training.model.Engine;
import com.lsy.training.model.Vendor;
import com.lsy.training.model.Vehicle;

/**
 * Servicebean für die Verwaltung von Vehicles
 */
@Stateless
public class VehicleService {

	@EJB
	VehicleDao vehicleDao; 
	
	@EJB
    VendorDao vendorDao;
	
	@EJB
	EngineDao engineDao;
	
	private final Logger log = LoggerFactory.getLogger(VehicleService.class);

	public void initData() {
		log.info("BEGIN INIT DATA");
		
		Vendor m1 = vendorDao.getByName("AUDI");
		Vendor m2 = vendorDao.getByName("VOLKSWAGEN");
		Vendor m3 = vendorDao.getByName("FORD");
		Vendor m4 = vendorDao.getByName("BMW");
		
		Engine e1 = engineDao.getByEngineId("1/2/3");
		Engine e11 = engineDao.getByEngineId("1/3/3");
		Engine e2 = engineDao.getByEngineId("2/2/3");
		Engine e3 = engineDao.getByEngineId("3/2/3");
		Engine e4 = engineDao.getByEngineId("4/2/3");
		
		Vehicle v1 = new Vehicle("WWAUZ12345678","A4",m1,e1,new BigDecimal("36000.0"), new Date());
		Vehicle v2 = new Vehicle("WWVWZ12345678","GOLF 6",m2,e3,new BigDecimal("25000.0"),new Date());
		Vehicle v3 = new Vehicle("WWBMW12345678","318i",m4,e2,new BigDecimal("41000.0"),new Date());
		Vehicle v4 = new Vehicle("WWAUZ12345672","A6",m1,e11,new BigDecimal("68000.0"),new Date());
		Vehicle v5 = new Vehicle("WWFOR12345672","Fiesta",m3,e4,new BigDecimal("15000.0"),new Date());
	
		vehicleDao.save(v1);
		vehicleDao.save(v2);
		vehicleDao.save(v3);
		vehicleDao.save(v4);
		vehicleDao.save(v5);
		
		log.info("END INIT DATA");
	}
	
	
}
