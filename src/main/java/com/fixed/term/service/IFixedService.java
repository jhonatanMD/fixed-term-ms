package com.fixed.term.service;

import java.text.ParseException;

import com.fixed.term.model.EntityTransaction;
import com.fixed.term.model.FixedTermEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IFixedService {

	Flux<FixedTermEntity> allFixed();
	Mono<FixedTermEntity> saveFixed(final FixedTermEntity fixed);
	Mono<FixedTermEntity> updFixed(final FixedTermEntity fixed);
	Mono<Void> dltFixed(String id);
	Mono<FixedTermEntity> fixedByNumAcc(final String numAcc);
	Mono<EntityTransaction> transactionFixed(String numAcc,String tipo,Double cash);

	Flux<FixedTermEntity> findByDoc(String numDoc);
	
	Flux<FixedTermEntity> findByBankAndDateOpenBetween(String bank,String dt1 ,String dt2) throws ParseException;
}
