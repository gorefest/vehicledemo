package com.lsy.training.ws.impl;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import com.lsy.training.dao.ApplicationLogDao;
import com.lsy.training.model.ApplicationLog;
import com.lsy.training.model.ApplicationStat;
import com.lsy.training.ws.ApplicationLogWebService;


@Stateless
@WebService // dies ist ein JAX-WS Webservice. WS-Annotationen müssen in Interfaces
            // sowie die EJB annotiert werden
            // Die Endpunktinformationen kann man sich dann aus dem
            // JBoss Management holen
public class ApplicationLogWebServiceImpl implements ApplicationLogWebService {

	@EJB
    ApplicationLogDao applicationLogDao;

	/* (non-Javadoc)
	 * @see com.lsy.training.ws.impl.ApplicationLogWebService#loadAll()
	 */
	@Override
	@WebMethod  // Dies ist eine Webservice-Methode.
	public Collection<ApplicationLog> loadAll() {
		return applicationLogDao.loadAll();
	}

	@Override
	@WebMethod
	public Collection<ApplicationStat> getApplicationStats() {
		return applicationLogDao.getApplicationStats();
	}
	
	
	
}
