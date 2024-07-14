package org.morkiy_nos.mokriy_nos_app.service;

import org.morkiy_nos.mokriy_nos_app.model.User;
import org.morkiy_nos.mokriy_nos_app.security.AppUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optional = userService.getByUsername(username);
        if(optional.isEmpty()) {
            throw new UsernameNotFoundException("Пользователь " + username + " не найден");
        }
        return new AppUserDetails(optional.get());
    }
}
