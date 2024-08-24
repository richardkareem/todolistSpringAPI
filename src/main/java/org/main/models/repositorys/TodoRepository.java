package org.main.models.repositorys;

import org.main.models.dao.Todos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todos, Integer> {
//    List<Todos> findTodoByIdUser(Integer idUser);
    public  Todos findFirstById(int id);
    public List<Todos> findAllByisDeletedFalse();
//    public List<Todos> findAllByIs
}
