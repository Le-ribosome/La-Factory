package fr.projet.lafactory.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import fr.projet.lafactory.component.MyAccessDeniedHandler;
import fr.projet.service.CustomUserDetailsService;


@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired 
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private MyAccessDeniedHandler accessDeniedHandler;
	
//Méthode 1 : définit les pages sécurisées et non sécurisées 	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().authorizeRequests().antMatchers(HttpMethod.OPTIONS).anonymous();


		http.authorizeRequests().antMatchers("/rest/**").authenticated()
		.and().httpBasic().and().csrf().disable();
	
	}
	
	//pour encrypter un mdp il faut un objet de type password encoder
	@Bean	
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder(); // à appeler au moment de l'authetification
	}
}
