package com.example.userregistrationsystem.config;

import org.springframework.context.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final UserDetailsService usrDetailsService;

	public SecurityConfig(UserDetailsService usrDetailsService) {
		this.usrDetailsService = usrDetailsService;
	}

	@Bean
	public static PasswordEncoder passEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filter(HttpSecurity http) throws Exception {
		return http
				.csrf(c -> c.disable())
				.authorizeHttpRequests(
					a -> a.requestMatchers("/register/**").permitAll()
						.requestMatchers("/","/index").authenticated()
						.requestMatchers("/users").hasRole("ADMIN")
				)
				.formLogin(
					f -> f.loginPage("/login")
						.loginProcessingUrl("/login")
						.defaultSuccessUrl("/index")
						.permitAll()
				)
				.logout(
					l -> l.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
						.permitAll()
				)
				.build();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usrDetailsService).passwordEncoder(passEncoder());
	}
}
