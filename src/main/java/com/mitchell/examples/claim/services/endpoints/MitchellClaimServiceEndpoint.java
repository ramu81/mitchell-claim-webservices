package com.mitchell.examples.claim.services.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.mitchell.examples.claim.MitchellClaimType;
import com.mitchell.examples.claim.mitchellclaimservice.MitchellClaimRequest;
import com.mitchell.examples.claim.mitchellclaimservice.MitchellClaimResponse;
import com.mitchell.examples.claim.services.MitchellClaimService;

@Endpoint
public class MitchellClaimServiceEndpoint {
	private static final String TARGET_NAMESPACE = "http://com/mitchell/examples/claim/mitchellclaimservice";
	@Autowired
	MitchellClaimService mitchellClaimService_i;

	@PayloadRoot(localPart = "MitchellClaimRequest", namespace = TARGET_NAMESPACE)
	public @ResponsePayload MitchellClaimResponse createClaim(
			@RequestPayload MitchellClaimRequest request) {
		MitchellClaimResponse claimResponse = new MitchellClaimResponse();
		MitchellClaimType claimType = mitchellClaimService_i
				.createClaim(request);
		claimResponse.setMitchellClaim(claimType);
		return claimResponse;
	}

	public void setMitchellClaimService_i(
			MitchellClaimService mitchellClaimService_i) {
		this.mitchellClaimService_i = mitchellClaimService_i;
	}
}
