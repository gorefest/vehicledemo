package com.lsy.training.ws.impl;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.jws.WebMethod;
import javax.jws.WebService;

import com.lsy.training.dto.VehicleDto;
import com.lsy.training.interceptor.LogInterceptor;
import com.lsy.training.service.dto.VehicleDtoService;
import com.lsy.training.ws.VehicleWebService;

@WebService
@Stateless
@Interceptors({LogInterceptor.class}) // Hier wird wieder Interception betrieben
public class VehicleWebServiceImpl implements VehicleWebService{

	@EJB
    VehicleDtoService dtoService;

	@WebMethod
	public Collection<VehicleDto> loadAll() {
		return dtoService.loadAll();
	}

	@Override
	@WebMethod
	public VehicleDto getByFgnr(String fgnr) {
		return dtoService.getByFgnr(fgnr);
	}

	@Override
	@WebMethod
	public void saveVehicle(VehicleDto dto) {
		dtoService.saveVehicle(dto);
	}
	
	
	
}
