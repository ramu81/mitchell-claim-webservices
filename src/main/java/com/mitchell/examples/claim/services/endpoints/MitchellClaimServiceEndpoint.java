package com.mitchell.examples.claim.services.endpoints;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.mitchell.examples.claim.MitchellClaimType;
import com.mitchell.examples.claim.mitchellclaimservice.ClaimResponse;
import com.mitchell.examples.claim.mitchellclaimservice.CreateClaimRequest;
import com.mitchell.examples.claim.mitchellclaimservice.DateRangeRequest;
import com.mitchell.examples.claim.mitchellclaimservice.ListOfClaims;
import com.mitchell.examples.claim.mitchellclaimservice.ReadClaimRequest;
import com.mitchell.examples.claim.mitchellclaimservice.UpdateClaimRequest;
import com.mitchell.examples.claim.services.MitchellClaimService;

@Endpoint
public class MitchellClaimServiceEndpoint {
	private static final String TARGET_NAMESPACE = "http://com/mitchell/examples/claim/mitchellclaimservice";
	@Autowired
	MitchellClaimService mitchellClaimService_i;

	@PayloadRoot(localPart = "CreateClaimRequest", namespace = TARGET_NAMESPACE)
	public @ResponsePayload ClaimResponse createClaim(
			@RequestPayload CreateClaimRequest request) {
		ClaimResponse claimResponse = new ClaimResponse();
		MitchellClaimType claimType = mitchellClaimService_i
				.createClaim(request);
		claimResponse.setClaimResponse(claimType);
		return claimResponse;
	}

	@PayloadRoot(localPart = "UpdateClaimRequest", namespace = TARGET_NAMESPACE)
	public @ResponsePayload ClaimResponse updateClaim(
			@RequestPayload UpdateClaimRequest request) {
		ClaimResponse claimResponse = new ClaimResponse();
		MitchellClaimType claimType = mitchellClaimService_i
				.updateClaim(request);
		claimResponse.setClaimResponse(claimType);
		return claimResponse;
	}

	@PayloadRoot(localPart = "ReadClaimRequest", namespace = TARGET_NAMESPACE)
	public @ResponsePayload ClaimResponse readClaim(
			@RequestPayload ReadClaimRequest request) {
		ClaimResponse claimResponse = new ClaimResponse();
		if (request != null) {
			MitchellClaimType claimType = mitchellClaimService_i
					.readClaim(request);
			claimResponse.setClaimResponse(claimType);
		}
		return claimResponse;
	}

	@PayloadRoot(localPart = "DateRangeRequest", namespace = TARGET_NAMESPACE)
	public @ResponsePayload ListOfClaims getListOfClaims(
			@RequestPayload DateRangeRequest request) {
		ListOfClaims claimResponse = new ListOfClaims();
		if (request != null) {
			List<MitchellClaimType> claimType = mitchellClaimService_i
					.getListOfClaims(request);
			claimResponse.getListOfClaims().addAll(claimType);
		}
		return claimResponse;
	}

	public void setMitchellClaimService_i(
			MitchellClaimService mitchellClaimService_i) {
		this.mitchellClaimService_i = mitchellClaimService_i;
	}
}
