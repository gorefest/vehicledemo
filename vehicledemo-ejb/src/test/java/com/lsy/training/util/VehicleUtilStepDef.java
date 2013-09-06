package com.lsy.training.util;

import java.util.Arrays;

import com.lsy.training.model.Engine;
import com.lsy.training.model.Vehicle;
import com.lsy.training.model.Vendor;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import static org.junit.Assert.*;

public class VehicleUtilStepDef {

	Vehicle v1, v2, v3;
	Engine e1, e2, e3;

	Vendor m1, m2, m3;

	Vendor probe;

	@Given("^a list of cars with (\\d+),(\\d+),(\\d+) hp$")
	public void a_list_of_cars_with_engine_engine_engine_hp(int arg1, int arg2,
			int arg3) {
		e1 = new Engine();
		e1.setKw((double) arg1);
		e2 = new Engine();
		e2.setKw((double) arg2);
		e3 = new Engine();
		e3.setKw((double) arg3);

		v1 = new Vehicle();
		v1.setEngine(e1);
		v2 = new Vehicle();
		v2.setEngine(e2);
		v3 = new Vehicle();
		v3.setEngine(e3);
	}

	@When("^the method is called find the car with the biggst engine$")
	public void the_method_is_called_find_the_car_with_the_biggst_engine() {
		probe = VehicleUtil.getBigBlockVendor(Arrays.asList(new Vehicle[] { v1,
				v2, v3 }));
	}

	@Given("^a list of vendors (\\d+),(\\d+),(\\d+) associated with the matching cars$")
	public void a_list_of_vendors_v_v_v_associated_with_the_matching_cars(
			int arg1, int arg2, int arg3) {
		m1 = new Vendor();
		m1.setId(Long.valueOf(arg1));
		m2 = new Vendor();
		m2.setId(Long.valueOf(arg2));
		m3 = new Vendor();
		m3.setId(Long.valueOf(arg3));
		v1.setVendor(m1);
		v2.setVendor(m2);
		v3.setVendor(m3);

	}

	@Then("^the found vendor should be (\\d+)$")
	public void the_found_vendor_should_be_v_(int arg1) {
		assertEquals(Long.valueOf(arg1),probe.getId());
	}


}
