package com.lsy.training.controller;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.lsy.training.dao.SearchDao;
import com.lsy.training.model.Vehicle;
import com.lsy.training.model.Engine.EngineType;
import com.lsy.training.service.VehicleSearchService;

@Named
@RequestScoped
public class VehicleSearchAdvancedController {

	@EJB
	SearchDao searchDao;

	@EJB
	VehicleSearchService searchService;
	
	// Suchparameter
	private String term;
	private Integer weightModel;
	private Integer weightVendor;
	private Integer weightComment;
	private EngineType engineType;
	
	private List<Vehicle> result;
	
	
	public List<EngineType> getAllEngineTypes() {
		return Arrays.asList(EngineType.values());		
	}
	
	public void search() {
		if (term != null && term.trim().length() > 0) {
			result = searchDao.searchVehiclesAdvanced(term, weightModel, weightVendor, weightComment, engineType);
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

	public Integer getWeightModel() {
		return weightModel;
	}

	public void setWeightModel(Integer weightModel) {
		this.weightModel = weightModel;
	}

	public Integer getWeightVendor() {
		return weightVendor;
	}

	public void setWeightVendor(Integer weightVendor) {
		this.weightVendor = weightVendor;
	}

	public Integer getWeightComment() {
		return weightComment;
	}

	public void setWeightComment(Integer weightComment) {
		this.weightComment = weightComment;
	}
	
	@PostConstruct
	public void postConstruct(){
		if (weightComment == null) {
			weightComment = 1;
		}
		if (weightModel == null) {
			weightModel = 1;
		}
		if (weightVendor == null) {
			weightVendor = 1;
		}
	}

	public EngineType getEngineType() {
		return engineType;
	}

	public void setEngineType(EngineType engineType) {
		this.engineType = engineType;
	}
	
}
