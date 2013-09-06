package com.lsy.training.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.lsy.training.model.Identifiable;
import com.lsy.training.model.Nameable;

/**
 * Abstraktest BasisDao, welches mit Hilfe von Generic und Criteriabuilder schon
 * mal die wichtigsten DAO-Funktionen für alle Objekte bereitstellt
 *
 * @param <T> - Model-Objekt, welches templatisiert wird
 */
public abstract class AbstractBaseDao<T extends Identifiable> {
	
	@PersistenceContext(unitName="fachlichkeit")
	EntityManager entityManager;

    /**
     * gibt ein Objekt anhand einer ID zurück.
     * @param id - die ID. Long, weil T eine Instanz von Identifiable ist
     * @return das ID
     */
	public T getById(Long id) {
		return entityManager.find(getManagedClass(), id);
	}

    /**
     * persistiert ein Objekt vom Typ T. Wichtig: Ein Flush findet hier
     * noch nicht statt, erst beim Verlassen der Transaktionsklammer
     * oder beim Entity-Detach
     *
     * @param object - das Objekt, welches persistiert wird.
     */
	public void save(T object) {
		entityManager.persist(object);
	}

    /**
     * Lädt alle Objekte der jew. Klasse mit Hilfe der Criteria API
     *
     * @return alle Objekte
     */
	public Collection<T> loadAll() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(getManagedClass());
		Root<T> root = cq.from(getManagedClass());
		cq.select(root);
		TypedQuery<T> qry = entityManager.createQuery(cq);
		return qry.getResultList();		
	}

    /**
     * Findet ein Objekt über seinen Namen mit Hilfe des Criteria Builder.
     * Geht nur, wenn das zu ladende Objekt eine Instanz von Nameable ist
     *
     * @param name - der Name des Objekts
     * @return das Objekt
     */
	public T getByName(String name) {
		Class<T> clazz = getManagedClass();
		if (!Nameable.class.isAssignableFrom(clazz)) {
			throw new IllegalArgumentException(clazz.getName()+ " is not of type Nameable");
		}
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(clazz);
		Root<T> root = cq.from(clazz);

		Predicate p = cb.equal(root.get("name"), name);
		cq.where(p);	
		cq.select(root);
		TypedQuery<T> qry = entityManager.createQuery(cq);
		return qry.getSingleResult();
		
	}

    /**
     * Abstrakte Helferfunktion, welches jedes DAO implementiert
     *
     * @return die Persistenzklasse, welche vom DAO gemanaged wird
     */
	public abstract Class<T> getManagedClass();

    /**
     * Gibt ein Objekt oder NULL zurück. Soll eine NoResultFoundException verhindern
     *
     * @param v die Collection, welche ein Objekt oder NULL liefern soll
     * @return ein Objekt oder NULL
     */
	protected T getSingleResult(Collection<T> v) {
		if (v.size() == 0) { 
			return null; 
		} else if (v.size() == 1) {
			return v.iterator().next();
		} else {
			throw new RuntimeException("Non-Unique Attribute!");
		}
	}
}
