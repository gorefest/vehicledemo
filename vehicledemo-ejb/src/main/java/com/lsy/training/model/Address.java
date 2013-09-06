package com.lsy.training.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Einbettbares Addressobject, welches mit den Tabellenzeilen der Entity
 * gespeichert wird
 */
@Embeddable // Hiermit signalisieren wir, dass dies in die Tabelle der Beziehungspartner
            // eine eingebettete Entity ist
public class Address {

	@Size(max=100) //Constraints, sind auch in @Embeddables erlaubt
	@NotNull
	String street;

	@Size(max=12)
	@NotNull
	String zip;
	
	@Size(max=100)
	@NotNull
	String city;
	
	@Size(max = 2, min=2)
	@NotNull
	String country;
	
	public Address(){
	}
	
	public Address(String street, String zip, String city, String country) {
		super();
		this.street = street;
		this.zip = zip;
		this.city = city;
		this.country = country;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + ((zip == null) ? 0 : zip.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Address))
			return false;
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		if (zip == null) {
			if (other.zip != null)
				return false;
		} else if (!zip.equals(other.zip))
			return false;
		return true;
	}

	
	
}