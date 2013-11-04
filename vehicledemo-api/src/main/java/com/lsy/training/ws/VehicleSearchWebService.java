package com.lsy.training.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.lsy.training.dto.VehicleDto;

@WebService
public interface VehicleSearchWebService {

	@WebMethod
	List<VehicleDto> search(String term);
	
	@WebMethod
	void reindexData(); 

	@WebMethod
	List<VehicleDto> search2(String term);
}
