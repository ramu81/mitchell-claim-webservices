package com.mitchell.examples.claim.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;

import com.mitchell.examples.claim.LossInfoType;
import com.mitchell.examples.claim.MitchellClaimType;
import com.mitchell.examples.claim.StatusCode;
import com.mitchell.examples.claim.VehicleInfoType;
import com.mitchell.examples.claim.VehicleListType;
import com.mitchell.examples.claim.services.dao.model.LossInfo;
import com.mitchell.examples.claim.services.dao.model.MitchellClaim;
import com.mitchell.examples.claim.services.dao.model.VehicleInfo;
import com.mitchell.examples.claim.services.dao.model.VehicleList;

public class MitchellUtil {
	private static final Logger logger = Logger.getLogger(MitchellUtil.class);

	/**
	 * 
	 * @param claimType
	 * @return
	 */
	public MitchellClaim copyClaimToModel(MitchellClaimType claimType) {
		MitchellClaim claim = new MitchellClaim();
		claim.setClaimNumber(claimType.getClaimNumber());
		claim.setClaimantFirstName(claimType.getClaimantFirstName());
		claim.setClaimantLastName(claimType.getClaimantLastName());
		claim.setAssignedAdjusterID(claimType.getAssignedAdjusterID());
		claim.setLossDate(toDate(claimType.getLossDate()));
		if (claimType.getStatus() != null)
			claim.setStatus(claimType.getStatus().toString());
		claim.setVehicles(copyVehicleList(claimType.getVehicles()));
		claim.setLossInfo(getLossInfo(claimType.getLossInfo()));
		return claim;
	}

	public MitchellClaimType copyClaimFromModel(MitchellClaim claim) {
		MitchellClaimType claimType = null;
		if(claim!=null) {
			claimType = new MitchellClaimType();
		claimType.setAssignedAdjusterID(claim.getAssignedAdjusterID());
		claimType.setClaimantFirstName(claim.getClaimantFirstName());
		claimType.setClaimantLastName(claim.getClaimantLastName());
		claimType.setClaimNumber(claim.getClaimNumber());
		claimType.setLossDate(toXMLGregorianCalendar(claim.getLossDate()));
		claimType.setStatus(StatusCode.fromValue(claim.getStatus()));
		}
		return claimType;
	}

	private LossInfo getLossInfo(LossInfoType lossInfoType) {
		LossInfo info = null;
		if (lossInfoType != null) {
			info = new LossInfo();
			if (lossInfoType.getCauseOfLoss() != null)
				info.setCauseOfLoss(lossInfoType.getCauseOfLoss().toString());
			info.setLossDescription(lossInfoType.getLossDescription());
			info.setReportedDate(toDate(lossInfoType.getReportedDate()));
		}
		return info;
	}

	private VehicleList copyVehicleList(VehicleListType vehicleList) {
		VehicleList vehicleDetails = null;
		if (vehicleList != null) {
			vehicleDetails = new VehicleList();
			List<VehicleInfo> vehicleInfo = new ArrayList<VehicleInfo>();
			for (VehicleInfoType vehicleInfoType : vehicleList
					.getVehicleDetails()) {
				vehicleInfo.add(getVehicleInfo(vehicleInfoType));
			}
			vehicleDetails.setVehicleDetails(vehicleInfo);
		}
		return vehicleDetails;
	}

	private VehicleInfo getVehicleInfo(VehicleInfoType vehicleInfoType) {
		VehicleInfo info = null;
		if (vehicleInfoType != null) {
			info = new VehicleInfo();
			info.setVin(vehicleInfoType.getVin());
			info.setDamageDescription(vehicleInfoType.getDamageDescription());
			info.setEngineDescription(vehicleInfoType.getEngineDescription());
			info.setExteriorColor(vehicleInfoType.getExteriorColor());
			info.setLicPlate(vehicleInfoType.getLicPlate());
			info.setMakeDescription(vehicleInfoType.getMakeDescription());
			info.setMileage(vehicleInfoType.getMileage());
			info.setModelDescription(vehicleInfoType.getModelDescription());
			info.setModelYear(vehicleInfoType.getModelYear());
		}
		return info;
	}

	/*
	 * Converts java.util.Date to javax.xml.datatype.XMLGregorianCalendar
	 */
	private static XMLGregorianCalendar toXMLGregorianCalendar(Date date) {
		GregorianCalendar gCalendar = new GregorianCalendar();
		gCalendar.setTime(date);
		XMLGregorianCalendar xmlCalendar = null;
		try {
			xmlCalendar = DatatypeFactory.newInstance()
					.newXMLGregorianCalendar(gCalendar);
		} catch (DatatypeConfigurationException ex) {
			logger.fatal("Method :  toXMLGregorianCalendar  :  " + ex);
		}
		return xmlCalendar;
	}

	/*
	 * Converts XMLGregorianCalendar to java.util.Date in Java
	 */
	private static Date toDate(XMLGregorianCalendar calendar) {
		if (calendar == null) {
			return null;
		}
		return calendar.toGregorianCalendar().getTime();
	}

}
