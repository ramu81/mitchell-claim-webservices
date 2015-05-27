package com.mitchell.examples.claim.services;

import java.util.List;

import com.mitchell.examples.claim.MitchellClaimType;
import com.mitchell.examples.claim.exceptions.ServiceException;
import com.mitchell.examples.claim.mitchellclaimservice.CreateClaimRequest;
import com.mitchell.examples.claim.mitchellclaimservice.DateRangeRequest;
import com.mitchell.examples.claim.mitchellclaimservice.ReadClaimRequest;
import com.mitchell.examples.claim.mitchellclaimservice.UpdateClaimRequest;

public interface MitchellClaimService {
	public void createClaim(CreateClaimRequest req)
			throws ServiceException;

	public MitchellClaimType readClaim(ReadClaimRequest request)
			throws ServiceException;

	public void updateClaim(UpdateClaimRequest req)
			throws ServiceException;

	public List<MitchellClaimType> getClaimList(DateRangeRequest request)
			throws ServiceException;

}
