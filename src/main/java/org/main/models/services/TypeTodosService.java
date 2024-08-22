package org.main.models.services;

import org.main.models.dao.TypeTodos;
import org.main.models.dto.UserDto;
import org.main.models.repositorys.TypeTodosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeTodosService {
@Autowired
    TypeTodosRepository typeTodosRepository;

    public UserDto  saveTypeTodo (TypeTodos todos){
        TypeTodos result = typeTodosRepository.save(todos);
        if(result.getName().isEmpty()){
            throw new Error("Name cannot be empty");
        }
        UserDto typeTodosDto = new UserDto(result.getId(), result.getName());
        return typeTodosDto;
    }
    public List<UserDto> getAllTypeTodos(){
        List<TypeTodos> typeTodos = typeTodosRepository.findAll();
        List<UserDto> typeTodosDto = new ArrayList<UserDto>();
        for(TypeTodos todos : typeTodos){
            typeTodosDto.add(new UserDto(todos.getId(), todos.getName()));
        }
        return typeTodosDto;
    }

    public UserDto findTypeById(int id){
        TypeTodos currentData =  typeTodosRepository.findFirstById(id);
        if(currentData == null){
            throw new Error("Type todos not found");
        }
        UserDto newData = new UserDto(currentData.getId(), currentData.getName());
        return newData;
    }

}
