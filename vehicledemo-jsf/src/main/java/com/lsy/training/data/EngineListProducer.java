package com.lsy.training.data;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import com.lsy.training.dao.EngineDao;
import com.lsy.training.dao.VehicleDao;
import com.lsy.training.model.Engine;
import com.lsy.training.model.Vehicle;

@RequestScoped
public class EngineListProducer {

	@EJB
	EngineDao engineDao;
	
	private Collection<Engine> engines;

	// @Named provides access the return value via the EL variable name
	// "members" in the UI (e.g.,
	// Facelets or JSP view)
	@Produces
	@Named
	public Collection<Engine> getEngines() {
		return engines;
	}

	@PostConstruct
   public void retrieveAllMembersOrderedByName() {
	   engines = engineDao.loadAll(); 
   }
}
