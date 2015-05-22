package com.mitchell.examples.claim.services.model;

import java.io.Serializable;
import java.util.Date;

public class MitchellClaim implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7649422725655430494L;
	protected String claimNumber;
	protected String claimantFirstName;
	protected String claimantLastName;
	protected String status;
	protected Date lossDate;
	protected LossInfoType lossInfo;
	protected Long assignedAdjusterID;
	protected VehicleListType vehicles;

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

	public LossInfoType getLossInfo() {
		return lossInfo;
	}

	public void setLossInfo(LossInfoType lossInfo) {
		this.lossInfo = lossInfo;
	}

	public Long getAssignedAdjusterID() {
		return assignedAdjusterID;
	}

	public void setAssignedAdjusterID(Long assignedAdjusterID) {
		this.assignedAdjusterID = assignedAdjusterID;
	}

	public VehicleListType getVehicles() {
		return vehicles;
	}

	public void setVehicles(VehicleListType vehicles) {
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
