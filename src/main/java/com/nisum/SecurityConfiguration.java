package com.nisum;

import com.nisum.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

                http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST,"/projects").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/projects/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/projects/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/employees/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/employees/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/employees/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/projects/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.GET,"/employees/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/").permitAll()
                .antMatchers("/authenticate").permitAll()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//                .and()
//                .httpBasic()
//                .and()
//                .formLogin();
    }

}
