package com.diario.demo.model;

import java.time.LocalDate;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/*Dichiarazione classe modello Diario*/
@Document(collection = "testi")
public class Diario {	
	
	/*Attributi*/
	@Id
	private String id;
	private int userId;
	private String testo;
	private LocalDate dataTesto;
	
	
	/*Metodo costruttore*/
    public Diario(String id, int userId, String testo, LocalDate dataTesto) {
        this.id = id;
        this.userId = userId;
        this.testo = testo;
        this.dataTesto = dataTesto;
    }
	
    
	/*Metodi getter e setter*/
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getTesto() {
		return testo;
	}
	public void setTesto(String testo) {
		this.testo = testo;
	}
	public LocalDate getDataTesto() {
		return dataTesto;
	}
	public void setDataTesto(LocalDate dataTesto) {
		this.dataTesto = dataTesto;
	}
	

}
