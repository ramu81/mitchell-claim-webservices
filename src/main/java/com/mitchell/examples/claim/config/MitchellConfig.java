<<<<<<< HEAD
package com.mitchell.examples.claim.config;

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

	public @Bean
	MongoDbFactory mongoDbFactory() throws Exception {
		return new SimpleMongoDbFactory(new MongoClient(), "demo");
	}

	public @Bean
	MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		return mongoTemplate;
	}

	@Bean(initMethod = "init")
	public MitchellClaimRepository getMitchellClaimRepo() {
		return new MitchellClaimRepository();
	}

	public @Bean
	CliamMapper getCliamMapper() {
		return new CliamMapper();
	}
}
=======
package com.mitchell.examples.claim.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mitchell.examples.claim.services.dao.MitchellClaimRepository;
import com.mitchell.examples.claim.util.MitchellUtil;
import com.mongodb.MongoClient;

@Configuration
public class MitchellConfig {

	public @Bean
	MongoDbFactory mongoDbFactory() throws Exception {
		return new SimpleMongoDbFactory(new MongoClient(), "demo");
	}

	public @Bean
	MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		return mongoTemplate;
	}

	@Bean(initMethod = "init")
	public MitchellClaimRepository getMitchellClaimRepo() {
		return new MitchellClaimRepository();
	}

	public @Bean
	MitchellUtil getMitchellUtil() {
		return new MitchellUtil();
	}
}
>>>>>>> origin/master
