package ru.Bogachev.RestTodo.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.Bogachev.RestTodo.entity.UserEntity;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository <UserEntity, Long> {
    UserEntity findByUsername(String username);
    List<UserEntity> findAll ();
}
