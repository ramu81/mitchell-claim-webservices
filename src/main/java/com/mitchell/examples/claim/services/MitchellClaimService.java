package com.mitchell.examples.claim.services;

import com.mitchell.examples.claim.MitchellClaimType;
import com.mitchell.examples.claim.mitchellclaimservice.MitchellClaimRequest;

public interface MitchellClaimService {
	public MitchellClaimType createClaim(MitchellClaimRequest req);
	public MitchellClaimType updateClaim(MitchellClaimRequest req);
}
