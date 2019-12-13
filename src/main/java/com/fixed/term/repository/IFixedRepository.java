package com.fixed.term.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.fixed.term.model.FixedTermEntity;

import reactor.core.publisher.Mono;

@Repository
public interface IFixedRepository  extends ReactiveMongoRepository<FixedTermEntity,String>{

	Mono<FixedTermEntity> findByDniCli(String dniCli);
	
}
