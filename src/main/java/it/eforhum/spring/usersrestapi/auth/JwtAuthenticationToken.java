package it.eforhum.spring.usersrestapi.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.Transient;

// serve a non far salvare in sessione il token
@Transient
public class JwtAuthenticationToken extends AbstractAuthenticationToken {

	private static final long serialVersionUID = 1L;

	private String originalJwtToken;
	private Object principal;
	
	public JwtAuthenticationToken(String originalJwtToken) {
		this(originalJwtToken, null, Collections.emptyList());
	}

	public JwtAuthenticationToken(String originalJwtToken, Object principal, Collection<GrantedAuthority> authorities) {
		super(authorities);
		this.originalJwtToken = originalJwtToken;
		this.principal = principal;
	}

	public String getOriginalJwtToken() {
		return originalJwtToken;
	}

	public Object getPrincipal() {
		return principal;
	}

	public void setPrincipal(Object principal) {
		this.principal = principal;
	}

	@Override
	public Object getCredentials() {
		return null;
	}
	
}
