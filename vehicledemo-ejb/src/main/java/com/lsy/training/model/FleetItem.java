package com.lsy.training.model;

import com.lsy.training.constraints.DefectRequiresComment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OrderColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries(
		{ @NamedQuery(
		name=FleetItem.GET_BY_FGNR
		, query="SELECT o FROM FleetItem o WHERE o.vehicle.fgnr = :fgnr") })
public class FleetItem extends AbstractEntity implements Identifiable {

	public static final String GET_BY_FGNR ="FleetItem.getByFgnr";
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	Long id;

	@Version    // Versionsfield für optmistic Locking in der DB, braucht keine getter/setter
	Long version;

	@ManyToOne
	@Valid
	@NotNull
	CustomerUnit customerUnit;

	@ManyToOne
	@Valid
	@NotNull
	Vehicle vehicle;

	@Temporal(TemporalType.DATE)
	Date createDate;

	@Temporal(TemporalType.DATE)
	Date lastModified;

	@ElementCollection
	@OrderColumn(name = "lfdno")
	@Valid
	List<MaintenanceActivity> maintenanceActivities;

	public FleetItem() {

	}

	public FleetItem(CustomerUnit customerUnit, Vehicle vehicle) {
		super();
		this.customerUnit = customerUnit;
		this.vehicle = vehicle;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CustomerUnit getCustomerUnit() {
		return customerUnit;
	}

	public void setCustomerUnit(CustomerUnit customerUnit) {
		this.customerUnit = customerUnit;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
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

	public List<MaintenanceActivity> getMaintenanceActivities() {
		return maintenanceActivities;
	}

	public void setMaintenanceActivities(
			List<MaintenanceActivity> maintenanceActivities) {
		this.maintenanceActivities = maintenanceActivities;
	}

	public void addMaintenanceActivity(MaintenanceActivity activity) {
		if (maintenanceActivities == null) {
			maintenanceActivities = new ArrayList<>();
		}
		maintenanceActivities.add(activity);
	}

}
