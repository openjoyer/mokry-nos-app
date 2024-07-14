package org.morkiy_nos.mokriy_nos_app.service;

import org.morkiy_nos.mokriy_nos_app.model.User;
import org.morkiy_nos.mokriy_nos_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
