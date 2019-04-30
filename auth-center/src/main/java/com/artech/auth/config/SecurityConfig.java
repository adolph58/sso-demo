package com.artech.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Description: Spring Security 配置
 */
@Configuration
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("customUserDetailsService")
    private UserDetailsService userDetailsService;

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    /**
     * 这一步的配置是必不可少的，否则 SpringBoot 会自动配置一个 AuthenticationManager,覆盖掉内存中的用户
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 添加 UserDetailsService， 实现自定义登录校验
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
                //.passwordEncoder(passwordEncoder());
        //remember me
        //auth.eraseCredentials(false);
    }

    /**
     * 匹配 "/" 路径，不需要权限即可访问
     * 匹配 "/user" 及其以下所有路径，都需要 "USER" 权限
     * 登录地址为 "/login"，登录成功默认跳转到页面 "/user"
     * 退出登录的地址为 "/logout"，退出成功后跳转到页面 "/login"
     * 默认启用 CSRF
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/css/**", "/js/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout().logoutUrl("/logout").permitAll()
                .logoutSuccessUrl("/login")
                //.and().rememberMe().tokenValiditySeconds(86400)
                .and()
                // 暂时禁用CSRF，否则无法提交登录表单
                .csrf().disable();

//        http.formLogin().loginPage("/login").permitAll().successHandler(loginSuccessHandler())
//                .and().authorizeRequests()
//                .antMatchers("/", "/oauth/token", "/oauth/authorize", "/oauth/check_token", "/login", "/**/*.js", "/**/*.css").permitAll()
//                .anyRequest().authenticated()
//                .and().rememberMe().tokenValiditySeconds(86400)
//                .and().csrf().disable(); // 暂时禁用CSRF，否则无法提交登录表单
                // 自动登录
                /*.and()
                    .rememberMe()
                    // 加密的秘钥
                    .key("unique-and-secret")
                    // 存放在浏览器端cookie的key
                    .rememberMeCookieName("remember-me-cookie-name")
                    // token失效的时间，单位为秒
                    .tokenValiditySeconds(60 * 60 * 25)*/



//        http.authorizeRequests().anyRequest().authenticated() // 所有请求都得经过认证和授权
//                .antMatchers("/").permitAll()
//                //.antMatchers("/user/**").hasRole("USER")
//                //.antMatchers("/**/*.js", "/**/*.css", "/images/**", "/checkcode", "/scripts/**", "/styles/**").permitAll()
//                .antMatchers("/oauth/token").permitAll()
//                .and()
//                .formLogin().loginPage("/login").permitAll()
//                .and()
//                //.logout().logoutUrl("/logout") // 退出的URL是/logout
//                //.logoutSuccessUrl("/login") // 退出成功后，跳转到/路径。
//                //.and()
//                // 暂时禁用CSRF，否则无法提交登录表单
//                // 这里之所以要禁用csrf，是为了方便。
//                // 否则，退出链接必须要发送一个post请求，请求还得带csrf token
//                // 那样我还得写一个界面，发送post请求
//                .csrf().disable()
//                .rememberMe().tokenValiditySeconds(86400); //24小时
//                // 自动登录
//                /*.and()
//                    .rememberMe()
//                    // 加密的秘钥
//                    .key("unique-and-secret")
//                    // 存放在浏览器端cookie的key
//                    .rememberMeCookieName("remember-me-cookie-name")
//                    // token失效的时间，单位为秒
//                    .tokenValiditySeconds(60 * 60 * 25)*/


    }

//    @Bean
//    public LoginSuccessHandler loginSuccessHandler(){
//        return new LoginSuccessHandler();
//    }

}
