package com.lsy.training.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Privatkunde, als eine von zwei Ausprägungen
 *
 * enthält die eingebettete Entity Person
 *
 * @see Person
 *
 */
@Entity // Einfache Entity
@NamedQueries({     // Named Query mit Embeddable als Parameter
	@NamedQuery(name = PrivateCustomer.GET_BY_PERSON
			, query = "SELECT o FROM PrivateCustomer o WHERE o.person = :person") 
	})
public class PrivateCustomer extends CustomerUnit {

	public static final String GET_BY_PERSON="PrivateCustomer.getByName";
	
	@Embedded   // Eingebettete Person , @see Person
	private Person person;

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public PrivateCustomer() {

	}

	public PrivateCustomer(Person person) {
		super();
		this.person = person;
	}

}
