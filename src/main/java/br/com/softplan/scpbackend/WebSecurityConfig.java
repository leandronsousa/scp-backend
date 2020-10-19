package br.com.softplan.scpbackend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Classe responsavel pelas configs de seguranca
 * 
 * @author leandro
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	/* (non-Javadoc)
	 * Desabilitados csrf
	 * Definidos metodos HTTP permitidos
	 * basic Auth
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests().antMatchers("/source").permitAll()
			.and().authorizeRequests().antMatchers("/swagger-ui", "/swagger-ui/**").permitAll()
			.and().authorizeRequests().antMatchers("/pessoas").authenticated().and().httpBasic();
	}

	/* (non-Javadoc)
	 * Configura usuario pre existente para autenticacao
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().passwordEncoder(passwordEncoder()).withUser("leandro").password("123456").roles("ADMIN");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
