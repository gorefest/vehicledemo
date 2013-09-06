package com.lsy.training.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lsy.training.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lsy.training.dao.CustomerUnitDao;
import com.lsy.training.model.Address;
import com.lsy.training.model.BusinessCustomer;
import com.lsy.training.model.PrivateCustomer;

/**
 * Servicebean für Kundendaten
 */
@Stateless
public class CustomerUnitService {

	private final Logger log = LoggerFactory.getLogger(EngineService.class);
	
	@EJB
	CustomerUnitDao customerUnitDao;

    /**
     * Initialisierungsroutine
     */
	public void initData() {
		log.info("BEGIN INIT DATA");
		
		Address a1 = new Address("Am Hafen 1","26529","Marienhafe","DE");
		Address a2 = new Address("Am Hafen 3","26529","Marienhafe","DE");
		Address a3 = new Address("In der Gass 12","56412","Ruppach","DE");
		
		
		Person p1 = new Person("Blaubär","Käpt'n");
		Person p2 = new Person("Blöd","Hein");
		Person p3 = new Person("Nachtigaller","Abdul");
		
		PrivateCustomer pc1 = new PrivateCustomer(p1);
		pc1.setAddress(a1);
		PrivateCustomer pc2 = new PrivateCustomer(p2);
		pc2.setAddress(a2);
		
		BusinessCustomer bc1 = new BusinessCustomer(p3, "Foobatz Inc.", "667/667");
		bc1.setAddress(a3);

		customerUnitDao.save(pc1);
		customerUnitDao.save(pc2);
		customerUnitDao.save(bc1);
		
		
		log.info("END INIT DATA");
	}
	
	
}
