package org.main.models.services;

import org.main.models.dao.Todos;
import org.main.models.dao.Users;
import org.main.models.dto.TodosDto;
import org.main.models.dto.TodosSaveDto;
import org.main.models.dto.UserDto;
import org.main.models.repositorys.TodoRepository;
import org.main.models.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TodoService {
    @Autowired
    TodoRepository todoRepository;
    @Autowired
    UserService userService;

    public TodosSaveDto saveTodo (TodosSaveDto todo){

        if(todo.getTitle().isEmpty()){
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if(todo.getDescription().isEmpty()){
            throw new IllegalArgumentException("Description cannot be empty");
        }
        if(todo.getId_user() == 0 ){
            throw new IllegalArgumentException("Need User Id");
        }
        todo.setCreatedAt(new Date());

        Users user = userService.getUserById(todo.getId_user());

        Todos DaoTodos = new Todos();
        DaoTodos.setTitle(todo.getTitle());
        DaoTodos.setCreatedAt(todo.getCreatedAt());
        DaoTodos.setUsers(user);
        DaoTodos.setDescription(todo.getDescription());
        Todos savedTodos =  todoRepository.save(DaoTodos);

        todo.setId(savedTodos.getId());
        return  todo;
    }

    public List<TodosDto> getAllTodos (){
        List<TodosDto> todosDto = new ArrayList<>();
        List<Todos> currentTodos = todoRepository.findAll();
        for(Todos current : currentTodos){
            TodosDto newTodos = new TodosDto(current.getId(), current.getTitle(), current.getDescription(), current.getCreatedAt(), current.getEndAt(), current.isCompleted(), current.getUsers().getName().isEmpty() ? null : current.getUsers().getName());
            todosDto.add(newTodos);
        }

        return  todosDto;
    }

    public List<TodosDto>getAllTodosById(int user_id){
        System.out.println(user_id);
        Users user = userService.getUserById(user_id);
        List<TodosDto> alltodos = getAllTodos();
        List<TodosDto> newTodos = new ArrayList<>();
        for(TodosDto t : alltodos){
            if(t.getUsername() == user.getName()){
                newTodos.add(t);
            }
        }

        return newTodos;
    }
}
