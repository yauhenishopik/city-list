package com.helmes.citylist.config;

import com.helmes.citylist.adapter.in.security.SecurityContextMockRepository;
import com.helmes.citylist.config.properties.CityListSecurityProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity
@EnableConfigurationProperties(CityListSecurityProperties.class)
public class WebSecurityConfig {

  private final CityListSecurityProperties securityProperties;
  private final SecurityContextMockRepository securityContextRepository;

  @Bean
  public SecurityFilterChain securityWebFilterChain(HttpSecurity http) throws Exception {
    return http
        .exceptionHandling()
        .authenticationEntryPoint((request, response, authException) -> response.setStatus(401))
        .accessDeniedHandler((request, response, accessDeniedException) -> response.setStatus(403))
        .and()
        .csrf().disable()
        .formLogin().disable()
        .httpBasic().disable()
        .securityContext().securityContextRepository(securityContextRepository)
        .and()
        .authorizeRequests()
        .mvcMatchers(HttpMethod.OPTIONS).permitAll()
        .antMatchers(securityProperties.getPermittedPaths()).permitAll()
        .anyRequest().authenticated()
        .and().build();
  }
}
