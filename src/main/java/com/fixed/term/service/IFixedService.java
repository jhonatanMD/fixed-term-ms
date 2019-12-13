package com.fixed.term.service;

import com.fixed.term.model.FixedTermEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IFixedService {

	Flux<FixedTermEntity> allFixed();
	Mono<FixedTermEntity> saveFixed(final FixedTermEntity fixed);
	Mono<FixedTermEntity> updFixed(final FixedTermEntity fixed);
	Mono<Void> dltFixed(String id);
	Mono<FixedTermEntity> fixedByDni(final String dniCl);
}
