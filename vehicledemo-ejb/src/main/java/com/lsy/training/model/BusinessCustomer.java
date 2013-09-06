package com.lsy.training.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Business Customer Unit, als eine von zwei CustomerUnit-Ausprägungen
 */
@Entity
public class BusinessCustomer extends CustomerUnit implements Nameable {

	@Embedded       // Eingebettete Entity
    @AttributeOverrides({   // Ueberschreiben der Column Names
            @AttributeOverride(column = @Column(name="BC_LASTNAME"),name="lastname"),
            @AttributeOverride(column = @Column(name="BC_FIRSTNAME"),name="firstname"),
            @AttributeOverride(column = @Column(name="BC_BIRTHDAY"),name="birthday")
    })
	Person boss;    // @see Person
	
	@NotNull
	String name;
	
	@NotNull
	@Column(unique=true)
	String vatId;

	public BusinessCustomer() {
		
	}
	
	public BusinessCustomer(Person boss, String companyName, String vatId) {
		super();
		this.boss = boss;
		this.name = companyName;
		this.vatId = vatId;
	}

	public Person getBoss() {
		return boss;
	}

	public void setBoss(Person boss) {
		this.boss = boss;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVatId() {
		return vatId;
	}

	public void setVatId(String vatId) {
		this.vatId = vatId;
	}
	
}
