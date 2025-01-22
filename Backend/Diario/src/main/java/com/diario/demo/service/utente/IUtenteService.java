package com.diario.demo.service.utente;

import java.util.List;
import java.util.Optional;

import com.diario.demo.model.Diario;
import com.diario.demo.model.Utente;

public interface IUtenteService {
		
	public List<Diario> dammiListaTestiPerUtente();
	
	public Optional<Diario> dammiTestoPerId(String id);
	
	public Diario creaTesto(Diario diario);
	
	public Optional<Diario> modificaTestoPerId(Diario diario,String id);
	
	public void eliminaTestoPerId(String id);
	
	public Utente registraUtente(Utente utente);

}
