package ru.Bogachev.RestTodo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.Bogachev.RestTodo.entity.UserEntity;
import ru.Bogachev.RestTodo.exception.UserAlreadyExistException;
import ru.Bogachev.RestTodo.exception.UserNotFoundException;
import ru.Bogachev.RestTodo.model.UserModel;
import ru.Bogachev.RestTodo.repos.UserRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public void registration (UserEntity user) throws UserAlreadyExistException {
        if(userRepo.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistException("Пользователь с таким именем существует !");
        }
        userRepo.save(user);
    }
    public UserModel getOne (Long id) throws UserNotFoundException {
        return UserModel.toModel(getUserById(id));
    }
    public List<UserModel> getAll () {
        return userRepo.findAll().stream()
                       .map(UserModel::toModel)
                .collect(Collectors.toList());
    }
    public Long deleteUser (Long id) throws UserNotFoundException {
        userRepo.deleteById(getUserById(id).getId());
        return id;
    }
    private UserEntity getUserById(Long id) throws UserNotFoundException {
        return userRepo.findById(id)
                       .orElseThrow(() -> new UserNotFoundException("Пользователь не найден !"));
    }

}
