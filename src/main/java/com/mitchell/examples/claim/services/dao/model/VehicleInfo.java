package com.mitchell.examples.claim.services.dao.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class VehicleInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2729281018241606659L;
	@Id
	protected String vin;
	protected int modelYear;
	protected String makeDescription;
	protected String modelDescription;
	protected String engineDescription;
	protected String exteriorColor;
	
	protected String licPlate;
	protected String licPlateState;
	protected Date licPlateExpDate;
	protected String damageDescription;
	protected Integer mileage;

	public VehicleInfo() {
		super();
	}

	public int getModelYear() {
		return modelYear;
	}

	public void setModelYear(int modelYear) {
		this.modelYear = modelYear;
	}

	public String getMakeDescription() {
		return makeDescription;
	}

	public void setMakeDescription(String makeDescription) {
		this.makeDescription = makeDescription;
	}

	public String getModelDescription() {
		return modelDescription;
	}

	public void setModelDescription(String modelDescription) {
		this.modelDescription = modelDescription;
	}

	public String getEngineDescription() {
		return engineDescription;
	}

	public void setEngineDescription(String engineDescription) {
		this.engineDescription = engineDescription;
	}

	public String getExteriorColor() {
		return exteriorColor;
	}

	public void setExteriorColor(String exteriorColor) {
		this.exteriorColor = exteriorColor;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getLicPlate() {
		return licPlate;
	}

	public void setLicPlate(String licPlate) {
		this.licPlate = licPlate;
	}

	public String getLicPlateState() {
		return licPlateState;
	}

	public void setLicPlateState(String licPlateState) {
		this.licPlateState = licPlateState;
	}

	public Date getLicPlateExpDate() {
		return licPlateExpDate;
	}

	public void setLicPlateExpDate(Date licPlateExpDate) {
		this.licPlateExpDate = licPlateExpDate;
	}

	public String getDamageDescription() {
		return damageDescription;
	}

	public void setDamageDescription(String damageDescription) {
		this.damageDescription = damageDescription;
	}

	public Integer getMileage() {
		return mileage;
	}

	public void setMileage(Integer mileage) {
		this.mileage = mileage;
	}

	@Override
	public String toString() {
		return "VehicleInfoType [modelYear=" + modelYear + ", makeDescription="
				+ makeDescription + ", modelDescription=" + modelDescription
				+ ", engineDescription=" + engineDescription
				+ ", exteriorColor=" + exteriorColor + ", vin=" + vin
				+ ", licPlate=" + licPlate + ", licPlateState=" + licPlateState
				+ ", licPlateExpDate=" + licPlateExpDate
				+ ", damageDescription=" + damageDescription + ", mileage="
				+ mileage + "]";
	}

}
