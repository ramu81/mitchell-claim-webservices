package com.mitchell.examples.claim.services.dao.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
@Document
public class VehicleList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3231537855355890866L;
	protected List<VehicleInfo> vehicleDetails;

	public VehicleList() {
		super();
	}

	public List<VehicleInfo> getVehicleDetails() {
		return vehicleDetails;
	}

	public void setVehicleDetails(List<VehicleInfo> vehicleDetails) {
		this.vehicleDetails = vehicleDetails;
	}

	@Override
	public String toString() {
		return "VehicleListType [vehicleDetails=" + vehicleDetails + "]";
	}

}
