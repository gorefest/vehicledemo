package com.lsy.training.util;

import java.util.Collection;

import com.lsy.training.model.Vendor;
import com.lsy.training.model.Vehicle;

/**
 * Demonstrationsbean für einen "klassischen" JUnit-Test
 */
public class VehicleUtil {

	public static Vendor getBigBlockVendor(Collection<Vehicle> x) {
		Vehicle bigBlockPred = null;
		Vehicle bigBlock = null;
		
		if (x == null || x.size() == 0){
			return null;
		}
		
		for (Vehicle vehicle : x) {
			if (bigBlock == null || bigBlock.getEngine().getKw() <= vehicle.getEngine().getKw()) {
				bigBlockPred = bigBlock;
				bigBlock = vehicle;
			}
		}
		
		if (bigBlockPred != null && 
				bigBlockPred.getEngine().getKw().equals(bigBlock.getEngine().getKw())) {
			throw new RuntimeException("Duplicate Bigblock!");
		}
		
		return bigBlock.getVendor();
	}

}
