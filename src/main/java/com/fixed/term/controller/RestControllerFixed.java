package com.fixed.term.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fixed.term.model.EntityTransaction;
import com.fixed.term.model.FixedTermEntity;
import com.fixed.term.service.IFixedService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api")
public class RestControllerFixed {
	@Autowired
	IFixedService imple;
	
	
	@GetMapping("/getFixeds")
	public Flux<FixedTermEntity> getFixeds(){
		return imple.allFixed();
	}
	
	@GetMapping("/getFixedDates/{dt1}/{dt2}/{bank}")
	Flux<FixedTermEntity> getFixedDates(@PathVariable("dt1") String dt1
			,@PathVariable("dt2") String dt2,@PathVariable("bank") String bank) throws ParseException{	
		return imple.findByBankAndDateOpenBetween(bank, dt1, dt2);
	}
	
	@GetMapping("/getFixedNumAcc/{numAcc}")
	public Mono<FixedTermEntity> getFixedDni(@PathVariable("numAcc") String numAcc){
		return imple.fixedByNumAcc(numAcc);
	}
	
	@GetMapping("/getFixedNumDoc/{numDoc}")
	public Flux<FixedTermEntity> getFixedNumDoc(@PathVariable("numDoc") String numDoc){
		return imple.findByDoc(numDoc);
	}

	@PostMapping("/postFixed")
	public Mono<FixedTermEntity> postFixed(@RequestBody final FixedTermEntity fixed){
		return imple.saveFixed(fixed);
	}
	
	
	@PutMapping("/updFixed")
	public Mono<FixedTermEntity> updFixed(@RequestBody final FixedTermEntity fixed){
		
		return imple.updFixed(fixed);
	}
	
	
	@DeleteMapping("/dltFixed")
	public Mono<Void> dltFixed(String id){
		return imple.dltFixed(id);
	}
	
	
	@PostMapping("/updTransancionesFixed/{numAcc}/{tipo}/{cash}")
	Mono<EntityTransaction> updTransanciones(@PathVariable("numAcc") String numAcc 
			,@PathVariable("tipo") String tipo ,@PathVariable("cash")  Double cash){
			return imple.transactionFixed(numAcc, tipo, cash);
	}
	
	
}
