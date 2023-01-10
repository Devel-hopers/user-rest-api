package it.eforhum.spring.usersrestapi.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

//@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

	private static Logger log = LogManager.getLogger(JwtAuthenticationProvider.class);
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		log.info("try to authenticate input object");
		JwtAuthenticationToken jwtAuth = (JwtAuthenticationToken) authentication;

//		all'interno del Provider verifico che l'utente 
		
//		TODO gestione autenticazione errata in caso di token JWT non valido		
		
//		if (true) {
//			throw new JwtAuthenticationException("Invalid JWT Token");
//		}
		
		String username = "user1";
		String password = "";
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		authorities.add(new SimpleGrantedAuthority("ROLE_EDIT"));
		
		User user = new User(username, password, authorities);
		
		return new JwtAuthenticationToken(jwtAuth.getOriginalJwtToken(), user, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return JwtAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
