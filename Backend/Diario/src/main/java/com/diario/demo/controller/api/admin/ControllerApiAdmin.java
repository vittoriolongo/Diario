package com.diario.demo.controller.api.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.diario.demo.model.Diario;
import com.diario.demo.model.Utente;
import com.diario.demo.service.admin.AdminService;

/*Controller Admin che gestisce le chiamate indirizzate al service admin*/
@RestController
public class ControllerApiAdmin {
	
@Autowired	
private AdminService adminService;


	/*Chiamate rest metodi GET per AreaUtentiAdmin*/
	@GetMapping("/admin/api/utenti")
	public Iterable<Utente> dammiListaUtenti(){
		return adminService.dammiListaUtentiAdmin();
	}
	
	
	@GetMapping("/admin/api/utenti/{username}")
	public Utente dammiUtentePerUsername(@PathVariable String username) {
	    return adminService.dammiUtentePerUsernameAdmin(username);  
	}

	
	/*Chiamate Rest metodi GET per AreaTestiAdmin*/
	@GetMapping("/admin/api/testi")
	public Iterable<Diario> dammiListaTestiAdmin(){
		return adminService.dammiTuttiTestiAdmin();
	}
	
	
	@GetMapping("/admin/api/testi/{userId}")
	public List<Diario> dammiListaTestiPerUtente(@PathVariable int userId) {
	    return adminService.dammiListaTestiAdminPerUtente(userId);
	}
	
	
	/*Chiamate Rest metodi POST per AreaTestiAdmin*/
	@PostMapping("/admin/api/testi/{userId}")
	public Diario creaTesto(@RequestBody Diario diario, @PathVariable int userId) {
		return adminService.creaTestoAdmin(diario, userId);
	}
	
	
	/*Chiamate Rest metodi PUT per AreaUtentiAdmin*/
	@PutMapping("/admin/api/utenti/{userId}")
	public Optional<Utente> modificaPasswordUtenteAdmin(@RequestBody Utente utente, @PathVariable int userId){
		return adminService.modificaPasswordUtenteAdmin(utente, userId);
	}
	
	
	/*Chiamate Rest metodi PUT per AreaTestiAdmin*/
	@PutMapping("/admin/api/testi/{id}")
	public Optional<Diario> modificaTesto(@RequestBody Diario diario, @PathVariable String id) {
		return adminService.modificaTestoAdminPerId(diario, id);
	}
	
	
	/*Chiamate Rest metodi DELETE per AreaTestiAdmin*/
	@DeleteMapping("/admin/api/testi/{id}")
	public void eliminaTesto(@PathVariable String id){
		adminService.eliminaTestoAdminPerId(id);
	}
	
	
	/*Chiamate Rest metodi DELETE per AreaUtentiAdmin*/
	@DeleteMapping("/admin/api/utenti/{id}")
	public void eliminaUtente(@PathVariable int id) {
	    adminService.eliminaUtenteAdminPerId(id);
	}

}

