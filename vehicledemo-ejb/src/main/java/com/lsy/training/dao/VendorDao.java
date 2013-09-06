package com.lsy.training.dao;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.lsy.training.model.MaintenanceActivity;
import com.lsy.training.model.Vendor;

/**
 * Implementierug des VendorDao. No-Interface-View
 */
@Stateless
public class VendorDao extends AbstractBaseDao<Vendor>{

	@Override
	public Class<Vendor> getManagedClass() {
		return Vendor.class;
	}

    /**
     * lädt einen Hersteller anhand seines Namens
     *
     * @param name - der Name des Objekts
     * @return der Hersteller
     *
     * @see com.lsy.training.model.Vendor
     */
	public Vendor getByName(String name) {
		TypedQuery<Vendor> qry = entityManager.createNamedQuery(Vendor.GET_BY_NAME, Vendor.class);
		qry.setParameter("name", name);
		return qry.getSingleResult();
	}

    /**
     *
     * @return alle Hersteller mit Fahrzeugportfolio
     *
     * @see com.lsy.training.model.Vendor
     */
	public Collection<Vendor> getWithPortfolio() {
		TypedQuery<Vendor> qry = entityManager.createNamedQuery(Vendor.GET_WITH_PORTFOLIO, Vendor.class);
		return qry.getResultList();
	}

    /**
     *
     * @return alle Hersteller, deren Fahrzeuge schon einmal wegen eines Defekts in der Werkstatt waren
     *
     * @see com.lsy.training.model.Vendor
     */
	public Collection<Vendor> getWithCrappyCars() {
		TypedQuery<Vendor> qry = entityManager.createNamedQuery(Vendor.GET_WITH_CRAPPY_CARS, Vendor.class);
		qry.setParameter("foo", MaintenanceActivity.MaintenanceType.DEFECT);
		return qry.getResultList();
	}
	
}
