package com.mitchell.examples.claim.services.repo.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MitchellClaim implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7649422725655430494L;
	@Id
	protected String claimNumber;
	protected String claimantFirstName;
	protected String claimantLastName;
	protected String status;
	protected Date lossDate;
	protected LossInfo lossInfo;
	protected Long assignedAdjusterID;
	protected VehicleList vehicles;

	public MitchellClaim() {
		super();
	}

	public String getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}

	public String getClaimantFirstName() {
		return claimantFirstName;
	}

	public void setClaimantFirstName(String claimantFirstName) {
		this.claimantFirstName = claimantFirstName;
	}

	public String getClaimantLastName() {
		return claimantLastName;
	}

	public void setClaimantLastName(String claimantLastName) {
		this.claimantLastName = claimantLastName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getLossDate() {
		return lossDate;
	}

	public void setLossDate(Date lossDate) {
		this.lossDate = lossDate;
	}

	public LossInfo getLossInfo() {
		return lossInfo;
	}

	public void setLossInfo(LossInfo lossInfo) {
		this.lossInfo = lossInfo;
	}

	public Long getAssignedAdjusterID() {
		return assignedAdjusterID;
	}

	public void setAssignedAdjusterID(Long assignedAdjusterID) {
		this.assignedAdjusterID = assignedAdjusterID;
	}

	public VehicleList getVehicles() {
		return vehicles;
	}

	public void setVehicles(VehicleList vehicles) {
		this.vehicles = vehicles;
	}

	@Override
	public String toString() {
		return "MitchellClaim [claimNumber=" + claimNumber
				+ ", claimantFirstName=" + claimantFirstName
				+ ", claimantLastName=" + claimantLastName + ", status="
				+ status + ", lossDate=" + lossDate + ", lossInfo=" + lossInfo
				+ ", assignedAdjusterID=" + assignedAdjusterID + ", vehicles="
				+ vehicles + "]";
	}

}