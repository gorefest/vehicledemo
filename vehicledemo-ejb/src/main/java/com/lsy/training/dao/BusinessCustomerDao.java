package com.lsy.training.dao;

import javax.ejb.Stateless;

import com.lsy.training.model.BusinessCustomer;

/**
 * Implementierug des BusinessCustomerDao. No-Interface-View
 */

@Stateless
public class BusinessCustomerDao extends AbstractBaseDao<BusinessCustomer>{

	@Override
	public Class<BusinessCustomer> getManagedClass() {
		return BusinessCustomer.class;
	}
	

}
