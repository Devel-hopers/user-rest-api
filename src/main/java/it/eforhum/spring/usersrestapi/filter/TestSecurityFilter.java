package it.eforhum.spring.usersrestapi.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

//@Component
public class TestSecurityFilter extends OncePerRequestFilter {

	private static Logger log = LogManager.getLogger(TestSecurityFilter.class);
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		log.info("Start TEST filter");
		
		SecurityContext context = SecurityContextHolder.createEmptyContext(); 
		Authentication authentication =
		    new TestingAuthenticationToken("user1", "123", "ROLE_USER"); 
		context.setAuthentication(authentication);

		SecurityContextHolder.setContext(context);
		
		filterChain.doFilter(request, response);
		
		log.info("Finished TEST filter");
	}

}
