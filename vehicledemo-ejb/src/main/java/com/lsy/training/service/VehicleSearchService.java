package com.lsy.training.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import com.lsy.training.convert.VehicleDtoConverter;
import com.lsy.training.dao.SearchDao;
import com.lsy.training.dto.VehicleDto;
import com.lsy.training.ws.VehicleSearchWebService;

@Stateless
@WebService
public class VehicleSearchService implements VehicleSearchWebService{

	@EJB
	SearchDao searchDao;
	
	@WebMethod
	public List<VehicleDto> search(String term){
		return new VehicleDtoConverter().from(searchDao.searchVehicles(term));
	}

	@WebMethod
	public List<VehicleDto> search2(String term){
		return new VehicleDtoConverter().from(searchDao.searchVehicles2(term));
	}

	
	@WebMethod
	public void reindexData() {
		searchDao.reindexData();
	}
	
	
	
}
