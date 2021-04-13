package com.trec.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.trec.security.jwt.JwtRequestFilter;

@Configuration
@Order(1)
public class RestSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
    
	//Expose AuthenticationManager as a Bean to be used in other services
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	http.antMatcher("/api/**");
    	
    	// URLs that need authentication to access to it
    	http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/dishes/**").hasRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/dishes/**").hasRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/dishes/**").hasRole("ADMIN");	
        
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/ingredient/**").hasRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/ingredient/**").hasRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/ingredient/**").hasRole("ADMIN");

        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/users/").hasRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/users/me").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/users/{id}").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/users/{id}").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/users/{id}/dishes").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/users/{id}/purchases").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/users/{id}/newPurchase").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/users/{id}/newPurchase").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/users/{id}/newPurchase").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/users/{iduser}/newPurchase/dishes/{iddish}").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/users/{id}/image").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/users/{id}/image").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/users/{id}/image").hasRole("USER");
    	
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/purchases/{id}").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/purchases/").hasRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/purchases/").hasRole("ADMIN");
        
        // Other URLs can be accessed without authentication
        http.authorizeRequests().anyRequest().permitAll();

        // Disable CSRF protection (it is difficult to implement in REST APIs)
     	http.csrf().disable();
        
        // Disable Http Basic Authentication
     	http.httpBasic().disable();

    		
        // Disable Form login Authentication
        http.formLogin().disable();

        // Avoid creating session (because every request has credentials) 
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
     // Add JWT Token filter
     	http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
