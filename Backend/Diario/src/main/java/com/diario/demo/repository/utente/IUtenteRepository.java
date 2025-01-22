package com.diario.demo.repository.utente;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.diario.demo.model.Utente;

/*Repository Diario che estende interfaccia con metodi CRUD per PostgreSQL*/
@Repository
public interface IUtenteRepository extends CrudRepository<Utente, Integer> {
	Optional<Utente> findByUtente(String utente);
}
