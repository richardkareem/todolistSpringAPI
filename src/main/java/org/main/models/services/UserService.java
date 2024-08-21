package org.main.models.services;

import org.main.models.dao.Users;
import org.main.models.dto.UserDto;
import org.main.models.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<UserDto> getALl(){
       List<Users> user =  userRepository.findAll();
       List<UserDto> userDtos = new ArrayList<>();
        for(Users u : user){
            UserDto userDto = new UserDto(u.getId(), u.getName());
            userDtos.add(userDto);
        }

        return  userDtos;
    }

    public Users saveUser(Users user){
        if(user.getName().isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        return userRepository.save(user);
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
}
