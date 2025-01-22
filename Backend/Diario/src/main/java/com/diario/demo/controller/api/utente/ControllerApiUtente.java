package com.diario.demo.controller.api.utente;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.diario.demo.model.Diario;
import com.diario.demo.model.Utente;
import com.diario.demo.service.utente.UtenteService;

import jakarta.validation.Valid;

/*Controller Utente che gestisce le chiamate indirizzate al service utente*/
@RestController
public class ControllerApiUtente {
	
	@Autowired
	private UtenteService service;
			
	/* Chiamate Rest metodi GET User*/
	@GetMapping ("/user/api/testi")
	List<Diario> mostraTesti() {
		return service.dammiListaTestiPerUtente();
	}
	
	@GetMapping("/user/api/username")
    public String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
	
	
	/*Chiamate Rest metodi POST User*/
	@PostMapping("/user/api/testi")
	Diario creaTesto(@RequestBody Diario diario) {
		return service.creaTesto(diario);
	}
	
	
	/*Chiamate Rest metodo POST RegistrazioneUser*/
	@PostMapping("/signin/registrazione")
	public Utente registraUtente(@Valid @RequestBody Utente utente)  {
		return service.registraUtente(utente);
	}
	
	
	/*Chiamate Rest metodi PUT User*/
	@PutMapping("/user/api/testi{id}")
	public Optional<Diario>modificaTesto(@RequestBody Diario diario, @PathVariable String id) {
		return service.modificaTestoPerId(diario, id);
	}
	
	/*Chiamate Rest metodi DELETE User*/
	@DeleteMapping("/user/api/testi/{id}")
	void eliminaTesto(@PathVariable String id){
		service.eliminaTestoPerId(id);
	return;
	}

}
