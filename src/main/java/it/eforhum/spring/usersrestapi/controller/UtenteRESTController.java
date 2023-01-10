package it.eforhum.spring.usersrestapi.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.eforhum.spring.usersrestapi.entity.Utente;
import it.eforhum.spring.usersrestapi.service.UtenteService;

@RestController
@RequestMapping(value = "/api/users")
@CrossOrigin
public class UtenteRESTController {
	
	private static Logger log = LogManager.getLogger(UtenteRESTController.class);
	
	@Autowired
	private UtenteService service;
	
	@GetMapping(value = "/")
	public List<Utente> findAll() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		log.info(authentication.getPrincipal());
		log.info(authentication.getCredentials());
		
		List<Utente> result = new ArrayList<>();
		
		Utente utente1 = new Utente();
		utente1.setId(1);
		utente1.setUsername("test1@gmail.com");
		utente1.setBornDate(LocalDate.of(2000, 10, 4));
		utente1.setRoles("ROLE_USER;ROLE_ADMIN");
		result.add(utente1);

		Utente utente2 = new Utente();
		utente2.setId(2);
		utente2.setUsername("test2@gmail.com");
		utente2.setBornDate(LocalDate.of(1998, 5, 16));
		utente2.setRoles("ROLE_USER;ROLE_READONLY");
		result.add(utente2);
		
		return result;
	}
	
	@GetMapping(value = "/{id}")
	public Utente findById(@PathVariable("id") int id) {
		
		return this.service.findById(id);
	}
	
	@PutMapping(value = "/{id}")
	public Utente update(@PathVariable("id") int id, @RequestBody Utente utente) {
		
		// richiamare il servizio per aggiornare su DB la entity
		Utente result = this.service.update(id, utente);
		
		return result;
	}

}
