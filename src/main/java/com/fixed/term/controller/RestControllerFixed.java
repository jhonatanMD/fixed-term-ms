package com.fixed.term.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fixed.term.model.FixedTermEntity;
import com.fixed.term.service.FixedServiceImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api")
public class RestControllerFixed {
	@Autowired
	FixedServiceImpl imple;
	
	
	@GetMapping("/getFixeds")
	public Flux<FixedTermEntity> getFixeds(){
		return imple.allFixed();
	}
	
	@GetMapping("/getFixedDni/{dni}")
	public Mono<FixedTermEntity> getFixedDni(@PathVariable("dni") String dni){
		return imple.fixedByDni(dni);
	}

	@PostMapping("/postFixed")
	public Mono<FixedTermEntity> postFixed(@RequestBody final FixedTermEntity fixed){
		Date dt = new Date();
		fixed.setDateReg(dt);
		

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
	
	
	@PostMapping("/updTransancionesFixed/{dni}/{tipo}/{cash}")
	Mono<FixedTermEntity> updTransanciones(@PathVariable("dni") String dni 
			,@PathVariable("tipo") String tipo ,@PathVariable("cash")  Double cash){
			return imple.fixedByDni(dni)
					.flatMap(p ->{
						if(tipo.equals("r") && p.getCash() >= cash) {
							p.setCash(p.getCash() - cash);
						}else if (tipo.equals("d")){
							p.setCash( p.getCash() + cash);
						}
				return imple.updFixed(p);
				});

	}
	
	
}
