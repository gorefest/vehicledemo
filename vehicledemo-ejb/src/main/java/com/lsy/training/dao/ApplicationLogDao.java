package com.lsy.training.dao;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.lsy.training.model.ApplicationLog;
import com.lsy.training.model.ApplicationStat;

/**
 * Implementierug des ApplicationLogDao. No-Interface-View
 */
@Stateless
public class ApplicationLogDao {

    /**
     * Das Logging-Entitybean wird in einer anderen DB gehalten.
     * Daher implementieren wir hier nicht das Generic und
     * müssen die Basisfunktionen selbst vorhalten
     */
	@PersistenceContext(unitName="logging", type=PersistenceContextType.EXTENDED)
	EntityManager entityManager;


    /**
     * speichert ein ApplicationLog Objekt
     * @param log
     */
	public void save(ApplicationLog log) {
		entityManager.persist(log);
	}

    /**
     * @return alle Log-Einträge
     */
	public Collection<ApplicationLog> loadAll() {
		TypedQuery<ApplicationLog> qry = entityManager.createNamedQuery(ApplicationLog.LOAD_ALL, ApplicationLog.class);
		return qry.getResultList();
	}

    /**
     * berechnet das transiente ApplicationStat-Objekt händisch
     *
     * @return alle Statistik-Objekte
     *
     * @see ApplicationStat
     * @see ApplicationLog NamedQueries
     *
     */
	public Collection<ApplicationStat> getApplicationStats2() {
		Query qry = entityManager.createNamedQuery(ApplicationLog.GET_AVG_DURATION_1);
		Collection<Object[]> data = qry.getResultList();
		Collection<ApplicationStat> result = new ArrayList<>(data.size());
		for (Object[] object : data) {
            // Wenn keine Inline-Konstruktion in JPQL gemacht wird, bekommt man als
            // Result-Type eine List<Object[]> und baut sich - wie beim JDBC-RowMapper -
            // das Result von Hand zusammen
			result.add(new ApplicationStat((String) object[0], (String) object[1], (Double) object[2]));
		}
		return result;
	}


    /**
     * berechnet die ApplicationStat-Objekte mittels JPQL-Inline-Konstruktor
     *
     * @return alle Statistik-Objekte
     *
     * @see  ApplicationStat
     * @see  ApplicationLog Named Queries
     *
     */
	public Collection<ApplicationStat> getApplicationStats() {
		Query qry = entityManager.createNamedQuery(ApplicationLog.GET_AVG_DURATION_2);
		return qry.getResultList();
	}
}
