package com.diario.demo.service.utente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.diario.demo.model.Diario;
import com.diario.demo.model.Utente;
import com.diario.demo.repository.diario.IDiarioRepository;
import com.diario.demo.repository.utente.IUtenteRepository;
import com.diario.demo.security.CustomUserDetailsService;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*Service utente per che si occupa di manipolare i dati del database*/
@Service
public class UtenteService implements IUtenteService {	
	
    @Autowired
    private IDiarioRepository diarioRepository;
    
    @Autowired
    private IUtenteRepository utenteRepository;
    
    @Autowired
    private CustomUserDetailsService userDetailsService;
    
    
    /*Metodi GET Utente Service*/
    @Override
    public List<Diario> dammiListaTestiPerUtente() {
    	
    	int userId = userDetailsService.getAuthenticatedUserId();
    	
        List<Diario> listaTesti = diarioRepository.findByUserId(userId);
        
        if (!listaTesti.isEmpty()) {
            return listaTesti;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato");
    }

    
    @Override
    public Optional<Diario> dammiTestoPerId(String id) {
    	Optional<Diario> idTrovato = diarioRepository.findById(id);
    	
    	if(idTrovato.isPresent()) {
        return diarioRepository.findById(id);
    	}
    	
    	throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato");
    }
    	
    

    /*Metodi POST Utente Service*/
    @Override
    public Diario creaTesto(Diario diario) {
    	int userId = userDetailsService.getAuthenticatedUserId();

        diario.setUserId(userId);
        diario.setDataTesto(LocalDate.now());

        return diarioRepository.save(diario);
    }
    
    @Override
    public Utente registraUtente(Utente utente) {
    	
        Optional<Utente> utenteTrovato = utenteRepository.findByUtente(utente.getUtente());
        
        
        if (utenteTrovato.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Utente già esistente");
        }

        utente.setRole("ROLE_USER");

        String passwordCifrata = BCrypt.hashpw(utente.getPassword(), BCrypt.gensalt());
        utente.setPassword(passwordCifrata);

        return utenteRepository.save(utente);
    }



    /*Metodi PUT Utente Service*/
    @Override
    public Optional<Diario> modificaTestoPerId(Diario diario, String id) {
    	
    	Optional<Diario> idTrovato = diarioRepository.findById(id);

    	if (idTrovato.isPresent()) {
    		Diario diarioEsistente = idTrovato.get();
    		diarioEsistente.setTesto(diario.getTesto());
    		diarioEsistente.setDataTesto(LocalDate.now());
    		return Optional.of(diarioRepository.save(diarioEsistente));
        }

        	throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Diario non trovato");
    	}
    

    /* Metodi DELETE Diario Service*/
    @Override
    public void eliminaTestoPerId(String Id) {
    	
    	if (diarioRepository.existsById(Id)) {
    		diarioRepository.deleteById(Id);
    		return;
    	} 
    	throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Testo non trovato");
    }

}

