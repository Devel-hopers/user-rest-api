package it.eforhum.spring.usersrestapi.controller;

import it.eforhum.spring.usersrestapi.entity.Utente;
import it.eforhum.spring.usersrestapi.service.UtenteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
@CrossOrigin
public class UtenteRESTController {
	
	private static final Logger log = LogManager.getLogger(UtenteRESTController.class);
	
	@Autowired
	private UtenteService service;
	
	@GetMapping
	public List<Utente> findAll() {
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
		log.info(result);
		return result;
	}
	
	@GetMapping(value = "/{id}")
	public Utente findById(@PathVariable("id") int id) {
		return this.service.findById(id);
	}
	
	@PutMapping(value = "/{id}")
	public Utente update(@PathVariable("id") int id, @RequestBody Utente utente) {
		// richiamare il servizio per aggiornare su DB la entity
		Utente userUpdated = this.service.update(id, utente);
		return userUpdated;
	}

}
