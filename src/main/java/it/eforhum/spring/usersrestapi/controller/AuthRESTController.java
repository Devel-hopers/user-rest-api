package it.eforhum.spring.usersrestapi.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.eforhum.spring.usersrestapi.util.JwtTokenUtil;

@RestController
@RequestMapping(value = "/auth")
@CrossOrigin
public class AuthRESTController {

	private static Logger log = LogManager.getLogger(AuthRESTController.class);
	
	@Autowired
	private JwtTokenUtil jwtUtil;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping(value = "/login")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
		
		log.info("Auth Controller");
		log.info("username: {}, password: {}", username, password);
		
		return this.jwtUtil.generateToken(username); 
	}
	
	@GetMapping(value = "/forbidden")
	public void loginPage(HttpServletResponse response) throws IOException {	
		
		response.sendError(HttpServletResponse.SC_FORBIDDEN);
	}
	
	@GetMapping(value = "/genpass")
	public String generatePassword(@RequestParam("p") String password) {
		
		return this.passwordEncoder.encode(password);
	}
}
