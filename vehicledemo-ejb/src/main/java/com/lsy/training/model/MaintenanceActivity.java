package com.lsy.training.model;

import com.lsy.training.constraints.DefectRequiresComment;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
@DefectRequiresComment
public class MaintenanceActivity {

	public static enum MaintenanceType {
		DEFECT,
		PLANNED_MAINTENANCE,
		REOPERATION
	}
	
	@Enumerated(EnumType.ORDINAL)
	MaintenanceType maintenanceType;
	
	@Temporal(TemporalType.DATE)
	@NotNull
	Date maintenanceDate;

	
	@Size(max=255)
	String comment;
	
	public MaintenanceType getMaintenanceType() {
		return maintenanceType;
	}

	public void setMaintenanceType(MaintenanceType maintenanceType) {
		this.maintenanceType = maintenanceType;
	}

	public Date getMaintenanceDate() {
		return maintenanceDate;
	}

	public void setMaintenanceDate(Date maintenanceDate) {
		this.maintenanceDate = maintenanceDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public MaintenanceActivity() {
		
	}

	public MaintenanceActivity(MaintenanceType maintenanceType,
			Date maintenanceDate, String comment) {
		super();
		this.maintenanceType = maintenanceType;
		this.maintenanceDate = maintenanceDate;
		this.comment = comment;
	}
	
}
