package it.eforhum.spring.usersrestapi.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.eforhum.spring.usersrestapi.dao.UtenteDao;
import it.eforhum.spring.usersrestapi.entity.Utente;

@Service
public class UtenteService {

	@Autowired
	private UtenteDao dao;
	
	public Utente findById(int id) {
		
		return this.dao.findById(id).orElseThrow(() -> new EntityNotFoundException());
	}
	
	public Utente update(int id, Utente utenteModified) {
		
		Utente entity = findById(id);
		
		entity.setUsername(utenteModified.getUsername());
		entity.setBornDate(utenteModified.getBornDate());
		
		entity = this.dao.save(entity);
		
		return entity;
	}
}
