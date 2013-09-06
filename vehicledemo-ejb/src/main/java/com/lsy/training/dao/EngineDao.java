package com.lsy.training.dao;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.lsy.training.model.Engine;

/**
 * Implementierug des EngineDao. No-Interface-View
 */
@Stateless
public class EngineDao extends AbstractBaseDao<Engine> {

	@Override
	public Class<Engine> getManagedClass() {
		return Engine.class;
	}

    /**
     * holt genau eine Engine mittels Engine ID (fachlicher Primary Key)
     *
     * @param engineId - die Engine ID
     * @return die Engine
     *
     * @see  Engine NamedQueries
     */
	public Engine getByEngineId(String engineId) {
		TypedQuery<Engine> qry = entityManager.createNamedQuery(Engine.GET_BY_ENGINE_ID, Engine.class);
		qry.setParameter("engineId", engineId);
		return qry.getSingleResult();
	}
	

}
