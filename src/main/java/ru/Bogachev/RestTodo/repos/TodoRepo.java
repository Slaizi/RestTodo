package ru.Bogachev.RestTodo.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.Bogachev.RestTodo.entity.TodoEntity;

@Repository
public interface TodoRepo extends CrudRepository <TodoEntity, Long> {
}
