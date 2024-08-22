package org.main.models.repositorys;


import org.main.models.dao.TypeTodos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeTodosRepository extends JpaRepository<TypeTodos, Integer> {
    TypeTodos findFirstById(int id);
}
