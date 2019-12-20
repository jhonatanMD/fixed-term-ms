package com.fixed.term.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fixed.term.model.EntityTransaction;
import com.fixed.term.model.FixedTermEntity;
import com.fixed.term.repository.IFixedRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FixedServiceImpl implements IFixedService {

	@Autowired
	IFixedRepository repository;
	EntityTransaction transaction;
	List<String> doc;
	Boolean ope = false;
	
	@Override
	public Flux<FixedTermEntity> allFixed() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Mono<FixedTermEntity> saveFixed(final FixedTermEntity fixed) {
		// TODO Auto-generated method stub
		doc = new ArrayList<>();
		fixed.setDateReg(new Date());
		fixed.setCash(fixed.getCashLoan());
		fixed.getHeads().forEach(head -> doc.add(head.getDniH()));
		return repository.findBytitularesByDoc(doc,fixed.getProfile())
				.switchIfEmpty(repository.save(fixed).flatMap(sv->{
			return Mono.just(sv);
		})).next();
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
	public Mono<FixedTermEntity> fixedByNumAcc(String numAcc) {
		// TODO Auto-generated method stub
		return repository.findByNumAcc(numAcc);
	}

	@Override
	public Mono<EntityTransaction> transactionFixed(String numAcc, String tipo, Double cash) {
		// TODO Auto-generated method stub
		transaction = new EntityTransaction();
		return  repository.findByNumAcc(numAcc)
				.flatMap(p ->{
					
								transaction.setCashA(p.getCash());
								if(tipo.equals("r") && p.getCash() >= cash) {
									p.setCash(p.getCash() - cash );
									transaction.setCashT(p.getCash());
									ope =true;
								}else if (tipo.equals("d")){
									if( p.getCashLoan() >= (p.getCash() + cash)) {
										p.setCash( p.getCash() + cash);
										transaction.setCashT(p.getCash());
										ope =true;
									}
								}
								
								if(ope) {
									transaction.setType(tipo);
								
									transaction.setCashO(cash);
									transaction.setCommi(0.0);
									transaction.setNumAcc(numAcc);
									transaction.setDateTra(new Date());	
								}
						repository.save(p).subscribe();
						return Mono.just(transaction);
				});

	}

	@Override
	public Flux<FixedTermEntity> findByDoc(String numDoc) {
		// TODO Auto-generated method stub
		return repository.findByDoc(numDoc);
	}

}
