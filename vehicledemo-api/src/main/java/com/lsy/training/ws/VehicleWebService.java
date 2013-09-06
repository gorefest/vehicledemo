package com.lsy.training.ws;

import java.util.Collection;


import javax.jws.WebMethod;
import javax.jws.WebService;

import com.lsy.training.dto.VehicleDto;

@WebService
public interface VehicleWebService {

	@WebMethod
	Collection<VehicleDto> loadAll();

	@WebMethod
	VehicleDto getByFgnr(String fgnr);
	
	@WebMethod
	void saveVehicle(VehicleDto dto);
	
}
