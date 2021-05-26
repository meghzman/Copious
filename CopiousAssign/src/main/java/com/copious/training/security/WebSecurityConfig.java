package com.copious.training.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    protected static final String[] AUTH_WHITELIST = {
            "/assignment1/v2/api-docs",
            "/assignment1/swagger/resources",
            "/assignment1/swagger/resources/**",
            "/assignment1/configuration/ui",
            "/assignment1/configuration/security",
            "/assignment1/swagger-ui.html",
            "/assignment1/swagger.json",
            "/assignment1/web0jars/**",
            "/assignment1/webjars/**",
            "/swagger-ui.html",
            "/swagger-ui.html/**",
            "CopiousAssign/swagger-ui.html",
            "/user/signup",
            "/login"
    };

    @Autowired
    AuthenticationFilter filter;

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .headers().frameOptions().deny()
                .cacheControl().disable()
                .httpStrictTransportSecurity().and().xssProtection().block(false);
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(AUTH_WHITELIST);
    }
}
/*
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/user/getall",
                "/user/login",
                "/webjars/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()

                .withUser("namhm")
                .password("{noop}...")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("...")
                .roles("ADMIN")
        ;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable().authorizeRequests().antMatchers("/login/**").permitAll().anyRequest()
                .authenticated().and().exceptionHandling().and().sessionManagement();


        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }*/
/*
    private final SignUpService userDetailsService;


    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurityConfig(SignUpService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/user/getall",
                "/user/login",
                "/webjars/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

      //  auth.userDetailsService(userDetailsService.loadUserByUsername("",)).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL)
                .permitAll().anyRequest().authenticated();//.and().
                //addFilter(new AuthenticationFilter(authenticationManager()));

    }*/