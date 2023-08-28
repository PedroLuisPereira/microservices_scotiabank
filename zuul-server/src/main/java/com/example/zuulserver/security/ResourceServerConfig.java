package com.example.zuulserver.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private Logger logger = LoggerFactory.getLogger(ResourceServerConfig.class);

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        // super.configure(resources);
        resources.tokenStore(jwtTokenStore());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // super.configure(http);
        http
                .authorizeRequests()
                .antMatchers("/api/auth/oauth/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/books/**", "api/ratings/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("api/**").hasRole("ADMIN")
                .anyRequest().authenticated();

    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {

        logger.info("Entro a JwtAccessTokenConverter");

        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("clave123456789");
        return jwtAccessTokenConverter;
    }

    @Bean
    public JwtTokenStore jwtTokenStore() {

        logger.info("Entro a JwtTokenStore");

        return new JwtTokenStore(jwtAccessTokenConverter());
    }
}
