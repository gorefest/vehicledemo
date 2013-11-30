package com.lsy.training.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.lsy.training.dao.SearchDao;
import com.lsy.training.model.Vehicle;
import com.lsy.training.service.VehicleSearchService;

@Named
@RequestScoped
public class VehicleSearchController {

	@EJB
	SearchDao searchDao;

	@EJB
	VehicleSearchService searchService;
	
	private String term;
	private List<Vehicle> result;
	
	public void search() {
		if (term != null && term.trim().length() > 0) {
			result = searchDao.searchVehicles22(term);
		}
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public List<Vehicle> getResult() {
		return result;
	}

	public void setResult(List<Vehicle> result) {
		this.result = result;
	}
	
	
	public void reindex() {
		searchService.reindexData();
	}
}
