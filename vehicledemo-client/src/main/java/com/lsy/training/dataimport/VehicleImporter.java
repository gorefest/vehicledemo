package com.lsy.training.dataimport;

import java.io.File;

import com.lsy.training.dto.VehicleDto;
import com.lsy.training.ws.VehicleWebService;

public class VehicleImporter extends AbstractImporter<VehicleDto>{

	VehicleWebService vehicleWebService;
	
	@Override
	public Long saveDto(VehicleDto dto) {
		return null; //vehicleWebService.saveVehicle(dto);
	}
    public VehicleImporter(VehicleWebService vehicleWebService, File file) {
		super();
		this.rowMapper = new VehicleRowMapper();
		this.fileScroller = new FileScroller();
		this.file = file;
		this.vehicleWebService = vehicleWebService;
	}
	
	
}
