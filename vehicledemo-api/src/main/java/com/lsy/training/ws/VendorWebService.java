package com.lsy.training.ws;

import java.util.Collection;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.lsy.training.dto.VendorDto;

@WebService
public interface VendorWebService {

	@WebMethod
	Collection<String> getVendorsWithPortfolio();

	@WebMethod
	Collection<String> getWithCrappyCars();

	@WebMethod
	Long saveVendor(VendorDto dto);
	
}