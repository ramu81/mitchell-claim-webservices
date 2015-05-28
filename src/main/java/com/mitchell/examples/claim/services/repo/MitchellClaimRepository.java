package com.mitchell.examples.claim.services.repo;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mitchell.examples.claim.exceptions.MongoRepoException;
import com.mitchell.examples.claim.mapper.CliamMapper;
import com.mitchell.examples.claim.services.repo.model.MitchellClaim;

/**
 * Repository for {@link Person}s
 * 
 * @author Ram Kondapalli
 */
@Repository
public class MitchellClaimRepository {

	private static final Logger logger = Logger
			.getLogger(MitchellClaimRepository.class);

	private MongoTemplate mongoTemplate;

	private CliamMapper cliamMapper;

	public MitchellClaimRepository() {
	}

	public MitchellClaimRepository(CliamMapper cliamMapper,
			MongoTemplate mongoTemplate) {
		super();
		this.cliamMapper = cliamMapper;
		this.mongoTemplate = mongoTemplate;
	}

	public MitchellClaim findMitchellClaim(String claimNum)
			throws MongoRepoException {
		MitchellClaim claim = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_id").is(claimNum));
			claim = mongoTemplate.findOne(query, MitchellClaim.class);
		} catch (Exception e) {
			logger.error("Method  : findMitchellClaim() Message : "
					+ e.getMessage() + " Cause : " + e.getCause());
			throw new MongoRepoException(e.getLocalizedMessage());
		}
		return claim;
	}

	public List<MitchellClaim> getMitchellClaimList(Date startDate, Date endDate)
			throws MongoRepoException {
		List<MitchellClaim> claims = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("lossDate").gte(startDate)
					.lt(endDate));
			logger.info(" Query ===== > " + query);
			claims = mongoTemplate.find(query, MitchellClaim.class);
		} catch (Exception e) {
			logger.error("Method  : findMitchellClaim() Message : "
					+ e.getMessage() + " Cause : " + e.getCause());
			throw new MongoRepoException(e);
		}
		return claims;
	}

	public boolean insertMitchellClaim(MitchellClaim claim)
			throws MongoRepoException {
		boolean flag = false;
		try {
			mongoTemplate.insert(claim);
			flag = true;
		} catch (Exception e) {
			logger.error("Method  : insertMitchellClaim() Message : "
					+ e.getMessage() + " Cause : " + e.getCause());
			throw new MongoRepoException(e);
		}
		return flag;
	}

	public void updateMitchellClaim(MitchellClaim source)
			throws MongoRepoException {
		try {
			System.out.println(source);
			String claimNum = source.getClaimNumber();
			MitchellClaim dest = findMitchellClaim(claimNum);
			if (dest != null) {
				cliamMapper.copyModelToModel(source, dest);
				mongoTemplate.save(dest);
			} else {
				throw new MongoRepoException("Record is not updated...");
			}
		} catch (Exception e) {
			logger.error("Method  : updateMitchellClaim() Message : "
					+ e.getMessage() + " Cause : " + e.getCause());
			e.printStackTrace();
			throw new MongoRepoException(e);
		}
	}

	/**
	 * Create a {@link MitchellClaim} collection if the collection does not
	 * already exists
	 */
	public void init() {
		try {
			if (!mongoTemplate.collectionExists(MitchellClaim.class)) {
				mongoTemplate.createCollection(MitchellClaim.class);
			}
		} catch (Exception e) {
			logger.error("Method  : init() Message : " + e.getMessage()
					+ " Cause : " + e.getCause());
		}
	}

}
