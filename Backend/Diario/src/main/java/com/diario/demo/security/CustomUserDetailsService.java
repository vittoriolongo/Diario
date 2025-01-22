package com.diario.demo.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;



import com.diario.demo.model.Utente;
import com.diario.demo.repository.utente.IUtenteRepository;

/*Service CustomerDEtails che si occupa del caricamento in sessione e auteticazione utenti */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final IUtenteRepository utenteRepository;
    

    public CustomUserDetailsService(IUtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }
    
    
    /* Metodo per codificare le password usando BCrypt */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    
    /* Metodo che carica l'utente dal database e restituisce i dettagli per l'autenticazione, inclusi i ruoli */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
        Utente utente = utenteRepository.findByUtente(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " non trovato"));

        List<GrantedAuthority> authorities = new ArrayList<>();
        if ("ROLE_USER".equals(utente.getRole())) {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        } else if ("ROLE_ADMIN".equals(utente.getRole())) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return new org.springframework.security.core.userdetails.User(
                utente.getUtente(),
                utente.getPassword(),
                authorities
        );
    }

    
    /* Metodo che restituisce l'ID dell'utente attualmente autenticato */
    public int getAuthenticatedUserId() {
    	
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); 


        Utente utente = utenteRepository.findByUtente(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato"));

        return utente.getId(); 
    }

}