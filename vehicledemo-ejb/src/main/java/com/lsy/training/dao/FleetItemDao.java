package com.lsy.training.dao;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.lsy.training.model.FleetItem;

/**
 * Implementierug des FleetItemDao. No-Interface-View
 */
@Stateless
public class FleetItemDao extends AbstractBaseDao<FleetItem>{

	@Override
	public Class<FleetItem> getManagedClass() {
		return FleetItem.class;
	}

    /**
     * Lädt ein FleetItem anhand einer FGNR
     *
     * @param fgnr - die FGNR (fachlicher Primary Key)
     * @return das FleetItem
     *
     * @see FleetItem
     */
	public FleetItem getByFgnr(String fgnr) {
		TypedQuery<FleetItem> qry = entityManager.createNamedQuery(FleetItem.GET_BY_FGNR, FleetItem.class);
		qry.setParameter("fgnr", fgnr);
		return getSingleResult(qry.getResultList());
	}
	
}
