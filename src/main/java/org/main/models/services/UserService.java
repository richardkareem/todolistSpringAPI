package org.main.models.services;

import io.jsonwebtoken.Jwt;
import org.main.models.configs.JwtAuthenticationEntryPoint;
import org.main.models.configs.JwtRequestFilter;
import org.main.models.configs.JwtTokenUtil;
import org.main.models.dao.Users;
import org.main.models.dto.LoginRequest;
import org.main.models.dto.UserDto;
import org.main.models.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    PasswordEncoder bcryptEncoder;

    @Autowired
    JwtRequestFilter jwtRequestFilter;

    public void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    public String loginCheck(LoginRequest loginRequest)  {

        try {
            Users user =  userRepository.findByUsername(loginRequest.getUsername());

            if(user == null){
                throw new UsernameNotFoundException("USER_NOT_FOUND");
            }

            authenticate(user.getUsername(), loginRequest.getPassword());
            final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());

            String token = jwtTokenUtil.generateToken(userDetails);
            return token;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public List<UserDto> getALl(){
       List<Users> user =  userRepository.findAll();
       List<UserDto> userDtos = new ArrayList<>();
        for(Users u : user){
            System.out.println(u.getName());
            UserDto userDto = new UserDto(u.getId(), u.getName());
            userDtos.add(userDto);
        }

        return  userDtos;
    }

    public Users saveUser(Users user){
        if(user.getName().isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        boolean pw = Boolean.parseBoolean(String.valueOf(Pattern.matches(
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", user.getPassword())));

        if(!pw){
            throw new IllegalArgumentException("At least 8 length character with uppercase, number and symbol");
        }

        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        userRepository.save(user);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        user.setPassword(token);
        return  user;
    }

    public Users getUserById(int id){
        Users users = userRepository.findById(id).orElse(null);
        if(users == null){
            throw new IllegalArgumentException("User not found");
        }
        if(users.getName().isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        return  users;
    }

    public Users getUserByUsername(String username){
        Users users = userRepository.findByUsername(username);
        return  users;
    }
    public void getUserByToken(HttpServletRequest request){
        jwtRequestFilter.getUserByToken(request);
    }
}
