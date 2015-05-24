package com.mitchell.examples.claim.services.dao;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mitchell.examples.claim.services.dao.model.MitchellClaim;
import com.mitchell.examples.claim.services.dao.model.Person;

/**
 * Repository for {@link Person}s
 * 
 * @author Ram Kondapalli
 */
@Repository
public class MitchellClaimRepository {

	private static final Logger logger = Logger
			.getLogger(MitchellClaimRepository.class);

	@Autowired
	MongoTemplate mongoTemplate;

	public MitchellClaim findMitchellClaim(String claimNum) {
		MitchellClaim claim = null;
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("_id").is(claimNum));
			 claim = mongoTemplate.findOne(query, MitchellClaim.class);
		} catch (Exception e) {
			logger.error("Method : insertMitchellClaim : " + e);
		}
		return claim;
	}

	public void insertMitchellClaim(MitchellClaim claim) {
		try {
			mongoTemplate.insert(claim);
		} catch (Exception e) {
			logger.error("Method : insertMitchellClaim : " + e);
		}
	}

	public void updateMitchellClaim(MitchellClaim claim) {
		try {
			mongoTemplate.save(claim);
		} catch (Exception e) {
			logger.error("Method : insertMitchellClaim : " + e);
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
			logger.error("Method : createMitchellClaimCollection : " + e);
		}
	}

}
