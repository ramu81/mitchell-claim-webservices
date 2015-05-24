package com.mitchell.examples.claim.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import com.mitchell.examples.claim.MitchellClaimType;
import com.mitchell.examples.claim.config.MitchellConfig;
import com.mitchell.examples.claim.mitchellclaimservice.CreateClaimRequest;
import com.mitchell.examples.claim.mitchellclaimservice.DateRangeRequest;
import com.mitchell.examples.claim.mitchellclaimservice.ReadClaimRequest;
import com.mitchell.examples.claim.mitchellclaimservice.UpdateClaimRequest;
import com.mitchell.examples.claim.services.dao.MitchellClaimRepository;
import com.mitchell.examples.claim.services.dao.model.MitchellClaim;
import com.mitchell.examples.claim.util.MitchellUtil;

@Service
public class MitchellClaimServiceImpl implements MitchellClaimService {
	private ApplicationContext ctx = new AnnotationConfigApplicationContext(
			MitchellConfig.class);
	private static final Logger logger = Logger
			.getLogger(MitchellClaimServiceImpl.class);
	private MitchellClaimRepository mitchellClaimRepo = ctx
			.getBean(MitchellClaimRepository.class);
	private MitchellUtil util = ctx.getBean(MitchellUtil.class);

	public MitchellClaimType createClaim(CreateClaimRequest req) {
		logger.debug(" Start createClaim...");
		MitchellClaimType source = req.getMitchellClaim();
		mitchellClaimRepo.insertMitchellClaim(util.copyClaimToModel(source));
		logger.debug(" End createClaim  MitchellClaimType ====>  " + source);
		return source;
	}

	public MitchellClaimType updateClaim(UpdateClaimRequest req) {
		logger.debug(" Start updateClaim...");
		MitchellClaimType source = req.getMitchellClaim();
		mitchellClaimRepo.updateMitchellClaim(util.copyClaimToModel(source));
		logger.debug(" End updateClaim  MitchellClaimType ====>  " + source);
		return source;
	}

	public MitchellClaimType readClaim(ReadClaimRequest req) {
		logger.debug(" Start readClaim...");
		String source = req.getClaimNumber();
		MitchellClaim claim = mitchellClaimRepo.findMitchellClaim(source);
		logger.debug(" End readClaim  MitchellClaimType ====>  " + claim);
		return util.copyClaimFromModel(claim);
	}

	public List<MitchellClaimType> getListOfClaims(DateRangeRequest request) {

		List<MitchellClaimType> listOfClaims = new ArrayList<MitchellClaimType>();
		System.out.println("  readClaim MitchellClaimType ====>  " + request);

		return listOfClaims;
	}
}
