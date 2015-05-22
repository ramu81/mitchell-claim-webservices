package com.mitchell.examples.claim.services.model;

import java.io.Serializable;
import java.util.List;

import com.mitchell.examples.claim.VehicleInfoType;

public class VehicleListType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3231537855355890866L;
	protected List<VehicleInfoType> vehicleDetails;

	public VehicleListType() {
		super();
	}

	public List<VehicleInfoType> getVehicleDetails() {
		return vehicleDetails;
	}

	public void setVehicleDetails(List<VehicleInfoType> vehicleDetails) {
		this.vehicleDetails = vehicleDetails;
	}

	@Override
	public String toString() {
		return "VehicleListType [vehicleDetails=" + vehicleDetails + "]";
	}

}
