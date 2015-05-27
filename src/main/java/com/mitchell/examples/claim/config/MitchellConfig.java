package com.mitchell.examples.claim.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mitchell.examples.claim.mapper.CliamMapper;
import com.mitchell.examples.claim.services.repo.MitchellClaimRepository;
import com.mongodb.MongoClient;

@Configuration
public class MitchellConfig {
	private static final Logger logger = Logger.getLogger(MitchellConfig.class);

	public @Bean MongoDbFactory mongoDbFactory() throws Exception {
		return new SimpleMongoDbFactory(new MongoClient(), "demo");
	}

	public @Bean MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		return mongoTemplate;
	}

	@Bean(initMethod = "init")
	public MitchellClaimRepository getMitchellClaimRepo() {
		MongoTemplate mongoTemplate = null;
		try {
			mongoTemplate = mongoTemplate();
		} catch (Exception e) {
			logger.error(e);
		}
		return new MitchellClaimRepository(cliamMapper(), mongoTemplate);
	}

	public @Bean CliamMapper cliamMapper() {
		return new CliamMapper();
	}
}
