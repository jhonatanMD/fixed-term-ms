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
	List<EntityTransaction> listTransaction;
	List<String> doc;
	Date dt = new Date();
	@Override
	public Flux<FixedTermEntity> allFixed() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Mono<FixedTermEntity> saveFixed(final FixedTermEntity fixed) {
		// TODO Auto-generated method stub
		doc = new ArrayList<>();
		fixed.setDateReg(dt);
		fixed.getHeads().forEach(head -> doc.add(head.getDniH()));
		return repository.findBytitularesByDoc(doc)
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
	public Mono<FixedTermEntity> transactionFixed(String numAcc, String tipo, Double cash) {
		// TODO Auto-generated method stub
		
		return  repository.findByNumAcc(numAcc)
				.flatMap(p ->{
					transaction = new EntityTransaction();
					 transaction.setCashA(p.getCash());
				//	String dFecha = new SimpleDateFormat("yyyy-MM-dd").format(dt);
				//	try {
					// Date fechaR = new SimpleDateFormat().parse(dFecha);
					 if(tipo.equals("r") && p.getCash() >= cash) {
						p.setCash(p.getCash() - cash);
					}else if (tipo.equals("d")){
						p.setCash( p.getCash() + cash);
					}
					//}catch (Exception e) {
				//		System.out.println(e.getMessage());
					//}
					
					transaction.setType(tipo);
					 transaction.setCashO(cash);
					 transaction.setCashT(p.getCash());
					 transaction.setDateTra(dt);
					listTransaction = new ArrayList<>();
					if(p.getTransactions() != null) {
						p.getTransactions().forEach(transac-> {
							listTransaction.add(transac);
						});
					}
					
					listTransaction.add(transaction);
					p.setTransactions(listTransaction);
			return repository.save(p);
			});

	}

	@Override
	public Mono<FixedTermEntity> findByDoc(String numDoc) {
		// TODO Auto-generated method stub
		return repository.findByDoc(numDoc);
	}

}
