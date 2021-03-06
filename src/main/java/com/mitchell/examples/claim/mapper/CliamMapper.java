package com.mitchell.examples.claim.mapper;

import java.util.ArrayList;
import java.util.List;

import com.mitchell.examples.claim.LossInfoType;
import com.mitchell.examples.claim.MitchellClaimType;
import com.mitchell.examples.claim.VehicleInfoType;
import com.mitchell.examples.claim.VehicleListType;
import com.mitchell.examples.claim.services.repo.model.LossInfo;
import com.mitchell.examples.claim.services.repo.model.MitchellClaim;
import com.mitchell.examples.claim.services.repo.model.VehicleInfo;
import com.mitchell.examples.claim.services.repo.model.VehicleList;
import com.mitchell.examples.claim.util.BeanUtils;

/***
 * 
 * @author Ram Kondapalli
 * 
 */
public class CliamMapper {
	/**
	 * Convert MitchellClaimType to MitchellClaim
	 * 
	 * @param claimType
	 * @return
	 */
	public MitchellClaim copyClaimToModel(MitchellClaimType claimType) {
		MitchellClaim claim = new MitchellClaim();
		BeanUtils.copyProperties(claimType, claim);
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
			BeanUtils.copyProperties(claim, claimType);
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
		BeanUtils.copyProperties(source, dest);
		if (source.getLossInfo() != null)
			BeanUtils.copyProperties(source.getLossInfo(), dest.getLossInfo());
		if (source.getVehicles() != null && dest.getVehicles() != null) {
			List<VehicleInfo> sourceList = source.getVehicles()
					.getVehicleDetails();
			List<VehicleInfo> destList = dest.getVehicles().getVehicleDetails();
			for (VehicleInfo sourcrInfo : sourceList) {
				boolean flag = false;
				for (VehicleInfo destInfo : destList) {
					if (destInfo.getVin().equalsIgnoreCase(sourcrInfo.getVin())) {
						flag = true;
						BeanUtils.copyProperties(sourcrInfo, destInfo);
					}
				}
				if (!flag)
					destList.add(sourcrInfo);
			}
		} else if (source.getVehicles() != null && dest.getVehicles() == null) {
			dest.setVehicles(source.getVehicles());
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
			BeanUtils.copyProperties(lossInfoType, info);
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
			BeanUtils.copyProperties(vehicleInfoType, info);
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
			BeanUtils.copyProperties(info, infoType);
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
			BeanUtils.copyProperties(vehicleInfo, vehicleInfoType);
		}
		return vehicleInfoType;
	}
}
