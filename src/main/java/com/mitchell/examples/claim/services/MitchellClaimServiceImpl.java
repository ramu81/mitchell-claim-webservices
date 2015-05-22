package com.mitchell.examples.claim.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mitchell.examples.claim.MitchellClaimType;
import com.mitchell.examples.claim.mitchellclaimservice.CreateClaimRequest;
import com.mitchell.examples.claim.mitchellclaimservice.DateRangeRequest;
import com.mitchell.examples.claim.mitchellclaimservice.ReadClaimRequest;
import com.mitchell.examples.claim.mitchellclaimservice.UpdateClaimRequest;

@Service
public class MitchellClaimServiceImpl implements MitchellClaimService {

	public MitchellClaimType createClaim(CreateClaimRequest req) {
		MitchellClaimType claimType = req.getMitchellClaim();
		System.out.println(req);
		System.out.println(" createClaim  MitchellClaimType ====>  "
				+ claimType);
		return claimType;
	}

	public MitchellClaimType updateClaim(UpdateClaimRequest req) {
		MitchellClaimType claimType = req.getMitchellClaim();
		System.out.println(req);
		System.out.println(" updateClaim  MitchellClaimType ====>  "
				+ claimType);
		return claimType;
	}

	public MitchellClaimType readClaim(ReadClaimRequest req) {
		MitchellClaimType claimType = new MitchellClaimType();
		claimType.setClaimNumber(req.getClaimNumber());
		System.out.println("  readClaim MitchellClaimType ====>  " + claimType);
		return claimType;
	}

	public List<MitchellClaimType> getListOfClaims(DateRangeRequest request) {

		List<MitchellClaimType> listOfClaims = new ArrayList<MitchellClaimType>();
		System.out.println("  readClaim MitchellClaimType ====>  " + request);

		return listOfClaims;
	}
}
