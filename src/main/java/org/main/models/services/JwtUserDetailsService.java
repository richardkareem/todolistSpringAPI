package org.main.models.services;



import org.main.models.dao.Users;
import org.main.models.repositorys.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class JwtUserDetailsService implements UserDetailsService {

    public static final Logger logger = LoggerFactory.getLogger(JwtUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info(username); //kl masukin token nanti hasilnya gini  Creating trans with username(cnth: rochard)
        Users user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new User(user.getUsername(), user.getPassword(),  new ArrayList<>());
    }







}