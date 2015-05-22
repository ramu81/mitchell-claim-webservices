package com.mitchell.examples.claim.services;

import org.springframework.stereotype.Service;

import com.mitchell.examples.claim.MitchellClaimType;
import com.mitchell.examples.claim.mitchellclaimservice.MitchellClaimRequest;

@Service
public class MitchellClaimServiceImpl implements MitchellClaimService {

	public MitchellClaimType createClaim(MitchellClaimRequest req) {
		MitchellClaimType claimType = req.getMitchellClaim();
		System.out.println(req);
		System.out.println("  MitchellClaimType ====>  "
				+ claimType);
		return claimType;
	}

	public MitchellClaimType updateClaim(MitchellClaimRequest req) {
		MitchellClaimType claimType = req.getMitchellClaim();
		System.out.println(req);
		System.out.println("  MitchellClaimType ====>  "
				+ claimType);
		return claimType;
	}
}
