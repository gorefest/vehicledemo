package com.lsy.training.dao;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.lsy.training.model.Vehicle;

/**
 * Implementierug des VehicleDao. No-Interface-View
 */
@Stateless
public class VehicleDao extends AbstractBaseDao<Vehicle>{

    /**
     * lädt ein Fahrzeug anhand seiner FGNR
     * @param fgnr - Fahrgestellnummer (Fachlicher PrimaryKey)
     * @return das Fahzrzug oder NULL
     * @see  Vehicle
     *
     */
	public Vehicle getByFgnr(String fgnr) {
		TypedQuery<Vehicle> qry = entityManager.createNamedQuery(Vehicle.GET_BY_FNGR, Vehicle.class);
		qry.setParameter("fgnr", fgnr);
		Collection<Vehicle> v = qry.getResultList();
		
		return getSingleResult(v);	
	}


    /**
     * lädt alle Fahrzeuge samt Zusatzdaten unter Zuhilfename eines NamedQuery (JOIN FETCH)
     *
     * @return Alle Fahrzeuge, sowie die referenzierten Zusatzdaten  (... mit alles)
     */
	public Collection<Vehicle> loadAllCompletley() {
		TypedQuery<Vehicle> qry = entityManager.createNamedQuery(Vehicle.LOAD_ALL_COMPLETELY, Vehicle.class);
		return qry.getResultList();
	}

	@Override
	public Class<Vehicle> getManagedClass() {
		return Vehicle.class;
	}
	
}
