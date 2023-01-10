package it.eforhum.spring.usersrestapi.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private Logger log = LogManager.getLogger(JwtAuthenticationFilter.class);
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.info("START JwtAuthenticationFilter");
		final String requestTokenHeader = request.getHeader("Authorization");
		// JWT Token is in the form "Bearer token". Remove Bearer word and get
		// only the Token
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			SecurityContext context = SecurityContextHolder.createEmptyContext();
//			creo l'oggetto che rappresenta l'utente autenticato
			Authentication authentication = new JwtAuthenticationToken("jwt-token-content"); 
			context.setAuthentication(authentication);
//			inserisco nel SecurityContext l'oggetto che rappresente l'utente autenticato
			SecurityContextHolder.setContext(context);
		}
//		!important
		filterChain.doFilter(request, response);
		log.info("Finished JwtAuthenticationFilter");
	}

}
