package org.main.controllers;

import org.main.models.dao.TypeTodos;
import org.main.models.dto.ResponseSuccess;
import org.main.models.dto.UserDto;
import org.main.models.services.TypeTodosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/type")
public class TypeTodosController {
    @Autowired
    private TypeTodosService typeTodosService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getAllTypes() {
        try {
            List<UserDto> typeTodo = typeTodosService.getAllTypeTodos();
            ResponseSuccess res = new ResponseSuccess<>("Data Type Todo", 200, typeTodo);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }catch (Exception e){
                return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> createTypeTodos(@RequestBody TypeTodos typeTodos) {
        try {
            UserDto resultTypeInserted =  typeTodosService.saveTypeTodo(typeTodos);
            ResponseSuccess res = new ResponseSuccess<>("Data Type Todo", 200, resultTypeInserted);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
