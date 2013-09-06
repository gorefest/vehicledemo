package com.lsy.training.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true) // wir haben kein identifier-Property. Deswegen ist das hier notwendig
public class VehicleDto implements Dto, Serializable{

	String fgnr;
	String modelName;
	String vendorName;
	String engineId;
	Integer cylinders;
	Integer ccm;
	String engineType;
	Double kw;
	BigDecimal price;

	public VehicleDto() {};
	public VehicleDto(String fgnr, String modelName, String vendorName,
			String engineId, Integer cylinders, Integer ccm, String engineType,
			Double kw, BigDecimal price) {
		super();
		this.fgnr = fgnr;
		this.modelName = modelName;
		this.vendorName = vendorName;
		this.engineId = engineId;
		this.cylinders = cylinders;
		this.ccm = ccm;
		this.engineType = engineType;
		this.kw = kw;
		this.price = price;
	}
	public String getFgnr() {
		return fgnr;
	}
	public void setFgnr(String fgnr) {
		this.fgnr = fgnr;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getEngineId() {
		return engineId;
	}
	public void setEngineId(String engineId) {
		this.engineId = engineId;
	}
	public Integer getCylinders() {
		return cylinders;
	}
	public void setCylinders(Integer cylinders) {
		this.cylinders = cylinders;
	}
	public Integer getCcm() {
		return ccm;
	}
	public void setCcm(Integer ccm) {
		this.ccm = ccm;
	}
	public String getEngineType() {
		return engineType;
	}
	public void setEngineType(String engineType) {
		this.engineType = engineType;
	}
	public Double getKw() {
		return kw;
	}
	public void setKw(Double kw) {
		this.kw = kw;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	@Override
	public String getIdentifier() {
		return fgnr;
	}
}
