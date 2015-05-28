package com.mitchell.examples.claim.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Configuration;

import com.mitchell.examples.claim.CauseOfLossCode;
import com.mitchell.examples.claim.LossInfoType;
import com.mitchell.examples.claim.MitchellClaimType;
import com.mitchell.examples.claim.StatusCode;
import com.mitchell.examples.claim.VehicleInfoType;
import com.mitchell.examples.claim.VehicleListType;
import com.mitchell.examples.claim.services.repo.model.LossInfo;
import com.mitchell.examples.claim.services.repo.model.MitchellClaim;
import com.mitchell.examples.claim.services.repo.model.VehicleInfo;
import com.mitchell.examples.claim.services.repo.model.VehicleList;
import com.mitchell.examples.claim.util.MitchellUtil;

/***
 * 
 * @author Ram Kondapalli
 * 
 */
@Configuration
public class CliamMapper {
	/**
	 * Convert MitchellClaimType to MitchellClaim
	 * 
	 * @param claimType
	 * @return
	 */
	public MitchellClaim copyClaimToModel(MitchellClaimType claimType) {
		MitchellClaim claim = new MitchellClaim();
		com.mitchell.examples.claim.util.BeanUtils.copyProperties(claimType,
				claim);
		claim.setVehicles(getVehicleList(claimType.getVehicles()));
		claim.setLossInfo(getLossInfo(claimType.getLossInfo()));
		return claim;
	}

	/**
	 * MitchellClaimType LossInfo to MitchellClaim
	 * 
	 * @param claim
	 * @return
	 */
	public MitchellClaimType copyClaimFromModel(MitchellClaim claim) {
		MitchellClaimType claimType = null;
		if (claim != null) {
			claimType = new MitchellClaimType();
			com.mitchell.examples.claim.util.BeanUtils.copyProperties(claim,
					claimType);
			claimType.setLossInfo(getLossInfoType(claim.getLossInfo()));
			claimType.setVehicles(getVehicleDetails(claim.getVehicles()));
		}
		return claimType;
	}

	/***
	 * Copy properties from model to model
	 * 
	 * @param source
	 * @param dest
	 * @return
	 */
	public MitchellClaim copyModelToModel(MitchellClaim source,
			MitchellClaim dest) {
		String[] ignorePro = MitchellUtil.getNullProperties(source);
		BeanUtils.copyProperties(source, dest, ignorePro);
		if (source.getLossInfo() != null) {
			ignorePro = MitchellUtil.getNullProperties(source.getLossInfo());
			BeanUtils.copyProperties(source.getLossInfo(), dest.getLossInfo(),
					ignorePro);
		}
		if (source.getVehicles() != null && dest.getVehicles() != null) {
			List<VehicleInfo> sourceList = source.getVehicles()
					.getVehicleDetails();
			List<VehicleInfo> destList = dest.getVehicles().getVehicleDetails();
			for (VehicleInfo sourcrInfo : sourceList) {
				for (VehicleInfo destInfo : destList) {
					if (destInfo.getVin().equalsIgnoreCase(sourcrInfo.getVin())) {
						ignorePro = MitchellUtil.getNullProperties(sourcrInfo);
						BeanUtils.copyProperties(sourcrInfo, destInfo,
								ignorePro);
					} else {
						destInfo = new VehicleInfo();
						ignorePro = MitchellUtil.getNullProperties(sourcrInfo);
						BeanUtils.copyProperties(sourcrInfo, destInfo,
								ignorePro);
					}
				}
			}
		} else if (source.getVehicles() != null && dest.getVehicles() == null) {
			List<VehicleInfo> sourceList = source.getVehicles()
					.getVehicleDetails();
			for (VehicleInfo sourcrInfo : sourceList) {
				VehicleInfo destInfo = new VehicleInfo();
				ignorePro = MitchellUtil.getNullProperties(sourcrInfo);
				BeanUtils.copyProperties(sourcrInfo, destInfo, ignorePro);
			}

		}
		return dest;

	}

	/***
	 * Convert LossInfoType to LossInfo
	 * 
	 * @param lossInfoType
	 * @return
	 */
	private LossInfo getLossInfo(LossInfoType lossInfoType) {
		LossInfo info = null;
		if (lossInfoType != null) {
			info = new LossInfo();
			com.mitchell.examples.claim.util.BeanUtils.copyProperties(
					lossInfoType, info);
		}
		return info;
	}

	/***
	 * Convert VehicleListType to VehicleList
	 * 
	 * @param vehicleList
	 * @return
	 */
	private VehicleList getVehicleList(VehicleListType vehicleList) {
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

	/**
	 * Convert VehicleInfoType to VehicleInfo
	 * 
	 * @param vehicleInfoType
	 * @return
	 */
	private VehicleInfo getVehicleInfo(VehicleInfoType vehicleInfoType) {
		VehicleInfo info = null;
		if (vehicleInfoType != null) {
			info = new VehicleInfo();
			com.mitchell.examples.claim.util.BeanUtils.copyProperties(
					vehicleInfoType, info);
		}
		return info;
	}

	/**
	 * Convert LossInfo to LossInfoType
	 * 
	 * @param info
	 * @return
	 */
	private LossInfoType getLossInfoType(LossInfo info) {
		LossInfoType infoType = null;
		if (info != null) {
			infoType = new LossInfoType();
			try {
				infoType.setCauseOfLoss(CauseOfLossCode.fromValue(info
						.getCauseOfLoss()));
			} catch (IllegalArgumentException e) {
				infoType.setCauseOfLoss(null);
			}
			infoType.setLossDescription(info.getLossDescription());
			infoType.setReportedDate(MitchellUtil.toXMLGregorianCalendar(info
					.getReportedDate()));
		}
		return infoType;
	}

	/***
	 * Convert VehicleList to VehicleListType
	 * 
	 * @param vehicles
	 * @return
	 */
	private VehicleListType getVehicleDetails(VehicleList vehicles) {
		VehicleListType listType = null;
		if (vehicles != null) {
			listType = new VehicleListType();
			List<VehicleInfoType> infoTypes = null;
			List<VehicleInfo> vehicleInfoList = vehicles.getVehicleDetails();
			if (vehicleInfoList != null && !vehicleInfoList.isEmpty()) {
				infoTypes = new ArrayList<VehicleInfoType>();
				for (VehicleInfo vehicleInfo : vehicleInfoList) {
					infoTypes.add(getVehicleInfoType(vehicleInfo));
				}
			}
			listType.getVehicleDetails().addAll(infoTypes);
		}
		return listType;
	}

	/***
	 * Convert VehicleInfo to VehicleInfoType
	 * 
	 * @param vehicleInfo
	 * @return
	 */
	private VehicleInfoType getVehicleInfoType(VehicleInfo vehicleInfo) {
		VehicleInfoType vehicleInfoType = null;
		if (vehicleInfo != null) {
			vehicleInfoType = new VehicleInfoType();
			vehicleInfoType.setVin(vehicleInfo.getVin());
			vehicleInfoType.setDamageDescription(vehicleInfo
					.getDamageDescription());
			vehicleInfoType.setEngineDescription(vehicleInfo
					.getEngineDescription());
			vehicleInfoType.setExteriorColor(vehicleInfo.getExteriorColor());
			vehicleInfoType.setLicPlate(vehicleInfo.getLicPlate());
			vehicleInfoType
					.setMakeDescription(vehicleInfo.getMakeDescription());
			vehicleInfoType.setMileage(vehicleInfo.getMileage());
			vehicleInfoType.setModelDescription(vehicleInfo
					.getModelDescription());
			vehicleInfoType.setModelYear(vehicleInfo.getModelYear());
		}
		return vehicleInfoType;
	}
}
