package org.main.controllers;

import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bind.annotation.Origin;
import org.main.models.dao.Users;
import org.main.models.dto.ResponseSuccess;
import org.main.models.dto.UserDto;
import org.main.models.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin( "*")
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> postSaveUser (@RequestBody Users user) throws SQLException, ClassNotFoundException {
        try{
            Users savedUser =  userService.saveUser(user);
            ResponseSuccess response = new ResponseSuccess<>("Berhasil Menambahkan Data", 200, savedUser);
            return  new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getAllUsers () throws SQLException, ClassNotFoundException {
        try {
            List<UserDto> users = userService.getALl();
            ResponseSuccess response = new ResponseSuccess<>("Berhasil Mendapatkan User", 200, users);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
