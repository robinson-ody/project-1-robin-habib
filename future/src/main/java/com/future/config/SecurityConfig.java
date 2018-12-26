/*
package com.future.config;

import com.future.services.MongoUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Bean;
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    MongoUserDetailService userDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasAnyRole("admin")
                .antMatchers("/user/**").hasAnyRole("manager")
                .antMatchers("http://localhost:3000").hasAnyRole("manager")
                .anyRequest().authenticated()
                .antMatchers("/resources/**").permitAll().anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                .and()
                .exceptionHandling();
        }
        @Bean
        public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;}

        @Autowired
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder()).and()
                    .inMemoryAuthentication()
                    .withUser("habib").password("habib123").roles("admin").and()
                    .withUser("robin").password("robin456").roles("manager");
    }

}*/
