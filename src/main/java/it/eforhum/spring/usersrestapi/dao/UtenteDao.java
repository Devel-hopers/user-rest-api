package it.eforhum.spring.usersrestapi.dao;

import it.eforhum.spring.usersrestapi.entity.Utente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteDao extends CrudRepository<Utente, Integer> {

}
