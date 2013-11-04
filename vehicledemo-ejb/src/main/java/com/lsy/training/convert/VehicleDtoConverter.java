package com.lsy.training.convert;

import com.lsy.training.dto.VehicleDto;
import com.lsy.training.model.Engine;
import com.lsy.training.model.Vehicle;

public class VehicleDtoConverter extends DtoConverter<Vehicle,VehicleDto>{

	public VehicleDto from(Vehicle vehicle) {
		VehicleDto result = new VehicleDto();
		Engine engine = vehicle.getEngine();
		result.setCcm(engine.getCcm());
		result.setCylinders(engine.getCylinders());
		result.setEngineId(engine.getEngineId());
		result.setEngineType(engine.getEngineType().toString());
		result.setFgnr(vehicle.getFgnr());
		result.setKw(engine.getKw());
		result.setVendorName(vehicle.getVendor().getName());
		result.setModelName(vehicle.getModelName());
		result.setPrice(vehicle.getPrice());
		return result;
	}
	 
}
