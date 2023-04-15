package com.clone.daangnmarketserver.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.JdbcOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final JdbcTemplate jdbcTemplate;
  private final ClientRegistrationRepository clientRegistrationRepository;

  @Bean
  public OAuth2AuthorizedClientService oAuth2AuthorizedClientService() {
    return new JdbcOAuth2AuthorizedClientService(jdbcTemplate, clientRegistrationRepository);
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.oauth2Login()
      .authorizedClientService(oAuth2AuthorizedClientService());

    http.authorizeHttpRequests()
      .anyRequest().authenticated();

    return http.build();
  }
}