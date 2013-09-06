package com.lsy.training.dto;

public class VendorDto implements Dto {

	private String name;

	public VendorDto() {
	}

	public VendorDto(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getIdentifier() {
		return name;
	}

}
