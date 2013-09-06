package com.lsy.training.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lsy.training.dao.EngineDao;
import com.lsy.training.dao.VendorDao;
import com.lsy.training.model.Vendor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lsy.training.model.Engine;
import com.lsy.training.model.Engine.EngineType;

/**
 * Servicebean für die Verwaltung von Engines
 */
@Stateless
public class EngineService {

	private final Logger log = LoggerFactory.getLogger(EngineService.class);
	
	@EJB
    EngineDao engineDao;
	
	@EJB
    VendorDao vendorDao;

    /**
     * Initialisierung
     */
	public void initData() {
		log.info("BEGIN INIT DATA");
		
		Vendor m1 = vendorDao.getByName("AUDI");
		Vendor m2 = vendorDao.getByName("VOLKSWAGEN");
		Vendor m3 = vendorDao.getByName("FORD");
		Vendor m4 = vendorDao.getByName("BMW");
		
		Engine e1 = new Engine(EngineType.DIESEL, 100.0d, 4, 1960, "1/2/3", m1);
		Engine e2 = new Engine(EngineType.PETROL, 130.0d, 4, 1880, "2/2/3", m4);
		Engine e3 = new Engine(EngineType.DIESEL, 176.0d, 6, 2980, "1/3/3", m1);
		Engine e4 = new Engine(EngineType.PETROL, 105.0d, 4, 1600, "3/2/3", m2);
		Engine e5 = new Engine(EngineType.LPG, 55.0d, 3, 990, "4/2/3", m3);

		engineDao.save(e1);
		engineDao.save(e2);
		engineDao.save(e3);
		engineDao.save(e4);
		engineDao.save(e5);
		
		log.info("END INIT DATA");
	
	}
	
}
