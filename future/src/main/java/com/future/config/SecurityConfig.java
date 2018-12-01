package com.future.config;

import com.future.services.MongoUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Bean;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    MongoUserDetailService userDetailService;
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/admin/**").hasRole("admin")
                .antMatchers("/user/**").hasRole("manager")
                .anyRequest().authenticated()
                .antMatchers("/resources/**").permitAll().anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
//                .failureUrl("/login.html?error=true")
                .permitAll()
                .and()
                .logout()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
        http.httpBasic();
        http.csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }


    @Autowired
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder()).and()
                .inMemoryAuthentication()
                .withUser("habib").password("habib123").roles("admin").and()
                .withUser("robin").password("robin456").roles("manager");
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**");
    }

}












/*
@Configuration
@EnableConfigurationProperties
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    MongoUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic().and().sessionManagement().disable();
    }//.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic().and().sessionManagement().disable();.antMatchers("templates/login")

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService);
    }
}*/

