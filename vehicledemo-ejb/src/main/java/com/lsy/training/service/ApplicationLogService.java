package com.lsy.training.service;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.lsy.training.dao.ApplicationLogDao;
import com.lsy.training.model.ApplicationLog;

/**
 * Log-Service Bean.
 *
 * @see  NumberService
 *
 */
@Stateless
public class ApplicationLogService {

	@EJB
    ApplicationLogDao applicationLogDao;
	
	@Asynchronous
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)        // Hier beginnt eine neue Transaktion,
                                                                        // d.h. die bestehende wird geparkt und
                                                                        // nach Verlassen fortgesetzt
	public void saveNewLogEntry(ApplicationLog applicationLog) {
		applicationLogDao.save(applicationLog);
	}
	
}
