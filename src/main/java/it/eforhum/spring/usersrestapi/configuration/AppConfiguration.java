package it.eforhum.spring.usersrestapi.configuration;

import javax.sql.DataSource;

import it.eforhum.spring.usersrestapi.filter.TestSecurityFilter;
import it.eforhum.spring.usersrestapi.service.CustomUserDetailsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import it.eforhum.spring.usersrestapi.auth.JwtAuthenticationFilter;
import it.eforhum.spring.usersrestapi.auth.JwtAuthenticationProvider;

@Configuration
@EnableGlobalAuthentication
public class AppConfiguration {

	private static Logger log = LogManager.getLogger(AppConfiguration.class);
	
//	@Autowired
//	private TestSecurityFilter testFilter;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthFilter;
	
//	@Autowired
//	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
		log.info("securityFilterChain");
		
//		disabilito la gestione della sessione
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		LoginUrlAuthenticationEntryPoint entryPoint = new LoginUrlAuthenticationEntryPoint("/auth/forbidden");
		entryPoint.setUseForward(true);
		
//		disabilito il controllo CSRF e chiedo di autenticare tutte le API esposte
		http.csrf().disable()
			.authorizeRequests()
//				Accetto tutte le richieste in get
			.antMatchers(HttpMethod.GET, "/auth/genpass")
			.permitAll()
			.anyRequest().authenticated()
			.and()
			.exceptionHandling().authenticationEntryPoint(entryPoint)
			.and()
//			serve ad abilitare il filtro UsernamePasswordAuthenticationFilter
//			e permettere di inviare le credenziali al path "/login"
			.formLogin().successForwardUrl("/auth/login");
		
//		http.addFilterBefore(this.testFilter, UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(this.jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//
//		il provider non può essere un Bean altrimenti viene sovrascritto quello di Spring
		http.authenticationProvider(new JwtAuthenticationProvider());
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passEncoder() {
		return new BCryptPasswordEncoder();
	}

//	opzione 1
//	I token vengono salvati in memoria
//	@Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user = User.withUsername("erfascio")
//				.password(passEncoder().encode("juventus"))
//				.roles("USER")
//				.build();
//        return new InMemoryUserDetailsManager(user);
//    }
	
//	opzione 2
//	I token vengono salvati in memoria
//	@Bean
//	public JdbcDaoImpl userDetailsService() {
//		JdbcDaoImpl jdbcDaoImpl = new JdbcDaoImpl();
//		jdbcDaoImpl.setDataSource(this.dataSource);
//		return jdbcDaoImpl;
//	}
	
//	opzione 3
//	creare un proprio Custom Bean che implementa l'interfaccia UserDetailsService
}
