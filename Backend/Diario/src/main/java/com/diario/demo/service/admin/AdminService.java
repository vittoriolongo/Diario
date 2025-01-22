package com.diario.demo.service.admin;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.diario.demo.model.Diario;
import com.diario.demo.model.Utente;
import com.diario.demo.repository.diario.IDiarioRepository;
import com.diario.demo.repository.utente.IUtenteRepository;

/*Service admin per che si occupa di manipolare i dati del database*/
@Service
public class AdminService implements IAdminService {
	
	@Autowired
	private IUtenteRepository utenteRepository;

	@Autowired
	private IDiarioRepository diarioRepository;
	
	
	/*Metodi GET Service Admin*/
	@Override
	public Iterable<Utente> dammiListaUtentiAdmin(){
		return utenteRepository.findAll();
	}
	
	
	@Override
	public Utente dammiUtentePerUsernameAdmin(String username) {
	    return utenteRepository.findByUtente(username)  
	        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato per username: " + username));
	}
	
	
	@Override
	 public Iterable<Diario> dammiTuttiTestiAdmin() {
	        return diarioRepository.findAll();
	 }
	
	
	@Override
	public List<Diario> dammiListaTestiAdminPerUtente(int userId) {
	    List<Diario> listaTesti = diarioRepository.findByUserId(userId); 
	    
	    if (!listaTesti.isEmpty()) {
	        return listaTesti;
	    }

	    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nessun testo trovato per l'utente con ID: " + userId);
	}
	
	
	/*Metodi POST Service Admin*/
	@Override
    public Diario creaTestoAdmin(Diario diario, int userId) {
    	diario.setUserId(userId);
        diario.setDataTesto(LocalDate.now());
    	return diarioRepository.save(diario);
    }

	
	
	/*Metodi PUT Service Admin*/
	@Override
	public Optional<Diario> modificaTestoAdminPerId(Diario diario,String id){
		
		Optional<Diario> idTrovato = diarioRepository.findById(id);
		
		if(idTrovato.isPresent()) {
			Diario diarioEsistente = idTrovato.get();
			diarioEsistente.setTesto(diario.getTesto());
			diarioEsistente.setDataTesto(LocalDate.now());
			return Optional.of(diarioRepository.save(diarioEsistente));
		}
		
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Testo non trovato");
	}
	
	
	@Override
	public Optional<Utente> modificaPasswordUtenteAdmin(Utente utente, int userId) {
		
	    Optional<Utente> idTrovato = utenteRepository.findById(userId);

	    if (idTrovato.isPresent()) {
	        Utente utenteEsistente = idTrovato.get();
	        // Cripta la password prima di salvarla
	        String passwordCriptata = new BCryptPasswordEncoder().encode(utente.getPassword());
	        utenteEsistente.setPassword(passwordCriptata);
	        utenteRepository.save(utenteEsistente);
	        return Optional.of(utenteEsistente);
	    }

	    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato");
	}


	/*Metodi DELETE Service Admin*/
	public void eliminaUtenteAdminPerId(int id) {
	    Optional<Utente> idTrovato = utenteRepository.findById(id);

	    if (idTrovato.isPresent()) {
	        utenteRepository.deleteById(id);
	        return; 
	    }

	    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato");
	}
	
	@Override
	 public void eliminaTestoAdminPerId(String Id) {
	    	
	    	if (diarioRepository.existsById(Id)) {
	    		diarioRepository.deleteById(Id);
	    		return;
	    	} 
	      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Testo non trovato");
	    }
}