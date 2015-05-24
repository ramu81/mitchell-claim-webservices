package com.mitchell.examples.claim.services.dao.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
@Document
public class LossInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2165055120552184359L;
	protected String causeOfLoss;
	protected Date reportedDate;
	protected String lossDescription;

	public LossInfo() {
		super();
	}

	public String getCauseOfLoss() {
		return causeOfLoss;
	}

	public void setCauseOfLoss(String causeOfLoss) {
		this.causeOfLoss = causeOfLoss;
	}

	public Date getReportedDate() {
		return reportedDate;
	}

	public void setReportedDate(Date reportedDate) {
		this.reportedDate = reportedDate;
	}

	public String getLossDescription() {
		return lossDescription;
	}

	public void setLossDescription(String lossDescription) {
		this.lossDescription = lossDescription;
	}

	@Override
	public String toString() {
		return "LossInfoType [causeOfLoss=" + causeOfLoss + ", reportedDate="
				+ reportedDate + ", lossDescription=" + lossDescription + "]";
	}

}
