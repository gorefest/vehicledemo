package com.lsy.training.model;

import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;


/**
 * Abstrakte Kundenoberklasse
 *
 * Demonstriert den Einsatz von Vererbung in Datenmodellen
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)   // Angabe der Vererbungsstrategie
public abstract class CustomerUnit extends AbstractEntity implements Identifiable{

	@Id // Technische PK-Spalte
	@GeneratedValue(strategy=GenerationType.SEQUENCE)   // Generiert aus SEQUENCE
	Long id;
	
	@Temporal(TemporalType.DATE)    // Datumswert mit Datumspräzision
	Date createDate;
	
	@Temporal(TemporalType.DATE)    // Datumswert mit Datumspräzision
	Date lastModified;

	@Embedded   // Eingebettet Entity
    @NotNull    // muss gefüllt ...
	@Valid      // ... und valide sein, d.h. alle Address-Constraints müsssen valid sein
	Address address;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
}
