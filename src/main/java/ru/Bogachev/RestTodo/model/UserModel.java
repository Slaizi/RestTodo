package ru.Bogachev.RestTodo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.Bogachev.RestTodo.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    private Long id;
    private String username;
    private List<TodoModel> listTodo;

    public static UserModel toModel(UserEntity userEntity) {
        UserModel userModel = new UserModel();
        userModel.setId(userEntity.getId());
        userModel.setUsername(userEntity.getUsername());
        userModel.setListTodo(userEntity.getTodos().stream().map(TodoModel::toModel).collect(Collectors.toList()));
        return userModel;
    }
}
