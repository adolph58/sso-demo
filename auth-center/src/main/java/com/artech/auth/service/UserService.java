package com.artech.auth.service;

import com.artech.domain.entity.User;
import com.artech.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 程江涛 on 2019/4/13.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public User findUserByLoginName(String loginName) {
        return userRepository.findByLoginName(loginName);
    }

//    public User addUser(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        return userRepository.save(user);
//    }
}
