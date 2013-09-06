package com.lsy.training.dto;

import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Hilfskonstrukt, das für das Marshalling einer DTO-Liste notwendig ist.
 */
@XmlRootElement         // Pflichtelement für XML-Marshalling
public class VehicleList {

	VehicleDto[] vehicles;

	public VehicleList() {
		
	}
	
	public VehicleList(Collection<VehicleDto> vehicleList){
		vehicles = new VehicleDto[vehicleList.size()];
		vehicles = vehicleList.toArray(vehicles);		
	}
	
	public VehicleDto[] getVehicles() {
		return vehicles;
	}

	public void setVehicles(VehicleDto[] vehicles) {
		this.vehicles = vehicles;
	}
	
	/*
	public static VehicleList from(Collection<VehicleDto> vehicleList){
	 
		VehicleList result = new VehicleList();
		result.vehicles = vehicleList.toArray(result.vehicles);
		return result;
	}
	*/
}
