package com.autodeli.config;

import com.autodeli.service.user.UserService;
import com.autodeli.utils.FilterChainExceptionHandlerFilter;
import com.autodeli.utils.JwtAuthenticationEntryPoint;
import com.autodeli.utils.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

import static com.autodeli.web.user.Role.ADMIN;
import static com.autodeli.web.user.Role.USER;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.HttpMethod.DELETE;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
  @Autowired
  private JwtAuthenticationFilter jwtAuthenticationFilter;

  @Autowired
  private FilterChainExceptionHandlerFilter filterChainExceptionHandlerFilter;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable()
        .authorizeRequests()
        .antMatchers(POST, "/api/login", "/api/register").permitAll()
        .antMatchers(GET, "/api/recipes/**").permitAll()
        .antMatchers(POST, "/api/recipes").hasAnyRole(USER.toString(), ADMIN.toString())
        .antMatchers(PUT, "/api/recipes").hasAnyRole(USER.toString(), ADMIN.toString())
        .antMatchers(DELETE, "/api/recipes").hasAnyRole(USER.toString(), ADMIN.toString())
        .antMatchers("/api/users/**").hasRole(ADMIN.toString())
        .antMatchers("/**").permitAll()
        .and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    http.addFilterBefore(filterChainExceptionHandlerFilter, LogoutFilter.class);
  }

  @Bean
  public UserDetailsService getUserDetailsService(UserService userService) {
    return userService::getUserByUsername;
  }

  @Bean
  public AuthenticationManager getAuthenticationManager() throws Exception {
    return super.authenticationManager();
  }
}
