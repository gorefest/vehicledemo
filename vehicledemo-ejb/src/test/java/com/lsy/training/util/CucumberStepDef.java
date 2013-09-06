package com.lsy.training.util;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import cucumber.runtime.PendingException;

public class CucumberStepDef {

	List<Integer> list = new ArrayList<>();
	int result;
	
	@Given("^a number (\\d+) entered into the calculator$")
	public void a_number_entered_into_the_calculator(int arg1) {
		list.add(arg1);
	}

	@When("^I perform add function$")
	public void I_perform_add_function() {
		result = 0;
		for (Integer i : list) {
			result += i;
		}
	}

	@Then("^result will be (\\d+)$")
	public void result_will_be(int arg1) {
		org.junit.Assert.assertEquals(arg1, result);
	}

}
