package ru.Bogachev.RestTodo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.Bogachev.RestTodo.entity.TodoEntity;
import ru.Bogachev.RestTodo.entity.UserEntity;
import ru.Bogachev.RestTodo.exception.TodoAlreadyExistException;
import ru.Bogachev.RestTodo.exception.TodoNotFoundException;
import ru.Bogachev.RestTodo.exception.UserNotFoundException;
import ru.Bogachev.RestTodo.model.TodoModel;
import ru.Bogachev.RestTodo.repos.TodoRepo;
import ru.Bogachev.RestTodo.repos.UserRepo;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepo todoRepo;
    private final UserRepo userRepo;

    public TodoModel creteTodo (TodoEntity todo, Long userId) throws UserNotFoundException, TodoAlreadyExistException {
        UserEntity user = userRepo.findById(userId)
                                  .orElseThrow(() -> new UserNotFoundException("Пользователь не найден !"));
        if (user.getTodos().stream()
                .anyMatch(t -> t.getTitle().equals(todo.getTitle()) && t.getCompleted().equals(todo.getCompleted()))) {
            throw new TodoAlreadyExistException("Такая задача уже существует!");
        }
        todo.setUser(user);
        return TodoModel.toModel(todoRepo.save(todo));
    }
    public TodoModel completeTodo (Long id) throws TodoNotFoundException {
        TodoEntity todo = todoRepo.findById(id)
                                        .orElseThrow(() -> new TodoNotFoundException("Запись отсутствует !"));
        todo.setCompleted(!todo.getCompleted());
        return TodoModel.toModel(todoRepo.save(todo));
    }
    public String deleteTodo (Long todoId) throws TodoNotFoundException {
        TodoEntity todo = todoRepo.findById(todoId)
                .orElseThrow(() -> new TodoNotFoundException("Запись отсутствует !"));
        todoRepo.delete(todo);
        return "Тодо с id: " + todoId + " успешно удалено!";
    }
}
