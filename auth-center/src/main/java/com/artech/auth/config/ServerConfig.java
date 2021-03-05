package com.artech.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;

/**
 * @Description: OAuth2.0 Server 配置
 */
@Configuration
@EnableAuthorizationServer
public class ServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Autowired
    @Qualifier("customUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Autowired
    private JwtTokenStore jwtTokenStore;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    /**
     *  配置 AuthorizationServer 安全认证的相关信息，创建 ClientCredentialsTokenEndpointFilter 核心过滤器
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 配置 token 获取和验证时的策略
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")//; //isAuthenticated():排除anonymous   isFullyAuthenticated():排除anonymous以及remember-me
                .allowFormAuthenticationForClients(); //允许表单认证  这段代码在授权码模式下会导致无法根据code　获取token　

                // .realm(resource_id) 指定哪些资源是需要授权验证的
    }

    /**
     * 配置 OAuth2 的客户端相关信息
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        /*clients.inMemory()
                .withClient("personal")
                // secret密码配置从 Spring Security 5.0开始必须以 {加密方式}+加密后的密码 这种格式填写
                *//*
                 *   当前版本5新增支持加密方式：
                 *   bcrypt - BCryptPasswordEncoder (Also used for encoding)
                 *   ldap - LdapShaPasswordEncoder
                 *   MD4 - Md4PasswordEncoder
                 *   MD5 - new MessageDigestPasswordEncoder("MD5")
                 *   noop - NoOpPasswordEncoder
                 *   pbkdf2 - Pbkdf2PasswordEncoder
                 *   scrypt - SCryptPasswordEncoder
                 *   SHA-1 - new MessageDigestPasswordEncoder("SHA-1")
                 *   SHA-256 - new MessageDigestPasswordEncoder("SHA-256")
                 *   sha256 - StandardPasswordEncoder
                 *//*
                .secret("{noop}secret")
                .scopes("all")
                .authorizedGrantTypes("authorization_code", "password", "refresh_token")
                .autoApprove(true);*/
        clients.jdbc(dataSource);
//        clients.inMemory()
//                .withClient("personal")//客户端ID
//                .secret(passwordEncoder.encode("secret"))//Spring Security 5.0 以上版本需要加密密码
//                .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit") //允许授权类型
//                .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT") //客户端可以使用的权限
//                .scopes("all")//授权用户的操作权限 ("read", "write", "trust")。允许授权范围
//                .accessTokenValiditySeconds(60*60*24)//token有效期为24小时
//                .refreshTokenValiditySeconds(600) //刷新token有效期为600秒
//                .autoApprove(true); // 为true 则不会被重定向到授权的页面，也不需要手动给请求授权,直接自动授权成功返回code
//                //.redirectUris("http://localhost:18080/", "http://localhost:18080/login", "http://localhost:18080/user", "http://localhost:18082", "http://localhost:18082/login", "http://localhost:18082/user", "http://www.example.com/");  //指定可以接受令牌和授权码的重定向URIs

    }

    /**
     * 配置 AuthorizationServerEndpointsConfigurer 众多相关类，包括配置身份认证器，
     * 配置认证方式，TokenStore，TokenGranter，OAuth2RequestFactory
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 配置 tokenStore，保存到 redis 缓存中
        endpoints.authenticationManager(authenticationManager)
                .tokenStore(jwtTokenStore).accessTokenConverter(jwtAccessTokenConverter)
                //.tokenStore(new CustomRedisTokenStore(redisConnectionFactory))
                // 不添加 userDetailsService，刷新 access_token 时会报错(无法加载用户信息)
                .userDetailsService(userDetailsService);

//        .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST,HttpMethod.OPTIONS)  //支持GET  POST  请求获取token
//        .reuseRefreshTokens(true) //开启刷新token
//        .tokenServices(tokenServices());
        // 使用最基本的 InMemoryTokenStore 生成 token
        //endpoints.authenticationManager(authenticationManager).tokenStore(memoryTokenStore());


    }

    // 使用最基本的InMemoryTokenStore生成token
//    @Bean
//    public TokenStore memoryTokenStore() {
//        return new InMemoryTokenStore();
//    }
}
