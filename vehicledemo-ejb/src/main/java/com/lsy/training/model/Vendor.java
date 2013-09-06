package com.lsy.training.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.lsy.training.constraints.Case;
import com.lsy.training.constraints.Case.CaseType;

@Entity
@NamedQueries ( {
	@NamedQuery(name= Vendor.GET_BY_NAME
				,query="SELECT o FROM Vendor o WHERE o.name = :name")
,   @NamedQuery(name= Vendor.GET_WITH_PORTFOLIO
				,query="SELECT o " +
						" FROM Vendor o " +
						"WHERE EXISTS (SELECT 1 " +
						"                FROM Vehicle v " +
						"			    WHERE v.vendor = o)")
,   @NamedQuery(name= Vendor.GET_WITH_CRAPPY_CARS2
	,query="SELECT o " +
			" FROM Vendor o " +
			"WHERE EXISTS (SELECT 1 " +
			"                FROM Vehicle v " +
			"			    WHERE v.vendor = o" +
			"				  AND EXISTS (SELECT 1" +
			"								FROM FleetItem x" +
			"							   JOIN x.maintenanceActivities AS m" +
			"							   WHERE x.vehicle = v" +
			"								 AND m.maintenanceType = :foo)" +
			"             )")
	
,   @NamedQuery(name= Vendor.GET_WITH_CRAPPY_CARS
	,query="SELECT x.vehicle.vendor" +
			" FROM FleetItem x" +
			" JOIN x.maintenanceActivities AS m " +
			"WHERE m.maintenanceType = :foo")
	}	)

public class Vendor extends AbstractEntity implements Identifiable, Nameable {

	public static final String GET_BY_NAME="Vendor.getByName";
	public static final String GET_WITH_PORTFOLIO="Vendor.getWithPortfolio";
	public static final String GET_WITH_CRAPPY_CARS="Vendor.getWithCrappyCars";
	public static final String GET_WITH_CRAPPY_CARS2="Vendor.getWithCrappyCars2";
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	Long id;
	
	@NotNull
	@Size(max=30)
	@Case(CaseType.UPPERCASE)
	String name;
	
	@Temporal(TemporalType.DATE)
	Date createDate;
	
	@Temporal(TemporalType.DATE)
	Date lastModified;

	public Vendor(){
		
	}
	
	public Vendor(String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Override
	public String toString() {
		return "Vendor [id=" + id + ", name=" + name + "]";
	}
	
}
