package com.mitchell.examples.claim.services;

import java.util.List;

import com.mitchell.examples.claim.MitchellClaimType;
import com.mitchell.examples.claim.mitchellclaimservice.CreateClaimRequest;
import com.mitchell.examples.claim.mitchellclaimservice.DateRangeRequest;
import com.mitchell.examples.claim.mitchellclaimservice.ReadClaimRequest;
import com.mitchell.examples.claim.mitchellclaimservice.UpdateClaimRequest;

public interface MitchellClaimService {
	public MitchellClaimType createClaim(CreateClaimRequest req);
	public MitchellClaimType readClaim(ReadClaimRequest request);
	public MitchellClaimType updateClaim(UpdateClaimRequest req);
	public List<MitchellClaimType> getListOfClaims(DateRangeRequest request);
	
}
