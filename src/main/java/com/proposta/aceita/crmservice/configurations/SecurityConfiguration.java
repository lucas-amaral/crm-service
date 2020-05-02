package com.proposta.aceita.crmservice.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

import static com.proposta.aceita.crmservice.entities.enums.Authority.ADMIN;
import static com.proposta.aceita.crmservice.entities.enums.Authority.SYSTEM;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final BCryptPasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
    private static final String[] OPENED_ENDPOINTS = { "/login", "/users" };

    @Configuration
    @Order(1)
    public static class InternalApiSecurityConfiguration extends WebSecurityConfigurerAdapter {
        @Value("${services.internal.username}")
        private String internalUsername;

        @Value("${services.internal.password}")
        private String internalPassword;

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {

            auth
                    .inMemoryAuthentication()
                    .passwordEncoder(PASSWORD_ENCODER)
                    .withUser(internalUsername)
                    .password(PASSWORD_ENCODER.encode(internalPassword))
                    .authorities(SYSTEM.name());
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http

                    .antMatcher("/internal/**")
                    .authorizeRequests()
                    .anyRequest()
                    .hasAuthority(SYSTEM.name())

                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.NEVER)

                    .and()
                    .httpBasic()
                    .and()
                    .csrf()
                    .disable();
        }
    }


    @Order(2)
    @Configuration
    public static class CrmSecurityConfiguration extends WebSecurityConfigurerAdapter {

        private final DataSource dataSource;

        public CrmSecurityConfiguration(DataSource dataSource) {
            this.dataSource = dataSource;
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {

            auth
                    .jdbcAuthentication()
                    .dataSource(dataSource)
                    .passwordEncoder(PASSWORD_ENCODER);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf()
                    .disable()

                    .authorizeRequests()
                    .antMatchers(HttpMethod.OPTIONS, "/**")
                    .permitAll()

                    .antMatchers(HttpMethod.POST, OPENED_ENDPOINTS)
                    .permitAll()

                    .anyRequest()
                    .hasAuthority(ADMIN.name())

                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)

                    .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout", HttpMethod.DELETE.name()))

                    .and()
                    .httpBasic();

        }
    }

}