package com.fixed.term.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fixed.term.model.FixedTermEntity;
import com.fixed.term.repository.IFixedRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FixedServiceImpl implements IFixedService {

	@Autowired
	IFixedRepository repository;
	
	@Override
	public Flux<FixedTermEntity> allFixed() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Mono<FixedTermEntity> saveFixed(final FixedTermEntity fixed) {
		// TODO Auto-generated method stub
		return repository.save(fixed);
	}

	@Override
	public Mono<FixedTermEntity> updFixed(final FixedTermEntity fixed) {
		// TODO Auto-generated method stub
		return repository.save(fixed);
	}

	@Override
	public Mono<Void> dltFixed(String id) {
		// TODO Auto-generated method stub
		return repository.deleteById(id);
	}

	@Override
	public Mono<FixedTermEntity> fixedByDni(String dniCl) {
		// TODO Auto-generated method stub
		return repository.findByDniCli(dniCl);
	}

}
