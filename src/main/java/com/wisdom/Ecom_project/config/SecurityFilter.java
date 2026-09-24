package com.wisdom.Ecom_project.config;

import com.wisdom.Ecom_project.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

  @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
    return http
            .csrf(customizer -> customizer.disable())
            .authorizeHttpRequests(request -> request
                    .requestMatchers("/register", "/login")
                    .permitAll()

                    .requestMatchers("/admin").hasRole("ADMIN")
                    .requestMatchers("/food-vendor").hasRole("FOOD_VENDOR")
                    .requestMatchers("/rider").hasRole("RIDER")
                    .requestMatchers("/dry-cleaner").hasRole("DRY_CLEANER")
                    .requestMatchers("/home-services").hasRole("HOME_SERVICES")

                    .requestMatchers("/wallet", "/orders").hasAllRoles("CUSTOMER", "ADMIN")
                    .anyRequest().authenticated())
            .httpBasic(Customizer.withDefaults())
            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtFilter ,UsernamePasswordAuthenticationFilter.class)
            .build();
  }
  @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
      DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
      provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
      return provider;

  }

  @Bean
    public AuthenticationManager  authenticationManager(AuthenticationConfiguration config){
      return  config.getAuthenticationManager();
   }
}