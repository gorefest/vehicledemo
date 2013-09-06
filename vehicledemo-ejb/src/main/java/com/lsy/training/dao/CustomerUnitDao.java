package com.lsy.training.dao;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.lsy.training.model.CustomerUnit;
import com.lsy.training.model.Person;
import com.lsy.training.model.PrivateCustomer;

/**
 * Implementierug des CustomerUnitDao. No-Interface-View
 */
@Stateless
public class CustomerUnitDao extends AbstractBaseDao<CustomerUnit> {

	@Override
	public Class<CustomerUnit> getManagedClass() {
		return CustomerUnit.class;
	}

    /**
     * Holt einen Privatkunden anhand einer Personen-Information
     *
     * @param p die Person
     * @return der Privatkunde
     *
     * @see com.lsy.training.model.PrivateCustomer NamedQueries
     *
     */
	public PrivateCustomer getByPerson(Person p) {
		TypedQuery<PrivateCustomer> qry = entityManager.createNamedQuery(PrivateCustomer.GET_BY_PERSON, PrivateCustomer.class);
		qry.setParameter("person", p);
		return qry.getSingleResult();
	}
	
	
	
}
