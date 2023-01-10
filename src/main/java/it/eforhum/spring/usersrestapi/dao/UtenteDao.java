package it.eforhum.spring.usersrestapi.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.eforhum.spring.usersrestapi.entity.Utente;

@Repository
public interface UtenteDao extends CrudRepository<Utente, Integer> {

}
