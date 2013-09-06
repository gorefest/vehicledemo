package com.lsy.training.ws.impl;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.jws.WebMethod;
import javax.jws.WebService;

import com.lsy.training.dao.VendorDao;
import com.lsy.training.dto.VendorDto;
import com.lsy.training.interceptor.LogInterceptor;
import com.lsy.training.model.Vendor;
import com.lsy.training.ws.VendorWebService;

/**
 * Wieder ein JAX-WS Bean, dessen API in das API-Modul ausgelagert wurde, um es im Client (@see DynamicClient)
 * dynamisch zu binden
 *
 * @see ApplicationLogWebServiceImpl
 * @see com.lsy.training.ws.VendorWebService
 *
 */
@Stateless
@WebService
@Interceptors({LogInterceptor.class})   // Hier wird der Loginterceptor über alle WS-Methoden gestülpt
public class VendorWebServiceImpl implements VendorWebService {

	@EJB
    VendorDao vendorDao;
	
	/* (non-Javadoc)
	 * @see com.lsy.training.ws.impl.VendorWebService#getVendorsWithPortfolio()
	 */
	@Override
	@WebMethod
	public Collection<String> getVendorsWithPortfolio() {
		Collection<Vendor> vendors = vendorDao.getWithPortfolio();
		return iterateVendors(vendors);
	}

	private Collection<String> iterateVendors(
            Collection<Vendor> vendors) {
		Collection<String> result = new ArrayList<>(vendors.size());
		for (Vendor vendor : vendors) {
			result.add(vendor.getName());
		}
		return result;
	}

	@Override
	@WebMethod
	public Collection<String> getWithCrappyCars() {
		return iterateVendors(vendorDao.getWithCrappyCars());
	}

	@Override
	@WebMethod
	public Long saveVendor(VendorDto dto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
