package com.diario.demo.repository.diario;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.lang.String;
import java.util.List;

import com.diario.demo.model.Diario;

/*Repository Diario che estende interfaccia con metodi CRUD per MondoDB*/
@Repository
public interface IDiarioRepository extends MongoRepository<Diario, String> {
	 List<Diario> findByUserId(int userId);
	 Diario findTopByOrderByUserIdDesc();
}
