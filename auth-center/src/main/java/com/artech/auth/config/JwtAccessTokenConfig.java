package com.artech.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author Arte
 * @create 21-3-5
 */
@Configuration
public class JwtAccessTokenConfig {

    /**
     * jwt Spring Security token 转换
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        // 签名
        accessTokenConverter.setSigningKey("jwt-token-sign");
        return accessTokenConverter;
    }

    @Bean
    public JwtTokenStore jwtTokenStore () {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }
}
