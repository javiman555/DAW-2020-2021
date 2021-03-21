package com.trec.security;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	RepositoryUserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10, new SecureRandom());
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	// Public pages
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/register").permitAll();
        http.authorizeRequests().antMatchers("/logout").permitAll();
        http.authorizeRequests().antMatchers("/menu").permitAll();
        http.authorizeRequests().antMatchers("/dishes/{id}").permitAll();
        http.authorizeRequests().antMatchers("/dishes/{id}/image").permitAll();
        http.authorizeRequests().antMatchers("/newuser").permitAll();
        http.authorizeRequests().antMatchers("/index").permitAll();
        http.authorizeRequests().antMatchers("/team").permitAll();
        http.authorizeRequests().antMatchers("/contact").permitAll();

        // Private pages
        http.authorizeRequests().antMatchers("/login").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/purchase/*").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/profile/*").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/newdish").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/editdish/*").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/removedish/*").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/edituser").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/add_food").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/removeingredient/{id}").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/newingredient").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/paydone").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/paydone").not().hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/adddish").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/cart").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/cart").not().hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/purchases").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/pay").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/pay").not().hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/processpay").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/processpay").not().hasAnyRole("ADMIN");
        
        // Login form
        http.formLogin().loginPage("/login");
        http.formLogin().usernameParameter("username");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/login");
        http.formLogin().failureUrl("/loginerror");

        // Logout
        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/logout");
    }
}
