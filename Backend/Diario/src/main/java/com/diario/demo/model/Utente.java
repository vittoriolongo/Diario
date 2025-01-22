package com.diario.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/*Dichiarazione classe modello Utente con regole di validazione*/
@Entity
@Table(name = "utenti")
public class Utente {
	
	/*Attributi*/
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    
    @Column(name = "utente", unique = true)
    @NotBlank(message = "Il nomeutente non può essere vuoto o composto solo da spazi")
    private String utente;

    
    @Column(name = "password")
    @Size(min = 4, message = "La password non può contenere meno di 6 caratteri")
    private String password;

    
    @Column(name = "role")
    private String role;
    
    

    /*Metodi getter e setter*/
    public String getRole() {
        return role;
    }
    

    public void setRole(String role) {
        this.role = role;
    }

    
    public int getId() {
        return id;
    }
    

    public void setId(int id) {
        this.id = id;
    }
    

    public String getUtente() {
        return utente;
    }
    

    public void setUtente(String utente) {
        this.utente = utente;
    }
    

    public String getPassword() {
        return password;
    }
    

    public void setPassword(String password) {
        this.password = password;
    }
}
