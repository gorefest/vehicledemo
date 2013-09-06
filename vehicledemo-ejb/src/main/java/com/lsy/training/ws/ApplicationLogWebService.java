package com.lsy.training.ws;

import java.util.Collection;

import javax.ejb.Local;
import javax.jws.WebMethod;
import javax.jws.WebService;

import com.lsy.training.model.ApplicationLog;
import com.lsy.training.model.ApplicationStat;

/**
 * Webservice Schnittstelle. Bei JAX-WS muss man diese immer anlegen
 */
@WebService
@Local
public interface ApplicationLogWebService {

	@WebMethod
	Collection<ApplicationLog> loadAll();
	
	@WebMethod
	Collection<ApplicationStat> getApplicationStats();

}