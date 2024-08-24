package org.main.controllers;
import org.main.models.dao.Todos;
import org.main.models.dto.ResponseSuccess;
import org.main.models.dto.TodosDto;
import org.main.models.dto.TodosSaveDto;
import org.main.models.repositorys.TodoRepository;
import org.main.models.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/todos")
public class TodosController {
    @Autowired
    TodoService todoService;
@Autowired
    TodoRepository todoRepository;
    @RequestMapping(value = "/{id_user}",method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getAllTodosById (
            @PathVariable("id_user") Integer id_user
    ){
        try {
            List<TodosDto> myTodos = todoService.getAllTodosById(id_user);
            ResponseSuccess response = new ResponseSuccess("Berhasil Mendapatkan Todos", 200, myTodos);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getALlTodos (){
        try {
            List<TodosDto> data = todoService.getAllTodos();
            ResponseSuccess response = new ResponseSuccess("Berhasil Mendapatkan Todos", 200, data);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }
    }


    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> postCreateTodo (@RequestBody TodosSaveDto todo){
        try {
            TodosSaveDto myTodos = todoService.saveTodo(todo);
            ResponseSuccess response = new ResponseSuccess("Berhasil Mendapatkan Todos", 200, myTodos);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_GATEWAY);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json", value = "/{id_todos}")
    public ResponseEntity<?> updateStatus (@PathVariable("id_todos") Integer id_todos){
        try {
            TodosDto myTodos = todoService.changeStatus(id_todos);
            ResponseSuccess response = new ResponseSuccess("Berhasil mengganti Status", 200, myTodos);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_GATEWAY);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, produces = "application/json", value = "/{id_todos}")
    public ResponseEntity<?> delete (@PathVariable("id_todos") Integer id_todos){
        try {
            todoService.deleteTodo(id_todos);
            ResponseSuccess response = new ResponseSuccess("Berhasil mengganti Status", 200, "Berhasil");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_GATEWAY);
        }
    }


}
