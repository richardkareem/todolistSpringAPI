package org.main.controllers;

import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bind.annotation.Origin;
import org.main.models.dao.Users;
import org.main.models.dto.LoginRequest;
import org.main.models.dto.ResponseSuccess;
import org.main.models.dto.UserDto;
import org.main.models.dto.UserResponseRegister;
import org.main.models.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
@CrossOrigin( "*")
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;


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

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> postSaveUser (@RequestBody Users user) throws SQLException, ClassNotFoundException {
        try{
            Users savedUser =  userService.saveUser(user);

            UserResponseRegister res = new UserResponseRegister(savedUser.getName(), savedUser.getUsername(),savedUser.getPassword());
            ResponseSuccess response = new ResponseSuccess<>("Berhasil Register", 200, res);
            return  new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception error) {
            return new ResponseEntity<>(error.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> login (@RequestBody LoginRequest userLogin) throws SQLException, ClassNotFoundException {
        try{
            String token = userService.loginCheck(userLogin);
            Users user = userService.getUserByUsername(userLogin.getUsername());
            UserResponseRegister responseUser = new UserResponseRegister(user.getName(), user.getPassword(), token);
            ResponseSuccess response = new ResponseSuccess<>("Berhasil Login", 200, responseUser);
            return  new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            ResponseSuccess response = new ResponseSuccess<>("Password or Username incorrect", 400, null);

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


}
