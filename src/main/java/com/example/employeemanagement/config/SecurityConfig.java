package com.example.employeemanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import com.example.employeemanagement.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@EnableWebSecurity
@Configuration
public class SecurityConfig  {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/login", "/register").permitAll()
						.requestMatchers("/employees/**").hasAnyRole("ADMIN", "HR", "MANAGER")
						.anyRequest().authenticated()
				)
				.formLogin(form -> form
						.defaultSuccessUrl("/employees", true)
						.permitAll()
				)
				.logout(logout -> logout
						.logoutUrl("/logout")
						.logoutSuccessUrl("/login?logout=true")
						.permitAll()
				)
				.httpBasic(httpBasic -> httpBasic.init(http));

		return http.build();
	}

	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails admin = User.builder()
				.username("admin")
				.password(passwordEncoder().encode("adminpassword"))
				.roles("ADMIN")
				.build();
		UserDetails hr = User.builder()
				.username("hr")
				.password(passwordEncoder().encode("hrpassword"))
				.roles("HR")
				.build();
		UserDetails manager = User.builder()
				.username("manager")
				.password(passwordEncoder().encode("managerpassword"))
				.roles("MANAGER")
				.department("IT")
				.build();

        List<UserDetails> userDetails = new ArrayList<>(List.of(admin, hr, manager));

		return new InMemoryUserDetailsManager(userDetails);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public MethodSecurityExpressionHandler expressionHandler() {
        return new DefaultMethodSecurityExpressionHandler();
	}
}

