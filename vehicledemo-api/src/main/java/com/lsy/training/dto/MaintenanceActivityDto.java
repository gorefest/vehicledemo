package com.lsy.training.dto;

import java.util.Date;

/**
 * REST-DTO für Maintenance Activities
 */
public class MaintenanceActivityDto implements Dto{

	String activityType;
	String comment;
	Date date;
	public String getActivityType() {
		return activityType;
	}
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String getIdentifier() {
		return date.getTime()+"."+activityType;
	}
	
	
}
