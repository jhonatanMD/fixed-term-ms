package com.fixed.term.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.fixed.term.model.FixedTermEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface IFixedRepository  extends ReactiveMongoRepository<FixedTermEntity,String>{

Mono<FixedTermEntity> findByNumAcc(String numAcc);

@Query("{ 'heads.dniH': {$in:[ ?0 ]} }")
Flux<FixedTermEntity> findBytitularesByDoc(List<String> doc);

@Query("{'heads.dniH':  ?0 } ")
Mono<FixedTermEntity> findByDoc(String doc);


}




