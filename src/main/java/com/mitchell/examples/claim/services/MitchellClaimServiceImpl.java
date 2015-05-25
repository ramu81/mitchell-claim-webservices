package com.mitchell.examples.claim.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import com.mitchell.examples.claim.MitchellClaimType;
import com.mitchell.examples.claim.config.MitchellConfig;
import com.mitchell.examples.claim.exceptions.MongoRepoException;
import com.mitchell.examples.claim.exceptions.ServiceException;
import com.mitchell.examples.claim.mapper.CliamMapper;
import com.mitchell.examples.claim.mitchellclaimservice.CreateClaimRequest;
import com.mitchell.examples.claim.mitchellclaimservice.DateRangeRequest;
import com.mitchell.examples.claim.mitchellclaimservice.ReadClaimRequest;
import com.mitchell.examples.claim.mitchellclaimservice.UpdateClaimRequest;
import com.mitchell.examples.claim.services.repo.MitchellClaimRepository;
import com.mitchell.examples.claim.services.repo.model.MitchellClaim;
import com.mitchell.examples.claim.util.MitchellUtil;

@Service
public class MitchellClaimServiceImpl implements MitchellClaimService {
	private ApplicationContext ctx = new AnnotationConfigApplicationContext(
			MitchellConfig.class);
	private static final Logger logger = Logger
			.getLogger(MitchellClaimServiceImpl.class);
	private MitchellClaimRepository mitchellClaimRepo = ctx
			.getBean(MitchellClaimRepository.class);
	private CliamMapper mapper = ctx.getBean(CliamMapper.class);

	public void createClaim(CreateClaimRequest req) throws ServiceException {
		logger.debug(" Start createClaim...");
		try {
			MitchellClaimType source = req.getMitchellClaim();
			if (source != null) {
				MitchellClaim claim = mapper.copyClaimToModel(source);
				mitchellClaimRepo.insertMitchellClaim(claim);
			} else {
				throw new ServiceException("Null Request");
			}
		} catch (MongoRepoException e) {
			logger.error("Method  : findMitchellClaim() Message : "
					+ e.getMessage() + " Cause : " + e.getCause());
			throw new ServiceException(e);
		} catch (Exception e) {
			logger.error("Method  : findMitchellClaim() Message : "
					+ e.getMessage() + " Cause : " + e.getCause());
			throw new ServiceException(e);
		}
		logger.debug(" End createClaim  ");
	}

	public void updateClaim(UpdateClaimRequest req) throws ServiceException {
		logger.debug(" Start updateClaim...");
		MitchellClaimType source = req.getMitchellClaim();
		try {
			if (source != null) {
				MitchellClaim claim = mapper.copyClaimToModel(source);
				mitchellClaimRepo.updateMitchellClaim(claim);
			} else {
				throw new ServiceException("Null Request");
			}
		} catch (MongoRepoException e) {
			logger.error("Method  : updateClaim() Message : " + e.getMessage()
					+ " Cause : " + e.getCause());
			e.printStackTrace();
			throw new ServiceException(e);
		} catch (Exception e) {
			logger.error("Method  : updateClaim() Message : " + e.getMessage()
					+ " Cause : " + e.getCause());
			e.printStackTrace();
			throw new ServiceException(e);
		}
		logger.debug(" End updateClaim...    ");
	}

	public MitchellClaimType readClaim(ReadClaimRequest req)
			throws ServiceException {
		logger.debug(" Start readClaim...");
		MitchellClaimType claimType = null;
		try {
			if (req != null) {
				String source = req.getClaimNumber();
				MitchellClaim claim = mitchellClaimRepo
						.findMitchellClaim(source);
				claimType = mapper.copyClaimFromModel(claim);
			} else {
				throw new ServiceException("Null Request");
			}
		} catch (MongoRepoException e) {
			logger.error("Method  : readClaim() Message : " + e.getMessage()
					+ " Cause : " + e.getCause());
			e.printStackTrace();
			throw new ServiceException(e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Method  : readClaim() Message : " + e.getMessage()
					+ " Cause : " + e.getCause());
			throw new ServiceException(e);
		}
		logger.debug(" End readClaim  MitchellClaimType ====>  " + claimType);
		return claimType;
	}

	public List<MitchellClaimType> getClaimList(DateRangeRequest request)
			throws ServiceException {
		List<MitchellClaimType> listOfClaims = null;
		try {
			if (request != null) {
				Date startDate, endDate;
				startDate = MitchellUtil.toDate(request.getStartDate());
				endDate = MitchellUtil.toDate(request.getEndDate());
				logger.info("  getClaimList ==> startDate : " + startDate
						+ " endDate " + endDate);
				List<MitchellClaim> mitchellClaims = mitchellClaimRepo
						.getMitchellClaimList(startDate, endDate);
				logger.info(" Method : getClaimList() :  mitchellClaims  :  " + mitchellClaims);
				if (mitchellClaims != null) {
					listOfClaims = new ArrayList<MitchellClaimType>();
					for (MitchellClaim mitchellClaim : mitchellClaims) {
						MitchellClaimType claimType = mapper
								.copyClaimFromModel(mitchellClaim);
						listOfClaims.add(claimType);
					}
				}
			} else {
				throw new ServiceException("Null Request");
			}
		} catch (MongoRepoException e) {
			logger.error("Method  : getClaimList() Message : " + e.getMessage()
					+ " Cause : " + e.getCause());
			throw new ServiceException(e);
		} catch (Exception e) {
			logger.error("Method  : getClaimList() Message : " + e.getMessage()
					+ " Cause : " + e.getCause());
			throw new ServiceException(e);
		}
		return listOfClaims;
	}

}
