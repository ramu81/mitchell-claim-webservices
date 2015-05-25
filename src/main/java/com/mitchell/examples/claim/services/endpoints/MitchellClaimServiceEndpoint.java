package com.mitchell.examples.claim.services.endpoints;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.mitchell.examples.claim.MitchellClaimType;
import com.mitchell.examples.claim.exceptions.ServiceException;
import com.mitchell.examples.claim.mitchellclaimservice.ClaimResponse;
import com.mitchell.examples.claim.mitchellclaimservice.CreateClaimRequest;
import com.mitchell.examples.claim.mitchellclaimservice.CreateClaimResponse;
import com.mitchell.examples.claim.mitchellclaimservice.DateRangeRequest;
import com.mitchell.examples.claim.mitchellclaimservice.ListOfClaims;
import com.mitchell.examples.claim.mitchellclaimservice.ReadClaimRequest;
import com.mitchell.examples.claim.mitchellclaimservice.UpdateClaimRequest;
import com.mitchell.examples.claim.mitchellclaimservice.UpdateClaimResponse;
import com.mitchell.examples.claim.services.MitchellClaimService;

@Endpoint
public class MitchellClaimServiceEndpoint {
	private static final String TARGET_NAMESPACE = "http://com/mitchell/examples/claim/mitchellclaimservice";
	@Autowired
	MitchellClaimService mitchellClaimService;
	private static final Logger logger = Logger
			.getLogger(MitchellClaimServiceEndpoint.class);

	@PayloadRoot(localPart = "CreateClaimRequest", namespace = TARGET_NAMESPACE)
	public @ResponsePayload
	CreateClaimResponse createClaim(@RequestPayload CreateClaimRequest request) {
		CreateClaimResponse claimResponse = new CreateClaimResponse();
		try {
			mitchellClaimService.createClaim(request);
			claimResponse.setCreateClaimResponse("Record inserted");
		} catch (ServiceException e) {
			claimResponse.setCreateClaimResponse(e.getLocalizedMessage());
			logger.error("Method  : createClaim() Message : " + e.getMessage()
					+ " Cause : " + e.getCause());
		}

		return claimResponse;
	}

	@PayloadRoot(localPart = "UpdateClaimRequest", namespace = TARGET_NAMESPACE)
	public @ResponsePayload
	UpdateClaimResponse updateClaim(@RequestPayload UpdateClaimRequest request) {
		UpdateClaimResponse updateClaimResponse = new UpdateClaimResponse();
		try {
			mitchellClaimService.updateClaim(request);
			updateClaimResponse.setUpdateClaimResponse("Record updated");
		} catch (ServiceException e) {
			updateClaimResponse.setUpdateClaimResponse("Update failed");
			logger.error("Method  : UpdateClaimRequest() Message : "
					+ e.getMessage() + " Cause : " + e.getCause());
		}

		return updateClaimResponse;
	}

	@PayloadRoot(localPart = "ReadClaimRequest", namespace = TARGET_NAMESPACE)
	public @ResponsePayload
	ClaimResponse readClaim(@RequestPayload ReadClaimRequest request) {
		ClaimResponse claimResponse = null;
		if (request != null) {
			try {
				MitchellClaimType claimType = mitchellClaimService
						.readClaim(request);
				claimResponse = new ClaimResponse();
				claimResponse.setClaimResponse(claimType);
			} catch (ServiceException e) {
				logger.error("Method  : readClaim() Message : "
						+ e.getMessage() + " Cause : " + e.getCause());
				e.printStackTrace();
			}

		}
		return claimResponse;
	}

	@PayloadRoot(localPart = "DateRangeRequest", namespace = TARGET_NAMESPACE)
	public @ResponsePayload
	ListOfClaims getListOfClaims(@RequestPayload DateRangeRequest request) {
		ListOfClaims claimResponse = null;
		if (request != null) {
			List<MitchellClaimType> claimType;
			try {
				claimType = mitchellClaimService.getClaimList(request);
				claimResponse = new ListOfClaims();
				claimResponse.getListOfClaims().addAll(claimType);
			} catch (ServiceException e) {
				logger.error("Method  : getListOfClaims() Message : "
						+ e.getMessage() + " Cause : " + e.getCause());
			}

		}
		return claimResponse;
	}

	public void setMitchellClaimService_i(
			MitchellClaimService mitchellClaimService_i) {
		this.mitchellClaimService = mitchellClaimService_i;
	}
}
