package com.neasaa.security;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
 
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
         .authorizeRequests().anyRequest().authenticated()
         .and()
         .httpBasic();
        //.authenticationEntryPoint(getBasicAuthEntryPoint());
    }
  
//    @Bean
//    public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
//        return new CustomBasicAuthenticationEntryPoint();
//    }
    
    @Bean
    public ConfigBaseAuthenticator configBaseAuthenticator () {
    	return new ConfigBaseAuthenticator();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception
    {
        auth.authenticationProvider(configBaseAuthenticator());
    }
}