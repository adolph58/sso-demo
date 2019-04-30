package com.artech.auth.service;

import com.artech.domain.entity.Role;
import com.artech.domain.entity.User;
import com.artech.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 程江涛 on 2019/4/13.
 *
 * 获取登录用户信息
 */

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLoginName(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名："+ username + "不存在！");
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        List<Role> roles = user.getRoles();
        if (roles != null) {
            for (Role role : roles ) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
                authorities.add(authority);
                System.out.println(role.getName());
            }
        }
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), authorities);
    }
}
