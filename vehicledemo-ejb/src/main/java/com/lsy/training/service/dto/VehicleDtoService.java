package com.lsy.training.service.dto;

import java.util.ArrayList;
import java.util.Collection;

import com.lsy.training.dao.EngineDao;
import com.lsy.training.dao.FleetItemDao;
import com.lsy.training.dao.VendorDao;
import com.lsy.training.dao.VehicleDao;
import com.lsy.training.dto.MaintenanceActivityDto;
import com.lsy.training.dto.VehicleDto;
import com.lsy.training.model.*;
import com.lsy.training.model.Vendor;


import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DTO-Service. Prinzipiell kann man DTO-Erzeugungen auch Generieren, allerdings ist das
 * annehmen von DTOs und einmergen in die DB eine Fachlichkeit, die der Tatsache geschuldet ist,
 * dass i.d.R. DTOs nicht alle Entitydaten mitbekommen und die Übernahme dieser Daten
 * bei Aktualisierungen genau abgewogen werden muss
 */
@Stateless
public class VehicleDtoService {

	private final Logger log = LoggerFactory.getLogger(VehicleDtoService.class);

	@EJB
	VehicleDao vehicleDao;
	
	@EJB
	EngineDao engineDao;
	
	@EJB
    VendorDao vendorDao;
	
	@EJB
	FleetItemDao fleetItemDao;
	
	public Collection<VehicleDto> loadAll() {
		log.info("BEGIN LOAD ALL");
		Collection<Vehicle> vehicles = vehicleDao.loadAllCompletley();
		Collection<VehicleDto> result = new ArrayList<>(vehicles.size());
		for (Vehicle vehicle  : vehicles) {
			result.add(from(vehicle));
		}
		log.info("END LOAD ALL");
		return result;
	}
	
	public VehicleDto getByFgnr(String fgnr) {
		return from(vehicleDao.getByFgnr(fgnr));
	}
	
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
	
	public void saveVehicle(VehicleDto dto) {
		Vehicle vehicle  = new Vehicle();
		
		vehicle.setFgnr(dto.getFgnr());
		vehicle.setModelName(dto.getModelName());
		vehicle.setPrice(dto.getPrice());
		
		Engine e = engineDao.getByEngineId(dto.getEngineId());
		Vendor m = vendorDao.getByName(dto.getVendorName());
		
		vehicle.setEngine(e);
		vehicle.setVendor(m);
		
		vehicleDao.save(vehicle);
		
	}
	
	
	public void updateVehicle(VehicleDto dto) {
		Vehicle v = vehicleDao.getByFgnr(dto.getFgnr());
		if (v == null) {
			saveVehicle(dto);			
		} else {
			if (!v.getEngine().getEngineId().equals(dto.getEngineId())) {
				Engine e = engineDao.getByEngineId(dto.getEngineId());
				v.setEngine(e);
			}
			if (!v.getVendor().getName().equals(dto.getVendorName())) {
				Vendor m = vendorDao.getByName(dto.getVendorName());
				v.setVendor(m);
			}
			
			if (!v.getModelName().equals(dto.getModelName())) {
				v.setModelName(dto.getModelName());
			}
			
			if (!v.getPrice().equals(dto.getPrice())) {
				v.setPrice(dto.getPrice());
			}
		}
	}
	
	public Collection<MaintenanceActivityDto> getMaintenanceActivitiesForFgnr(String fgnr) {
		FleetItem fi = fleetItemDao.getByFgnr(fgnr);
		if (fi == null) {
			return new ArrayList<MaintenanceActivityDto>();
		}
		Collection<MaintenanceActivity> act = fi.getMaintenanceActivities();
		Collection<MaintenanceActivityDto> result = new ArrayList<>(act.size());
		for (MaintenanceActivity m : act) {
			MaintenanceActivityDto dto = new MaintenanceActivityDto();
			dto.setActivityType(m.getMaintenanceType().toString());
			dto.setComment(m.getComment());
			dto.setDate(m.getMaintenanceDate());
			result.add(dto);
		}
		return result;
	}
}
