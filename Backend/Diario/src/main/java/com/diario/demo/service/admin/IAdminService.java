package com.diario.demo.service.admin;

import java.util.List;
import java.util.Optional;

import com.diario.demo.model.Diario;
import com.diario.demo.model.Utente;

public interface IAdminService {
	
	public Iterable<Utente> dammiListaUtentiAdmin();
	
	public Utente dammiUtentePerUsernameAdmin(String username);
	
	public List<Diario> dammiListaTestiAdminPerUtente(int userId);
	
	public Iterable<Diario> dammiTuttiTestiAdmin();
	
	public Diario creaTestoAdmin(Diario diario, int userId);
	
	public Optional<Diario> modificaTestoAdminPerId(Diario diario,String id);
	
	public Optional<Utente> modificaPasswordUtenteAdmin(Utente utente, int userId);
	
	public void eliminaUtenteAdminPerId(int id);	
	
	public void eliminaTestoAdminPerId(String id);	

}

